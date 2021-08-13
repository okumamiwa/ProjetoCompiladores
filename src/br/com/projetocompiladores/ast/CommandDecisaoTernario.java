package br.com.projetocompiladores.ast;

import java.util.ArrayList;

public class CommandDecisaoTernario extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	
	public CommandDecisaoTernario(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
	}
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("if ("+condition+") {");
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generateJavaCode());
		}
		str.append("} else { ");
		for (AbstractCommand cmd: listaFalse) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}\n");
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + ", listaTrue=" + listaTrue + ", listaFalse=" + listaFalse + "]";
	}
}