package br.com.projetocompiladores.main;

import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;

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
			
			String file = "input.txt";
			if (args.length > 0) {
				file = args[0];
			}
			
			lexer = new ProjLangLexer(CharStreams.fromFileName(file));
			
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			parser = new ProjLangParser(tokens);
			parser.prog();
			
			ArrayList<String> warnings = parser.warnings();
			if(warnings.size() > 0) {
				System.out.println("############# WARNINGS #############");
				for(String w : warnings) {
					System.out.println(w);
				}
			}
			
			System.out.println("********* COMPILATION SUCESSFULL *********");
			System.out.println("---------------- COMMANDS ----------------");
			parser.exibeComandos();
			
			parser.generateCode();
		} 
		catch (ProjSemanticException ex) {
			System.err.println("SEMANTIC ERROR - " + ex.getMessage());
		}
		catch (Exception ex){
			System.err.println("ERROR: " + ex.getMessage());
		}
	}
}
