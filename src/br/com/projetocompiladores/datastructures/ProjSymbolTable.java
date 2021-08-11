package br.com.projetocompiladores.datastructures;

import java.util.HashMap;
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
		return map.get(symbolName);
	}
	
	public ArrayList<ProjSymbol> getAll(){
		ArrayList<ProjSymbol> lista = new ArrayList<ProjSymbol>();
		for (ProjSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}
}
