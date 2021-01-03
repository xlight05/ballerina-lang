package org.ballerinalang.langserver.completions;

import io.ballerina.toml.syntax.tree.Node;
import io.ballerina.toml.syntax.tree.NonTerminalNode;
import io.ballerina.toml.syntax.tree.Token;
import org.ballerinalang.langserver.commons.CompletionContext;

import java.util.List;

/**
 * Represents the Completion operation context for toml.
 *
 * @since 2.0.0
 */
public interface TomlCompletionContext extends CompletionContext {
    /**
     * Set the token at the completion's cursor position.
     *
     * @param token {@link Token} at the cursor
     */
    void setTokenAtCursor(Token token);

    /**
     * Get the token at the cursor.
     *
     * @return {@link Token}
     */
    Token getTokenAtCursor();

    /**
     * Set the node at cursor.
     *
     * @param node {@link NonTerminalNode} at the cursor position
     */
    void setNodeAtCursor(NonTerminalNode node);

    /**
     * Get the node at the completion request triggered cursor position.
     *
     * @return {@link NonTerminalNode} at the cursor position
     */
    NonTerminalNode getNodeAtCursor();

    /**
     * Add a resolver to the resolver chain.
     *
     * @param node {@link Node} to be added to the chain
     */
    void addResolver(Node node);

    /**
     * Get the resolver chain which is the list of node evaluated against the completion item resolving.
     *
     * @return {@link List} of nodes
     */
    List<Node> getResolverChain();
}
