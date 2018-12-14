import java.util.Arrays;

public class HelloWorld {

    public static void main(String[] args) {


    	// complex number creator factory
    	ComplexFactory cf = new ComplexFactory();
    	dftFactory mdftFactory = new dftFactory();

    	Complex[][] idft2_result = cf.create(2,5);



    	// --------------------------------------------------
    	// ONE DIMENSIONAL DFT

    	// create Complex[] of size [5]
    	Complex[] dft_result = cf.create(5);

    	// input data
    	double[] double_vector_1 = {1., 2., 3., 4., 5.};
    	mdftFactory.dft(dft_result, double_vector_1);

    	// DISPLAY DFT RESULT

        System.out.println("--------------------");
        for (int i=0; i<dft_result.length; i++ ) {
	        System.out.print("("+dft_result[i].real+" + "+dft_result[i].imag+"i ) ");
        }
        System.out.print("\n");
        System.out.println("--------------------");


    	// --------------------------------------------------
    	// ONE DIMENSIONAL DFT WITH ZERO PADDING

    	// create Complex[] of size [10]
    	Complex[] dft_result_pad = cf.create(10);

    	// input data
     	double[] double_vector_2 = {1., 2., 3., 4., 5.};
    	mdftFactory.dft(dft_result_pad, double_vector_2);

    	// DISPLAY DFT RESULT

        System.out.println("--------------------");
        for (int i=0; i<dft_result_pad.length; i++ ) {
	        System.out.print("("+dft_result_pad[i].real+" + "+dft_result_pad[i].imag+"i ) ");
        }
        System.out.print("\n");
        System.out.println("--------------------");


    	// --------------------------------------------------
    	// TWO DIMENSIONAL DFT

    	// create Complex[][] of size [2][5]
    	Complex[][] dft2_result  = cf.create(2,5);

    	double[][] matrix_in = { {1., 2., 3, 4, 5.}, {0., 1., 0., 1., 0} };
		mdftFactory.dft2(dft2_result, matrix_in);


		// DISPLAY REAL RESULT
        System.out.println("--------------------");
        for (int i=0; i<dft2_result.length; i++ ) {
        	for (int j=0; j<dft2_result[i].length; j++ ) {
		        System.out.print(" "+dft2_result[i][j].real);
	        }
	        System.out.print("\n");
        }
		// DISPLAY IMAG RESULT
        System.out.println("--------------------");
        for (int i=0; i<dft2_result.length; i++ ) {
        	for (int j=0; j<dft2_result[i].length; j++ ) {
		        System.out.print(" "+dft2_result[i][j].imag);
	        }
	        System.out.print("\n");
        }
        System.out.println("--------------------");
    }
}