package amazonproducts;

import java.util.Scanner;

//NOTE - I didn't use system.err.print because it messed up error loops, so sorry for poor error readability!

public class AmazonProductManager {

	AmazonProductList productList = new AmazonProductList(); // Manages the list of products
	Scanner input = new Scanner(System.in); // Input scanner to capture user input
	private String filename = "Sample-Amazon-Products-v2.csv"; // Default file for saving/loading product list

	public static void main(String[] args) throws AmazonProductException {

		AmazonProductManager manage = new AmazonProductManager();
		try {
			manage.manageProductList(); // Start the product management interface
		} catch (AmazonProductException e) {

			System.out.println("AmazonProductException: " + e.getLocalizedMessage());

		}

	}
	// Manages the main product management flow
	public void manageProductList() throws AmazonProductException {

		AmazonProductManager use = new AmazonProductManager();
		int option = -1;
		// Main loop for interacting with the user until they choose to exit
		while (option != 0) {
			option = -1;
			use.showMenu();

			try {

				option = Integer.parseInt(input.nextLine().replace(" ", ""));

			} catch (Exception e) {
				
				// If the user enters a non-integer, loop once again
			}

			// Handle the user selection based on menu choice
			switch (option) {
			
			case 0:
				use.exit();
				break;
			case 1:
				use.createProduct();
				break;
			case 2:
				use.displayProductList();
				break;
			case 3:
				use.addProduct();
				break;
			case 4:
				use.editProduct();
				break;
			case 5:
				use.deleteProduct();
				break;
			case 6:
				use.saveProductList();
				break;
			case 7:
				use.search();
				break;
			default:
				
				System.out.println("AmazonProductException: Invalid input - type a number between 0 and 7");
				
			}

		}
	}
	// Displays the main menu of options for the user
	public void showMenu() {

		System.out.print("================================\r\n" + "|| Menu - Amazon Products: A1 ||\r\n"
				+ "================================\r\n" + "0. Exit\r\n" + "1. Load product list\r\n"
				+ "2. Show product list\r\n" + "3. Add product\r\n" + "4. Edit a product\r\n"
				+ "5. Delete a product\r\n" + "6. Save product list\r\n" + "7. Search in the list\r\n"
				+ "Choose an option:");
	}
	// Exits the application and prints a closing message
	public void exit() {
		System.out.println("================================ \n" + "||    [Application ended]     || \n"
				+ "================================");
	}
	// Prompts the user for a file name and loads the product list from a CSV file
	public void createProduct() throws AmazonProductException {
		boolean isValid = true;
		while (isValid) {
			try {
				System.out.print(
						"Name of file to create ProductList (default file will be added if no input is given): ");
				
				String file = input.nextLine();

				if (file.isEmpty()) {
					file = "src\\amazonproducts\\Sample-Amazon-Products-v2.csv";
					isValid = false;
				}
				productList.createList(file);
				
				System.out.println("product loaded!");
				
			} catch (AmazonProductException e) {
				
				System.out.println("AmazonProductException: " + e.getLocalizedMessage());
				
			}
		}
	}
	// Displays all products in the product list
	public void displayProductList() {
		
		System.out.println("PRODUCTLIST .........");
		productList.printList();
		
	}

