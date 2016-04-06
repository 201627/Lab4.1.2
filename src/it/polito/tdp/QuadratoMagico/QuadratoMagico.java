package it.polito.tdp.QuadratoMagico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.QuadratoMagico.Posizione;

public class QuadratoMagico {

	private int N;	//dimensione
	private int magico;
	
	private Map<Posizione , Integer> caselle ;
	private List<Posizione>	posizioni;
	private List<Integer>	valori;
	private List<Integer>	sommaRighe;
	private List<Integer>	sommaCol;
	private int[] sommaDiag;
	

	public QuadratoMagico(int n) {
		
		N = n;
		magico = n*(n*n + 1)/2;
		caselle = new HashMap<Posizione, Integer>();
		posizioni = new ArrayList<Posizione>();
		valori = new ArrayList<Integer>();
		sommaRighe = new ArrayList<Integer>();
		sommaCol = new ArrayList<Integer>();
		sommaDiag =  new int[2];
		sommaDiag[0] = 0;
		sommaDiag[1] = 0;
		
		for(int riga = 0; riga <= N-1; riga++){
			for (int col = 0; col <= N - 1; col++){
				posizioni.add(new Posizione(riga, col));
			}
		}
		
		for ( int i = 1 ; i<= n*n ; i++){
			valori.add(i);
		}
		for ( int i = 0 ; i<= n-1 ; i++){
			sommaRighe.add(i , 0);
			sommaCol.add(i, 0);
		}
		
			
	}
	
	public Integer get(Posizione p){
		if (caselle.get(p)!= null)
			return caselle.get(p);
		else
			return null;
	}
	
	public void set(Posizione p, int i){
		if (caselle.get(p)== null){
			caselle.put(p, i);
			sommaRighe.set(p.getRiga(), sommaRighe.get(p.getRiga())+i);
			sommaCol.set(p.getCol(), sommaCol.get(p.getCol()) + i);
			if(p.getRiga()==p.getCol()){
				sommaDiag[0]+=i;
			}else if(p.getRiga()== N - 1 - p.getCol()){
				sommaDiag[1]+=i;
			}
		} else
			throw new RuntimeException("Casella già occupata");
	}
	
	public void delete(Posizione p){
		if (caselle.get(p)!= null){
			int i = caselle.get(p);
			caselle.remove(p);
			sommaRighe.set(p.getRiga(), sommaRighe.get(p.getRiga())- i );
			sommaCol.set(p.getCol(), sommaRighe.get(p.getCol()) - i);
			if(p.getRiga()==p.getCol()){
				sommaDiag[0]-=i;
			}else if(p.getRiga()== N - 1 - p.getCol()){
				sommaDiag[1]-=i;
			}
		}
			
		else
			throw new RuntimeException("Casella ancora vuota");
		
	}
	
	public boolean isValid(Posizione p){
		if (posizioni.contains(p))
			return true;
		else 
			return false;
	}
	
	public int size(){
		return posizioni.size();
	}
	
	public List<Posizione> getPosizioni(){
		return posizioni;
		
	}

	public List<Integer> getValori() {
		return valori;
	}
	
	public int getMagico(){
		return magico;
	}

	public List<Integer> getSommaRighe() {
		return sommaRighe;
	}

	public List<Integer> getSommaCol() {
		return sommaCol;
	}

	public int[] getSommaDiag() {
		return sommaDiag;
	}
	
	public int getLato(){
		return N;
	}

	public Map<Posizione, Integer> getCaselle() {
		return caselle;
	}
	
}
