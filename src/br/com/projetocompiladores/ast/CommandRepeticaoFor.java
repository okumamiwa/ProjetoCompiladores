package br.com.projetocompiladores.ast;

import java.util.ArrayList;

public class CommandRepeticaoFor extends AbstractCommand {
	private String declaration;
	private String condition;
	private String action;
	private ArrayList<AbstractCommand> listaCmd;
	
	public CommandRepeticaoFor(String declaration, String condition, String action, ArrayList<AbstractCommand> l) {
		this.declaration = declaration;
		this.condition = condition;
		this.action = action;
		this.listaCmd = l;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("for ("+declaration+";"+condition+";"+action+") {\n");
		for (AbstractCommand cmd: listaCmd) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", listaCmd=" + listaCmd + "]";
	}

}
