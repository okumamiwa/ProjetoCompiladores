package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.frmt.Indentacao;

public class CommandAtribuicao extends AbstractCommand {
	private String id;
	private String expr;
        private Indentacao indentador=new Indentacao();
	
	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}

	@Override
	public String generateJavaCode() {
		return indentador+id + " = "+expr+";\n";
	}
	
	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}

}
