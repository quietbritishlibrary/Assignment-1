package amazonproducts;

public class AmazonProductCategory {
	
	//property
	private String categoryName;
	
	
	//no-arg constructor
	public AmazonProductCategory() {}
	

	// parameterized constructor
	public AmazonProductCategory(String myCategory) {
		categoryName = myCategory;
	}
	
	//getter
	public String getCategory() {
		return categoryName;
	}
	
	//setter
	public void setCategory(String myCategory) {
		categoryName = myCategory;
	}
	

}
