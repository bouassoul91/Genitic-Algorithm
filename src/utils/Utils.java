package utils;

public class Utils {

	private Utils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * A -Help- method to get the maximum of an array
	 * 
	 * @param numbers
	 * @return
	 */
	public static int getIntOfMin(double[] numbers) {
		double min = numbers[0];
		int minInd = 0;

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] < min) {
				min = numbers[i];
				minInd = i;
			}
		}

		return minInd;
	}

	/**
	 * 
	 * @param indexs
	 * @param valuesArray
	 * @return
	 */
	public static double[] getDoubleValues(int m, int[] indexs, double[] valuesArray) {
		double[] vArray = new double[indexs.length];
		for (int i = 0; i < indexs.length; i++) {
			vArray[i] = valuesArray[indexs[i]];
		}

		return vArray;
	}

	/**
	 * 
	 * @param indexRange
	 * @param valuesRange
	 * @return
	 */
	public static int getMinInRange(int m, int[] indexRange, double[] valuesRange) {
		double min = 0;
		int minIndex = 0;
		for (int d : indexRange) {
			if (valuesRange[d - m] < min) {
				min = valuesRange[d - m];
				minIndex = d;
			}
		}
		return minIndex;
	}

	/**
	 * 
	 * @param array
	 * @param element
	 * @param endIndex
	 * @return
	 */
	public static boolean hasItem(int array[], int element, int endIndex) {
		boolean found = false;

		for (int i = 0; i < endIndex; i++) {
			if (array[i] == element) {
				found = true;
			}
		}
		return found;
	}
}
