package br.com.projetocompiladores.datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ProjSymbolTable {
	
	private HashMap<String, ProjSymbol> map;
	
	public ProjSymbolTable() {
		map = new HashMap<String, ProjSymbol>();
		
	}
	
	public void add(ProjSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String symbolName) {
		return map.get(symbolName) != null;
	}
	
	public ProjSymbol get(String symbolName) {
		ProjSymbol s = map.get(symbolName);
		s.setUsed();
		return s;
	}
	
	public String getTypeByID(String id) {
		ProjVariable variable = (ProjVariable) this.get(id);
		if (variable.getType() == ProjVariable.TEXT) {
			return "TEXT";
		} else return "NUMBER";
	}
	
	public ArrayList<ProjSymbol> getAll(){
		ArrayList<ProjSymbol> lista = new ArrayList<ProjSymbol>();
		for (ProjSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}
	
	public List<ProjSymbol> getNonUsed() {
		ArrayList<ProjSymbol> allSymbols = this.getAll();
		List<ProjSymbol>      nonUsed	 = allSymbols.stream().filter(s -> !s.used).collect(Collectors.toList());
		return nonUsed;
	}
}
