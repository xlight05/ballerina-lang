package org.ballerinalang.langserver.completions.toml;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemKind;
import org.eclipse.lsp4j.InsertTextFormat;

public class TomlSnippetBuilder {
    public static CompletionItem getContainerImageSnippet() {
        Table rootNode = new Table("container.image");
        rootNode.addKeyValuePair("name", "Project Name", ValueType.STRING);
        rootNode.addKeyValuePair("repository", "local", ValueType.STRING);
        rootNode.addKeyValuePair("tag", "latest", ValueType.STRING);
        rootNode.addKeyValuePair("tag", "openjdk:slim", ValueType.STRING);
        String s = rootNode.prettyPrint();
        CompletionItem item = new CompletionItem();
        item.setLabel("All");
        item.setKind(CompletionItemKind.Snippet);
        item.setDetail("Snippetzz");
        //item.setSortText("1");
        item.setInsertText(s);
        item.setInsertTextFormat(InsertTextFormat.Snippet);

        return item;
    }
}
