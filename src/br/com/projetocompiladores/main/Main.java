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
			long start = System.currentTimeMillis();
			
			ProjLangLexer lexer;
			ProjLangParser parser;
			
			String file = "input3.txt";
			if (args.length > 0) {
				file = args[0];
			}
			
			lexer = new ProjLangLexer(CharStreams.fromFileName(file));
			
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			parser = new ProjLangParser(tokens);
			parser.prog();
			
			parser.exibeWarnings();
			
			System.out.println( "-".repeat(45) + " COMMANDS " + "-".repeat(45));
			parser.exibeComandos();
			System.out.println("-".repeat(100) + "\n");
			
			parser.generateCode();
			System.out.println("COMPILATION SUCESSFULL");

			System.out.printf("Total time: %d ms", System.currentTimeMillis() - start);
			
		} 
		catch (ProjSemanticException ex) {
			System.err.println("SEMANTIC ERROR - " + ex.getMessage());
		}
		catch (Exception ex){
			System.err.println("ERROR: " + ex.getMessage());
		}
	}
}
