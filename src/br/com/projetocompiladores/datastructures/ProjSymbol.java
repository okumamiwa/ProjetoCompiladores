package br.com.projetocompiladores.datastructures;

public abstract class ProjSymbol {
	
	protected String name;
	
	public abstract String generateJavaCode();
	public ProjSymbol(String name) {
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProjSymbol [name=" + name + "]";
	}
}
