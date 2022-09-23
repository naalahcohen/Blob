package git;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class TreeTest extends TestCase {
	private String SHA1str = "";
	private byte[] SHA1;
	private String listString="";

	@Test
	void test() throws IOException, NoSuchAlgorithmException
	{
		ArrayList<String> treeContents = new ArrayList<String>();
		treeContents.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		treeContents.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		treeContents.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		treeContents.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		treeContents.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		TreeObject t = new TreeObject(treeContents);
		int i;
		for(i = 0;i < treeContents.size() - 1;i++) {
		    listString += treeContents.get(i) + "\n";
		}
		listString += treeContents.get(i);
//		System.out.println(listString);
		SHA1str = generateSHA1(listString);
//		System.out.println(SHA1str);
		File check = new File("objects/" + SHA1str);
		String fileToString = getFileString("objects/" + SHA1str);
		System.out.println(fileToString);
		assertTrue(check.exists());
		assertEquals(listString, fileToString);
	}
	
	public String generateSHA1(String x) throws NoSuchAlgorithmException
	{
		String sha1Str = "";
		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
	    SHA1 = sha1.digest((x).getBytes()); 
	    for(byte b : SHA1 ) {
	    	  sha1Str += String.format("%02x",b);
	    	}
	    return sha1Str;
	}
//	
//	private static String generateSHA1(String password)
//	{
//	    String sha1 = "";
//	    try
//	    {
//	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//	        crypt.reset();
//	        crypt.update(password.getBytes("UTF-8"));
//	        sha1 = byteToHex(crypt.digest());
//	    }
//	    catch(NoSuchAlgorithmException e)
//	    {
//	        e.printStackTrace();
//	    }
//	    catch(UnsupportedEncodingException e)
//	    {
//	        e.printStackTrace();
//	    }
//	    return sha1;
//	}
//	
	public String getFileString(String fileName) throws IOException
	{
		FileInputStream fstream = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine = "";
		ArrayList<String> list = new ArrayList<String>();

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console - do what you want to do
		  list.add(strLine);
		}
		String listString="";
		int i;
		for(i = 0;i < list.size() - 1;i++) {
		    listString += list.get(i) + "\n";
		}
		listString += list.get(i);
		return listString;
	}
}
