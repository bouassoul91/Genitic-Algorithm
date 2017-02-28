package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import data.Indiv;

public class CnW {

	public static Map<Pair<Integer, Integer>, ArrayList<Integer>> list;

	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 */
	private CnW(int m, int n, int p) {
		// super();
		list = new HashMap<Pair<Integer, Integer>, ArrayList<Integer>>(); 
	}

	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param I
	 * @param t
	 */
	public static void assignToClosest(int m, int n, int p, Indiv I, double[][] t) {
		int closeAmb, closeHops;
		for (int i = 0; i < I.chromo.length; i++) {
			closeAmb = CnW.getNearestAmb(m, i, t);
			closeHops = CnW.getNearestHosp(m, n, p, i, t);

			Pair<Integer, Integer> P = new Pair<Integer, Integer>(closeAmb, closeHops);

			//on verifie si il existe deja une victime qui est deja affecté au couple (CloseAmb,CloseHosp)
			//si oui ce victime est affecté à ce couple qui existe deja
			//sinon il ny a pas de vict affecte a ce couple et donc 
			//on crée une nouvelle list de vict qui seront affecte a ce couple
			if (CnW.comparePairs(P, CnW.list)) {
				CnW.list.get(P).add(i);
			} else {
				CnW.list.put(P, new ArrayList<Integer>(i));
			}
		}
	}

	/**
	 * 
	 * @param P
	 * @param list
	 * @param t
	 * @return
	 */
	public static double[][] computeSavings(Pair<Integer, Integer> P, ArrayList<Integer> list, double [][] t){
		
		double [][] savings = new double[list.size()][list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				savings[i][j] = CnW.computeS(list.get(i), list.get(j), P.getT(), P.getU(), t);
				savings[j][i] = savings[i][j];
			}
		}
		return savings;
	}
	
	/**
	 * 
	 * @param savings
	 * @param listVict
	 * @return
	 */
	public static ArrayList<Pair<Integer, Integer>> getClassification(double[][] savings, ArrayList<Integer> listVict){
		double [][] t = savings;
		double max = 0;
		ArrayList<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
		
		Pair<Integer, Integer> P = new Pair<Integer, Integer>(0,0);
		
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t.length; j++) {
				if(t[i][j] > max){
					max = t[i][j];
					P.setT(listVict.get(i));
					P.setU(listVict.get(j));
				}
			}
		}
		
		list.add(P);
		t[P.getT()][P.getU()] = 0;
		//cest la liste classifiee
		return list;
	}
	
	public static void getRouts(Pair<Integer, Integer> P, ArrayList<Pair<Integer, Integer>> list){
		int [][] sol = null;
		for(int i = 0; i< list.size(); i++){
			
		}
	}
	
	public static void addPairs(int[][] sol, Pair<Integer, Integer> P){
		
	}
	
	/**
	 * 
	 * @param P
	 * @param r
	 * @return
	 */
	public static int communNodes(Pair<Integer, Integer> P, int[] r){
		int ret = 0;
		for (int i = 0; i < r.length; i++) {
			if(P.getT() == r[i] && P.getU() == r[i]){
				ret = 2;
			}else if(P.getT() == r[i]){
				ret = 1;
			}else if(P.getU() == r[i]){
				ret = 1;
			}else{
				ret = 0;
			}
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @param m
	 * @param vict
	 * @param t
	 * @return
	 */
	public static int getNearestAmb(int m, int vict, double[][] t) {

		int index = 0;
		double min = 0;

		for (int i = 0; i < m; i++) {
			if (min > t[i][vict]) {
				min = t[i][vict];
				index = i;
			}
		}

		return index;
	}

	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param vict
	 * @param t
	 * @return
	 */
	public static int getNearestHosp(int m, int n, int p, int vict, double[][] t) {

		int index = 0;
		double min = 0;

		for (int h = 0; h < p; h++) {
			if (min > t[vict][m + n + h]) {
				min = t[vict][m + n + h];
				index = m + n + h;
			}
		}

		return index;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param h
	 * @param t
	 * @return
	 */
	//calcule saving pour un seul victime determine
	public static double computeS(int i, int j, int k, int h, double[][] t) {
		double s = 0;

		s = t[i][h] + t[h][j] - t[i][j];

		return s;
	}

	/**
	 * 
	 * @param P
	 * @param list
	 * @return
	 */
	//on com
	@SuppressWarnings("unchecked")
	public static boolean comparePairs(Pair<Integer, Integer> P, Map<Pair<Integer, Integer>, ArrayList<Integer>> list){
		boolean bool = false;
		
		for (int i = 0; i < list.size(); i++) {
			if(P.compareTo((Pair<Integer, Integer>) list.keySet().toArray()[i])){
				bool = true;
			}
		}
		
		return bool;
	}
}
