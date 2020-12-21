/*
 * Copyright (c) 2018, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ballerinalang.langserver.completions.util;

import io.ballerina.toml.syntax.tree.DocumentNode;
import io.ballerina.toml.syntax.tree.KeyValueNode;
import io.ballerina.toml.syntax.tree.Node;
import io.ballerina.toml.syntax.tree.NonTerminalNode;
import io.ballerina.toml.syntax.tree.SeparatedNodeList;
import io.ballerina.toml.syntax.tree.SyntaxKind;
import io.ballerina.toml.syntax.tree.SyntaxTree;
import io.ballerina.toml.syntax.tree.TableNode;
import io.ballerina.toml.syntax.tree.ValueNode;
import io.ballerina.tools.text.LinePosition;
import io.ballerina.tools.text.TextDocument;
import io.ballerina.tools.text.TextDocuments;
import io.ballerina.tools.text.TextRange;
import org.ballerinalang.langserver.commons.completion.LSCompletionException;
import org.ballerinalang.langserver.completions.TomlCompletionContext;
import org.ballerinalang.langserver.completions.toml.TomlSnippetBuilder;
import org.ballerinalang.langserver.util.references.TokenOrSymbolNotFoundException;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Common utility methods for the completion operation.
 */
public class TomlCompletionUtil {

    /**
     * Get the completion Items for the context.
     *
     * @param ctx Completion context
     * @return {@link List}         List of resolved completion Items
     */
    public static List<CompletionItem> getCompletionItems(TomlCompletionContext ctx) throws LSCompletionException,
            TokenOrSymbolNotFoundException {
        fillTokenInfoAtCursor(ctx);
        NonTerminalNode nodeAtCursor = ctx.getNodeAtCursor();
        List<CompletionItem> items = route(ctx, nodeAtCursor);

        return items;
    }

    /**
     * Get the nearest matching provider for the context node.
     * Router can be called recursively. Therefore, if there is an already checked resolver in the resolver chain,
     * that means the particular resolver could not handle the completions request. Therefore skip the particular node
     * and traverse the parent ladder to find the nearest matching resolver. In order to handle this, the particular
     * resolver chain check has been added.
     *
     * @param node node to evaluate
     * @return {@link Optional} provider which resolved
     */
    public static List<CompletionItem> route(TomlCompletionContext ctx, Node node) {
        List<CompletionItem> completionItems = new ArrayList<>();
        HashMap<String, CompletionItem> map = new HashMap<>();
        if (node == null) {
            return completionItems;
        }

        Node reference = node;
        while ((reference != null)) {
            if (reference.kind() == SyntaxKind.TABLE) {
                TableNode tableNode = (TableNode) reference;
                switch (toDottedString(tableNode.identifier())) {
                    //TODO dont suggest the things that are already there
                    case "container.image":
                        map.put("name", TomlSnippetBuilder.getContainerImageName());
                        map.put("repository", TomlSnippetBuilder.getContainerImageRepository());
                        map.put("tag", TomlSnippetBuilder.getContainerTag());
                        map.put("base", TomlSnippetBuilder.getContainerImageBase());
                        map.put("container.image.user", TomlSnippetBuilder.getContainerImageUserSnippet());
                        break;
                    case "cloud.deployment.probes.readiness":
                        map.put("port", TomlSnippetBuilder.getProbePortSnippet());
                        map.put("path", TomlSnippetBuilder.getProbePathSnippet());
                        break;
                    default:
                        break;
                }
                for (KeyValueNode field : tableNode.fields()) {
                    String key = toDottedString(field.identifier());
                    map.remove(key);
                }
                break;
            } else if (reference.kind() == SyntaxKind.TABLE_ARRAY) {
                break;
            } else {
                reference = reference.parent();
            }
        }

        return new ArrayList<>(map.values());
    }

    private static String toDottedString(SeparatedNodeList<ValueNode> nodeList) {
        String output = "";
        for (ValueNode valueNode : nodeList) {
            String valueString = valueNode.toString();
            output = output + "." + valueString;
        }
        return output.substring(1);
    }

    /**
     * Find the token at cursor.
     */
    public static void fillTokenInfoAtCursor(TomlCompletionContext context) throws TokenOrSymbolNotFoundException {
        try {
            Path tomlFilePath = context.filePath();
            if (tomlFilePath != null) {
                TextDocument textDocument = TextDocuments.from(Files.readString(tomlFilePath));
                Position position = context.getCursorPosition();
                int txtPos =
                        textDocument.textPositionFrom(LinePosition.from(position.getLine(), position.getCharacter()));
                // TODO: Try to delegate the set option to the context
                context.setCursorPositionInTree(txtPos);
                TextRange range = TextRange.from(txtPos, 0);
                Path filePath = tomlFilePath.getFileName();
                if (filePath != null) {
                    String path = filePath.toString();
                    SyntaxTree st = SyntaxTree.from(textDocument, path);
                    NonTerminalNode nonTerminalNode = ((DocumentNode) st.rootNode()).findNode(range);
                    while (true) {
                        /*
                        ModulePartNode's parent is null
                         */
                        if (nonTerminalNode.parent() != null && !withinTextRange(txtPos, nonTerminalNode)) {
                            nonTerminalNode = nonTerminalNode.parent();
                            continue;
                        }
                        break;
                    }

                    context.setNodeAtCursor(nonTerminalNode);
                }
            }
        } catch (IOException e) {
            throw new TokenOrSymbolNotFoundException("Couldn't find a valid document!");
        }
    }

    private static boolean withinTextRange(int position, NonTerminalNode node) {
        TextRange rangeWithMinutiae = node.textRangeWithMinutiae();
        TextRange textRange = node.textRange();
        TextRange leadingMinutiaeRange = TextRange.from(rangeWithMinutiae.startOffset(),
                textRange.startOffset() - rangeWithMinutiae.startOffset());
        return leadingMinutiaeRange.endOffset() <= position;
    }
}
