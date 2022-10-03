package tester;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import git.Blob;
import git.Commit;
import git.Index;
import git.TreeObject;

public class TreeObjTester {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
	
//	ArrayList<String> arr = new ArrayList<String>();
//	arr.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
//	
//	
//	TreeObject test= new TreeObject(arr);
		Index indxie = new Index ();
		indxie.init(); 
		Blob b = new Blob ("test1.txt"); 
		indxie.add("test1.txt");
		Blob b2 = new Blob ("test2.txt"); 
		indxie.add("test2.txt");
		Commit com = new Commit ("i hate myself and my life", "Lauren LaPorta",null);
//		com.makeFile();
//		ArrayList<String> treeContents = com.arr();
//		TreeObject t = new TreeObject(treeContents, "null");
//		File file = new File ("index"); 
//		PrintWriter writer = new PrintWriter(file);
//		writer.print("");
//		writer.close();
		Blob b3 = new Blob ("test3.txt"); 
		indxie.add("test3.txt");
	
	
		Commit com2 = new Commit ("i am so cool", "Lauren LaPorta",com.sha1());
		System.out.println("sha1" + com.sha1());
//		com2.makeFile();
//		ArrayList<String> treeContent = com.arr();
//		TreeObject t2 = new TreeObject(treeContents, "ac9587d6b4edbd5bede560358a397ccb7f90839d");
//		File file2 = new File ("index"); 
//		PrintWriter writer2 = new PrintWriter(file2);
//		writer2.print("");
//		writer2.close();
//		
//		
//		Blob b3 = new Blob ("test3.txt"); 
//		indxie.add("test3.txt");
//		String s = com2.sha1();
//		Commit com3 = new Commit ("hey besties", "Lauren LaPorta", com2.sha1());
//		System.out.println("sha2" + com2.sha1());
//		com3.makeFile();
//		ArrayList<String> treeConten = com.arr();
//		TreeObject t3 = new TreeObject(treeContents, "3b46e499e180621ea52520459ebf03c84eb43230");
//		File file3 = new File ("index"); 
//		PrintWriter writer3 = new PrintWriter(file3);
//		writer3.print("");
//		writer3.close();
		
	}
}
