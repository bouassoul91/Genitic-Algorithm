/*
 * Copyright (c) 2012, 2013. All rights reserved.
 *  Use is subject to license terms.
 *
 *
 */

package mainTest;

import java.io.*;
import java.util.Scanner;

import algorithm.CrossOver;
import data.Indiv;
//import data.Indiv;
import data.Popul;
import utils.UtilDataVerif;

public class TestCrossOver2Index {

	static int[] chromo;
	static int[][] sol;
	static int[][][] x;

	public TestCrossOver2Index() {
		// TODO Auto-generated constructor stub
	}

	static void fun(File f, File f1) throws IOException {
		int cV[] = { 4, 3, 2, 4, 5 };

		int m = 4, n = 9, p = 2;
		int M = 100;
		
		double[] start = new double[m];
		double[] dig = new double[n + m + p];

		int INFINITY = 10000000;
		double t[][]; // cost (time or distance)
		t = new double[m + n + p][m + n + p];
		for (int i1 = 0; i1 < m + n + p; i1++)
			// set adjacency
			for (int j = 0; j < m + n + p; j++)
				// matrix
				t[i1][j] = INFINITY; // to infinity

		// xPos et yPos de Amb and Vict and Hosp
		double xPos[] = new double[m + n + p];
		double yPos[] = new double[m + n + p];

		xPos[0] = 70.31782782869787;
		yPos[0] = 92.36814490882759;
		xPos[1] = 63.52378695115027;
		yPos[1] = 79.9714674936928;
		xPos[2] = 55.87776275489126;
		yPos[2] = 16.50123689417171;
		xPos[3] = 97.24805313144223;
		yPos[3] = 84.1595918890261;
		xPos[4] = 66.23234142247216;
		yPos[4] = 18.84988065798171;
		xPos[5] = 86.34113115195;
		yPos[5] = 67.73265432482248;
		xPos[6] = 27.361562495205682;
		yPos[6] = 61.86788617172467;
		xPos[7] = 95.91276889357137;
		yPos[7] = 43.732857104787406;
		xPos[8] = 3.982202080982933;
		yPos[8] = 23.344062882985817;
		xPos[9] = 51.61426618757289;
		yPos[9] = 9.670337026412657;
		xPos[10] = 95.00600776618002;
		yPos[10] = 61.256745116679625;
		xPos[11] = 95.70960416737209;
		yPos[11] = 45.801362890668806;
		xPos[12] = 63.670538568511944;
		yPos[12] = 81.21860231137337;
		xPos[13] = 46.22893198176511;
		yPos[13] = 27.924588054981847;
		xPos[14] = 58.3271578549869;
		yPos[14] = 5.196402800343258;
		
		double[] deadline = new double[n];
		for (int i = 0; i < n; i++) {
			deadline[i] = 100000000;
		}
		// deadline[1] = 0;
		deadline[4] = 0;
		deadline[3] = 0;
		deadline[5] = 0;
		//deadline[7] = 0;
		//deadline[8] = 0;
		//deadline[9] = 0;
		//deadline[10] = 0;

		// compute cost for the arcs that exists in the graph (Amb x Vict) and
		// (Vict x Vict) and (Vict x Hosp) and (Hosp x Vict)
		// Amb x Vict
		for (int i1 = 0; i1 < m; i1++) {
			for (int j = m; j < m + n; j++) {
				if (i1 != j) {
					t[i1][j] = Math.sqrt(Math.pow(xPos[i1] - xPos[j], 2) + Math.pow(yPos[i1] - yPos[j], 2));
				}
			}
		}
		// System.out.println();
		// Vict x Vict
		for (int i1 = m; i1 < m + n; i1++) {
			for (int j = m; j < m + n; j++) {
				if (i1 != j) {
					t[i1][j] = Math.sqrt(Math.pow(xPos[i1] - xPos[j], 2) + Math.pow(yPos[i1] - yPos[j], 2));
				}
			}
		}

		// System.out.println();
		// Vict x Hosp
		for (int i1 = m; i1 < m + n; i1++) {
			for (int j = m + n; j < m + n + p; j++) {
				if (i1 != j) {
					t[i1][j] = Math.sqrt(Math.pow(xPos[i1] - xPos[j], 2) + Math.pow(yPos[i1] - yPos[j], 2));
				}
			}
		}

		Popul pop = new Popul(m, n, p, cV, t, M, 2);
		Popul newGenrartion = new Popul(m, n, p, cV, t, M, 4);
		String S = "";
		for (int j = 0; j < pop.Indivs.size(); j++) {
			S = S + pop.Indivs.get(j).toString();
		}
		
		FileWriter fw = new FileWriter(f);

		fw.write(S);
		fw.flush();
		fw.close();

		//Scanner in = new Scanner(System.in); 
		//int readLineInteger = in.nextInt();
		
		int cH[] = { 3, 5 };
		
		int ind = 3;
		int ind1 = 6;
		newGenrartion.Indivs = CrossOver.crossOver(m, n, p, cV, t, M, pop.Indivs.get(0), pop.Indivs.get(1), ind, ind1);
		
		S = "";
		for (int j = 0; j < newGenrartion.Indivs.size(); j++) {
			//pop.Indivs.set(j, UtildataVerif.hospConst(m, n, p, t, M, cH, pop.Indivs.get(j)));
			S = S + newGenrartion.Indivs.get(j).toString();
			//System.out.println();
		}

		fw = new FileWriter(f1);

		fw.write(S);
		fw.flush();
		fw.close();
		System.out.println("Finished with success");
	}

	public static void main(String[] args) throws IOException {
		File fData = new File("data.txt");
		File fDataCorr = new File("dataCorr.txt");

		fun(fData, fDataCorr);
	}

}
