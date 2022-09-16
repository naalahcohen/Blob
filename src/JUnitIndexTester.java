import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JUnitIndexTester {

	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		TesterMethods.writeStringToFile("indexTest.txt", "something");
		TesterMethods.writeStringToFile("indexTest2.txt", "something2");
		TesterMethods.writeStringToFile("indexTest3.txt", "something3");
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws IOException {
		TesterMethods.deleteFile(TesterMethods.getSha1Name(TesterMethods.getContents())); 
	}
	
	@Test
	void test() throws NoSuchAlgorithmException, IOException {
		Index ind1 = new Index();
		ind1.init();
		File indexFile= new File("index");
		assertTrue(indexFile.exists());
		
		ind1.add("indexTest.txt");
		File i1= new File("./objects"+ TesterMethods.getSha1Name(TesterMethods.getContents()));
		assertTrue(i1.exists());
		
		ind1.remove("indexTest.txt");
		assertFalse(i1.exists());
		
		ind1.add("indexTest.txt"); 
		ind1.add("indexTest2.txt");
		ind1.add("indexTest3.txt");
		
		assertTrue(i1.exists());
		ind1.remove("indexTest.txt");
		assertFalse(i1.exists()); 
		
	}
 
}
