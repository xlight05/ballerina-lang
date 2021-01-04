package org.ballerinalang.langserver.codeaction;

import org.ballerinalang.langserver.commons.CodeActionContext;
import org.eclipse.lsp4j.CodeAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Code Action Router for C2C.
 *
 * @since 2.0.0
 */
public class TomlCodeActionRouter {

    /**
     * Returns a list of supported code actions.
     *
     * @param ctx {@link CodeActionContext}
     * @return list of code actions
     */
    public static List<CodeAction> getAvailableCodeActions(CodeActionContext ctx) {
        List<CodeAction> codeActions = new ArrayList<>();
        CodeAction action = new CodeAction();
        action.setTitle("Test");

        codeActions.add(action);
//        CodeActionProvidersHolder codeActionProvidersHolder = CodeActionProvidersHolder.getInstance();
//
//        // Get available node-type based code-actions
//        SyntaxTree syntaxTree = ctx.workspace().syntaxTree(ctx.filePath()).orElseThrow();
//        Optional<Node> matchedNode = CodeActionUtil.getTopLevelNode(ctx.cursorPosition(), syntaxTree);
//        CodeActionNodeType matchedNodeType = CodeActionUtil.codeActionNodeType(matchedNode.orElse(null));
//        SemanticModel semanticModel = ctx.workspace().semanticModel(ctx.filePath()).orElseThrow();
//        String relPath = ctx.workspace().relativePath(ctx.filePath()).orElseThrow();
//        if (matchedNode.isPresent() && matchedNodeType != CodeActionNodeType.NONE) {
//            Range range = CommonUtil.toRange(matchedNode.get().lineRange());
//            Node expressionNode = CodeActionUtil.largestExpressionNode(matchedNode.get(), range);
//            TypeSymbol matchedTypeSymbol = semanticModel.type(relPath, expressionNode.lineRange()).orElse(null);
//
//            PositionDetails posDetails = CodeActionPositionDetails.from(matchedNode.get(), null, matchedTypeSymbol);
//            ctx.setPositionDetails(posDetails);
//            codeActionProvidersHolder.getActiveNodeBasedProviders(matchedNodeType).forEach(provider -> {
//                try {
//                    List<CodeAction> codeActionsOut = provider.getNodeBasedCodeActions(ctx);
//                    if (codeActionsOut != null) {
//                        codeActions.addAll(codeActionsOut);
//                    }
//                } catch (Exception e) {
//                    String msg = "CodeAction '" + provider.getClass().getSimpleName() + "' failed!";
//                    LSClientLogger.logError(msg, e, null, (Position) null);
//                }
//            });
//        }
//
//        // Get available diagnostics based code-actions
//        List<Diagnostic> cursorDiagnostics = ctx.cursorDiagnostics();
//        if (cursorDiagnostics != null && !cursorDiagnostics.isEmpty()) {
//            for (Diagnostic diagnostic : cursorDiagnostics) {
//                PositionDetails positionDetails = computePositionDetails(diagnostic.getRange(), syntaxTree, ctx);
//                ctx.setPositionDetails(positionDetails);
//                codeActionProvidersHolder.getActiveDiagnosticsBasedProviders().forEach(provider -> {
//                    try {
//                        List<CodeAction> codeActionsOut = provider.getDiagBasedCodeActions(diagnostic, ctx);
//                        if (codeActionsOut != null) {
//                            codeActions.addAll(codeActionsOut);
//                        }
//                    } catch (Exception e) {
//                        String msg = "CodeAction '" + provider.getClass().getSimpleName() + "' failed!";
//                        LSClientLogger.logError(msg, e, null, (Position) null);
//                    }
//                });
//            }
//        }
        return codeActions;
    }
}
