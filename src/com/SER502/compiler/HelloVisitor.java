// Generated from D:/Antlr/test/src/com/SER502/compiler\Hello.g4 by ANTLR 4.5.1
package com.SER502.compiler;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code ProgramLabel}
	 * labeled alternative in {@link HelloParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramLabel(HelloParser.ProgramLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockLabel}
	 * labeled alternative in {@link HelloParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockLabel(HelloParser.BlockLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HelloParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintLabel}
	 * labeled alternative in {@link HelloParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintLabel(HelloParser.PrintLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(HelloParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StackDefLabel}
	 * labeled alternative in {@link HelloParser#stack}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStackDefLabel(HelloParser.StackDefLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#stackOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStackOper(HelloParser.StackOperContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PushLabel}
	 * labeled alternative in {@link HelloParser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPushLabel(HelloParser.PushLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PopLabel}
	 * labeled alternative in {@link HelloParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopLabel(HelloParser.PopLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctCallLabel}
	 * labeled alternative in {@link HelloParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctCallLabel(HelloParser.FunctCallLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallParamLabel}
	 * labeled alternative in {@link HelloParser#callParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallParamLabel(HelloParser.CallParamLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PassParameters}
	 * labeled alternative in {@link HelloParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassParameters(HelloParser.PassParametersContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParameterLabel}
	 * labeled alternative in {@link HelloParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterLabel(HelloParser.ParameterLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctDefLabel}
	 * labeled alternative in {@link HelloParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctDefLabel(HelloParser.FunctDefLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(HelloParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclarationLabel}
	 * labeled alternative in {@link HelloParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationLabel(HelloParser.DeclarationLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HelloParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IFStatementLabel1}
	 * labeled alternative in {@link HelloParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIFStatementLabel1(HelloParser.IFStatementLabel1Context ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#ifblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfblock(HelloParser.IfblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#elseblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseblock(HelloParser.ElseblockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LoopStatLabel}
	 * labeled alternative in {@link HelloParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatLabel(HelloParser.LoopStatLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LoopBlock}
	 * labeled alternative in {@link HelloParser#whileBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopBlock(HelloParser.LoopBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnLabel}
	 * labeled alternative in {@link HelloParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnLabel(HelloParser.ReturnLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakLabel}
	 * labeled alternative in {@link HelloParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakLabel(HelloParser.BreakLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentLabel}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentLabel(HelloParser.AssignmentLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#assignmentBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentBlock(HelloParser.AssignmentBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLabel(HelloParser.IntegerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PopExLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopExLabel(HelloParser.PopExLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DivLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivLabel(HelloParser.DivLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndORNotLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndORNotLabel(HelloParser.AndORNotLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultLabel(HelloParser.MultLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubtLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtLabel(HelloParser.SubtLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentLabel(HelloParser.IdentLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctCallExprLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctCallExprLabel(HelloParser.FunctCallExprLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLabel(HelloParser.BooleanLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GTLTEQLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTLTEQLabel(HelloParser.GTLTEQLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddLabel(HelloParser.AddLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GTEQLTEQLabel}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTEQLTEQLabel(HelloParser.GTEQLTEQLabelContext ctx);
}