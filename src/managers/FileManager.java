package managers;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class FileManager {

	public static String readLevel(String levelName) {
		String out = null;
		try {
		      File level = new File(levelName);
		      Scanner reader = new Scanner(level);
		      while (reader.hasNextLine()) {
		    	  out = out + reader.nextLine();
		      }
	    reader.close();
	    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
	    }
		return out;
	}
	
	public static void writeFile() {
		
	}
}
