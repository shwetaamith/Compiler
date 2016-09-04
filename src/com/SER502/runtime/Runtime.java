package com.SER502.runtime;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Runtime {

    private static ArrayList<String> stack;
    private static HashMap<String, String> vars;
    private static HashMap<String, ArrayList<String>> funcs;
    private static ArrayList<String> scope;
    private static HashMap<String, ArrayList<String>> stacks;
    private static boolean ifToggle;
    private static String whilecondition1;
    private static String whilecondition2;
    private static ArrayList<String> whilestat = new ArrayList<String>();
    private static String condition;
    private static String instruction;
    private static boolean whl;
    private static boolean cnd;
    private static int count=0;

    public static void main(String[] args) throws Exception{
        stack = new ArrayList<>();
        vars = new HashMap<>();
        funcs = new HashMap<>();
        scope = new ArrayList<>();
        stacks = new HashMap<>();
        scope.add("root");
        try(InputStream in = new FileInputStream(new File("TestOut.txt"))) {
            Scanner scn = new Scanner(in);
            evaluate(scn);
        }
        ifToggle = true;
    }

    public static void pushStack(String instruction){
        stack.add(instruction);
    }

    public static String popStack(){
        String value = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return value;
    }

    public static void evaluate(Scanner scn) throws Exception{
        while(scn.hasNext()) {
            String instr = scn.next();
            if(instr.equals(Instructions.LOAD)){
            	if(whl){ }
            	else{
                String value = scn.next();
                pushStack(value);
            	}
            }
            else if(instr.equals(Instructions.POS)){

            }
            else if(instr.equals(Instructions.DEFINE)){
                String value = popStack();
                String varName = scn.next();
                if(value.matches("[0-9]+")){
                    vars.put(scope.get(scope.size()-1)+"_"+varName, value);
                }else{
                    vars.put(scope.get(scope.size()-1)+"_"+varName,
                            vars.get(scope.get(scope.size()-1))+"_"+value);
                }
            }
               else if(instr.equals(Instructions.ADD)||
                    instr.equals(Instructions.SUB)||
                    instr.equals(Instructions.MUL)||
                    instr.equals(Instructions.DIV)) {
            	if(whl){ }
            	else{
            		
            		String val2 = popStack();
            		String val1 = popStack();
            		int int1, int2;
            		if (val1.matches("[0-9]+")) {
                    int1 = Integer.parseInt(val1);
            		} else {
                    int1 = Integer.parseInt(vars.get(scope.get(scope.size() - 1) + "_" + val1));
            		}
            		if (val2.matches("[0-9]+")) {
            			int2 = Integer.parseInt(val2);
            		} else {
            			int2 = Integer.parseInt(vars.get(scope.get(scope.size() - 1) + "_" + val2));
            		}
            		if (instr.equals(Instructions.ADD)) {
            			pushStack(Integer.toString((int1 + int2)));
                    /*if(whl){
                    whilestat.add(Instructions.ADD);
                    }*/
            		} else if (instr.equals(Instructions.SUB)) {
                    pushStack(Integer.toString((int1 - int2)));
                    /*if(whl){
                    	whilestat.add(Instructions.SUB);
                    }*/
            		} else if (instr.equals(Instructions.MUL)) {
                    pushStack(Integer.toString(int1 * int2));
                   /* if(whl){
                    	whilestat.add(Instructions.MUL);
                    }*/
            		} else if (instr.equals(Instructions.DIV)) {
                    pushStack(Integer.toString(int1 / int2));
                    
                }
            }
            }
            else if(instr.equals(Instructions.FPARAM)){
                ArrayList<String> fparams = new ArrayList<>();
                fparams.add(scn.next());
                while(!scn.next().equals(Instructions.DFUNC)){
                    fparams.add(scn.next());
                }
                ArrayList<String> ins = new ArrayList<>();
                String funcName = scn.next();
                ins.add(scn.next());
                ins.add(scn.next());
                ins.add("0");
                for(int i = 0; i < fparams.size(); i++){
                    ins.add(fparams.get(i));
                }
                String input;
                String instructions = "";
                while (!(input = scn.nextLine()).contains(Instructions.END + " " + funcName)) {
                    if(input.length() != 0) {
                        instructions += input + "\n";
                    }
                }
                ins.add(instructions);
                funcs.put(funcName, ins);
                for(int i = 0; i < fparams.size(); i++){
                    vars.put(funcName+"_0_"+fparams.get(i), "");
                }
            }
            else if(instr.equals(Instructions.DFUNC)) {
                ArrayList<String> ins = new ArrayList<>();
                String funcName = scn.next();
                ins.add(scn.next());
                ins.add(scn.next());
                ins.add("0");
                String input;
                String instructions = "";
                while (!(input = scn.nextLine()).contains(Instructions.END + " " + funcName)) {
                    if(input.length() != 0) {
                        instructions += input + "\n";
                    }
                }
                ins.add(instructions);
                funcs.put(funcName, ins);
            }
                    else if(instr.equals(Instructions.STORE)){
            	if(whl){ }
            	else{
                String value = popStack();
                String varName = scn.next();
                if(value.matches("[0-9]+")){
                    vars.put(scope.get(scope.size()-1)+"_"+varName, value);
                }else{
                    vars.put(scope.get(scope.size()-1)+"_"+varName,
                            vars.get(scope.get(scope.size()-1)+"_"+value));
                     }
               	} 
                        
            }
            else if(instr.equals(Instructions.IF)){
                String value = popStack();
                if(value.equals(Instructions.TRUE)){
                    ifToggle = true;
                }else{
                    ifToggle = false;
                    String val;
                    do{
                        if(scn.hasNext()) {
                            val=scn.next();
                        }else {
                            break;
                        }
                    } while(!val.equals(Instructions.IFTRUE)&&!value.equals(Instructions.ENDIF));
                }
            }
            else if(instr.equals(Instructions.IFTRUE)){
                if(ifToggle){
                    String value;
                    do{if(scn.hasNext()) value=scn.next();else break;}
                        while(!value.equals(Instructions.IFFALSE)&&!value.equals(Instructions.ENDIF));
                }
            }
              else if(instr.equals(Instructions.SLOOP)){
                // TODO: LOOPING
            	
            	String input;
            	while( !(scn.hasNext(Instructions.GOTO))){
            		input = scn.nextLine();           		
            		whilestat.add(input);
            		
            	}
            	whilestat.add("load"+" "+whilecondition1);
            	whilestat.add("load"+" "+whilecondition2);
            	whilestat.add(condition);           	          	
            	whl = true;
            	
            }
            else if(instr.equals(Instructions.GOTO)){
                // TODO: return to beginning of loop            	
                     	whl = false;
            	int i = whilestat.size();
                	String ins = whilestat.get(0) +"\n";
               		instruction = ins;
               		for(int index=1;index<i;index++){
               		 //String ins = whilestat.get(0);
               		 instruction = instruction + whilestat.get(index)+"\n";
               		}
            	while(popStack().equals(Instructions.TRUE)){
            		count++;
            		evaluate(new Scanner(instruction));
            	}           
            }
            else if(instr.equals(Instructions.INVOKE)){
                String funcName = scn.next();
                ArrayList<String> ins = funcs.get(funcName);
                int numParams = Integer.parseInt(ins.get(0));
                int numArgs = Integer.parseInt(scn.next());
                if(numArgs != numParams){
                    throw new Error("RUNTIME ERROR: Function " + funcName + " takes " + numParams +
                            " arguments, not " + numArgs);
                }else{
                    scope.add(funcName+"_"+ins.get(2));
                    for(int i = 0; i < numArgs; i++){
                        String value = popStack();
                        if(value.matches("[0-9]+")){
                            vars.put(funcName+"_"+ins.get(2)+"_"+ins.get(i+3), value);
                        }else{
                            vars.put(funcName+"_"+ins.get(2)+"_"+ins.get(i+3), vars.get(scope.get(scope.size()-2)+"_"+value));
                        }
                        int depth = Integer.parseInt(ins.get(2));
                        depth++;
                        ins.set(2, Integer.toString(depth));
                    }
                    evaluate(new Scanner(ins.get(3+numParams)));

                    scope.remove(scope.size()-1);
                }
            }
             else if(instr.equals(Instructions.PRINT)){
            	if(whl){ }
            	else{
                		String value = popStack();
                		if(value.matches("[0-9]+")){
                    			System.out.println(value);
               		 }else{
                    			System.out.println(vars.get(scope.get(scope.size()-1)+"_"+value));
               		 }
            	}
            }
            else if(instr.equals(Instructions.LT)){
                String val2 = popStack();
                String val1 = popStack();
                int int1, int2;
                if(val1.matches("[0-9]+")){
                    int1 = Integer.parseInt(val1);
                }else{
                    String val = vars.get(scope.get(scope.size()-1)+"_"+val1);
                    int1 = Integer.parseInt(vars.get(scope.get(scope.size()-1)+"_"+val1));
                }
                if(val2.matches("[0-9]+")){
                    int2 = Integer.parseInt(val2);
                }else{
                    int2 = Integer.parseInt(vars.get(scope.get(scope.size()-1)+"_"+val2));
                }
                if(int1<int2){
                    pushStack("True");
                }else{
                    pushStack("False");
                }
                condition = Instructions.LT;
            	whilecondition1 = val1;
            	whilecondition2 = val2;
            }
            else if(instr.equals(Instructions.GT)){
                String val2 = popStack();
                String val1 = popStack();
                int int1, int2;
                if(val1.matches("[0-9]+")){
                    int1 = Integer.parseInt(val1);
                }else{
                    int1 = Integer.parseInt(vars.get(scope.get(scope.size()-1)+"_"+val1));
                }
                if(val2.matches("[0-9]+")){
                    int2 = Integer.parseInt(val2);
                }else{
                    int2 = Integer.parseInt(vars.get(scope.get(scope.size()-1)+"_"+val2));
                }
                if(int1>int2){
                    pushStack("True");
                }else{
                    pushStack("False");
                }
                condition = Instructions.GT;
                whilecondition1 = val1;
                whilecondition2 = val2;
            }
            else if(instr.equals(Instructions.ET)){
                String val1 = popStack();
                String val2 = popStack();
                int int1, int2;
                if(val1.matches("[0-9]+")){
                    int1 = Integer.parseInt(val1);
                }else{
                    int1 = Integer.parseInt(vars.get(scope.get(scope.size()-1)+"_"+val1));
                }
                if(val2.matches("[0-9]+")){
                    int2 = Integer.parseInt(val2);
                }else{
                    int2 = Integer.parseInt(vars.get(scope.get(scope.size()-1)+"_"+val2));
                }
                if(int1==int2){
                    pushStack("True");
                }else{
                    pushStack("False");
                }
                condition = Instructions.ET;
                whilecondition1 = val1;
                whilecondition2 = val2;
            }
            else if(instr.equals(Instructions.DSTACK)){
                ArrayList<String> st = new ArrayList<>();
                String stackName = scn.next();
                stacks.put(stackName, st);
            }
            else if(instr.equals(Instructions.PUSHTO)){
                String stackName = scn.next();
                String value = scn.next();
                ArrayList<String> st = stacks.get(stackName);
                if(value.matches("[0-9]+")){
                    st.add(value);
                }else{
                    st.add(vars.get(scope.get(scope.size()-2)+"_"+value));
                }
                stacks.put(stackName, st);
            }
            else if(instr.equals(Instructions.POPFROM)){
                String stackName = scn.next();
                ArrayList<String> st = stacks.get(stackName);
                stack.add(st.get(st.size()-1));
                st.remove(st.size()-1);
            }
            else if(instr.equals(Instructions.PEEKFROM)){
                String stackName = scn.next();
                ArrayList<String> st = stacks.get(stackName);
                stack.add(st.get(st.size()-1));
            }
        }
    }

}
