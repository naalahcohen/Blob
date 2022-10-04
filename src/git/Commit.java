package git;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		TreeObject tobj = new TreeObject (arr(), parentTree()); 
		prevT = tobj.curfileName;
		makeFile();
		PrintWriter head = new PrintWriter("head");
		head.print(generateSHA1(getContents()));
		head.close();
		FileWriter f = new FileWriter ("index");
		PrintWriter writer = new PrintWriter(f);
		writer.print("");
		writer.close();
	}
	//creates arrayList of index 
	public ArrayList <String> arr() throws NoSuchAlgorithmException, IOException{
		Scanner s = new Scanner(new File ("index"));
		Path p = Paths.get("index");
		String c = Files.readString(p);
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNextLine()){
			String ss = s.nextLine();
		    list.add(ss);
		}
		s.close();
		return list; 
	}
	
	public String parentTree () throws IOException {
		if (parent!=null) {
		BufferedReader br = new BufferedReader(new FileReader("objects/"+ parent));
			return br.readLine();
		}
		return "null";
	}
	
	public String returnFirstLine () throws FileNotFoundException, NoSuchAlgorithmException, IOException {
			if (parent!=null) {
				BufferedReader br = new BufferedReader(new FileReader("objects/"+parent));
				br.readLine();
				br.readLine();
				return br.readLine();
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
//		contents += returnFirstLine();
		contents += "\n" + author;
		contents += "\n" + getDate();
		contents += "\n" + summary;
		return contents;
	}
	
	public String cont () throws IOException, NoSuchAlgorithmException {
		String contents = "";
	//	contents += returnFirstLine();
		contents += prevT; 
		contents += "\n"+ parent;
	//	contents += "\n" + prevT;
		contents += "\nnull";
		if (parent!=null) {
		FileReader f = new FileReader ("objects/" +parent);
		BufferedReader br = new BufferedReader (f);
		String pt1 = br.readLine() + "\n" + br.readLine();
		br.readLine();
		String pt2 = br.readLine() + "\n" + br.readLine() +  "\n" + br.readLine() + "\n";
		PrintWriter rew = new PrintWriter ("objects/" +parent);
		rew.write (pt1 + "\n" + sha1() + "\n" + pt2);
		rew.close();
		}
		contents += "\n" + author;
		contents += "\n" + getDate();
		contents += "\n" + summary;
		return contents;
	
	}
	
	private static String generateSHA1(String password)
	{
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}



	public String getDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return(dtf.format(now));
	}
	
	public String makeFile() throws NoSuchAlgorithmException, IOException
	{
		String fileName = generateSHA1(getContents());
		 Path p = Paths.get("objects/"+ fileName);
	        try {
	            Files.writeString(p, cont(), StandardCharsets.ISO_8859_1);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("make file " + fileName);
	        return fileName;
	 
	}
}


//private static String byteToHex(final byte[] hash)
//{
//    Formatter formatter = new Formatter();
//    for (byte b : hash)
//    {
//        formatter.format("%02x", b);
//    }
//    String result = formatter.toString();
//    formatter.close();
//    return result;
//}
	
//	public void updateParent(String parent) throws NoSuchAlgorithmException, IOException
//	{
//		String str5 ="objects/" + parent;
//		System.out.println( "updateParent" + str5);
//		setVariable(3,generateSHA1(getContents()),str5);
//	}
//	
//		public static void setVariable(int lineNumber, String data, String fileName) throws IOException {
//			    Path path = Paths.get(fileName);
//			    ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path, StandardCharsets.UTF_8);
//			    lines.set(lineNumber -2, data);
//			    Files.write(path, lines, StandardCharsets.UTF_8);
//			}

