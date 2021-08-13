package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.frmt.Indentacao;
import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaCmd;
        private Indentacao indentador = new Indentacao();

	public CommandRepeticao(String condition, ArrayList<AbstractCommand> l) {
		this.condition = condition;
		this.listaCmd = l;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append(indentador+"while ("+condition+") {\n");
                indentador.Indenta();
		for (AbstractCommand cmd: listaCmd) {
			str.append(cmd.generateJavaCode());
		}
                indentador.DeIndenta();
		str.append(indentador+"}\n");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", listaCmd=" + listaCmd + "]";
	}

}
