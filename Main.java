package version3Projet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Main
{
    public static void main(String[] argv) throws IOException {
    	  	  	
    	//variables 
        String line;
        double epsilon = Math.pow(10, -6); //ESSAYER DE DIMINIUER
        ArrayList<Double> pi0 = new ArrayList<Double>();
        ArrayList<Double> pi1 = new ArrayList<Double>();
        ArrayList<Double> e = new ArrayList<Double>();
        int m; //nb non nul
        int j, n = 0; //n : dimension de la matrice
        ArrayList<String> tabi = new ArrayList<String>(); //tableau ligne
        ArrayList<String> tabj = new ArrayList<String>(); //tableau colonne
        ArrayList<String> tabv = new ArrayList<String>(); //tableau valeur
        ArrayList<String> stringl = new ArrayList<String>(); //tableau de la ligne
        ArrayList<Integer> tabnull = new ArrayList<Integer>(); //tableau des lignes avec degrÃ© 0
        
        //loader
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Emmanuel\\eclipse-workspace\\PageRank\\src\\version3Projet\\Stanford.txt"))) {
            for (int i = 0; i < 1; i++) { //def n
            	n = Integer.parseInt(br.readLine().replaceAll(" ",""));
                e = new ArrayList<Double>(Collections.nCopies(n, 1.0));//initialisation e
            }

            tabj = new ArrayList<String>(n);
            tabi = new ArrayList<String>(n);
            tabv = new ArrayList<String>(n);

            m = Integer.parseInt(br.readLine().replaceAll(" ","")); //def m
            
            int i;
            for (i = 0; i < n; i++) { //parcours txt
                line = br.readLine();
                stringl = new ArrayList<String>(Arrays.asList(line.split(" ")));
                String indice=stringl.get(0);
                if (Integer.parseInt(stringl.get(1)) == 0){
                    tabnull.add(Integer.parseInt(indice));
                }
                for (j = 2; j < stringl.size(); j++) {
                    if (j % 2 == 0) {
                        tabj.add(stringl.get(j));
                        tabi.add(indice);
                    } else {
                        tabv.add(stringl.get(j));
                    }
                }
            }

            /*System.out.println(epsilon);
            System.out.println(n);
            System.out.println(m);
            System.out.println(tabi);
            System.out.println(tabj);
            System.out.println(tabv);*/

            br.close();
            
            System.out.println("fin read");
            
            ArrayList<element> listEl = new ArrayList<element>();
            for (i=0;i<m;i++){        	
            	int i1 = Integer.parseInt(tabi.get(i)); //crï¿½ation liste d'ï¿½lï¿½ment de la matrice
                int j1 = Integer.parseInt(tabj.get(i));
                double v1 = Double.parseDouble(tabv.get(i));
                listEl.add(new element(i1,j1,v1));
                double init = 1.0/n;
                double zero = 0.0;
                pi0.add(init); // initialisation pi0
                pi1.add(zero); //init pi1
            }
            
            Matrice m1 = new Matrice(m, n, listEl);//crï¿½ation matrice 
            m1.triColonne();
            
            System.out.println("fin tri");
            
            //m1.print();
            
            ArrayList<Integer> listeD = m1.tableauIndiceColonneD();
            
            System.out.println("fin tabIndiceD");
            //ArrayList<Integer> listeF = m1.tableauIndiceColonneF();
            
            //System.out.println("D : " + listeD);
            //System.out.println("F : " +listeF);
            //System.out.println(pi);      
            //System.out.println(listEl);     
            Moteur mot = new Moteur(m1, pi0);
            ArrayList<Double> F = Moteur.createF(tabnull,m1);
            
            System.out.println("fin createF");
            
            //ArrayList<Double> pi1 = mot.multiplication(listeD, listeF);
            //pi1 = mot.multiplication(pi0, listeD, listeF);
            //System.out.println(pi1);
            //System.out.println(Moteur.normeInf(pi1));
            
            double ninf = Moteur.normeInf(pi1, pi0);
            int count = 1;
            int tic = 0;
            
            long startTime1 = System.nanoTime();
            System.out.println("Début convergence");
            //System.out.println("taille D" +listeD.size());
            //System.out.println("taille M" +m1.dimension);
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
            //System.out.println("\nFin convergence " + (endTime1 - startTime1) + " milliseconds");
            
            System.out.println("Pi1 : " +pi1);
            double sum = 0;
            for(Double d : pi1)
                sum += d;
            System.out.println("Sum : " +sum + " Fin convergence " + (double)elapsedTime1/ 1000000000.0 + " seconds" + " taille pi1 : "+ pi1.size() + " itération : " + (count-1));
            
            
                      
        }
    }
}