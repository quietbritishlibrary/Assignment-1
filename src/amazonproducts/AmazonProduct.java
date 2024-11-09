package amazonproducts;

public class AmazonProduct {
	
	//field
	private int id;
	private String name;
	AmazonProductCategory category;
	AmazonProductSubCategory subCategory;
	private String imageURL;
	private String link;
	private float rating;
	private int nRatings;
	private float discountPrice;
	private float actualPrice;
	private String[] title;
	
	
	
	// parameterized constructor
	public AmazonProduct(int myId, String myName, AmazonProductCategory myCategory, AmazonProductSubCategory mySubCategory, String myImageURL, String myLink, float myRating, int my_nRating, float myDiscountPrice, float myActualPrice ) {
		id = myId;
		name = myName;
		category = myCategory;
		subCategory = mySubCategory;
		imageURL =  myImageURL;
		link = myLink;
		rating = myRating;
		nRatings = my_nRating;
		discountPrice = myDiscountPrice;
		actualPrice= myActualPrice;
		
	};
	
	// no-arg constructor
	public AmazonProduct() {}

	
	// constructor used to assign substrings to appropriately parsed AmazonProduct property
	public AmazonProduct(String [] data) {
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
		this.category = new AmazonProductCategory (data[2]);
		this.subCategory = new AmazonProductSubCategory(data[3],category);
		this.imageURL = data[4];
		this.link = data[5];
		this.rating = AmazonProductUtil.convertStrToFloat(data[6]);
		this.nRatings = Integer.parseInt(data[7].replace(",", ""));
		this.discountPrice = AmazonProductUtil.convertStrToFloat(data[8]);
		this.actualPrice = AmazonProductUtil.convertStrToFloat(data[9]);
	}
	
	//formats appropriate string to display AmazonProduct data
	@Override
	public String toString () {
		String report = "";
				
		report = "[" + id + ", " + name + ", " + category.getCategory() + ", " + subCategory.getSubCategory() + ", " + imageURL  + ", " + link + ", " + rating + ", " + nRatings + ", " + discountPrice + ", " + actualPrice + "]";
		return report;
	}
	
	// toString method specialized for CSV format
	public String toCSV () {
		String report = "";
				
		report = "\"" + id + "\",\"" + name + "\",\"" + 
		           category.getCategory() + "\",\"" + 
		           subCategory.getSubCategory() + "\",\"" + 
		           imageURL + "\",\"" + link + "\",\"" + 
		           rating + "\",\"" + nRatings + "\",\"" + 
		           discountPrice + "\",\"" + actualPrice + "\"";		
		           return report;
	}
	
	
	// getters
	
	public float getActual_Price() {
		return actualPrice;
	}
	
	public float getDiscount_Price() {
		return discountPrice;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLink() {
		return link;
	}
	
	public AmazonProductCategory getMain_category() {
		return category;
	}
	
	public AmazonProductSubCategory getSub_category() {
		return subCategory;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getTitle() {
		return title;
	}
	
	public int getNo_of_ratings() {
		return nRatings;
	}
	
	public float getRatings() {
		return rating;
	}
	
	public String getUrl_Image() {
		return imageURL;
	}
	
    //setters 
	
	public void setActual_Price(float myActualPrice) {
		actualPrice = myActualPrice;
	}
	
	public void setDiscount_Price(float myDiscountPrice) {
		discountPrice = myDiscountPrice;
	}
	
	public void setId( int myId) {
		id = myId;
	}
	
	public void setLink(String myLink) {
		link = myLink;
	}
	
	public void setMain_category(AmazonProductCategory myCategory) {
		category = myCategory;
	}
	
	public void setSub_category(AmazonProductSubCategory mySubCategory) {
		subCategory = mySubCategory;
	}
	
	public void setName(String myName) {
		 name = myName;
	}
	
	public void setTitle(String[] myTitle) {
		title = myTitle;
	}
	
	public void setNo_of_ratings(int my_nRatings) {
		nRatings = my_nRatings;
	}
	
	public void setRatings(float myRating) {
		rating = myRating;
	}
	
	public void setUrl_Image(String myImageURL) {
		imageURL = myImageURL;
	}
	
	
	
	
	

	
	

}
