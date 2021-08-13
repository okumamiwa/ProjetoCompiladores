package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.frmt.Indentacao;
import java.util.ArrayList;

public class CommandDecisaoTernario extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
        private Indentacao indentador = new Indentacao();
        
	public CommandDecisaoTernario(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
	}
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append(indentador+"if ("+condition+") {\n");
                indentador.Indenta();
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generateJavaCode());
		}
                indentador.DeIndenta();
		str.append(indentador+"}\n"+indentador+"else {\n");
                indentador.Indenta();
		for (AbstractCommand cmd: listaFalse) {
			str.append(cmd.generateJavaCode());
		}
                indentador.DeIndenta();
		str.append(indentador+"}\n");
		return str.toString();
	}
	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + ", listaTrue=" + listaTrue + ", listaFalse=" + listaFalse + "]";
	}
}
