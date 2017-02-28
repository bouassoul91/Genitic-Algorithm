package algorithm;

import java.util.Random;

import data.Indiv;
import utils.UtilData;

public class Mutation {

	private Mutation() {
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
	 * @param I1
	 * @return
	 */
	public static Indiv Mutiation2p(int m, int n, int p, int[] cV, double[][] t, int M, Indiv I1){
		Random r = new Random();
		int p1, p2, tmp;
		
		p1 = r.nextInt(n);
		do{
			p2 = r.nextInt(n);
		}while(p1 == p2);
		
		tmp = I1.chromo[p1];
		I1.chromo[p1] = I1.chromo[p2];
		I1.chromo[p2] = tmp;

		I1.sol = UtilData.solutionR(m, n, p, cV, I1.chromo);
		I1.capReAmb = UtilData.capAmb(I1.sol);
		I1.x = UtilData.Variables(m, n, p, I1.sol);
		I1.cost = UtilData.costFunction(m, n, p, I1.x, t, M);
		
		return I1;
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
	 * @return
	 */
	public static Indiv Mutiation3p(int m, int n, int p, int[] cV, double[][] t, int M, Indiv I1){
		Random r = new Random();
		int p1, p2, p3, tmp1, tmp2;
		
		p1 = r.nextInt(n);
		do{
			p2 = r.nextInt(n);
			p3 = r.nextInt(n);
		}while(p1 == p2 || p1 == p3 || p2 == p3);
		
		
		tmp1 = I1.chromo[p1];
		tmp2 = I1.chromo[p2];
		I1.chromo[p1] = I1.chromo[p3];
		I1.chromo[p2] = tmp1;
		I1.chromo[p3] = tmp2;
		
		I1.sol = UtilData.solutionR(m, n, p, cV, I1.chromo);
		I1.capReAmb = UtilData.capAmb(I1.sol);
		I1.x = UtilData.Variables(m, n, p, I1.sol);
		I1.cost = UtilData.costFunction(m, n, p, I1.x, t, M);
		
		return I1;
	}

}
