package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.frmt.Indentacao;

public class CommandEscrita extends AbstractCommand {
	private String id;
        private Indentacao indentador = new Indentacao();
	
	public CommandEscrita(String id) {
		this.id = id;
	}

	@Override
	public String generateJavaCode() {
		return indentador+"System.out.println("+id+");\n";
	}
	
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}

}
