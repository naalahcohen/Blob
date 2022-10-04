package tester;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import git.Blob;
import git.Commit;
import git.Index;
import git.TesterMethods;


class JUnitTest {

	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		Index indxie = new Index ();
		indxie.init(); 
	}
	
	@AfterAll
	static void tearDownAfterClass() throws IOException {
		
	}
	
	@Test
	void testCommit() throws NoSuchAlgorithmException, IOException {
		Index indxie = new Index ();
		indxie.init(); 
		Blob b = new Blob ("test1.txt"); 
		indxie.add("test1.txt");
		Blob b2 = new Blob ("test2.txt"); 
		indxie.add("test2.txt");
		Commit com = new Commit ("i hate myself and my life", "Lauren LaPorta",null);

		Blob b3 = new Blob ("test3.txt"); 
		indxie.add("test3.txt");
	
	
		Commit com2 = new Commit ("i am so cool", "Lauren LaPorta",com.sha1());
		

		Blob b4 = new Blob ("test4.txt"); 
		indxie.add("test4.txt");
		String s = com2.sha1();
		Commit com3 = new Commit ("crys", "Lauren LaPorta", com2.sha1());
		
		Blob b5 = new Blob ("test6.txt"); 
		indxie.add("test6.txt");
		Commit com4 = new Commit ("bye", "Lauren LaPorta", com3.sha1());
		
	}
 
}
