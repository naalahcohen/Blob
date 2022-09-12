import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Index {
	private HashMap<String,String> objects;
	private String SHA1="";
	
	public Index()
	{
		objects = new HashMap<String,String>();
	}
	
	public void init()
	{
		new File("objects").mkdir();
		File f = new File("objects/" + "index");
	}
	
	public void add(String fileName) throws NoSuchAlgorithmException, IOException
	{
		Blob b = new Blob(fileName);
		SHA1 = b.getSHA1();
		objects.put(fileName, SHA1);
		PrintWriter out = new PrintWriter("index");
		out.print(fileName + " : " + SHA1);
		out.close();
		// add objects to index using print writer
	}
	
	public void remove(String fileName) throws FileNotFoundException
	{
		File remove = new File("objects/",objects.get(fileName));
		remove.delete();
		if (objects.containsKey(fileName))
		{
			objects.remove(fileName);
		}
		PrintWriter out = new PrintWriter("index");
		for (String name: objects.keySet()) {
		    String key = name.toString();
		    String value = objects.get(name).toString();
		    out.print(key + " : " + value);
		}
		out.close();
	}

	}
