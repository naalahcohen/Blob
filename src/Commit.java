import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Commit {
	private String parent;
	private String next;
	private String pTree;
	private String author;
	private String summary;
	private String date;
	private String str;
	private byte[] SHA1;
	
	
	public Commit(String fileName, String summary1, String author1, String parent1) throws IOException
	{
		pTree = fileName;
		summary = summary1;
		author = author1;
		parent = parent1;
		next = "";
	}
	
	public String getContents()
	{
		String contents = "";
		contents += pTree;
		contents += "\n" + parent;
		contents += "\n" + next;
		contents += "\n" + author;
		contents += "\n" + getDate();
		contents += "\n" + summary;
		return contents;
	}
	
	public String generateSHA1(String str) throws IOException, NoSuchAlgorithmException
	{
		String SHA1Str = "";
		 MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		    SHA1 = sha1.digest((str).getBytes()); 
		    for(byte b : SHA1 ) {
		    	  SHA1Str += String.format("%02x",b);
		    	}
		    return SHA1Str;
	}
	
	public String getDate()
	{
		String timeStamp = new SimpleDateFormat("MM/dd/yy").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	public void makeFile() throws NoSuchAlgorithmException, IOException
	{
		String fileName = generateSHA1(getContents());
		 Path p = Paths.get("objects/"+ fileName);
	        try {
	            Files.writeString(p, getContents(), StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	

}
