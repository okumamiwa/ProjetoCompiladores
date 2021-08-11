package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.datastructures.ProjVariable;

public class CommandLeitura extends AbstractCommand {
	private String id;
	private ProjVariable var;
	
	public CommandLeitura(String id, ProjVariable var) {
		this.id = id;
		this.var = var;
	}
	
	@Override
	public String generateJavaCode() {
		return id +"= _key." + (var.getType()==ProjVariable.NUMBER? "nextDouble();": "nextLine();");
	}
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}
	
}
