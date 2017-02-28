/*
 * Copyright (c) 2012, 2013. All rights reserved.
 *  Use is subject to license terms.
 *
 *
 */

package data;

import java.util.ArrayList;

/**
 * 
 * @author bouassou
 *
 */
public class Popul {

	/**
	 * An integer defining the size of the population.
	 */
	int pSize;
	
	/**
	 * An arrayList containing all the individuals of the population.
	 */
	public ArrayList<Indiv> Indivs;
	
	/**
	 * 
	 */
	public Popul() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param m
	 * @param n
	 * @param p
	 * @param cV
	 * @param t
	 * @param M
	 * @param pSize
	 */
	public Popul(int m, int n, int p, int[] cV, double[][] t, int M, int pSize) {
		this.pSize = pSize;
		
		this.Indivs = new ArrayList<Indiv>(pSize);
		for (int i = 0; i < pSize; i++) {
			this.Indivs.add(new Indiv(m, n, p, cV, t, M));
		}
	}
	
}
