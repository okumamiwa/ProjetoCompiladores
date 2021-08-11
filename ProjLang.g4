grammar ProjLang;

@header{
	import br.com.projetocompiladores.datastructures.ProjSymbol;
	import br.com.projetocompiladores.datastructures.ProjVariable;
	import br.com.projetocompiladores.datastructures.ProjSymbolTable;
	import br.com.projetocompiladores.exceptions.ProjSemanticException;
	import br.com.projetocompiladores.ast.ProjProgram;
	import br.com.projetocompiladores.ast.AbstractCommand;
	import br.com.projetocompiladores.ast.CommandLeitura;
	import br.com.projetocompiladores.ast.CommandEscrita;
	import br.com.projetocompiladores.ast.CommandAtribuicao;
	import br.com.projetocompiladores.ast.CommandDecisao;
	import br.com.projetocompiladores.ast.CommandRepeticao;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private ProjSymbolTable symbolTable = new ProjSymbolTable();
	private ProjSymbol symbol;
	private ProjProgram program = new ProjProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> listaCmd;
	
	public void verificaID(String id) {
		if (!symbolTable.exists(id)){
			throw new ProjSemanticException("Symbol " + id + " not declared");
		}
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTarget();
	}
}

prog	: 'programa' decl bloco 'fimprog;'
		  	{  	
		  		program.setVarTable(symbolTable);
           	  	program.setComandos(stack.pop());
        	} 
        ;
		
decl	: (declaravar) + 
		;
		
declaravar	: tipo ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new ProjVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new ProjSemanticException("Symbol " +_varName + " already declared");
	                  }
                  	}
				( 	VIR 
					ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new ProjVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new ProjSemanticException("Symbol " +_varName + " already declared");
	                  }
                    }
				)* 
					SC
			;
	
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		
tipo	: 'numero'	{_tipo = ProjVariable.NUMBER;}
		| 'texto'	{_tipo = ProjVariable.TEXT;}
		;
		
cmd 	: cmdleitura
		| cmdescrita 
		| cmdatrib	 
		| cmdselecao
		| cmdrepeticao
		;
		
cmdleitura	: 'leia' 	AP 
						ID { verificaID(_input.LT(-1).getText());
						     _readID = _input.LT(-1).getText();
                 	  	} 
						FP	 
						SC
				{
              		ProjVariable var = (ProjVariable)symbolTable.get(_readID);
              		CommandLeitura cmd = new CommandLeitura(_readID, var);
              		stack.peek().add(cmd);
              	};
			
cmdescrita	: 'escreva' AP 
						ID { verificaID(_input.LT(-1).getText());
							 _writeID = _input.LT(-1).getText();
                 	  	} 
                 	  	FP 
                 	  	SC
                {
               	  	CommandEscrita cmd = new CommandEscrita(_writeID);
               	  	stack.peek().add(cmd);
               	}	
			;

cmdatrib	: 	ID { verificaID(_input.LT(-1).getText());
                     _exprID = _input.LT(-1).getText();
     	  		} 
     	  		ATTR { _exprContent = ""; } 
     	  		expr 
     	  		SC {
               	 	CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 	stack.peek().add(cmd);
               	}
			;

cmdselecao	:  'se' AP 
					ID 	  { _exprDecision = _input.LT(-1).getText(); }
					OPREL { _exprDecision += _input.LT(-1).getText(); }
					(ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
					FP 
					ACH { 	curThread = new ArrayList<AbstractCommand>(); 
                      		stack.push(curThread);
                    }
                    (cmd)+ 
                    FCH { listaTrue = stack.pop();	
                    } 
				('senao' 
					ACH {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	} 
                   	(cmd+)
                   	FCH {
                   		listaFalse = stack.pop();
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
				)?
			;
			
cmdrepeticao: 'enquanto'	AP
							ID 		{ _exprDecision = _input.LT(-1).getText(); }
							OPREL 	{ _exprDecision += _input.LT(-1).getText(); }
							(ID | NUMBER)	{ _exprDecision += _input.LT(-1).getText(); }
							FP	
							ACH	{	curThread = new ArrayList<AbstractCommand>();
									stack.push(curThread);
							}
							(cmd)+
							FCH {	listaCmd = stack.pop(); 
									CommandRepeticao cmd = new CommandRepeticao( _exprDecision, listaCmd);
									stack.peek().add(cmd);
							}
			;
						

expr		: termo ( 
	           	OP { _exprContent += _input.LT(-1).getText();}
	            termo
	            )*
			;
			
termo		: ID 	{ verificaID(_input.LT(-1).getText());
	               	  _exprContent += _input.LT(-1).getText();
              } 
              | 
              NUMBER{ _exprContent += _input.LT(-1).getText();
              }
              |
              TEXT	{ _exprContent += _input.LT(-1).getText();
              }
			;
		
AP	: '('
	;	
	
FP	: ')'
	;
	
SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;		
	
ATTR: '='
	;
	 
VIR : ','
    ;
    
ACH : '{'
    ;
     
FCH	: '}'
    ;
	 
OPREL 	: '>' | '<' | '>=' | '<=' | '==' | '!='
      	;	
     
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?	 
	 	;
	 	
TEXT : '"' ( '\\"' | . )*? '"' ;

    
WS	: (' ' | '\t' | '\n' | '\r') -> skip;
	 	
	 	
	 	
	 