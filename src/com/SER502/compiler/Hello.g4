grammar Hello;

program: 'program' IDENT ';'
		 (declaration|function)*
		 'begin'
	     block													#ProgramLabel		
		 ;

block:	 '{'(statement)* '}' 										#BlockLabel 
		 ;

statement: assignment														
		   |declaration														 
		   |ifStatement														
		   |loopStatement														
		   |functionCall 
		   |stackOper
		   |returnStatement													
		   |breakStatement
		   |print
		 ;


print: 'print(' expr=expression ')' ';'                                                               #PrintLabel
     ;

declaration:variable|constant|stack
			;
stack: 'Stack' stackname=IDENT ASSIGNMENT 'new' 'Stack' ';' 										#StackDefLabel
	;

stackOper: push | pop 
		 ;

push : 'pushStack' stackName=IDENT value=INTEGER ';'												#PushLabel
	 ;
pop :    operName=('popStack'|'peekStack')   stackName=IDENT 									#PopLabel
	   ;
	
functionCall:funName=IDENT '('callParam=callParameters? ')' 	       								#FunctCallLabel
			;	

callParameters: first=expression (','expression)*							 				    	#CallParamLabel
			  ;	
parameters: first=parameter (',' parameter)*													    #PassParameters
		  ;																

parameter: 'var'? type name=IDENT										  							#ParameterLabel
		 ;
		 
function: 'function' (ret=type)? name=IDENT '('parm=parameters? ')'
		 funcBlock=block																	      	  #FunctDefLabel
		;		


WS : [ \t\r\n] -> channel(HIDDEN)                             			   // skip spaces, tabs, newlines
   ; 

constant:'const' type IDENT  ':=' expression ';'
		;

variable:'var' 										
		  type 
		  variableName=IDENT
		  (value=':=' expression)';'													#DeclarationLabel
		  ;


type: 'Integer'
	;

ifStatement : 'if' express1=expression 'then' onTrue=ifblock         
			('else' onFalse=elseblock)?                              
			'endif' ';'																 #IFStatementLabel1
			;

ifblock: (statement)* ;

elseblock: (statement)* ;										

loopStatement:'while' expr=expression loopBlock=whileBlock end='endwhile' ';' 			#LoopStatLabel
			 ;
			 
whileBlock: (statement)*																#LoopBlock
		  ;
			
returnStatement:'return' value=expression? ';'											#ReturnLabel
			   ;			 
breakStatement:'break' 'when' expression ';'											#breakLabel
			  ;
			
assignment: var=IDENT ASSIGNMENT assgBlock=assignmentBlock ';'  						   		#AssignmentLabel
		  ;
assignmentBlock: expression|functionCall ;

expression:	 leftexpr=expression op=DIV rightexpr=expression			 			#DivLabel
		   | leftexpr=expression op=MULT rightexpr=expression						#MultLabel
		   | leftexpr=expression op=SUBT rightexpr=expression	 					#SubtLabel
		   | leftexpr=expression op=ADD rightexpr=expression	 					#AddLabel
		   | leftexpr=expression op=(GT|LT|EQ|NE) rightexpr=expression 				#GTLTEQLabel
		   | leftexpr=expression op=(GTEQ|LTEQ) rightexpr=expression 				#GTEQLTEQLabel
		   | leftexpr=expression op=(AND|OR|NOT) rightexpr=expression 				#AndORNotLabel
		   | pop																	#PopExLabel
		   | INTEGER 																#IntegerLabel
		   | IDENT																	#IdentLabel
		   | functionCall															#FunctCallExprLabel
		   | BOOLEAN																#BooleanLabel
		  ;




BOOLEAN: 'TRUE'|'FLASE';


   		
INTEGER: '0'..'9'+													
	   ;
IDENT : ('a'..'z'|'A'..'Z')([a-z]|[0-9]|[A-Z])*
	  ;   

COMMENT:'//' .* ('\n'|'\r')-> channel(HIDDEN)
		;
		
ADD:  '+';
MULT: '*';
SUBT: '-';
DIV:  '/';
OR:   '|';
AND:  '&';
LTEQ: '<=';
NE:   '!=';
EQ:   '=';
LT:   '<';
GT:   '>';
MOD:  '%' ;
GTEQ: '>='; 
NOT: '!';
ASSIGNMENT: ':=';
LPARE: '(';
RPARE: ')';
			