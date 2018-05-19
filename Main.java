package readerUpgrade;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main
{
	public static void main(String[] argv) throws IOException {
		System.out.println("reader 2");	
		//variables 
		//String line;
		double epsilon = Math.pow(10, -8); //ESSAYER DE DIMINIUER
		List<Double> pi0 = new ArrayList<Double>();
		List<Double> pi1 = new ArrayList<Double>();
		//List<Double> e = new ArrayList<Double>();
		/*int m; //nb non nul
		int n = 0; //n : dimension de la matrice
		List<String> tabi = new ArrayList<String>(); //tableau ligne
		List<String> tabj = new ArrayList<String>(); //tableau colonne
		List<String> tabv = new ArrayList<String>(); //tableau valeur*/
		/*List<String> stringl = new ArrayList<String>(); //tableau de la ligne*/
		List<Integer> tabnull = new ArrayList<Integer>(); //tableau des lignes avec degrÃ© 0

		//Reader
		int tailleM;
		int nbElt;
		List<element> tabMatrice = new ArrayList<element>();
		List<String> line;
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Emmanuel\\eclipse-workspace\\PageRank\\src\\readerUpgrade\\wb-edu.txt")))
		{
			tailleM = Integer.parseInt(br.readLine().replace(" ",""));
			nbElt = Integer.parseInt(br.readLine().replace(" ",""));		
			//Parcour de toutes les lignes
			int i;
			int j;

			for(i=0;i<tailleM;i++)
			{
				if(i%100000 == 0)
					System.out.println(i);
				line = Arrays.asList(br.readLine().replace("  "," ").split(" "));	
				//ajout des elements a la liste
				
				if(Integer.parseInt(line.get(1)) !=0)
				{
					for(j = 2; j<=2 * Integer.parseInt(line.get(1));j +=2)
					{
						tabMatrice.add(new element(Integer.parseInt(line.get(0)),Integer.parseInt(line.get(j)),Double.parseDouble(line.get(j+1))));
					}
				}
				else if (Integer.parseInt(line.get(1)) ==0) {
					tabnull.add(Integer.parseInt(line.get(0)));
				}
			}
		}
		

		

		//TEST
		/*FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream("C:\\Users\\Emmanuel\\git\\PageRank\\src\\LastVersion\\Stanford_BerkeleyV2.txt");
		    sc = new Scanner(inputStream, "UTF-8");
		    tailleM = Integer.parseInt(sc.nextLine().replace(" ",""));
		    System.out.println(tailleM);
			nbElt = Integer.parseInt(sc.nextLine().replace(" ",""));
			System.out.println(nbElt);
		    while (sc.hasNextLine()) {


				//List<String> line = Arrays.asList(sc.nextLine().replace("  "," ").split(" "));		        		        		
				//Parcours de toutes les lignes
				int i;
				int j;

				for(i=0;i<tailleM;i++)
				{
					List<String> line = Arrays.asList(sc.nextLine().replace("  "," ").split(" "));	
					//ajout des elements a la liste

					if(Integer.parseInt(line.get(1)) !=0)
					{
						for(j = 2; j<=2 * Integer.parseInt(line.get(1));j +=2)
						{
							tabMatrice.add(new element(Integer.parseInt(line.get(0)),Integer.parseInt(line.get(j)),Double.parseDouble(line.get(j+1))));
						}
					}
					else if (Integer.parseInt(line.get(1)) ==0) {
						tabnull.add(Integer.parseInt(line.get(0)));
					}
				}
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}*/

		//affiche(tabMatrice);


		/*System.out.println(epsilon);
            System.out.println(n);
            System.out.println(m);
            System.out.println(tabi);
            System.out.println(tabj);
            System.out.println(tabv);*/

		//br.close();

		System.out.println("fin read");
		//System.out.println("taille tabi : "+tabi.size());
		System.out.println("nb elem non nul : "+nbElt);
		/*System.out.println(tabi.get(816022));
            System.out.println(tabj.get(816022));
            System.out.println(tabv.get(816022));
            System.out.println(tabj.get(816023));
            System.out.println(tabv.get(816023));
            System.out.println(tabi.get(816023));*/


		/*List<element> listEl = new ArrayList<element>();
		for (i=0;i<m;i++){        	
			int i1 = Integer.parseInt(tabi.get(i)); //crï¿½ation liste d'ï¿½lï¿½ment de la matrice
			int j1 = Integer.parseInt(tabj.get(i));
			double v1 = Double.parseDouble(tabv.get(i));
			listEl.add(new element(i1,j1,v1));
			/*double init = 1.0/n;
                double zero = 0.0;
                pi0.add(init); // initialisation pi0
                pi1.add(zero); //init pi1
		}*/



		Matrice m1 = new Matrice(nbElt, tailleM, tabMatrice);//crï¿½ation matrice 	
		m1.triColonne();

		System.out.println("fin tri");

		//m1.print();

		List<Integer> listeD = m1.tableauIndiceColonneD();

		System.out.println("fin tabIndiceD");
		//List<Integer> listeF = m1.tableauIndiceColonneF();

		//System.out.println("D : " + listeD);
		//System.out.println("F : " +listeF);
		//System.out.println(pi);      
		//System.out.println(listEl);     
		Moteur mot = new Moteur(m1, pi0);
		//m1.print();
		//System.out.println("D : " + listeD);


		//Ajout PROJET
		/*Moteur.modifGraph(listeD, tabnull);
		System.out.println("Taille après modif : " + m1.in.size());
		//System.out.println(tabnull);
		//m1.print();
		m1.triColonne();       
		//m1.print();
		listeD = m1.tableauIndiceColonneD();*/

		int i2;
		for (i2=0;i2<m1.dimension;i2++){        	
			double init = 1.0/tailleM;
			double zero = 0.0;
			pi0.add(init); // initialisation pi0
			pi1.add(zero); //init pi1
		}

		List<Double> F = Moteur.createF(tabnull,m1);
		//System.out.println("tabnull : " + tabnull);
		//System.out.println("Vecteur F : " + F);
		
		Runtime r = Runtime.getRuntime(); 
		System.out.println("mémoire libre après liste elem : " + r.freeMemory()); 
		//liberation memoire ?
				//tabMatrice.clear();
				//System.out.println(m1.in);
				tabMatrice = null;
				//System.out.println(m1.in);
				tabnull.clear();
				//tabnull = null;
				System.gc(); 
				Runtime r1 = Runtime.getRuntime(); 
				System.out.println("mémoire libre après liste elem vide : " + r1.freeMemory()); 

		System.out.println("fin createF");
		System.out.println("taille F : " +F.size());
		System.out.println("taille pi0 : " +pi0.size());
		//List<Double> pi1 = mot.multiplication(listeD, listeF);
		//pi1 = mot.multiplication(pi0, listeD, listeF);
		//System.out.println(pi1);
		//System.out.println(Moteur.normeInf(pi1));

		double ninf = Moteur.normeInf(pi1, pi0);
		int count = 1;

		long startTime1 = System.nanoTime();
		System.out.println("Début convergence");
		System.out.println("taille D" +listeD.size());
		System.out.println("taille M" +m1.dimension);
		//System.out.println(listeD);
		//System.out.println(listeD.get(281730));

		while(ninf > epsilon) {        
			long startTime = System.nanoTime();
			pi1 = mot.multiplication(pi0, F, listeD/*, listeF*/);           
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;
			System.out.println("\nItération pi1 " + count + " : " + (double)elapsedTime/ 1000000000.0 + " seconds");
			count ++;
			//long startTime1 = System.nanoTime();
			ninf = Moteur.normeInf(pi1, pi0);
			//long endTime1 = System.nanoTime();
			//System.out.println("Norme Inf " + count + " : " + (endTime1 - startTime1) + " milliseconds");
			long startTime2 = System.nanoTime();
			pi0 = mot.multiplication(pi1, F, listeD/*, listeF*/);
			long endTime2 = System.nanoTime();
			long elapsedTime2 = endTime2 - startTime2;
			System.out.println("\nItération pi0 " + count + " : " + (double)elapsedTime2/ 1000000000.0 + " seconds");
			count ++;
			//System.out.println(pi1);
			//System.out.println(pi0);
			//ninf = Moteur.normeInf(pi1, pi0);
			System.out.println("normeInf : " + ninf);
		}
		long endTime1 = System.nanoTime();
		long elapsedTime1 = endTime1 - startTime1;


		//System.out.println("Pi1 : " +pi1);
		double sum = 0;
		for(Double d : pi1)
			sum += d;
		System.out.println("Sum : " +sum + " Fin convergence " + (double)elapsedTime1/ 1000000000.0 + " seconds" + " taille pi1 : "+ pi1.size() + " itération : " + (count-1));



	}
}
