import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class driver {
    
    /* 
     * @title: Mesonet Data File Parser v2
     * @author: Patrick Wright, Bryson Reece
     * 
     * @version: 9/12/2016
     * 
     * @source: https://github.com/brysonreece/Oklahoma-Mesonet-Data-Parser
     * 
     * @description: This program parses Mesonet data files and displays the
     * high and low temps for the day, along with their locations. Modified for
     * style by @Patrick Wright
     */
    
    public static void main(String[] args) throws IOException 
    {
        
        /*
         * This method asks for a file location, however uses "data.txt" if nothing
         * is specified.  It reads in the file one line at a time and
         * prints that line into an array. It then analyzes that array for information
         * such as the daily high and low temperatures and where they were recorded,
         * then outputs that information to the user. When the end of the file is 
         * reached, it closes out its resources and exits gracefully. 
         */
        
        System.out.println("Please enter the Mesonet data file location: ");
        
        //Scans document to determine length
        Scanner scanner = new Scanner(System.in);       
        String fileLocation = scanner.nextLine();
        
        /* ===== THIS BLOCK OF CODE PROVIDED BY @SARAH WU ===== */
        if (fileLocation.trim().isEmpty()) 
        {
            // Using the default location of "here" if no file is specified.
            File phil = new File("");
            fileLocation = phil.getAbsolutePath() + "/data.txt";
        }
        /* ===== END CONTRIBUTION ===== */
        
        scanner.close();
        System.out.println("Attempting to read file at " + fileLocation);
        
        //Reads file and outputs high and low temps for the day and their locations
        fileReader fr = new fileReader();
        fr.outputFileReader(fileLocation);
        
        
    }

}
