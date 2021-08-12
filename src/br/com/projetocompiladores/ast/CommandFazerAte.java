package br.com.projetocompiladores.ast;

import java.util.ArrayList;

public class CommandFazerAte extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaCmd;

	public CommandFazerAte(String condition, ArrayList<AbstractCommand> l) {
		this.condition = condition;
		this.listaCmd  = l;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("do {\n");
		for (AbstractCommand cmd: listaCmd) {
			str.append(cmd.generateJavaCode());
		}
		str.append("} \n while ("+condition+");");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandFazerAte [condition=" + condition + ", listaCmd=" + listaCmd + "]";
	}

}
