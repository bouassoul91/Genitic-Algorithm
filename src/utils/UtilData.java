/*
 * Copyright (c) 2012, 2013. All rights reserved.
 *  Use is subject to license terms.
 *
 *
 */

package utils;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * @author bouassou
 *
 */
public final class UtilData {

	/**
	 * Block of all needed variables, arrays and objects
	 */

	/**
	 * m : Number of Ambulances n : Number of patients p : Number of Hospitals
	 */

	/**
	 * chromosome : an array containing all patients indexes Note that the index
	 * of the first patient is 'm'
	 */
	private static int[] chromo;

	/**
	 * <p>
	 * A double dimension array (m lines) of integers.
	 * </p>
	 * Each line (0 < k < m) represent an ambulance -index- (first column) and
	 * all the patients that will be delivered to a hospital -index- (last
	 * column) by the ambulance k.
	 * 
	 */
	public static int[][] sol;

	/**
	 * An array which represent all decision variables.
	 */
	public static int[][][] x;

	/**
	 * 
	 */
	private UtilData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns an array of randomly generate integers between 'm' included and
	 * 'm+n' excluded, which represent a chromosome.
	 * <p>
	 * Example : </br>
	 * &nbsp &nbsp &nbsp &nbsp if m = 2 and n = 5 an example of the result will
	 * be [5,2,6,4,3]
	 * </p>
	 * 
	 * @param m
	 *            : Number of the Ambulances
	 * @param n
	 *            : Number of the victims
	 * @return int[] : The randomly generated chromosome
	 */
	public static int[] chromo(int m, int n) {
		ArrayList<Integer> list = new ArrayList<Integer>(n);
		Random rand = new Random();
		UtilData.chromo = new int[n];

		int i;
		for (i = 0; i < n; i++) {
			list.add(i + m);
		}

		i = 0;
		while (list.size() > 0) {
			int index = rand.nextInt(list.size());
			UtilData.chromo[i] = list.get(index);
			list.remove(index);
			i++;
		}

		return UtilData.chromo;
	}

	/**
	 * 
	 * @param chromo
	 * @param sol
	 * @return
	 */
	public static int[] updateChromo(int[] chromo, int[][] sol) {
		int[] newChromo = null;

		for (int i = 0; i < sol.length; i++) {
			newChromo = ArrayUtils.addAll(newChromo, ArrayUtils.subarray(sol[i], 1, sol[i].length - 1));
		}

		for (int i = 0; i < chromo.length; i++) {
			if (!ArrayUtils.contains(newChromo, chromo[i])) {
				newChromo = ArrayUtils.add(newChromo, chromo[i]);
			}
		}

		return newChromo;
	}

	/**
	 * Returns a double dimension array representing the solution.
	 * 
	 * @param m
	 *            : Number of the Ambulances
	 * @param n
	 *            : Number of the victims
	 * @param p
	 *            : Number of hospitals
	 * @param cV
	 *            : An array of -integers- representing the capacity of each
	 *            ambulance.
	 * @param chromo
	 *            : The chromosome.
	 * @return int[][] : The solution array.
	 */
	public static int[][] solutionR(int m, int n, int p, int cV[], int[] chromo) {
		UtilData.sol = new int[m][];
		Random rn = new Random();
		int rdNum = 0, st = 0;

		for (int i = 0; i < UtilData.sol.length; i++) {
			rdNum = rn.nextInt(cV[i] + 1) + 1;
			int tab[] = new int[rdNum + 2];
			int k = i;

			tab[0] = i;
			tab[rdNum + 1] = (m + n) + (int) (Math.random() * p);
			
			for (int j = 0; j < rdNum; j++) {
				if (j + st < chromo.length) {
					tab[j + 1] = chromo[j + st];
				} else {
					tab[j + 1] = (m + n) + (int) (Math.random() * p);
					tab = ArrayUtils.subarray(tab, 0, j + 2);
					j = rdNum + 1;
					i = m + 1;
				}
			}

			if (tab.length > 2) {
				st = st + rdNum;
				UtilData.sol[k] = new int[tab.length];
				UtilData.sol[k] = tab;
			} else {
				st = st + rdNum;
				UtilData.sol[k] = ArrayUtils.subarray(tab, 0, 1);
			}
		}
		
		for (int j = 0; j < m; j++) {
			if(UtilData.sol[j] == null){
				UtilData.sol[j] = ArrayUtils.add(UtilData.sol[j], j);
			}
		}
		
		st = 0;
		return UtilData.sol;
	}

