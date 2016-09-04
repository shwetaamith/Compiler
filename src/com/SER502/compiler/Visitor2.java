package com.SER502.compiler;

import java.io.*;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

import com.SER502.compiler.HelloParser.AddLabelContext;
import com.SER502.compiler.HelloParser.SubtLabelContext;
import com.SER502.compiler.HelloParser.AndORNotLabelContext;
import com.SER502.compiler.HelloParser.AssignmentBlockContext;
import com.SER502.compiler.HelloParser.AssignmentLabelContext;
import com.SER502.compiler.HelloParser.BlockLabelContext;
import com.SER502.compiler.HelloParser.CallParamLabelContext;
import com.SER502.compiler.HelloParser.DeclarationLabelContext;
import com.SER502.compiler.HelloParser.DivLabelContext;
import com.SER502.compiler.HelloParser.ElseblockContext;
import com.SER502.compiler.HelloParser.FunctCallLabelContext;
import com.SER502.compiler.HelloParser.FunctDefLabelContext;
import com.SER502.compiler.HelloParser.MultLabelContext;
import com.SER502.compiler.HelloParser.ParameterLabelContext;
import com.SER502.compiler.HelloParser.PassParametersContext;
import com.SER502.compiler.HelloParser.PopLabelContext;
import com.SER502.compiler.HelloParser.ProgramLabelContext;
import com.SER502.compiler.HelloParser.PushLabelContext;
import com.SER502.compiler.HelloParser.ReturnLabelContext;
import com.SER502.compiler.HelloParser.StackDefLabelContext;
import com.SER502.compiler.HelloParser.StatementContext;
import com.SER502.compiler.HelloParser.GTLTEQLabelContext;
import com.SER502.compiler.HelloParser.IFStatementLabel1Context;
import com.SER502.compiler.HelloParser.IdentLabelContext;
import com.SER502.compiler.HelloParser.IfblockContext;
import com.SER502.compiler.HelloParser.IntegerLabelContext;
import com.SER502.compiler.HelloParser.LoopBlockContext;
import com.SER502.compiler.HelloParser.LoopStatLabelContext;

public class Visitor2 extends HelloBaseVisitor<String>{

    File fileOut=new File("TestOut.txt");
    FileWriter fw=new FileWriter(fileOut.getAbsoluteFile());
    BufferedWriter bufWriter= new BufferedWriter(fw);
    String output="";

    Visitor2() throws Exception {
        super();
    }
    int count=0;
    HashMap<String,Integer> variableTab=new HashMap<String,Integer>();
    /*This following funcMap is a symbol table for Functions.Key is the function name and value is another LinkedList for Parameters. 
     The inner LinkedList has name of the parameter as the Key and Integer(which models a memory position in actual language)
     as value.
     */
    HashMap<String,LinkedList<String>> funcMap=new HashMap<String,LinkedList<String>>();
    HashMap<String,Integer> stackMap=new HashMap<String,Integer>();
    String parameterList ="",currFunc="";
    @Override
    public String visitProgramLabel(ProgramLabelContext ctx) {
    	System.out.println("begin ");
        visitChildren(ctx);
        aggregateResult();
        return null;
    }

