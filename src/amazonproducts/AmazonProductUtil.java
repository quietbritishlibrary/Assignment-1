package amazonproducts;

import java.util.ArrayList;

public class AmazonProductUtil {
    
    // Constant for the number of columns in the CSV file
    private static final int NUMCOLS = 10;

    /**
     * Converts a cleaned string (representing a number) into a float.
     * This method strips out any non-numeric characters (like commas and spaces)
     * and converts the cleaned string to a float.
     * 
     * @param str The string to be converted to a float.
     * @return The float value of the string after cleaning.
     */
    public static float convertStrToFloat(String str) {
        
        String cleanedStr = "";  // Holds each character that is a digit
        ArrayList<String> fullStr = new ArrayList<>();  // List to store digits
        
        // Loop through each character in the string and add digits to the list
        for(int i = 0; i < str.length(); i++) {
            if(Character.isDigit(str.charAt(i))) {
                cleanedStr = String.valueOf(str.charAt(i)); 
                fullStr.add(cleanedStr); // 
            } else {
                continue; 
            }
        }
        
        // Convert the list of digits back to a string, then clean up any unwanted characters
        String number = fullStr.toString();
        number = number.replace(",", "")  
                       .replace("[", "")  
                       .replace("]", "")  
                       .replace(" ", ""); 
        
        // Parse the cleaned-up string to a float and return it
        return Float.parseFloat(number);
    }

    /**
     * Splits a CSV line into an array of values (strings) based on commas.
     * This method handles quoted strings and commas within those quotes.
     * 
     * @param string The line from the CSV file to be parsed.
     * @param start The starting index to begin reading the string.
     * @return An array of strings representing the values in the CSV line.
     */
    public static String[] lineReader(String string, int start) {
        
        // Array to hold the values of each column
        String[] str = new String[NUMCOLS];
        int index = 0;  // Index to keep track of the position in the array
        final char chComma = ',';  // Character used to separate columns in CSV
        final char chQuotes = '"'; // Character used to enclose values in quotes
        
        // Find the first comma to start splitting the line
        int end = string.indexOf(chComma);
        String value;

        // Loop through the string to extract values between commas
        while (start < end) {
            if (string.charAt(start) == chQuotes) {
                start++;  // Skip the opening quote
                end = string.indexOf(chQuotes, start + 1);  // Find the closing quote
            }
            
            // Extract the value between the start and end positions
            value = string.substring(start, end);
            value = value.trim();  // Remove any leading/trailing spaces
            str[index++] = value;  // Store the value in the array
            
            // Update the start position for the next value
            if (string.charAt(end) == chQuotes)
                start = end + 2;  // Skip past the closing quote
            else
                start = end + 1;  // Skip past the comma

            // Find the next comma
            end = string.indexOf(chComma, start + 1);
        }

        // If there are any characters left after the last comma, extract the final value
        if (start < string.length()) {
            value = string.substring(start);
            str[index++] = value;  
        }
        
        // Return the array containing all the values from the CSV line
        return str;
    }
}
