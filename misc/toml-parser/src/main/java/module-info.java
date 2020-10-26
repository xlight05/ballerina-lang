module io.ballerina.toml {
    requires io.ballerina.tools.api;
    requires org.apache.commons.text;
    exports io.ballerina.toml.syntax.tree;
    exports io.ballerina.toml.semantic;
    exports io.ballerina.toml.semantic.ast;
    exports io.ballerina.toml.semantic.diagnostics;
}
