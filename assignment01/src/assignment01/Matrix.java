package assignment01;

import java.util.Arrays;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	// Constructor with data for new matrix (automatically determines
	// dimensions)
	public Matrix(int data[][]) {
		numRows = data.length; // d.length is the number of 1D arrays in the 2D
								// array
		if (numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length; // d[0] is the first 1D array
		}
		this.data = new int[numRows][numColumns]; // create a new matrix to hold
													// the data
		// copy the data over

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	@Override // instruct the compiler that we do indeed intend for this method
				// to override the superclass' (Object) version
	public boolean equals(Object other) {
		if (!(other instanceof Matrix)) { // make sure the Object we're
											// comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix) other; // if the above was not true, we know
										// it's safe to treat 'o' as a Matrix

		/*
		 * TODO: replace the below return statement with the correct code, you
		 * must return the correct value after determining if this matrix is
		 * equal to the input matrix
		 */

		int newMatrix[][] = new int[matrix.numRows][matrix.numColumns];
		
		// Checks to see if the 2 2D arrays have the same amount of rows.
		if (data[0].length == newMatrix[0].length) {

			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numColumns; j++) {

					if (data[i][j] != newMatrix[i][j]) {
						return false;
					}

				}

			}

		}

		return true; // placeholder
	}

	@Override // instruct the compiler that we do indeed intend for this method
				// to override the superclass' (Object) version
	public String toString() {
		/*
		 * TODO: replace the below return statement with the correct code, you
		 * must return a String that represents this matrix, as specified on the
		 * assignment page
		 */
		String matrixBuild = "";

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {

				matrixBuild += data[i][j] + " ";
			}
			matrixBuild += "\n";
		}
		return matrixBuild; // placeholder
	}

	public Matrix times(Matrix matrix) {
		/*
		 * TODO: replace the below return statement with the correct code, This
		 * function must check if the two matrices are compatible for
		 * multiplication, if not, return null. If they are compatible,
		 * determine the dimensions of the resulting matrix, and fill it in with
		 * the correct values for matrix multiplication
		 */

		return null; // placeholder
	}

	public Matrix plus(Matrix matrix) {
		/*
		 * TODO: replace the below return statement with the correct code, This
		 * function must check if the two matrices are compatible for addition,
		 * if not, return null. If they are compatible, create a new matrix and
		 * fill it in with the correct values for matrix addition
		 */
		return null; // placeholder
	}
}
