grammar ProjLang;

@header{
	import br.com.projetocompiladores.datastructures.ProjSymbol;
	import br.com.projetocompiladores.datastructures.ProjVariable;
	import br.com.projetocompiladores.datastructures.ProjSymbolTable;
	import br.com.projetocompiladores.exceptions.ProjSemanticException;
	import java.util.ArrayList;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private ProjSymbolTable symbolTable = new ProjSymbolTable();
	private ProjSymbol symbol;
	
	private void verificaID(String id) {
		if (!symbolTable.exists(id)){
			throw new ProjSemanticException("Symbol " + id + " not declared");
		}
	}
}

prog	: 'programa' decl bloco 'fimprog;'
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
	
bloco	: (cmd) +
		;
		
tipo	: 'numero'	{_tipo = ProjVariable.NUMBER;}
		| 'texto'	{_tipo = ProjVariable.TEXT;}
		;
		
cmd 	: cmdleitura {System.out.println("Comando de leitura reconhecido");}
		| cmdescrita {System.out.println("Comando de escrita reconhecido");}
		| cmdatrib	 {System.out.println("Comnando de atribuicao reconhecido");}	
		;
		
cmdleitura	: 'leia' 	AP 
						ID { verificaID(_input.LT(-1).getText());
                 	  	} 
						FP	 
						SC
			;
			
cmdescrita	: 'escreva' AP 
						ID { verificaID(_input.LT(-1).getText());
                 	  	} 
                 	  	FP 
                 	  	SC
			;

cmdatrib	: 	ID { verificaID(_input.LT(-1).getText());
     	  		} 
     	  		ATTR expr SC
			;

expr		: termo (OP termo)*
			;
			
termo		: ID { verificaID(_input.LT(-1).getText());
              } 
              | NUMBER
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
     
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?	 
	 	;
	 	
WS	: (' ' | '\t' | '\n' | '\r') -> skip;
	 	
	 	
	 	
	 