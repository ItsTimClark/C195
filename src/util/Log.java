// Import packages
package util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class Log {
    // Set Log file name and path
    private static final String FILENAME = "log.txt";
    
    // Enable FileWriter for Logging with Errors
    public static void log (String username, boolean success) {
        try (FileWriter fw = new FileWriter(FILENAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println(ZonedDateTime.now() + " " + username + (success ? " Success" : " Failure"));
        } catch (IOException e) {
            System.out.println("Log Error: " + e.getMessage());
        }
    }
}
