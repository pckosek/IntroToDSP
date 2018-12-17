import java.lang.Math;

public class dftFactory {

	public final static double TWOPI = 6.283185307179586476925287;


	public dftFactory() {
	}

    public void dft(Complex[] fft_out, double[] real_in) {

    	// determine the size of the FFT
    	int N = fft_out.length;


    	// pre compute cos and sin wavetables
    	//  	(so we don't have to calculate them each step of the operation)
    	double[] cos_data = new double[N];
    	double[] sin_data = new double[N];

    	double t;
    	for (int i=0; i<N; i++) {
    		t = TWOPI*i/N;
    		cos_data[i] = Math.cos(t);
    		sin_data[i] = Math.sin(t);
    	}

    	int indx;
		for (int k=0; k<N; k++) {

			// reset current output to zero
			fft_out[k].real = 0;
			fft_out[k].imag = 0;

			// iterate over waveform
			indx = 0;
			for (int n=0; n < real_in.length; n++) {
				fft_out[k].real += real_in[n] * cos_data[indx];
				fft_out[k].imag -= real_in[n] * sin_data[indx];

				indx += k;
				indx %= N;
			}
    	}
    }


    public void dft2(Complex[][] fft_out, double[][] real_in) {

    	// 2-D DFT IS A TWO STEP PROCESS. 
    	//  - STEP 1. DFT COLUMNS
    	//  - STEP 2. DFT ROWS OF DFT'd COLUMNS

    	// -----------------------------------------------------
    	// - PART 1, DFT THE COLUMNS

    	// determine the size of the 2-D DFT
    	int N_rows = fft_out.length;
    	int N_cols = fft_out[0].length;

    	int input_rows = real_in.length;
    	int input_cols = real_in[0].length;

    	// pre compute cos and sin data
    	double[] cos_data = new double[N_rows];
    	double[] sin_data = new double[N_rows];

    	double t;
    	for (int i=0; i<N_rows; i++) {
    		t = TWOPI*i/N_rows;
    		cos_data[i] = Math.cos(t);
    		sin_data[i] = Math.sin(t);
    	}

    	// take a dft of each column
    	for (int column_no=0; column_no<input_cols; column_no++) {
    		
	    	int indx;
			for (int k=0; k<N_rows; k++) {

				// reset current output to zero
				fft_out[k][column_no].real = 0;
				fft_out[k][column_no].imag = 0;

				// iterate over waveform
				indx = 0;
				for (int n=0; n < input_rows; n++) {

					fft_out[k][column_no].real += real_in[n][column_no] * cos_data[indx];
					fft_out[k][column_no].imag -= real_in[n][column_no] * sin_data[indx];

					indx += k;
					indx %= N_rows;
				}
	    	}
	    }

    	// -----------------------------------------------------
    	// - PART 2, DFT THE ROWS

    	// pre compute cos and sin data
    	cos_data = new double[N_cols];
    	sin_data = new double[N_cols];

    	for (int i=0; i<N_cols; i++) {
    		t = TWOPI*i/N_cols;
    		cos_data[i] = Math.cos(t);
    		sin_data[i] = Math.sin(t);
    	}

    	// temporary placeholder
    	double[] temp_real_row = new double[N_cols];
    	double[] temp_imag_row = new double[N_cols];

    	// take a dft of each row
    	for (int row_no=0; row_no<N_rows; row_no++) {
    		

	    	int indx;
			for (int k=0; k<N_cols; k++) {

				// reset current output to zero
				temp_real_row[k] = 0;
				temp_imag_row[k] = 0;

				// iterate over waveform
				indx = 0;
				for (int n=0; n < N_cols; n++) {
	
					temp_real_row[k] += fft_out[row_no][n].real * cos_data[indx] - fft_out[row_no][n].imag * sin_data[indx];
					temp_imag_row[k] -= (fft_out[row_no][n].real * sin_data[indx] + fft_out[row_no][n].imag * cos_data[indx]);

					indx += k;
					indx %= N_cols;
				}

	    	}

			for (int k=0; k<N_cols; k++) {
				fft_out[row_no][k].real = temp_real_row[k];
				fft_out[row_no][k].imag = temp_imag_row[k];
			}
	    }
	}    


    public void idft2(Complex[][] Complex_out, Complex[][] Complex_in) {

    	// 2-D DFT IS A TWO STEP PROCESS. 
    	//  - STEP 1. DFT COLUMNS
    	//  - STEP 2. DFT ROWS OF DFT'd COLUMNS

    	// -----------------------------------------------------
    	// - PART 1, DFT THE COLUMNS

    	// determine the size of the 2-D DFT
    	int N_rows = Complex_in.length;
    	int N_cols = Complex_in[0].length;

    	int input_rows = N_rows;
    	int input_cols = N_cols;

    	// idft's require scaling
    	double scale_factor = 1./(N_rows * N_cols);


    	// pre compute cos and sin wave tables
    	double[] cos_data = new double[N_rows];
    	double[] sin_data = new double[N_rows];

    	double t;
    	for (int i=0; i<N_rows; i++) {
    		t = TWOPI*i/N_rows;
    		cos_data[i] = Math.cos(t);
    		sin_data[i] = Math.sin(t);
    	}

    	// take a dft of each column
    	for (int column_no=0; column_no<input_cols; column_no++) {
    		
	    	int indx;
			for (int k=0; k<N_rows; k++) {

				// reset current output to zero
				Complex_out[k][column_no].real = 0;
				Complex_out[k][column_no].imag = 0;

				// iterate over waveform
				indx = 0;
				for (int n=0; n < input_rows; n++) {

					Complex_out[k][column_no].real += Complex_in[n][column_no].real * cos_data[indx] + Complex_in[n][column_no].imag * sin_data[indx];
					Complex_out[k][column_no].imag += Complex_in[n][column_no].real * sin_data[indx] - Complex_in[n][column_no].imag * cos_data[indx];

					indx += k;
					indx %= N_rows;
				}
	    	}
	    }

    	// -----------------------------------------------------
    	// - PART 2, DFT THE ROWS

    	// pre compute cos and sin data
    	cos_data = new double[N_cols];
    	sin_data = new double[N_cols];

    	for (int i=0; i<N_cols; i++) {
    		t = TWOPI*i/N_cols;
    		cos_data[i] = Math.cos(t);
    		sin_data[i] = Math.sin(t);
    	}

    	// temporary placeholder
    	double[] temp_real_row = new double[N_cols];
    	double[] temp_imag_row = new double[N_cols];

    	// take a dft of each row
    	for (int row_no=0; row_no<N_rows; row_no++) {
    		

	    	int indx;
			for (int k=0; k<N_cols; k++) {

				// reset current output to zero
				temp_real_row[k] = 0;
				temp_imag_row[k] = 0;

				// iterate over waveform
				indx = 0;
				for (int n=0; n < N_cols; n++) {
	
					temp_real_row[k] += scale_factor*(Complex_out[row_no][n].real * cos_data[indx] + Complex_out[row_no][n].imag * sin_data[indx]);
					temp_imag_row[k] += scale_factor*(Complex_out[row_no][n].real * sin_data[indx] - Complex_out[row_no][n].imag * cos_data[indx]);

					indx += k;
					indx %= N_cols;
				}

	    	}

			for (int k=0; k<N_cols; k++) {
				Complex_out[row_no][k].real = temp_real_row[k];
				Complex_out[row_no][k].imag = temp_imag_row[k];
			}
	    }
	}    
}