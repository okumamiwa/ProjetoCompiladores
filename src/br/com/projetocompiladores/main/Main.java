package br.com.projetocompiladores.main;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import br.com.projetocompiladores.exceptions.ProjSemanticException;
import br.com.projetocompiladores.parser.ProjLangLexer;
import br.com.projetocompiladores.parser.ProjLangParser;

/*classe de interação com usuário
 *arquivo fonte: input.txt
 */

public class Main {
	public static void main(String[] args) {
		try {
			ProjLangLexer lexer;
			ProjLangParser parser;
			
			lexer = new ProjLangLexer(CharStreams.fromFileName("input.txt"));
			
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			parser = new ProjLangParser(tokens);
			parser.prog();
			
			System.out.println("Compilation Successfull");
		} 
		catch (ProjSemanticException ex) {
			System.err.println("SEMANTIC ERROR - " + ex.getMessage());
		}
		catch (Exception ex){
			System.err.println("ERROR: " + ex.getMessage());
		}
	}
}
