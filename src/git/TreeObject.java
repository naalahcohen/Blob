package git;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class TreeObject {
	ArrayList<String> arr;
	String str1 = ""; 
	String name = "";
	String removefile = ""; 
	String curfileName = ""; 
	String shadname = ""; 
	ArrayList<String> newArr;
	
	public TreeObject(ArrayList<String> arrList, String parent) throws IOException, NoSuchAlgorithmException {
        newArr = new ArrayList <String> (); 
		arr = arrList;
        str1 = parent;
        for(String str: arr) {
        	shadname += str;
        }
        curfileName = sha1(shadname);
        creatingTree (); 
	}
	
	public String getcurtreesha() {
		return curfileName;
	}
	
	public String getContents() {
		StringBuilder sb = new StringBuilder();
		arr.forEach((word) -> sb.append(word));
		String fileSha1Name = getSha1Name(sb.toString());
		return fileSha1Name;
	}
	
	
	public String sha1 (String sha) {
		String value = sha;
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}

		return sha1;
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
	// creatingTree based off of arrayList that is full of the index
	public PrintWriter creatingTree () throws IOException, NoSuchAlgorithmException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(curfileName)); 
		pw.append("tree : " + str1 + "\n");
		for(String str: arr) {
			if((str.substring(0,9).equals("*deleted*"))) {
				connectingToEverythingBut();
			}
			else if((str.substring(0,8).equals("*edited*"))) {
				edit();
			}
			else {
			 pw.append("blob : " + getFileName(str) + getHash(str) + "\n");
			}
			for(String sturng: newArr) {
				pw.append(sturng + "\n");
			}
//			}
//			else {
//				removefile = str.substring(8);
//				connectingToEverythingBut(); 
//			}
		}
		pw.close();
		return pw;
		
	}
	
	public void edit() throws IOException, NoSuchAlgorithmException {
		connectingToEverythingBut();
		Blob b = new Blob (removefile);
	}
	
	public void connectingToEverythingBut() throws IOException {
		connect(str1, removefile); 
	}
	
	public void connect(String tree, String fileName) throws IOException {
		BufferedReader s = new BufferedReader(new FileReader(tree));
		String br = s.readLine(); 
		while(s.ready()) {
			String line = s.readLine(); 
			if(line.substring(49).equals(fileName)) {
				newArr.add(br);
				break;
			}
			else {
				newArr.add(line);
			}
		}
		connect(br.substring(7),fileName);
	}
	
	
	//creating hashmap of everthing in index
	public HashMap creatingHash () throws FileNotFoundException {
		HashMap <String,String> hashie = new HashMap (); 
		for(String str: arr) {
			hashie.put(getFileName(str), getHash(str));
		}
		return hashie; 
	}
	// getting hash of everthing in index + parent tree
	public String gettingHash() throws IOException, NoSuchAlgorithmException {
		HashMap <String,String> hashie = creatingHash();
		String str = ""; 
		for(String key : hashie.keySet()) {
			str += key + hashie.get(key) ;
		}
		str += str1;
		return getSha1Name(str);
	}
	//gets Hash of file
	public static String getHash(String str) {
		String hash = str.substring(str.length()-40);
		return hash; 
	}
	// gets fileName
	public static String getFileName(String str) {
		String fileName = str.substring(0,str.length()-42);
		return fileName; 
	}

//	public static ArrayList convertIndex() throws FileNotFoundException {
//		Scanner s = new Scanner(new File("index.txt"));
//		ArrayList<String> list = new ArrayList<String>();
//		while (s.hasNext()){
//		    list.add(s.next());
//		}
//		s.close();
//		return list; 
////		File index = new File("index");
////		try {
////		      FileWriter myWriter = new FileWriter("tree.txt");
////		      for(int i =0; i < countingNumberOfLines("index.txt"); i++) {
////		    	  myWriter.write("blob :" + 
////		      }
////		      myWriter.write("Files in Java might be tricky, but it is fun enough!");
////		      myWriter.close();y7h
////		      System.out.println("Successfully wrote to the file.");
////		    } catch (IOException e) {
////		      System.out.println("An error occurred.");
////		      e.printStackTrace();
////		    }
//	}
	
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
	public String returnName() {
		return name; 
	}
}
