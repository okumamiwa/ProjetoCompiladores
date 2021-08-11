package br.com.projetocompiladores.datastructures;

public class ProjVariable extends ProjSymbol {
	
	public static final int NUMBER = 0;
	public static final int TEXT   = 1;
	
	private int type;
	private String value;
	
	public ProjVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ProjVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}

	@Override
	public String generateJavaCode() {
		String str;
		if (type == NUMBER) {
			str = "double ";
		}
		else {
			str = "String ";
		}
		return str + " " + super.name + ";";
	}
}
