/*
  * Copyright (c) 2012, 2013. All rights reserved.
 *  Use is subject to license terms.
 *
 *
 */

package data;

import utils.UtilData;

/**
 * 
 * @author Bouassoul Badar
 *
 */
public class Indiv {

	/**
	 * Each Individual should have four attributes :
	 * 1. Chromosome (chromo).
	 * 2. Solution (sol). 
	 * 3. Variables (x).
	 * 4. Cost (cost)
	 * 
	 */
	
	public int[] chromo;
	public int[] capReAmb;
	public int[][] sol;
	public int[][][] x;
	public double cost;

	/**
	 * 
	 */
	public Indiv(){
		super();
	}
	
	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 */
	public Indiv(int m, int n, int p) {
		this.chromo = new int[n];
		this.sol = new int[m][n];
		this.capReAmb = new int[m];
		this.x = new int[m + n + p][m + n + p][m];
	}

	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param cV
	 * @param t
	 * @param M
	 */
	public Indiv(int m, int n, int p, int[] cV, double[][] t, int M) {
		this.chromo = UtilData.chromo(m, n);
		this.sol = UtilData.solutionR(m, n, p, cV, chromo);
		this.capReAmb = UtilData.capAmb(this.sol);
		this.x = UtilData.Variables(m, n, p, this.sol);
		this.cost = UtilData.costFunction(m, n, p, x, t, M);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString(){
		String S ="";
		S ="------------------Chromosome------------------ \n";
		S = S + UtilData.toSting(this.chromo) + "\n-------------------Solution------------------\n";
		S = S + UtilData.toSting(this.sol) + "\n-------------------Variables------------------\n";
		S = S + UtilData.toSting(this.x) + "\n-------------------cost------------------\n";
		S = S + this.cost + "\n \n \n";
		return S;
	}
}
