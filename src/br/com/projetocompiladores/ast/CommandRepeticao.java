package br.com.projetocompiladores.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaCmd;
	
	public CommandRepeticao(String condition, ArrayList<AbstractCommand> l) {
		this.condition = condition;
		this.listaCmd = l;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
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
