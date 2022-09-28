package git;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class TreeObject {
	
	public TreeObject(ArrayList<String> arrList) throws IOException {
		StringBuilder sb= new StringBuilder();
		arrList.forEach((word) -> sb.append(word));
		String fileSha1Name= getSha1Name(sb.toString());
		
		File sha1File = new File("./objects/"+fileSha1Name);
        sha1File.createNewFile();
        
        FileWriter myWriter = new FileWriter("./objects/"+fileSha1Name);
        arrList.forEach((word) -> {
			try {
				myWriter.write(word);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        myWriter.close();
		
	}
	
	private static String getSha1Name(String password)
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
	
	public static File creatingTree () throws FileNotFoundException {
		ArrayList <String> listie = convertIndex(); 
		for(String str: listie) {
			 PrintWriter pw = new PrintWriter(new FileOutputStream(fileName)); 
			 pw.write("blob :" + getFileName(str) + " " + getHash(str));
			 
			 pw.close();
		}
		
	}
	
	public static HashMap creatingHash () throws FileNotFoundException {
		HashMap <String,String> hashie = new HashMap (); 
		ArrayList <String> listie = convertIndex(); 
		for(String str: listie) {
			hashie.put(getFileName(str), getHash(str));
		}
		return hashie; 
	}
	
	public static String getFileName(String str) {
		String hash = str.substring(str.length()-40);
		return hash; 
	}
	
	public static String getHash(String str) {
		String fileName = str.substring(0,str.length()-42);
		return fileName; 
	}

	public static ArrayList convertIndex() throws FileNotFoundException {
		Scanner s = new Scanner(new File("index.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		return list; 
//		File index = new File("index");
//		try {
//		      FileWriter myWriter = new FileWriter("tree.txt");
//		      for(int i =0; i < countingNumberOfLines("index.txt"); i++) {
//		    	  myWriter.write("blob :" + 
//		      }
//		      myWriter.write("Files in Java might be tricky, but it is fun enough!");
//		      myWriter.close();y7h
//		      System.out.println("Successfully wrote to the file.");
//		    } catch (IOException e) {
//		      System.out.println("An error occurred.");
//		      e.printStackTrace();
//		    }
	}
	
	public static int countingNumberOfLines(String filename) {

	    int count = 0;

	    try {
	      // create a new file object
	      File file = new File(filename);

	      // create an object of Scanner
	      // associated with the file
	      Scanner sc = new Scanner(file);

	      // read each line and
	      // count number of lines
	      while(sc.hasNextLine()) {
	        sc.nextLine();
	        count++;
	      }
	      System.out.println("Total Number of Lines: " + count);

	      // close scanner
	      sc.close();
	    } catch (Exception e) {
	      e.getStackTrace();
	    }
		return count;
	    
	}
}
