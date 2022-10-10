/**
 * @author: jialiangzhao
*Classroom: csc 335
*file: Cryptograms.java
*Content: This file is mainly responsible for running 
*two classes and implementing their corresponding operations
 *to users. He also introduced a check file system.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class Cryptograms {
	/**
	 * 
	 * @param args This is the program's own input, used to scan files
	 * @throws FileNotFoundException This is an error that the file cannot be found anyway.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner;
		
		ArrayList<String> content= new ArrayList<String>();
		
		String[] command;
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
			 System.out.print("Enter a command (type help to see commands):" );
			 scanner= new Scanner(System.in);
			 command=scanner.nextLine().split(" ");
			 b.work(command);
			 System.out.println();
		 }
			System.out.println("\nYou got it!");
		 
	}

}
