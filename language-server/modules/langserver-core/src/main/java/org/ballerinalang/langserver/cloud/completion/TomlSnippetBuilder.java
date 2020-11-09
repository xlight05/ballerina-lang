package org.ballerinalang.langserver.cloud.completion;

public class TomlSnippetBuilder {
    public static void main (String[] args) {
        TomlSnippetBuilder builder = new TomlSnippetBuilder();
        System.out.println(builder.build());
    }

    public String build() {
        Table rootNode = new Table("container.image");
        rootNode.addKeyValuePair("name", "\"Project Name\"");
        rootNode.addKeyValuePair("repository", "\"local\"");
        rootNode.addKeyValuePair("tag", "\"latest\"");
        rootNode.addKeyValuePair("tag", "\"openjdk:slim\"");
        return rootNode.prettyPrint();
    }
}
