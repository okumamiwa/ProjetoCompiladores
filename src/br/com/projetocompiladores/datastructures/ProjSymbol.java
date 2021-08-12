package br.com.projetocompiladores.datastructures;

public abstract class ProjSymbol {
	
	protected String name;
	protected boolean used;
	
	public abstract String generateJavaCode();
	public ProjSymbol(String name) {
		this.name = name;
		this.used = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsed() {
		this.used = true;
	}

	@Override
	public String toString() {
		return "ProjSymbol [name=" + name + "]";
	}
}
