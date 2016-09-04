package com.SER502.compiler;


import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.SER502.compiler.HelloParser.ProgramContext;

public class Main {
    public static void main(String[] args) throws Exception{
        ANTLRInputStream input=new ANTLRFileStream("test.demo");
        HelloLexer lexer=new HelloLexer(input);
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        HelloParser hp=new HelloParser(tokens);
        hp.setErrorHandler(new BailErrorStrategy());
        try{
        ParseTree pt=hp.program();
        new Visitor2().visit(pt);
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}
