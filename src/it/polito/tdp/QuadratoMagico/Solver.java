package it.polito.tdp.QuadratoMagico;

import it.polito.tdp.QuadratoMagico.Posizione;
import it.polito.tdp.QuadratoMagico.Solver;
import it.polito.tdp.QuadratoMagico.QuadratoMagico;

public class Solver {

	public void risolvi(int n){
		
		QuadratoMagico qm = new QuadratoMagico(n);
		int passo = 0;
		
		this.riempi(qm , passo);
	}

private void riempi(QuadratoMagico qm, int passo){
		
		if (passo == qm.size()){
			//soluzione trovata
			System.out.println("soluzione trovata");
			
			for (Posizione pp : qm.getPosizioni())
				System.out.format("%d %d ->%d\n", pp.getRiga(), pp.getCol(), qm.get(pp));
			
		}else {
			
			Posizione p = qm.getPosizioni().get(passo);
			
			for (Integer i : qm.getValori()){
				if (tentativoValido(qm, p , 0)){
					qm.set(p, i);
					qm.getValori().remove(i);
					riempi(qm, passo+1);
					qm.delete(p);
					qm.getValori().add(i);
				}
			}
	
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Solver s = new Solver();
		s.risolvi(3);
		
		
	}
	
	private boolean tentativoValido(QuadratoMagico qm , Posizione p, Integer i	){
		
		
		int tempRiga = qm.getSommaRighe().get(p.getRiga()) + i;
		int tempCol  = qm.getSommaCol().get(p.getCol()) + i;	
		int tempDiag1 = 0;
		int tempDiag2 = 0;
		if(p.getRiga()==p.getCol()){
			tempDiag1 = qm.getSommaDiag()[0] +i;
		}else if(p.getRiga()== qm.getLato() -1 - p.getCol()){
			tempDiag2 = qm.getSommaDiag()[1] +i;
		}
		//controllo riga
		if (tempRiga > qm.getMagico() || tempCol > qm.getMagico() 
				|| tempDiag1 > qm.getMagico() || tempDiag2 > qm.getMagico())
			return false;
			
		return true;
	
	}
}
