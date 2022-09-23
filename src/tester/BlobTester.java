package tester;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import git.Blob;

public class BlobTester {
	public static void main (String[] args) throws IOException, NoSuchAlgorithmException 
	{
		Blob blob1 = new Blob("blobTestFile.txt");	
		System.out.println(blob1.getSHA1());
	}
}
