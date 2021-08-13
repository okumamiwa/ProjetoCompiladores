package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.datastructures.ProjVariable;
import br.com.projetocompiladores.frmt.Indentacao;

public class CommandLeitura extends AbstractCommand {
	private String id;
	private ProjVariable var;
        private Indentacao indentador = new Indentacao();
	
	public CommandLeitura(String id, ProjVariable var) {
		this.id = id;
		this.var = var;
	}
	
	@Override
	public String generateJavaCode() {
		return indentador+id +"= _key." + (var.getType()==ProjVariable.NUMBER? "nextDouble();": "nextLine();")+"\n";
	}
	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}
	
}
