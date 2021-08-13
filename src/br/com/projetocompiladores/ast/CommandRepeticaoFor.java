package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.frmt.Indentacao;
import java.util.ArrayList;

public class CommandRepeticaoFor extends AbstractCommand {
	private String declaration;
	private String condition;
	private String action;
	private ArrayList<AbstractCommand> listaCmd;
        private Indentacao indentador=new Indentacao();
	
	public CommandRepeticaoFor(String declaration, String condition, String action, ArrayList<AbstractCommand> l) {
		this.declaration = declaration;
		this.condition = condition;
		this.action = action;
		this.listaCmd = l;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append(indentador+"for ("+declaration+";"+condition+";"+action+") {\n");
                indentador.Indenta();
		for (AbstractCommand cmd: listaCmd) {
			str.append(indentador+cmd.generateJavaCode());
		}
		str.append(indentador+"}");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", listaCmd=" + listaCmd + "]";
	}

}
