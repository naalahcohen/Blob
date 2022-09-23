package tester;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import git.Blob;
import git.TesterMethods;

class JUnitBlobTester {

	
	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		TesterMethods.writeStringToFile("test.txt", "something");
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws IOException {
		TesterMethods.deleteFile(TesterMethods.getSha1Name(TesterMethods.getContents())); 
	}
	
	@Test
	void test() throws NoSuchAlgorithmException, IOException {
		Blob blob1 = new Blob("test.txt");
		File f1= new File("./objects/"+ TesterMethods.getSha1Name(TesterMethods.getContents()));
		assertTrue(f1.exists());
	}
	
//	
	

}
