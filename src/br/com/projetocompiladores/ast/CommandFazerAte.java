package br.com.projetocompiladores.ast;

import br.com.projetocompiladores.frmt.Indentacao;
import java.util.ArrayList;

public class CommandFazerAte extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaCmd;
        private Indentacao indentador=new Indentacao();

	public CommandFazerAte(String condition, ArrayList<AbstractCommand> l) {
		this.condition = condition;
		this.listaCmd  = l;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append(indentador+"do {\n");
                indentador.Indenta();
		for (AbstractCommand cmd: listaCmd) {
			str.append(cmd.generateJavaCode());
		}
                indentador.DeIndenta();
		str.append(indentador+"} while ("+condition+");\n");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandFazerAte [condition=" + condition + ", listaCmd=" + listaCmd + "]";
	}

}
