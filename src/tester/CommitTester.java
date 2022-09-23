package tester;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import git.Commit;

public class CommitTester {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException
	{
		Commit c = new Commit("pTree","changed blob", "Lauren","");
		c.makeFile();
		
		Commit c1 = new Commit("pTree2", "edited blob", "Lauren2", "9a25a0373ad67204cd98b4dd702489f26c1a0aed");
		c1.makeFile();
		
		Commit c2 = new Commit("pTree3", "edited blob", "Lauren2", "8c4ebd36dd6e55fb624a469da3dbd89b5a9d01a7");
		c2.makeFile();
	}
}
