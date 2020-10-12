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
package io.ballerina.toml.internal.parser.tree;

import io.ballerina.toml.syntax.tree.KeyValueNode;
import io.ballerina.toml.syntax.tree.Node;
import io.ballerina.toml.syntax.tree.NonTerminalNode;
import io.ballerina.toml.syntax.tree.SyntaxKind;

import java.util.Collection;
import java.util.Collections;

/**
 * This is a generated internal syntax tree node.
 *
 * @since 2.0.0
 */
public class STKeyValueNode extends STDocumentMemberDeclarationNode {
    public final STNode identifier;
    public final STNode assign;
    public final STNode value;
    public final STNode newLines;

    STKeyValueNode(
            STNode identifier,
            STNode assign,
            STNode value,
            STNode newLines) {
        this(
                identifier,
                assign,
                value,
                newLines,
                Collections.emptyList());
    }

    STKeyValueNode(
            STNode identifier,
            STNode assign,
            STNode value,
            STNode newLines,
            Collection<STNodeDiagnostic> diagnostics) {
        super(SyntaxKind.KEY_VALUE, diagnostics);
        this.identifier = identifier;
        this.assign = assign;
        this.value = value;
        this.newLines = newLines;

        addChildren(
                identifier,
                assign,
                value,
                newLines);
    }

    public STNode modifyWith(Collection<STNodeDiagnostic> diagnostics) {
        return new STKeyValueNode(
                this.identifier,
                this.assign,
                this.value,
                this.newLines,
                diagnostics);
    }

    public STKeyValueNode modify(
            STNode identifier,
            STNode assign,
            STNode value,
            STNode newLines) {
        if (checkForReferenceEquality(
                identifier,
                assign,
                value,
                newLines)) {
            return this;
        }

        return new STKeyValueNode(
                identifier,
                assign,
                value,
                newLines,
                diagnostics);
    }

    public Node createFacade(int position, NonTerminalNode parent) {
        return new KeyValueNode(this, position, parent);
    }

    @Override
    public void accept(STNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T apply(STNodeTransformer<T> transformer) {
        return transformer.transform(this);
    }
}
