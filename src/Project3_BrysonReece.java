// Yay, imports!
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/* 
 * @title: Mesonet Data File Parser
 * @project: three
 * @author: Bryson Reece
 * 
 * @class: CS 1323
 * @professor: Sarah Wu
 * @university: University of Oklahoma
 * 
 * @description: This program parses Mesonet data files and displays the
 * high and low temps for the day, along with their locations.
 */

public class Project3_BrysonReece {
	
	/*
	 * This method asks for a file location, however uses "data.txt" if nothing
	 * is specified.  It reads in the file one line at a time and
	 * prints that line into an array. It then analyzes that array for information
	 * such as the daily high and low temperatures and where they were recorded,
	 * then outputs that information to the user. When the end of the file is 
	 * reached, it closes out its resources and exits gracefully.
	 */
	
	public static void main(String[] args) {
		System.out.println("Please enter the Mesonet data file location: ");
		
		Scanner scanner = new Scanner(System.in);		
		String fileLocation = scanner.nextLine();
		
		/* ===== THIS BLOCK OF CODE PROVIDED BY @SARAH WU ===== */
		if (fileLocation.trim().isEmpty()) {
			// Using the default location of "here" if no file is specified.
			File phil = new File("");
			fileLocation = phil.getAbsolutePath() + "/data.txt";
		}
		/* ===== END CONTRIBUTION ===== */
		
		scanner.close();
		System.out.println("Attempting to read file at " + fileLocation);
		
		try(BufferedReader fileReader = new BufferedReader(new FileReader(fileLocation))) {

			// We initialize a variable to track whether this is the first line or not
			boolean firstLine = true;
			
			// These are baseline temperatures, we use these to compare our first values to.
			double minTemp = 9999;
			double maxTemp = 0;
			
			// fileLength counts how many lines are in a file.
			// xValue is the position in the file line where x is.
			int fileLength = getFileLength(fileLocation);
			int nameValue = 1;
			int minValue = 20;
			int maxValue = 19;
			
			// Placeholder Strings to hold the town names of our minimum/maximum temperatures.
			String minTown = null;
			String maxTown = null;
			
			// We read the first line available and hold the result in fileLine.
			String fileLine = fileReader.readLine();
			
			// PART OF THE HARD WAY
			StringTokenizer st = null;
			
			
			// Let's loop through the entire file.
			for (int i = 0; i <= fileLength; ++i) {
				// If this is the first time reading a line...
				if ((!(fileLine == null)) && firstLine) {
					// Skip it.
					firstLine = false;
				}
				
				// If this is NOT the first line...
				else if (!(fileLine == null)) {
					// Split the line into an array.
					
										/* ==== THE EASY WAY ==== */
					// result = fileLine.split(",");
					
										/* ==== THE HARD WAY ==== */
					// We initialize a StringTokenizer and split our line into tokens.
					st = new StringTokenizer(fileLine, ",");
					// A temporary variable to hold the value for the amount of tokens we have.
					int tokenLength = st.countTokens();
					// We create an empty array of the same size as tokenLength.
					String[] result = new String[tokenLength];
					
					// Loop through these tokens and move them into the aforementioned array.
					for (int q = 0; q < tokenLength; q++) {
						result[q] = st.nextToken();
					}
					
					// Create a new array holding JUST the information we need.
					String currentLine[] = { result[nameValue], result[minValue], result[maxValue] };
					
					// Placeholder variable to hold the currently highest/lowest temperature.
					Double currentMin = null;
					Double currentMax = null;
					
					// If the current minimum temperature value is empty, assign an arbitrary value to compare to.
					if (currentLine[1].equals(" ")) {
						currentLine[1] = "9999";
					}
					
					// Assign whatever the current array value is to the current minimum temperature.
					currentMin = Double.parseDouble(currentLine[1]);
					
					// If the current maximum temperature value is empty, assign an arbitrary value to compare to.
					if (currentLine[2].equals(" ")) {
						currentLine[2] = "0.0";
					}
					
					// Assign whatever the current array value is to the current maximum temperature.
					currentMax = Double.parseDouble(currentLine[2]);
					
					/* === LET'S START COMPARING === */
					
					// If the current minimum read is less than the previous read...
					if (currentMin < minTemp) {
						// Assign whatever town the minimum value is from to minTown
						minTown = currentLine[0];
						// The temperature value is the new minimum.
						minTemp = currentMin;
					}
					
					// If the current maximum read is greater than or equal to the previous read...
					if (currentMax >= maxTemp) {
						// Assign whatever town the maximum value is from to maxTown
						maxTown = currentLine[0];
						// The temperature value is the new maximum.
						maxTemp = currentMax;
					}
					
				}
				// If we've reached the end of the file...
				else if (fileLine == null) {
					// Print out our results ***** Also, we cast our values as ints in order to round our values.
					System.out.println("The Maximum Temperature for the day was " + (int)maxTemp + ", observed at " + maxTown + ".");
					System.out.println("The Minimum Temperature for the day was " + (int)minTemp + ", observed at " + minTown + ".");
					//System.out.println(Arrays.toString(result));
				}
				// Read the next line and start the loop over.
				fileLine = fileReader.readLine();
			}
			
	/* ===== THE REST OF THIS STATEMENT PROVIDED BY @SARAH WU ===== */
		}
		catch (FileNotFoundException e) {
			System.out.println("The file could not be found.  Please check "
					+ "the location and run again."
					+ "\nNote that some systems are case sensitive so "
					+ "\"data.txt\" is different than \"DATA.txt\".");
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		return;
	}
	
	/* ===== This method returns the file length in lines as an int value ===== */
	public static int getFileLength (String fileLocation) {
		// Initialize an empty Scanner.
		Scanner fileRead = null;
		// Initialize an int to serve as a counter.
		int i = 0;
		
		// We try to read the given file.
		try {
			// Load our file into our Scanner.
			fileRead = new Scanner(new File(fileLocation));
		}
		// If the file wasn't found, print out the exception.
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// While the file still has lines left to read...
		while (fileRead.hasNextLine()) {
			if (i == 0) {
				// Increment by one.
				++i;
			}
			else {
				// Read the next line.
				fileRead.nextLine();
				// Increment by one.
				++i;
			}
		}
		// Decrement our final value by one, as .hasNextLine returns true on the last line for some reason.
		--i;
		// Unload our file and close our Scanner.
		fileRead.close();
		// Return our total count.
		return i;
	}

}
