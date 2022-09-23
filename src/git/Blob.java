package git;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Blob {
	private byte[] SHA1;
	private String SHA1Str = "";
	String str = "";
	
	public Blob(String fileName) throws IOException, NoSuchAlgorithmException
	{
		FileInputStream fstream = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console - do what you want to do
		  str += strLine;
		}
		System.out.println(str);

		//Close the input stream
		fstream.close();
		SHA1(str);
		newFile();
	}
	
	public void SHA1(String x) throws NoSuchAlgorithmException
	{

	    MessageDigest sha1 = MessageDigest.getInstance("SHA1");
	    SHA1 = sha1.digest((x).getBytes()); 
	    for(byte b : SHA1 ) {
	    	  SHA1Str += String.format("%02x",b);
	    	}

	}
	
	public String getSHA1()
	{
		return SHA1Str;
	}
	
//	public static String toSHA1(byte[] convertme) {
//	    MessageDigest md = null;
//	    try {
//	        md = MessageDigest.getInstance("SHA-1");
//	    }
//	    catch(NoSuchAlgorithmException e) {
//	        e.printStackTrace();
//	    } 
//	    return new String(md.digest(convertme));

	
	public void newFile() throws IOException
	{
//		System.out.println ("./Blob/" + SHA1);
		File SHA1File = new File("objects/" + SHA1Str);
		SHA1File.createNewFile();
		//String destination = ("./objects/" + SHA1);
		PrintWriter out = new PrintWriter(SHA1File);
		out.print(str);
		out.close();
	}
	
	
	
	

}
