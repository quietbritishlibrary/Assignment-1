package amazonproducts;

public class AmazonProductSubCategory {
    
    // Instance variables to hold the sub-category name and associated product category
    private String subCategoryName;      
    private AmazonProductCategory category;  
    
    // No-argument constructor for creating an empty AmazonProductSubCategory object
    public AmazonProductSubCategory() {}
    
    // Parameterized constructor for initializing an AmazonProductSubCategory with a name and category
    public AmazonProductSubCategory(String mySubCategoryName, AmazonProductCategory myCategory) {
        subCategoryName = mySubCategoryName;  
        category = myCategory;                 
    }
    
    // Getter method to retrieve the product category associated with this sub-category
    public AmazonProductCategory getCategory() {
        return category;  
    }
    
    // Getter method to retrieve the name of the sub-category
    public String getSubCategory() {
        return subCategoryName;  
    }
    
    // Setter method to update the product category associated with this sub-category
    public void setCategory(AmazonProductCategory myCategory) {
        category = myCategory;  
    }
    
    // Setter method to update the name of the sub-category
    public void setSubCategory(String mySubCategoryName) {
        subCategoryName = mySubCategoryName; 
    }
}
