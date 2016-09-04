HELLO

Team Members:
Shweta Murthy
ASU ID: 1208065920

Derek Hamel
ASU ID: 1206055574

Dhruvrajsinh Parmar
ASU ID: 1209872414

This document is not intended to be used as a guide to learn �Hello�. 
It is only intended to aid in testing and grading purposes by the TA or the instructor.

Contents:
o Introduction
o Installation and testing guide
o Link To YouTube Video
o Outcomes addressed
o Sample programs
o References
	

Introduction:

The language implemented is termed as �Hello� to reflect our introduction into fully fledged language invention experience.
The language is developed primarily using java and ANTLR4 as a tool to implement lexer and parser for the grammar. 
The language doesn�t reflect any particular language construct but is meant to be close to already familiar languages,
to aid ease-of-use.

Installation and testing guide:

Run the executable jar hello.jar by simply running this command in the command line or terminal:
java -jar hello.jar <program_file_name>

Note: make sure the program file is in the same directory as the hello.jar

OR

By executing code:
Prerequisites:-
ANTLR 4 Runtime jar and Complete jar (Available here)
Following files :- 
Hello.g4,Antlr Generated Lexer and Parser, Tokens
(It can be found in the uploaded package file) 
 

Steps:-
Save ANTLR 4 jars in your workspace where the grammar is located and the runtime jar with your buildpath. Runtime packages are needed by the Main.java file.

In the Visitor2 class you can change the output file name of the intermediate code by specifying it at the following line of code
File fileOut=new File("<your-file-name>");

In the Main Program put the input file name containing your program at 
ANTLRInputStream input=new ANTLRFileStream(�<your-program-file-name>�);

Run Main

Verify output and generated intermediate code file.
Specify file name of generated intermediate code file in Runtime.java at
InputStream in = new FileInputStream(new File("<your-file-name"))

Run Runtime.java.


Link to the YouTube Video
https://youtu.be/LewrW6Pe-uE


Outcomes addressed:
The language implemented addresses the following-
Binary operators- addition, subtraction, multiplication, division, less than, greater than, equal to.
Primitive type- Integer, Boolean(True,False)
Parameterized procedures, functions, anonymous functions
Assignment statement
Decision construct- if-then-else
Iteration- while statement
Data structure- Stack
Recursion support
Low-level intermediate code


Sample programs:

1)Factorial:

program Factorial; var Integer x:=4;
function Integer factorial(Integer number){
        if number < 2 then
        return 1;
        else
        return number * factorial(number - 1);
    }
begin{
    print(factorial(x));
}

2)Stack

program Stack;
begin{
       Stack stack1 := new Stack;
       pushStack stack1 1;
       print(popStack stack1);
}

3)Iteration

program Loop;
var Integer x:=4;
begin
{
while
x>2
x:=x-1;
endwhile;
print(x);
}




References:
http://www.antlr.org/

