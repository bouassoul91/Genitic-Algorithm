package algorithm;

import java.util.ArrayList;

import data.Indiv;
import utils.UtilData;
import utils.Utils;

public class CrossOver {

	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param cV
	 * @param t
	 * @param M
	 * @param I1
	 * @param I2
	 * @param ind
	 * @return
	 */
	public static ArrayList<Indiv> crossOver(int m, int n, int p, int[] cV, double[][] t, int M, Indiv I1, Indiv I2,
			int ind) {

		ArrayList<Indiv> childs = new ArrayList<Indiv>();

		Indiv C1 = new Indiv(m, n, p);
		Indiv C2 = new Indiv(m, n, p);

		int j = 0;
		for (int i = 0; i < ind; i++) {
			C1.chromo[j] = I1.chromo[i];
			j++;
		}

		for (int i = 0; i < I2.chromo.length; i++) {
			if (!(Utils.hasItem(C1.chromo, I2.chromo[i], ind))) {
				C1.chromo[j] = I2.chromo[i];
				j++;
			}
		}

		C1.sol = UtilData.solutionR(m, n, p, cV, C1.chromo);
		C1.capReAmb = UtilData.capAmb(C1.sol);
		C1.x = UtilData.Variables(m, n, p, C1.sol);
		C1.cost = UtilData.costFunction(m, n, p, C1.x, t, M);
		childs.add(C1);

		j = 0;
		for (int i = 0; i < ind; i++) {
			C2.chromo[j] = I2.chromo[i];
			j++;
		}

		for (int i = 0; i < I1.chromo.length; i++) {
			if (!(Utils.hasItem(C2.chromo, I1.chromo[i], ind))) {
				C2.chromo[j] = I1.chromo[i];
				j++;
			}
		}

		C2.sol = UtilData.solutionR(m, n, p, cV, C2.chromo);
		C2.capReAmb = UtilData.capAmb(C2.sol);
		C2.x = UtilData.Variables(m, n, p, C2.sol);
		C2.cost = UtilData.costFunction(m, n, p, C2.x, t, M);
		childs.add(C2);

		return childs;
	}

	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param cV
	 * @param t
	 * @param M
	 * @param I1
	 * @param I2
	 * @param ind1
	 * @param ind2
	 * @return
	 */
	public static ArrayList<Indiv> crossOver(int m, int n, int p, int[] cV, double[][] t, int M, Indiv I1, Indiv I2,
			int ind1, int ind2) {

		ArrayList<Indiv> childs = new ArrayList<Indiv>();

		Indiv C1 = new Indiv(m, n, p);
		Indiv C2 = new Indiv(m, n, p);

		int j = 0;
		for (int i = 0; i < ind1; i++) {
			C1.chromo[j] = I1.chromo[i];
			j++;
		}

		for (int i = ind2; i < I1.chromo.length; i++) {
			C1.chromo[i] = I1.chromo[i];
		}

		for (int i = 0; i < I2.chromo.length; i++) {
			if (!(Utils.hasItem(C1.chromo, I2.chromo[i], I2.chromo.length))) {
				C1.chromo[j] = I2.chromo[i];
				j++;
			}
		}

		C1.sol = UtilData.solutionR(m, n, p, cV, C1.chromo);
		C1.capReAmb = UtilData.capAmb(C1.sol);
		C1.x = UtilData.Variables(m, n, p, C1.sol);
		C1.cost = UtilData.costFunction(m, n, p, C1.x, t, M);
		childs.add(C1);

		j = 0;
		for (int i = 0; i < ind1; i++) {
			C2.chromo[j] = I2.chromo[i];
			j++;
		}

		for (int i = ind2; i < I2.chromo.length; i++) {
			C2.chromo[i] = I2.chromo[i];
		}

		for (int i = 0; i < I1.chromo.length; i++) {
			if (!(Utils.hasItem(C2.chromo, I1.chromo[i], I2.chromo.length))) {
				C2.chromo[j] = I1.chromo[i];
				j++;
			}
		}

		C2.sol = UtilData.solutionR(m, n, p, cV, C2.chromo);
		C2.capReAmb = UtilData.capAmb(C2.sol);
		C2.x = UtilData.Variables(m, n, p, C2.sol);
		C2.cost = UtilData.costFunction(m, n, p, C2.x, t, M);
		childs.add(C2);

		return childs;
	}

	private CrossOver() {
		// TODO Auto-generated constructor stub
	}

}
