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
package org.ballerinalang.langserver.completions;

import org.ballerinalang.annotation.JavaSPIService;
import org.ballerinalang.langserver.commons.CompletionContext;
import org.ballerinalang.langserver.commons.CompletionExtension;
import org.ballerinalang.langserver.completions.toml.TomlSnippetBuilder;
import org.ballerinalang.langserver.completions.util.CompletionUtil;
import org.ballerinalang.langserver.completions.util.TomlCompletionUtil;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Completion extension implementation for ballerina.
 *
 * @since 2.0.0
 */
@JavaSPIService("org.ballerinalang.langserver.commons.LanguageExtension")
public class TomlCompletionExtension implements CompletionExtension {
    @Override
    public boolean validate(CompletionParams inputParams) {
        return inputParams.getTextDocument().getUri().endsWith("Kubernetes.toml");
    }

    @Override
    public List<CompletionItem> execute(CompletionParams inputParams, CompletionContext context)
            throws Throwable {
        List<CompletionItem> completionItems = TomlCompletionUtil.getCompletionItems(context);
//        List<CompletionItem> list = new ArrayList<>();
//        list.add(TomlSnippetBuilder.getContainerImageSnippet());
        return completionItems;
    }
}
