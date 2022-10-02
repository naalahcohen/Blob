package git;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import java.util.Scanner;
import git.TreeObject;

public class Commit {
	private String parent;
	private String next;
	private String pTree;
	private String author;
	private String summary;
	private String date;
	private String str;
	private TreeObject todiefor;
	private byte[] SHA1;
	private String contents = "";
	private String prevT;
	
	
	public Commit(String summary1, String author1, String parent1) throws IOException, NoSuchAlgorithmException
	{
		
		summary = summary1;
		author = author1;
		parent = parent1;
		next = "";
		
		String parent3 = returnFirstLine (); 
		TreeObject tobj = new TreeObject (arr(),parent3); 
		File f = new File ("index");
		PrintWriter writer = new PrintWriter(f);
		writer.print("");
		writer.close();
	}
	//creates arrayList of index 
	public ArrayList <String> arr() throws NoSuchAlgorithmException, IOException{
		BufferedReader s = new BufferedReader(new FileReader("index"));
		ArrayList<String> list = new ArrayList<String>();
		while (s.ready()){
		    list.add(s.readLine());
		}
//		list.add(returnFirstLine());
		s.close();
		return list; 
	}
	
	public String returnFirstLine () throws FileNotFoundException, NoSuchAlgorithmException, IOException {
			if (parent!=null) {
			BufferedReader brTest = new BufferedReader(new FileReader("objects/"+parent));
		    return brTest.readLine();
			}
			return "null";
	}
	
	public  String sha1() throws FileNotFoundException, NoSuchAlgorithmException, IOException {
		contents = getContents(); 
		return generateSHA1(contents);
	}
	
	
	public String getContents() throws FileNotFoundException, NoSuchAlgorithmException, IOException
	{
		String contents = "";
		contents += returnFirstLine(); 
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
	        System.out.println("make file" + fileName);
	        return fileName;
	 
	}
	
	public void updateParent(String parent) throws NoSuchAlgorithmException, IOException
	{
		String str5 ="objects/" + parent;
		System.out.println( "updateParent" + str5);
		setVariable(3,generateSHA1(getContents()),str5);
	}
	
		public static void setVariable(int lineNumber, String data, String fileName) throws IOException {
			    Path path = Paths.get(fileName);
			    ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path, StandardCharsets.UTF_8);
			    lines.set(lineNumber -2, data);
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
