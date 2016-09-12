import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileLength {
    

    public int getFileLength(String fileLocation) {
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