    private void aggregateResult() {
        try {
            bufWriter.write(output);
            bufWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String visitParameterLabel(ParameterLabelContext ctx) {
        System.out.println("funcparam "+ctx.name.getText());
        output=output+"funcparam "+ctx.name.getText()+"\n";
        parameterList=parameterList+ctx.name.getText()+" ";
        return null;
    }

    @Override
    public String visitIntegerLabel(IntegerLabelContext ctx) {
        System.out.println("load "+ctx.getText());
        output=output+"load "+ctx.getText()+"\n";
        count++;
        return String.valueOf(ctx.INTEGER().getText());
    }

    @Override
    public String visitAssignmentLabel(AssignmentLabelContext ctx) {
        String name=ctx.IDENT().getText();
        boolean match=false;
        String value=null;
        if(currFunc!=""){
        	LinkedList<String> funcParamList = funcMap.get(currFunc);
        	for(String s:funcParamList){
        		if(s.equals(name)){
        			 value=visit(ctx.assgBlock);
        	         System.out.println("storefuncVar "+name);
        	         output=output+"store "+name+"\n";
        	         count++;
        	         match=true;
        	         break;
        		}
        	}
        	if(!match){
        		if(variableTab.get(name)!=null){
        			value=visit(ctx.assgBlock);
        	        System.out.println("store "+name);
        	        output=output+"store "+name+"\n";
        	        count++;
                }else{
                	try {
                        throw new Exception("Undefined Variable ");
                    } catch (Exception e) {
                        System.err.println("Variable  this"+name+" is not defined");
                        System.exit(100);
                    }
                }
        	}
        }else{
        if(variableTab.get(name)!=null){
            value=visit(ctx.assgBlock);
            System.out.println("store "+name);
            output=output+"store "+name+"\n";
            count++;
        }else{
            try {
                throw new Exception("Undefined Variable ");
            } catch (Exception e) {
                System.err.println("Variable "+name+" is not defined");
                System.exit(100);
            }
        }
        }
        return null;
    }

    @Override
    public String visitAndORNotLabel(AndORNotLabelContext ctx) {
        visitChildren(ctx);
        switch(ctx.op.getType()){
            case HelloParser.AND:
                System.out.println("AND");
                output+="AND"+"\n";
                count++;
                break;
            case HelloParser.OR:
                System.out.println("OR");
                output+="OR"+"\n";
                count++;
                break;
            case HelloParser.NOT:
                System.out.println("NOT");
                output+="NOT"+"\n";
                count++;
        }
        return null;

    }

    @Override
	public String visitAssignmentBlock(AssignmentBlockContext ctx) {
		visitChildren(ctx);
    	return null;
	}

	@Override
    public String visitAddLabel(AddLabelContext ctx) {
        visitChildren(ctx);
        System.out.println("ADD");
        output+="ADD"+"\n";
        count++;
        return null;
    }

    @Override
    public String visitDivLabel(DivLabelContext ctx) {
        visitChildren(ctx);
        System.out.println("Divide");
        output+="Divide"+"\n";
        count++;
        return null;

    }

    @Override
    public String visitSubtLabel(SubtLabelContext ctx) {
        visitChildren(ctx);
        System.out.println("SUB");
        output+="SUB"+"\n";
        count++;
        return null;
    }

    @Override
    public String visitMultLabel(MultLabelContext ctx) {
        visitChildren(ctx);
        System.out.println("Multiply");
        output+="Multiply"+"\n";
        count++;
        return null;

    }

    @Override
    public String visitIdentLabel(IdentLabelContext ctx) {
    	boolean match=false;
        String name=ctx.IDENT().getText();
        if(currFunc!=""){
        	LinkedList<String> funcParamList = funcMap.get(currFunc);
        	for(String s:funcParamList){
        		if(s.equals(name)){
                    System.out.println("load "+ctx.getText());
                    output+="load "+ctx.getText()+"\n";
                    count++;
                    match=true;
                    break;
        		}
        	}
        	if(!match){
        		if(variableTab.get(name)!=null){
        			 System.out.println("load "+ctx.getText());
                     output+="load "+ctx.getText()+"\n";
                     count++;
                     match=true;
                }else{
                	try {
                        throw new Exception("Undefined Variable ");
                    } catch (Exception e) {
                        System.err.println("Variable "+name+" is not defined");
                        System.exit(100);
                    }
                }
        	}
        }
        else{
        if(variableTab.get(name)!=null){
            System.out.println("load "+ctx.getText());
            output+="load "+ctx.getText()+"\n";
            count++;
        }else{
            try {
                throw new Exception("Undefined Variable ");
            } catch (Exception e) {
                System.err.println("Variable "+name+" is not defined");
                System.exit(100);
            }
        }
        }
        return ctx.getText();
    }

    @Override
	public String visitStackDefLabel(StackDefLabelContext ctx) {
    	System.out.println("DefineStack "+ctx.stackname.getText());
    	output=output+"DefineStack "+ctx.stackname.getText()+"\n";
    	stackMap.put(ctx.stackname.getText(),stackMap.size());
    	return null;
	}

	@Override
    public String visitLoopStatLabel(LoopStatLabelContext ctx) {
        int startCount=count;
        visit(ctx.expr);
        System.out.println("loopstart ");
        output=output+"loopstart "+"\n";
        visit(ctx.loopBlock);
        System.out.println("gotostart "+startCount++);
        output=output+"gotostart "+(startCount++)+ "\n";
        return null;
    }

    @Override
	public String visitLoopBlock(LoopBlockContext ctx) {
    	visitChildren(ctx);
    	return null;
    }

	@Override
    public String visitFunctCallLabel(FunctCallLabelContext ctx) {
        if(funcMap.containsKey(ctx.funName.getText())){
        currFunc=ctx.funName.getText();
    	String cnt=visit(ctx.callParam);
    	if(Integer.parseInt(cnt)==funcMap.get(ctx.funName.getText()).size()){
        System.out.println("invoke "+ctx.funName.getText()+" "+cnt);
        output=output+"invoke "+ctx.funName.getText()+" "+cnt+"\n";
        currFunc="";
    	}
    	else{
    	System.err.println("Undefined Function "+ctx.funName.getText()+" with "+cnt+" parameters ");
        System.exit(100);
    	}
    	}else{
    	System.err.println("Undefined Function "+ctx.funName.getText());
    	System.exit(100);
    	}
        return null;
    }

    @Override
    public String visitCallParamLabel(CallParamLabelContext ctx) {
        int cnt=0;
        if(ctx.first!=null){
            visitChildren(ctx);
            cnt=ctx.getChildCount();
            cnt=cnt-(cnt/2);
        }
        return cnt+"";
    }

    @Override
    public String visitPassParameters(PassParametersContext ctx) {
        int cnt=0;
        if(ctx.first!=null){
            cnt=ctx.getChildCount();
            cnt=cnt-(cnt/2);
            visitChildren(ctx);
            String arr[]=parameterList.split(" ");
            for(String param:arr){
            funcMap.get(currFunc).add(param);
            }
        }
        
        return cnt+"";
    }

    @Override
    public String visitFunctDefLabel(FunctDefLabelContext ctx) {
    	if(!funcMap.containsKey(ctx.name.getText())){
    	funcMap.put(ctx.name.getText(), new LinkedList<String>());
    	currFunc=ctx.name.getText();
    	String cnt="";
        if(ctx.parm!=null){
            cnt=visit(ctx.parm);
        }
        parameterList="";
        if(ctx.ret!=null){
            System.out.println("definefunct "+ctx.name.getText()+" "+cnt+" "+ctx.ret.getText());
            output=output+"definefunct "+ctx.name.getText()+" "+cnt+" "+ctx.ret.getText()+"\n";
        }else{
            System.out.println("definefunct "+ctx.name.getText()+" "+cnt);
            output=output+"definefunct "+ctx.name.getText()+" "+cnt+"\n";
        }
        visit(ctx.funcBlock);
  /*      if(ctx.funcstat!=null){
        	System.out.println("Right Here");
            visit(ctx.funcstat);
        }
        if(ctx.funcret!=null){
            visit(ctx.funcret);
        }*/
        System.out.println("end "+ctx.name.getText());
        output=output+"end "+ctx.name.getText()+"\n";
        currFunc="";
    	}
    	else{
    		System.out.println("Function "+ ctx.name.getText()+ "Already Defined");
    		currFunc="";
    	}
        return null;
    }

    @Override
	public String visitPushLabel(PushLabelContext ctx) {
    	if(stackMap.containsKey(ctx.stackName.getText())){
    	System.out.println("PushTo "+ctx.stackName.getText()+" " + ctx.value.getText());
		output=output+"PushTo "+ctx.stackName.getText()+" " + ctx.value.getText()+"\n";
    	}else{
		System.out.println("Stack not Defined " +ctx.stackName.getText());
		System.exit(100);
    	}
		return null;
	}

	@Override
	public String visitPopLabel(PopLabelContext ctx) {
		if(stackMap.containsKey(ctx.stackName.getText())){
		switch(ctx.operName.getText()){
		case "popStack" :
			System.out.println("PopFrom "+ctx.stackName.getText());
			output=output+"PopFrom "+ctx.stackName.getText()+"\n";
			break ;
		case "peekStack" :
			System.out.println("PeekFrom "+ctx.stackName.getText());
			output=output+"PeekFrom "+ctx.stackName.getText()+"\n";
			break ;
		}
		}else{
			System.out.println("Stack not Defined " +ctx.stackName.getText());
			System.exit(100);
		}
		return null;
	}

	@Override
    public String visitBlockLabel(BlockLabelContext ctx) {
        visitChildren(ctx);
        return null;
    }


    @Override
    public String visitReturnLabel(ReturnLabelContext ctx) {
        if(ctx.value!=null){
            visit(ctx.value);
        }
        System.out.println("return ");
        output=output+"return "+"\n";
        return null;
    }

    @Override
    public String visitGTLTEQLabel(GTLTEQLabelContext ctx) {
        visitChildren(ctx);
        switch(ctx.op.getType()){
            case HelloParser.GT:
                System.out.println("greaterThan");
                output=output+"greaterThan"+"\n";
                break;
            case HelloParser.LT:
                System.out.println("lessThan");
                output=output+"lessThan"+"\n";
                break;
            case HelloParser.EQ:
                System.out.println("EqualTo");
                output=output+"equalTo"+"\n";
                break;
        }
        return null;
    }



    @Override
    public String visitDeclarationLabel(DeclarationLabelContext ctx) {
        String var=ctx.variableName.getText();
        variableTab.put(var, variableTab.size());
        System.out.println("position "+variableTab.size());
        output=output+"position "+variableTab.size()+"\n";
        if(ctx.value!=null){
            visit(ctx.expression());
        }
        count++;
        System.out.println("define "+var);
        output=output+"define "+var+"\n";
        count++;
        return null;
    }

    @Override
    public String visitIFStatementLabel1(IFStatementLabel1Context ctx) {
    	System.out.println("IFExecution");
    	visit(ctx.express1);
        System.out.println("IF");
        output=output+"IF"+"\n";
        visit(ctx.onTrue);
        count++;
        System.out.println("IfTrue");
        output=output+"IfTrue"+"\n";
        count++;
        visit(ctx.onFalse);
        System.out.println("endIf");
        output=output+"endIf"+"\n";
        return null;
    }

    @Override
	public String visitIfblock(IfblockContext ctx) {
    	visitChildren(ctx);
    	return null;
	}

	@Override
	public String visitElseblock(ElseblockContext ctx) {
		visitChildren(ctx);
    	return null;
	}

	@Override
    public String visitPrintLabel(HelloParser.PrintLabelContext ctx){
        visit(ctx.expr);
        System.out.println("print");
        output+="print\n";
        return null;
    }
}
