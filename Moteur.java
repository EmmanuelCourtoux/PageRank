package readerUpgrade;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Moteur {
	static Matrice m;
	List<Double> vecteur;
	Double alpha = 0.85;

	Moteur(Matrice m, List<Double> v){
		this.m = m;
		this.vecteur = v;
	}


	public List<Double> multiplication(List<Double> pi, List<Double> F, List<Integer> indiceD/*, List<Integer> indiceF*/) {
		//List<Double> pi = this.vecteur;
		Matrice m = this.m;

		//System.out.println(m.in.size());
		List<Double> res = new ArrayList<Double>(m.dimension+1);
		//List<Integer> e = new List<Integer>(Collections.nCopies(m.dimension+1, 1));
		int i = 0;
		int j = 0;
		double D = calcDelta(F, pi);
		while(i  < m.dimension){  //parcours colonnes m i = num colonne
			//System.out.println("i"+i);
			double tmp = 0.0;
			
			if(i+1 < m.dimension) {
				for(j = indiceD.get(i); j < indiceD.get(i+1) && i < m.in.size(); j++){ //parcours les valeurs dans la colonne 
					//System.out.println("j"+j);
					element el = m.in.get(j);//element de la colonne
					//System.out.println(el.value);
					//D = calcDelta(F, pi); // calcul delta PROBLEME
					tmp = tmp + el.value * pi.get(el.i-1) * alpha;//on multiplie l'element de la colonne par la valeur associe ï¿½ sa ligne sur le vecteur pi
					//System.out.println(tmp);
				}
			}
			else { //Cas dernier élément
				for(j = indiceD.get(i); j < m.in.size(); j++){ //parcours les valeurs dans la colonne 
					//System.out.println("boucle fin");
					//System.out.println("j"+j);
					element el = m.in.get(j);//element de la colonne
					//System.out.println(el.value);
					//D = calcDelta(F, pi); // calcul delta
					tmp = tmp + el.value * pi.get(el.i-1) * alpha;//on multiplie l'element de la colonne par la valeur associe ï¿½ sa ligne sur le vecteur pi
					//System.out.println(tmp);
				}
			}
			res.add(i, tmp + ( (1-alpha)/m.dimension) + (D)/m.dimension); //ajout alpha
			//System.out.println(res);
			/*if(i%1000==0) {
				System.out.print("\r"+i);
			}*/
			i++;			
		}
		return res; //Return Pik+1
	}

	public static double normeInf(List<Double> v1, List<Double> v2) {
		double tmp=0.0;
		for(int i = 0; i < v1.size(); i++) {
			double res = Math.abs((v1.get(i))-v2.get(i));
			if(res > tmp) {
				tmp = res;
			}
		}
		return tmp;
	}

	//a = indice des lignes nulles 
	public static List<Double> createF(List<Integer> a, Matrice m){
		List<Double> F = new ArrayList<Double>(Collections.nCopies(m.dimension, 0.0));
		for (Integer i : a){
			F.set(i-1, 1.0);
		}
		//System.out.println("tabnull : " +a);
		//System.out.println("VectF : " +F);
		return F;
	}

	//a = F d = Pi retourne Delta
	public Double calcDelta(List<Double> a, List<Double> d){
		double res = 0.0;
		int borne = a.size();
		for (int i =0; i < m.dimension; i++){
			res += a.get(i) * d.get(i);
		}
		return res*alpha;
		/*double res = 0.0;
		System.out.println("ver");
		if(a.size()%2==0)
			for (int i =1; i < a.size(); i=i+2){
				res += a.get(i-1) * d.get(i-1);
				res += a.get(i) * d.get(i);
			}
		else {
			for (int i =1; i < a.size(); i=i+2){
				res += a.get(i-1) * d.get(i-1);
				res += a.get(i) * d.get(i);
			}
			res += a.get(a.size()-1) * d.get(a.size()-1);
		}	
		return res*alpha;*/
	}
	
	public static void modifGraph(List<Integer> Index, List<Integer> Nulle) {
		int k = 0;
		for(int i : Nulle) {
			k = Index.get(i) - Index.get(i-1); //Nombre de pere du site sans lien de sortie
			//System.out.println(Index.get(i));
			//System.out.println("Affichage k:" +k);
			if(k >= 2) { //Si il y a au moins 2 peres
				for(int j =0; j < k; j++) {	
					m.dimension ++; //dimension matrice
					//System.out.println("Affichage 1:" +(m.in.get(Index.get(i-1)+j)).i);
					element e1 = new element(m.dimension, (m.in.get(Index.get(i-1)+j)).i, 1.0); //Création nouveau lien
					
					m.in.add(e1); //Ajout nouveau lien
					//System.out.println("Affichage 2:" +(m.in.get(Index.get(i-1)+j)).i);
					m.in.get(Index.get(i-1)+j).j = m.dimension; //Modification du père pour pointer sur le nouveau site					
					m.nbElement++; //Incrémentation nombre non nul				
				}
			}
			else if (k == 1) {
				m.dimension ++;
				element e1 = new element(m.dimension, Index.get(i-1) , 1.0);
				m.in.add(e1);
				m.nbElement++;
			}				
		}
	}

}
