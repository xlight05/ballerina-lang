/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.bindgen.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.ballerinalang.bindgen.command.BindingsGenerator.allClasses;
import static org.ballerinalang.bindgen.command.BindingsGenerator.isDirectJavaClass;
import static org.ballerinalang.bindgen.utils.BindgenConstants.ACCESS_FIELD;
import static org.ballerinalang.bindgen.utils.BindgenConstants.MUTATE_FIELD;
import static org.ballerinalang.bindgen.utils.BindgenUtils.handleOverloadedMethods;
import static org.ballerinalang.bindgen.utils.BindgenUtils.isAbstractClass;
import static org.ballerinalang.bindgen.utils.BindgenUtils.isFinalField;
import static org.ballerinalang.bindgen.utils.BindgenUtils.isPublicField;
import static org.ballerinalang.bindgen.utils.BindgenUtils.isPublicMethod;

/**
 * Class for storing details pertaining to a specific Java class used for Ballerina bridge code generation.
 */
public class JClass {

    private String prefix;
    private String className;
    private String packageName;
    private String shortClassName;

    private boolean isInterface = false;
    private boolean directClass = false;
    private boolean isAbstractClass = false;

    private Set<String> superClasses = new HashSet<>();
    private List<JField> fieldList = new ArrayList<>();
    private List<JMethod> methodList = new ArrayList<>();
    private List<JConstructor> constructorList = new ArrayList<>();
    private List<JConstructor> initFunctionList = new ArrayList<>();

    public JClass(Class c) {

        this.className = c.getName();
        this.prefix = this.className.replace(".", "_").replace("$", "_");
        this.shortClassName = c.getSimpleName();
        this.packageName = c.getPackage().getName();

        Class sClass = c.getSuperclass();
        allClasses.add(shortClassName);
        populateImplementedInterfaces(c.getInterfaces());
        while (sClass != null) {
            populateImplementedInterfaces(sClass.getInterfaces());
            String simpleClassName = sClass.getSimpleName().replace("$", "");
            superClasses.add(simpleClassName);
            allClasses.add(simpleClassName);
            sClass = sClass.getSuperclass();
        }
        if (c.isInterface()) {
            this.isInterface = true;
        } else if (isAbstractClass(c)) {
            this.isAbstractClass = true;
        }

        if (isDirectJavaClass()) {
            this.directClass = true;
            populateConstructors(c.getConstructors());
            populateInitFunctions();
            populateMethods(getMethods(c));
            methodList.sort(Comparator.comparing(JMethod::getParamTypes));
            handleOverloadedMethods(methodList);
            methodList.sort(Comparator.comparing(JMethod::getJavaMethodName));
            populateFields(c.getFields());
        }
    }

    private List<Method> getMethods(Class classObject) {

        Method[] declaredMethods = classObject.getMethods();
        List<Method> classMethods = new ArrayList<>();
        for (Method m : declaredMethods) {
            if (!m.isSynthetic() && (!m.getName().equals("toString"))) {
                classMethods.add(m);
            }
        }
        return classMethods;
    }

    private void populateConstructors(Constructor[] constructors) {

        int i = 1;
        for (Constructor constructor : constructors) {
            JConstructor jConstructor = new JConstructor(constructor);
            jConstructor.setConstructorName("new" + this.shortClassName + i);
            this.constructorList.add(jConstructor);
            i++;
        }
    }

    private void populateInitFunctions() {

        int j = 1;
        for (JConstructor constructor : this.constructorList) {
            JConstructor newCons = null;
            try {
                newCons = (JConstructor) constructor.clone();
            } catch (CloneNotSupportedException ignore) {

            }
            if (newCons != null) {
                newCons.setExternalFunctionName(constructor.constructorName);
                newCons.setConstructorName("" + j);
                this.initFunctionList.add(newCons);
            }
            j++;
        }
    }

    private void populateMethods(List<Method> declaredMethods) {

        for (Method method : declaredMethods) {
            if (isPublicMethod(method)) {
                JMethod jMethod = new JMethod(method);
                this.methodList.add(jMethod);
            }
        }
    }

    private void populateFields(Field[] fields) {

        for (Field field : fields) {
            this.fieldList.add(new JField(field, ACCESS_FIELD));
            if (!isFinalField(field) && isPublicField(field)) {
                this.fieldList.add(new JField(field, MUTATE_FIELD));
            }
        }
    }

    private void populateImplementedInterfaces(Class[] interfaces) {

        for (Class interfaceClass : interfaces) {
            allClasses.add(interfaceClass.getSimpleName());
            superClasses.add(interfaceClass.getSimpleName());
            if (interfaceClass.getInterfaces() != null) {
                populateImplementedInterfaces(interfaceClass.getInterfaces());
            }
        }
    }

    public String getShortClassName() {

        return shortClassName;
    }

    public String getPackageName() {

        return packageName;
    }
}
