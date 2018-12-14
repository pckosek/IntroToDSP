public class ComplexFactory {

	public ComplexFactory() {
	}

	public Complex[] create(int nRows) {
		Complex[] vector_out = new Complex[nRows];
		for (int i=0; i<nRows; i++ ) {
			vector_out[i] = new Complex();
		}
		return vector_out;
	}

	public Complex[][] create(int nRows, int nCols) {
		Complex[][] matrix_out = new Complex[nRows][nCols];
		for (int i=0; i<nRows; i++ ) {
			matrix_out[i] = this.create(nCols);
		}
		return matrix_out;
	}

}