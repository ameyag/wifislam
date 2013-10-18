package wifislam.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

	
	public static String readFiletoString(String path){
		
		Path p1 = Paths.get(path);
	    StringBuffer fileAsString = new StringBuffer("");
	    
		try (InputStream in = Files.newInputStream(p1);
			     BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			
			String tempLine=null;
			
			    while ((tempLine = reader.readLine()) != null) {
			    	fileAsString=fileAsString.append(tempLine);
			    }
			} catch (IOException x) {
			    System.err.println(x);
			}
		
		return fileAsString.toString();
	}
}
