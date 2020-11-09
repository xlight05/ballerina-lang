package org.ballerinalang.langserver.cloud.completion;

public class KeyValuePair implements TomlNode {

    private String key;
    private String defaultValue;
    private int id;

    public KeyValuePair(String key, String defaultValue, int id) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.id = id;
    }

    @Override
    public String prettyPrint() {
        return key + "=" + "${" + id + ":" + defaultValue + "}";
    }
}
