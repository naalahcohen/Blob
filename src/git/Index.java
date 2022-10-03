package git;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Index {
	private HashMap<String,String> objects;
	private String SHA1="";
	private FileWriter fw; 
	
	public Index()
	{
	
	}
	
	public void init()
	{
		objects = new HashMap<String,String>();
		new File("objects").mkdir();
		File f = new File("objects/" + "index");
		File head = new File ("objects/" + "head");
	}
	
	
	
	public void add(String fileName) throws NoSuchAlgorithmException, IOException
	{
		Blob b = new Blob(fileName);
		SHA1 = b.getSHA1();
		BufferedWriter p = new BufferedWriter(new FileWriter("index", true));
		p.append(fileName + " : "+ SHA1 + "\n");
		p.close();
//		objects.put(fileName, SHA1);
//		update();
	}
	
	public void remove(String fileName) throws FileNotFoundException
	{
		File remove = new File("objects/",objects.get(fileName));
		remove.delete();
		if (objects.containsKey(fileName))
		{
			objects.remove(fileName);
		}
		update();
	}
	
	public String getFileString(String fileName) throws IOException
	{
	FileInputStream fstream = new FileInputStream("index");
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	String strLine = "";
	String str = "";

	//Read File Line By Line
	while ((strLine = br.readLine()) != null)   {
	  // Print the content on the console - do what you want to do
	  str += strLine;
	}
		return str;
	}
	
	public void update() throws FileNotFoundException
	{
		PrintWriter f = new PrintWriter("index"); //prepares the index file to write 
		for (String name: objects.keySet()) {
		    String key = name.toString();
		    String value = objects.get(name).toString();
		    f.println(key + " : " + value);
		}
		f.close();
	}
	
	public void delete(String fileName) throws FileNotFoundException {
		PrintWriter f = new PrintWriter("index");
		f.append("*deleted*" + fileName);
		f.close();
	}

	
	}

