/*Author: jialiangzhao
Classroom: csc 335
file: Cryptograms.java
Content: This file is mainly responsible for running 
two classes and implementing their corresponding operations
 to users. He also introduced a check file system.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Cryptograms {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner;
		Character read1;
		Character read2;
		ArrayList<String> content= new ArrayList<String>();
		Scanner file = new Scanner(new File(args[0]));
        String first = file.nextLine();
        content.add(first);
	   while(file.hasNext()) {
		   first = file.nextLine();
		   content.add(first);
	   }

	 int line=new Random().nextInt(content.size());
		 CryptogramModel a=new CryptogramModel(content.get(line));
		
		
		 CryptogramController b=new CryptogramController(a);
		 System.out.println(a.getEncryptedString());
		 while(b.isGameOver()) {
			
			 System.out.print("Enter the letter to replace: ");
			scanner= new Scanner(System.in);
			 read1 = scanner.nextLine().charAt(0);
			 
				 
			 System.out.print("Enter its replacement: ");
			 scanner= new Scanner(System.in);
			 read2 = scanner.nextLine().charAt(0);
			 System.out.println();
			 
			
			b.makeReplacement(read1, read2);
			System.out.println(b.getEncryptedQuote());
			System.out.println(a.getEncryptedString());
		 }
			System.out.println("\nYou got it!");
		 
	}

}
