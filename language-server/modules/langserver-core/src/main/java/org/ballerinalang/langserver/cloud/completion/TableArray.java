package org.ballerinalang.langserver.cloud.completion;

import java.util.List;

public class TableArray implements TomlNode {
    private String name;
    private List<TomlNode> nodes;
    private static final int SPACING = 4;

    public TableArray(String name, List<TomlNode> nodes) {
        this.name = name;
        this.nodes = nodes;
    }

    @Override
    public String prettyPrint() {
        StringBuilder prettyString = new StringBuilder();
        prettyString.append("[[").append(name).append("]]").append(System.lineSeparator());
        for (TomlNode node : nodes) {
            prettyString.append(getIndentation()).append(node.prettyPrint()).append(System.lineSeparator());
        }
        prettyString.append(System.lineSeparator());
        return prettyString.toString();
    }

    private String getIndentation() {
        return " ".repeat(SPACING);
    }
}
