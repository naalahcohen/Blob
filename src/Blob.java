import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Blob {
	private byte[] byteArray;
	private String SHA1;
	
	public Blob(String path) throws IOException
	{
		Path p1 = Paths.get(path);
		byteArray = Files.readAllBytes(p1);
		System.out.println(byteArray);
		SHA1 = toSHA1(byteArray);
		newFile();
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
	
	public void newFile() throws FileNotFoundException
	{
		System.out.println ("./Blob/" + SHA1);
		File SHA1File = new File("./Blob/" + SHA1);
		String destination = ("./objects/" + SHA1);
		PrintWriter out = new PrintWriter("SHA1File");
		String str1 = new String(byteArray);
		out.print(str1);
		out.close();
	}
	
	
	
	

}
