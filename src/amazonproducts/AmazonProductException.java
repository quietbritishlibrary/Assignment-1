package amazonproducts;

@SuppressWarnings("serial")
public class AmazonProductException extends Exception {
	
	// I didn't use System.err.print() because it breaks in loops, which may cause issues.
	public AmazonProductException(String errorMessage) {
		super(errorMessage); // Pass the error message to the parent Exception class
	}

}
