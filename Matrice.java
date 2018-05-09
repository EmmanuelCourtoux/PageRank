package version3Projet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Matrice {
	int nbElement;
	int dimension;
	ArrayList<element> in;	
	
	Matrice(int m, int n, ArrayList<element> e){
		this.nbElement = m;
		this.dimension = n;
		this.in = e;
	}
	
	public void print() {
		System.out.println("Dimension " + dimension);
		System.out.println("Nombre d'�l�ments non nuls " + nbElement);
		for(element e : this.in) {			
			e.print();
		}	
		System.out.println("End");
	}
	
	/*public int countNull(Matrice m){// nb �l�ment nul
		int acc = 0;
		for(element e : m.in) {
			if (e.value == 0) {
				acc += 1;
			}
		}
		return acc;
	}*/
	
	public void triColonne() { // tri de la matrice par colonne
		Collections.sort(this.in);
	}
	
	public ArrayList<Integer> tableauIndiceColonneD() {
		ArrayList<element> l = this.in;
		element tmp = l.get(0); //premier elem
		//tmp.print();
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(tmp.j-1);
		for (int i=0; i < l.size();i++) {
			if(l.get(i).j == tmp.j+1) {
				tmp = l.get(i);
				res.add(i);
				//System.out.println("IndD : " +i);
			}
			if(l.get(i).j > tmp.j) { //remplir indiceD pour les colonnes vide (non connexe)
				//System.out.println(tmp.j +" "+ l.get(i).j);			
				int diff = l.get(i).j - tmp.j;
				for (int k = 0; k < diff-1; k++) {
					res.add(i);
				}
				tmp = l.get(i);
				res.add(i);
				//System.out.println("IndD : " +i);
			}
		}
		//System.out.println(res.size());
		return res;
	}
	
	/*public ArrayList<Integer> tableauIndiceColonneF() {
		ArrayList<element> l = this.in;
		element tmp = l.get(0);
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (element e : l) {
			if(e.j == tmp.j) {
				tmp = e;		
			}
			if(e.j > tmp.j) {				
				res.add(l.indexOf(tmp));	
				tmp = e;
				System.out.println("IndF : " +l.indexOf(tmp));
			}		
			if(l.indexOf(e) == l.size()-1) { //dernier elem
				res.add(l.indexOf(e));	
			}
		}
		return res;
	}*/

}
