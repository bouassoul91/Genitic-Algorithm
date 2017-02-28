package utils;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import data.Indiv;

/**
 * 
 * @author bouassou
 *
 */
public class UtilDataVerif {

	/**
	 * This class can not be instanced
	 */
	private UtilDataVerif() {
		// TODO Auto-generated constructor stub
	}

	// **********Utils to correct Hosp const*********
	/**
	 * A function to correct all constraints related to hospital capacities.
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param I
	 * @param cH
	 * @return
	 */
	public static Indiv hospConst(int m, int n, int p, double[][] t, int M, int[] cH, Indiv I) {

		int[] capRehosp = new int[p];

		for (int i = 0; i < p; i++) {
			int[] listAmbHospi = new int[m];
			//int iter = 0;
			for (int j = 0; j < m; j++) {
				if (m + n + i == I.sol[j][I.sol[j].length - 1]) {
					capRehosp[i] = capRehosp[i] + I.capReAmb[j];
					listAmbHospi[j] = i + m + n;
					//iter++;
				}
			}

			//while (ArrayUtils.contains(listAmbHospi, 0)) {
			//	listAmbHospi = ArrayUtils.removeElement(listAmbHospi, 0);
			//}

			if (capRehosp[i] > cH[i]) {
				int diff = capRehosp[i] - cH[i];
				I.sol = UtilDataVerif.removeVict(diff, listAmbHospi, I.sol);
			}
		}

		return UtilDataVerif.updateIndiv(m, n, p, t, M, I);
	}

	/**
	 * 
	 * @param diff
	 * @param listAmbHospi
	 * @param sol
	 * @return
	 */
	public static int[][] removeVict(int diff, int[] listAmbHospi, int[][] sol) {
		Random rn = new Random();
		for (int i = 0; i < diff; i++) {
			int rdAmbu;
			do {
				rdAmbu = rn.nextInt(listAmbHospi.length);
			} while (sol[rdAmbu].length <= 2 || listAmbHospi[rdAmbu] == 0);
			
			int rdVict = rn.nextInt(sol[rdAmbu].length - 2) + 1;
			sol[rdAmbu] = ArrayUtils.remove(sol[rdAmbu], rdVict);

			if (sol[rdAmbu].length <= 2) {
				sol[rdAmbu] = ArrayUtils.subarray(sol[rdAmbu], 0, 1);
			}
		}
		return sol;
	}

	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param t
	 * @param M
	 * @param I
	 * @return
	 */
	public static Indiv updateIndiv(int m, int n, int p, double[][] t, int M, Indiv I) {
		I.chromo = UtilData.updateChromo(I.chromo, I.sol);
		I.capReAmb = UtilData.capAmb(I.sol);
		I.x = UtilData.Variables(m, n, p, I.sol);
		I.cost = UtilData.costFunction(m, n, p, I.x, t, M);

		return I;
	}

	// **********Utils to correct Deadline const*********
	public static Indiv deadlineConst(int m, int n, int p, int M, double start[], double t[][], double dig[],
			double deadline[], Indiv I) {
		double sum, num = 0;
		for (int k = 0; k < m; k++) {
			for (int j1 = m; j1 < m + n; j1++) {
				num = 0;
				sum = start[k]; // The total Time of each Amb

				for (int i = 0; i < m + n + p; i++) {
					if (i < m + n && I.x[i][j1][k] == 1) {
						num = num + I.x[i][j1][k];
					}
					for (int j = 0; j < m + n + p; j++) {
						if (I.x[i][j][k] == 1) {
							if (i < m + n && j < m + n && m <= j) {
								sum = sum + dig[j] * I.x[i][j][k];
							}
							sum = sum + t[i][j] * I.x[i][j][k];
						}
					}
				}

				if (num == 1 && sum > deadline[j1 - m]) {
					I.sol = UtilDataVerif.removeVict(m, k, I, deadline);
					I = UtilDataVerif.updateIndiv(m, n, p, t, M, I);
					//k--;
				}
			}
		}
		return I;
	}

	public static int[][] removeVict(int m, int ambK, Indiv I, double[] deadline) {

		int minIndex = Utils.getMinInRange(m, ArrayUtils.subarray(I.sol[ambK], 1, I.sol[ambK].length -1), deadline);
		I.sol[ambK] = ArrayUtils.remove(I.sol[ambK], minIndex);

		return I.sol;
	}

}
