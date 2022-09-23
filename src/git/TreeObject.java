import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;

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
}
