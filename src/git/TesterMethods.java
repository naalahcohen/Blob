package git;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class TesterMethods {
	private static String contents;
	
	public static void writeStringToFile(String fileName, String fileContents) throws IOException {
		String sha1Code=getSha1Name(fileContents);
		File sha1File = new File("./objects/"+sha1Code);
        sha1File.createNewFile();
        
        FileWriter myWriter = new FileWriter(sha1File.getName());
        myWriter.write(fileContents);
        myWriter.close();
        
        contents=fileContents;
	}
	
	public static String getContents() {
		return contents;
	}
	
	public static String getSha1Name(String password)
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
	
	public static void deleteFile(String fileToDelete) throws IOException {
		String sha1OfFileToRemove= getSha1Name(contents);
		String path= "./object/"+ sha1OfFileToRemove;
		Files.delete(Paths.get(path));
	}
}
