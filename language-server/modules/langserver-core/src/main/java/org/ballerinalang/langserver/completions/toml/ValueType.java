package org.ballerinalang.langserver.completions.toml;

public enum ValueType {
    STRING("\"","\""),
    NUMBER("",""),
    ARRAY("[","]");

    private String startingSeparator;
    private String endingSeparator;
    private ValueType(String startingSeparator, String endingSeparator) {
        this.startingSeparator = startingSeparator;
        this.endingSeparator = endingSeparator;
    }

    public String getStartingSeparator() {
        return startingSeparator;
    }

    public String getEndingSeparator() {
        return endingSeparator;
    }
}
