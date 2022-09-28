package git;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;

public class Commit {
	private String parent;
	private String next;
	private String pTree;
	private String author;
	private String summary;
	private String date;
	private String str;
	private byte[] SHA1;
	
	
	public Commit(String fileName, String summary1, String author1, String parent1) throws IOException, NoSuchAlgorithmException
	{
		pTree = fileName;
		summary = summary1;
		author = author1;
		parent = parent1;
		next = "";
		if (!parent.equals(""))
		{
			updateParent("objects/" + parent);
		}
		
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
	
	private static String generateSHA1(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
//	public String generateSHA1(String str) throws IOException, NoSuchAlgorithmException
//	{
//		String SHA1Str = "";
//		 MessageDigest sha1 = MessageDigest.getInstance("SHA1");
//		    SHA1 = sha1.digest((str).getBytes()); 
//		    for(byte b : SHA1 ) {
//		    	  SHA1Str += String.format("%02x",b);
//		    	}
//		    return SHA1Str;
//	}
//	
	public String getDate()
	{
		String timeStamp = new SimpleDateFormat("MM/dd/yy").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	public String makeFile() throws NoSuchAlgorithmException, IOException
	{
		String fileName = generateSHA1(getContents());
		 Path p = Paths.get("objects/"+ fileName);
	        try {
	            Files.writeString(p, getContents(), StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return fileName;
	}
	
	public void updateParent(String parent) throws NoSuchAlgorithmException, IOException
	{
		setVariable(3,generateSHA1(getContents()),parent);
	}
	
		public static void setVariable(int lineNumber, String data, String fileName) throws IOException {
			    Path path = Paths.get(fileName);
			    ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path, StandardCharsets.UTF_8);
			    lines.set(lineNumber - 1, data);
			    Files.write(path, lines, StandardCharsets.UTF_8);
			}
		
	public void convertIndex() {
		
		
	}
	}
//			FileInputStream fstream = new FileInputStream(parent);
//			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//
//			String pContents = "";
//			pContents += br.readLine();
//			pContents += br.readLine();
//			br.readLine();
//			pContents += generateSHA1(getContents());
//			pContents += br.readLine();
//			pContents += br.readLine();
//			pContents += br.readLine();
//			System.out.println(pContents);
//			}
