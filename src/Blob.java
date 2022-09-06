import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Blob {
	private byte[] byteArray;
	
	public Blob(String path) throws IOException
	{
		Path p1 = Paths.get(path);
		byteArray = Files.readAllBytes(p1);
	}
	
	public static String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return new String(md.digest(convertme));
	}
	
	public void newFile()
	{
//		PrintWriter out = new PrintWriter(outputFileName);
//		.\\test\\objects\\SHA1
	}
	
	
	

}