	// Adds a new product to the list after validating the user input
	public void addProduct() throws AmazonProductException {

		boolean isValid = true;

		// initialize all numbers (float/int) to unreachable numbers;
		int id = -1;
		float rating = -1;
		int nRatings = -1;
		float discountPrice = -1;
		float actualPrice = -1;

		System.out.println("Enter the product data: ");
		// Input loop for product ID
		while (isValid) {
			try {
				System.out.print("- Id (skip it to automatically be assigned an id): ");
				
				String idEntered = input.nextLine();
				
				// Auto-assign ID if none provided
				if(idEntered.isEmpty()){
					id = productList.getSize();
					isValid = false;
					break;
				}
				
				id = Integer.parseInt(idEntered);

				if (id < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: ID must be positive!");

				} else {
					
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				
				System.out.println(e.getLocalizedMessage());
				

			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: ID must be an integer! ");
				
			}
		}
		isValid = true; // reinitialize validation variable to true
		
		String name = "";
		// Input loop for product name
		while(isValid) {
			try {
		System.out.print("- Name: ");
		
	     name = input.nextLine();

		if (name.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Name can't be empty!");
		}else {
			isValid = false;
		}
			}catch(AmazonProductException e) {
				System.out.println(e.getLocalizedMessage());
			}
		
		
		}
		isValid = true;
		
		String categoryName = "";
		// Input loop for category name
		while(isValid) {
			try {
		
		System.out.print("- Main Category: ");
		
		 categoryName = input.nextLine();
		if (categoryName.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Main Category can't be empty!");
		}else {
			isValid = false;
		}}catch(AmazonProductException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
			}
			
		
	
		AmazonProductCategory category = new AmazonProductCategory(categoryName);
		
		isValid = true;
		String subCategoryName = "";
		// Input loop for sub category name
		while(isValid) {
			try {
		
		System.out.print("- Sub Category: ");
		
		subCategoryName = input.nextLine();
		if (subCategoryName.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Sub Category can't be empty!");
		}else {
			isValid = false;
		} 
		} catch(AmazonProductException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
		AmazonProductSubCategory subCategory = new AmazonProductSubCategory(subCategoryName, category);
		
		isValid = true;;
		
		String imageURL = "";
		// Input loop for image URL
		while(isValid) {
			try {
		System.out.print("- Image URL: ");
		
		 imageURL = input.nextLine();
		if (imageURL.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Image URL can't be empty!");
		}else {
			isValid = false;
		
			}
			}catch(AmazonProductException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		
			
			isValid = true;
			String link = "";
		// Input loop for Link
		while(isValid) {
			try {
		System.out.print("- Link: ");
		
		 link = input.nextLine();
		if (link.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Link can't be empty!");
		}else {
			isValid = false;
		}
		
			}catch(AmazonProductException e) {
				System.out.println(e.getLocalizedMessage());
			}
		
			
		
		}
		
		isValid = true;;
		// Input loop for Rating
		while (isValid) {
			try {
				
				System.out.print("- Rating: ");
				
				rating = Float.parseFloat(input.nextLine());

				if ( rating < 0 || rating > 5) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Rating must be within range (0-5)");
				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				
			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: Rating must be a float: " + e.getLocalizedMessage());
				
			}
		}

		isValid = true;
		
		// Input loop for Number of Ratings
		while (isValid) {
			try {
				
				System.out.print("- Number of Ratings: ");
				
				nRatings = Integer.parseInt(input.nextLine());

				if (nRatings < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Number of Ratings must be positive!");
				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				
			} catch (NumberFormatException e) {
				
				System.out.println(
						"AmazonProductException: Number of Ratings must be an int: " + e.getLocalizedMessage());
				
			}

		}
		isValid = true; // reinitialize validation variable to true
		// Input loop for Discount Price
		while (isValid) {
			try {
				
				System.out.print("- Discount Price: ");
				
				discountPrice = Float.parseFloat(input.nextLine());

				if (discountPrice < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Price must be positive!");
				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				
			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: Price must be a float!: " + e.getLocalizedMessage());
				
			}
		}

		isValid = true; 
		// Input loop for Actual Price
		while (isValid) {
			try {
				
				System.out.print("- Actual Price: ");
				
				actualPrice = Float.parseFloat(input.nextLine());

				if (actualPrice < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Price must be positive!");
				} else {
					isValid = false;
				}

			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				

			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: Price must be a float!: " + e.getLocalizedMessage());
				
			}

		}
		AmazonProduct newProduct = new AmazonProduct(id, name, category, subCategory, imageURL, link, rating, nRatings,
				discountPrice, actualPrice);
		productList.add(newProduct);
		
		System.out.println("product loaded!");
		
		

	}
	// Edits an existing product based on the product ID
	// process is nearly identical to addProduct()  
	public void editProduct() throws AmazonProductException {

		boolean isValid = true;

		int id = -1;
		float rating = -1;
		int nRatings = -1;
		float discountPrice = -1;
		float actualPrice = -1;
		int index = -1;
		int bit = -1;

		while (isValid) {
			try {
				
				System.out.print("- Enter the id of the product to edit: ");
				
				String productIndex = (input.nextLine().replace(" ", ""));
				index = Integer.parseInt(productIndex);

				for (int i = 0; i < productList.getSize(); i++) {
					AmazonProduct product = productList.findProductByIndex(i);
					if (index == product.getId()) {
						index = product.getId();
						isValid = false;
						bit = 1;
					}

				}

				if (bit != 1) {

					throw new AmazonProductException("ID not found");
				}

			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: You must enter an existing product id!");
				
			} catch (AmazonProductException e) {
				
				System.out.println("AmazonProductException: " + e.getLocalizedMessage());
				
			}

		}

		isValid = true; // reinitialize validity variable to true
		
		System.out.println("Enter the product data: ");
		

		while (isValid) {
			try {
				
				System.out.print("- Enter id of product: ");
				
				String idEntered = input.nextLine();
				id = Integer.parseInt(idEntered);

				if (id < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: ID must be positive!");

				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				

			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: ID must be an integer! " + e.getMessage());
				
			}
		}
		isValid = true; // reinitialize validation variable to true

		String name = "";
		
		while(isValid) {
			try {
		System.out.print("- Name: ");
		
	     name = input.nextLine();

		if (name.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Name can't be empty!");
		}else {
			isValid = false;
		}
			}catch(AmazonProductException e) {
				System.out.println(e.getLocalizedMessage());
			}
		
		
		}
		isValid = true;
		
		String categoryName = "";
		
		while(isValid) {
			try {
		
		System.out.print("- Main Category: ");
		
		 categoryName = input.nextLine();
		if (categoryName.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Main Category can't be empty!");
		}else {
			isValid = false;
		}}catch(AmazonProductException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
			}
			
		
	
		AmazonProductCategory category = new AmazonProductCategory(categoryName);
		
		isValid = true;
		String subCategoryName = "";
		while(isValid) {
			try {
		
		System.out.print("- Sub Category: ");
		
		subCategoryName = input.nextLine();
		if (subCategoryName.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Sub Category can't be empty!");
		}else {
			isValid = false;
		} 
		} catch(AmazonProductException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
		AmazonProductSubCategory subCategory = new AmazonProductSubCategory(subCategoryName, category);
		
		isValid = true;;
		
		String imageURL = "";
		
		while(isValid) {
			try {
		System.out.print("- Image URL: ");
		
		 imageURL = input.nextLine();
		if (imageURL.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Image URL can't be empty!");
		}else {
			isValid = false;
		
			}
			}catch(AmazonProductException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		
			
			isValid = true;
			String link = "";
		while(isValid) {
			try {
		System.out.print("- Link: ");
		
		 link = input.nextLine();
		if (link.isEmpty()) {
			throw new AmazonProductException("AmazonProductException: Link can't be empty!");
		}else {
			isValid = false;
		}
		
			}catch(AmazonProductException e) {
				System.out.println(e.getLocalizedMessage());
			}
		
			
		
		}
		
		isValid = true;

		while (isValid) {
			try {
				
				System.out.print("- Rating: ");
				
				rating = Float.parseFloat(input.nextLine());

				if (rating < 0 || rating > 5) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Rating must be within range (0-5)");
				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				
			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: Rating must be a float: " + e.getLocalizedMessage());
				
			}
		}

		isValid = true; // reinitialize validation variable to true

		while (isValid) {
			try {
				
				System.out.print("- Number of Ratings: ");
				
				nRatings = Integer.parseInt(input.nextLine());

				if (nRatings < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Number of Ratings must be positive!");
				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
			
				System.out.println(e.getLocalizedMessage());
				
			} catch (NumberFormatException e) {
				
				System.out.println(
						"AmazonProductException: Number of Ratings must be an int: " + e.getLocalizedMessage());
				
			}

		}
		isValid = true; // reinitialize validation variable to true

		while (isValid) {
			try {
				
				System.out.print("- Discount Price: ");
				
				discountPrice = Float.parseFloat(input.nextLine());

				if (discountPrice < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Price must be positive!");
				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				
			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException:  Price must be a float!: " + e.getLocalizedMessage());
				
			}
		}

		isValid = true; // reinitialize validation variable to true

		while (isValid) {
			try {
				
				System.out.print("- Actual Price: ");
				
				actualPrice = Float.parseFloat(input.nextLine());

				if (actualPrice < 0) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Price must be positive!");
				} else {
					isValid = false;
				}

			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				

			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: Price must be a float!: " + e.getLocalizedMessage());
				
			}

		}

		AmazonProduct newProduct = new AmazonProduct(id, name, category, subCategory, imageURL, link, rating, nRatings,
				discountPrice, actualPrice);
		productList.edit(index, newProduct);

		
		System.out.println("Product edited succesfully");
		

	}
	// Deletes a product from the list based on its ID
	public void deleteProduct() throws AmazonProductException {

		boolean isValid = true;
		int productId = -1;
		boolean productFound = false;

		while (isValid) {

			try {
				
				System.out.print("- Enter the id of the product you want to delete: ");
				
				productId = Integer.parseInt(input.nextLine().replace(" ", ""));

				for (int i = 0; i < productList.getSize(); i++) {
					AmazonProduct product = productList.findProductByIndex(i);
					if (productId == product.getId()) {
						isValid = false;
						productFound = true;
						productId = i;
					}
				}

				if (productFound == false) {
					throw new AmazonProductException("AmazonProductException: ID isn't valid. Reenter a valid ID");
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				
			} catch (NumberFormatException e) {
				
				System.out.println("AmazonProductException: You must enter an existing product ID!");
				
			}

		}

		productList.delete(productId); // Delete the product with the given ID
		
		System.out.println("product deleted");
		

	}
	// Saves the current product list to a CSV file
	public void saveProductList() throws AmazonProductException {
		try {
			// System.out.print("Enter the csvFile: ");
			// String csvFile = input.nextLine();

			productList.saveList(filename);
			
			System.out.println("file saved!");
			

		} catch (AmazonProductException e) {

			System.out.println("AmazonProductException: Error saving product list: " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			
			System.out.println("AmazonProductException: Product not found. Load a product to save.");
			
		}
	}
	//search for products with the given string
	public void search() throws AmazonProductException {
		String query = "";
		boolean isValid = true;

		while (isValid) {
			try {
				
				System.out.print("Search: ");
				
				query = input.nextLine();
				if (query.isEmpty()) {
					isValid = true;
					throw new AmazonProductException("AmazonProductException: Search cannot be empty!");
				} else {
					isValid = false;
				}
			} catch (AmazonProductException e) {
				
				System.out.println(e.getLocalizedMessage());
				
			}
		}
		productList.search(query);
	}

}
