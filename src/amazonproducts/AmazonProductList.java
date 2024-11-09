package amazonproducts;

import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class AmazonProductList {

    // Constant for the number of columns in the CSV file
    private final int NUMCOLS = 10;

    // List to store the default title of the CSV file (e.g., headers)
    private ArrayList<String> DEFAULT_TITLE = new ArrayList<>();

    // List to store the best-selling Amazon products
    private ArrayList<AmazonProduct> bestsellers = new ArrayList<AmazonProduct>();

    /**
     * Creates a list of products by reading data from a CSV file.
     * @param csvFile The path to the CSV file
     * @throws AmazonProductException If there is an issue reading the file
     */
    public void createList(String csvFile) throws AmazonProductException { 
        
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile));) {
            // Read the first line to get the column headers and store it as the default title
            String title = reader.readLine();
            DEFAULT_TITLE.add(title);
            
            // Read each line from the CSV file and process the data into products
            String line = reader.readLine();
            String[] data = new String[NUMCOLS];
           
            while (line != null) {
                // Split each line into individual product data
                data = AmazonProductUtil.lineReader(line, 0);    
                // Create a new AmazonProduct object and add it to the bestsellers list
                AmazonProduct product = new AmazonProduct(data);                
                bestsellers.add(product);
                line = reader.readLine(); // Read the next line
            }
            
        } catch(FileNotFoundException e) {
            // Handle case where the file is not found
            throw new AmazonProductException("Error opening the file. " + e.getMessage());
        } catch (IOException e) {
            // Handle other IO exceptions
            throw new AmazonProductException("File not found! " +  e.getMessage());
        }  
    }
    
    /**
     * Prints the list of products along with the default title (headers).
     * If the list is empty, a message is displayed.
     */
    
    public void printList() {
        if(bestsellers.isEmpty()) {
            // Inform the user if the list is empty
            System.out.println("List is empty! Load a product list to view.");
        } else {
            // Print the default title (headers)
            System.out.println(DEFAULT_TITLE);
            // Print each product's details
            for(AmazonProduct product : bestsellers) {
                System.out.println(product);
            }
        }
    }
    
    /**
     * Adds a new product to the list of bestsellers.
     * @param p The product to be added
     * @throws AmazonProductException If the product data is invalid or empty
     */
    public void add(AmazonProduct p) throws AmazonProductException {
        try {    
            // Add the product to the list of bestsellers
            bestsellers.add(p);
        } catch (NullPointerException e) {
            // Handle case where the product is null
            throw new AmazonProductException("The data is empty!");
        } catch(NumberFormatException e) {
            // Handle case where there is a format error in the data
            throw new AmazonProductException("The number isn't in the right format!");
        }
    }
    
    /**
     * Finds a product by its index in the list.
     * @param index The index of the product
     * @return The AmazonProduct object at the specified index
     */
    public AmazonProduct findProductByIndex(int index){
        return bestsellers.get(index);
    }
    
    /**
     * Creates a new, empty product at the specified position in the list.
     * @param pos The position where the product should be created
     * @throws AmazonProductException If the position is already occupied
     * @return The new AmazonProduct object
     */
    public AmazonProduct createProduct(int pos) throws AmazonProductException {
        if(pos < bestsellers.size()) {
            throw new AmazonProductException("Product slot is taken");
        }
        AmazonProduct p = new AmazonProduct(); // Create an empty product
        return p;
    }
    
    /**
     * Edits an existing product at the specified position.
     * @param pos The position of the product to be edited
     * @param p The updated product data
     * @throws AmazonProductException If the product data is invalid
     */
    public void edit(int pos, AmazonProduct p) throws AmazonProductException {
        if(p == null) {
            // Handle case where the product to be edited is null
            throw new AmazonProductException("Product data is empty");
        }
        
        // Find the product in the list by its position and update it
        for(int i = 0; i < bestsellers.size(); i++) {
            AmazonProduct product = bestsellers.get(i);
            if(pos == product.getId()) {
                pos = bestsellers.indexOf(product); // Get the index of the product
                bestsellers.set(pos, p); // Replace the product with the new one
            }
        }
    }
    
    /**
     * Deletes a product from the list by its position.
     * @param pos The position of the product to be deleted
     * @throws AmazonProductException If there is an error while deleting the product
     */
    public void delete(int pos) throws AmazonProductException {
        bestsellers.remove(pos); // Remove the product from the list
    }
    
    /**
     * Saves the current product list to a CSV file.
     * @param csvFile The path to the CSV file
     * @throws AmazonProductException If there is an error writing to the file
     */
    public void saveList(String csvFile) throws AmazonProductException {
        try (FileWriter file = new FileWriter(csvFile)){
            // Write the default title (headers) to the file
            file.write(DEFAULT_TITLE.get(0) + "\n");
            
            // Write each product to the file in CSV format
            for(AmazonProduct product : bestsellers) {
                file.write(product.toCSV() + "\n");
            }       
        } catch (IOException e) {
            // Handle IO exceptions (e.g., if file cannot be written)
            throw new AmazonProductException("Error saving the file: " + e.getMessage());
        } catch(IndexOutOfBoundsException e) {
            // Handle case where index is out of bounds
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * Searches the list of products by a keyword or data string.
     * The search checks against various product properties (name, id, category, etc.).
     * @param data The search query string
     * @throws AmazonProductException If there is an error during the search
     */
    public void search(String data) throws AmazonProductException {
    	int productFound = 0;
    	
    	System.out.println("The products associated are: ");
    	
    	for(int i = 0; i < bestsellers.size();i++) {
    		
    		AmazonProduct product = bestsellers.get(i);
    		
    		if(product.getName().toLowerCase().contains(data)) {
    			productFound++;   			
    			System.out.println(product);
    		
    			continue;
    		}if (String.valueOf(product.getId()).contains(data)){ 
    			productFound++;    			
    			System.out.println(product);
    			
    			continue;
    		}if(product.getLink().toLowerCase().contains(data)) { 
    			productFound++;   		
    			System.out.println(product);
    		
    			continue;
    		}if(String.valueOf(product.getMain_category()).toLowerCase().contains(data)) {
    			productFound++;    			
    			System.out.println(product);
    		
    			continue;
    		}if(String.valueOf(product.getSub_category()).toLowerCase().contains(data)) {
    			productFound++;   		
    			System.out.println(product);
    		
    			continue;
    		}if(product.getUrl_Image().toLowerCase().contains(data)) {
    			productFound++;    			
    			System.out.println(product);
    			
    			continue;
    		}if(String.valueOf(product.getRatings()).contains(data)) {
    			productFound++;    			
    			System.out.println( product);
    			
    			continue;
    		}if(String.valueOf(product.getNo_of_ratings()).contains(data)) {
    			productFound++;
    			System.out.println(product);
    			
    			continue;
    		}if(String.valueOf(product.getDiscount_Price()).contains(data)) {
    			productFound++;    		
    			System.out.println(product);
    			
    			continue;
    		}if(String.valueOf(product.getActual_Price()).contains(data)) {
    			productFound++;  		
    			System.out.println(product);
    			
    			continue;
    		}

    	}
    	
    	if(productFound == 0) {
    		
			System.out.println("search empty!");
			
		}
    	
    	
    }
    
    /**
     * Returns the number of products in the list.
     * @return The size of the product list
     */
    public int getSize() {
        return bestsellers.size();
    }
}