	/**
	 * Returns an array of integers representing the 'current' capacity of all
	 * ambulances.
	 * 
	 * @param m
	 *            : Number of ambulances .
	 * @param sol
	 *            : The solution array.
	 * @return int[] : An array containing the current capacity of all
	 *         ambulance.
	 */
	public static int[] capAmb(int[][] sol) {
		int[] capReAmb = new int[sol.length];
		for (int i = 0; i < capReAmb.length; i++) {
			if (sol[i].length > 2) {
				capReAmb[i] = sol[i].length - 2;
			} else {
				capReAmb[i] = sol[i].length;
			}

		}
		return capReAmb;
	}

	/**
	 * Returns an array representing all variables.
	 * 
	 * @param m
	 *            : Number of the Ambulances
	 * @param n
	 *            : Number of the victims
	 * @param p
	 *            : Number of hospitals
	 * @param sol
	 *            : The solution.
	 * @return int[][][] : The variables array.
	 */
	public static int[][][] Variables(int m, int n, int p, int[][] sol) {

		int i = 0;
		int j = 0;

		UtilData.x = new int[m + n + p][m + n + p][m];

		for (int i1 = 0; i1 < m + n + p; i1++) {
			for (int j1 = 0; j1 < m + n + p; j1++) {
				for (int k1 = 0; k1 < m; k1++) {
					UtilData.x[i1][j1][k1] = 0;
				}
			}
		}

		for (int k = 0; k < sol.length; k++) {
			for (int i1 = 0; i1 < sol[k].length - 1; i1++) {
				if (sol[k].length >= 2) {
					i = sol[k][i1];
					j = sol[k][i1 + 1];
					UtilData.x[i][j][k] = 1;
				}
			}
		}

		return UtilData.x;
	}

	/**
	 * 
	 * @param m
	 *            : Number of the Ambulances
	 * @param n
	 *            : Number of the victims
	 * @param p
	 *            : Number of hospitals
	 * @param x
	 *            : The variables array.
	 * @param t
	 *            : The travel time array.
	 * @param M
	 *            : Weighting parameter.
	 * @return double : The Cost.
	 */
	public static double costFunction(int m, int n, int p, int x[][][], double t[][], int M) {

		double cost = 0;

		for (int i = 0; i < m + n + p; i++) {
			for (int j = 0; j < m + n + p; j++) {
				for (int k = 0; k < m; k++) {
					cost = cost + t[i][j] * x[i][j][k];
				}
			}
		}

		for (int i = 0; i < m + n; i++) {
			for (int j = m; j < m + n; j++) {
				for (int k = 0; k < m; k++) {
					cost = cost - M * x[i][j][k];
				}
			}
		}

		return cost;
	}

	/**
	 * A toSting method to display the chromosome
	 * 
	 * @param chromo
	 *            : Chromosome to display.
	 * @return a String representation of the chromosome -array-, '|| End of
	 *         Individual' if null array input
	 */
	public static String toSting(int[] chromo) {
		String S = "";
		for (int i = 0; i < chromo.length; i++) {
			S = S + "|" + chromo[i];
		}
		S = S + "| End Of Individual \n";
		return S;
	}

	/**
	 * A toSting method to display the solution
	 * 
	 * @param chromo
	 * @return a String representation of the solution -array-, '' if null array
	 *         input
	 */
	public static String toSting(int[][] sol) {
		String S = "";
		for (int i = 0; i < sol.length; i++) {
			S = S + "|";
			for (int j = 0; j < sol[i].length; j++) {
				S = S + sol[i][j] + "|";
			}
			S = S + "\n";
		}
		return S;
	}

	/**
	 * A toSting method to display the variables
	 * 
	 * @param x
	 * @return a String representation of the variables -array-, '' if null
	 *         array input
	 */
	public static String toSting(int[][][] x) {
		String S = "";
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				for (int j2 = 0; j2 < x[i][j].length; j2++) {
					if (x[i][j][j2] == 1)
						S = S + "x[" + i + "]" + "[" + j + "]" + "[" + j2 + "]" + " = " + x[i][j][j2] + "\n";
				}
			}
		}
		return S;
	}

}
