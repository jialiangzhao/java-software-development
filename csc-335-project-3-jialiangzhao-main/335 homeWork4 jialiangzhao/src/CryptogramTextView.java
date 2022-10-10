/**
 * @author: jialiangzhao
*Classroom: csc 335
*file:CryptogramTextView.java
*Content: This is the second part of main. He is responsible
* for telling you in plain text. How to play the game. You
*  can use the same operation commands as project2 to control it.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class CryptogramTextView {
	/**Responsible for running the text part is triggered by main -text.
	 * 
	 * @author zhaojialiang
	 *
	 */
	public final String arg1="src/quotes.txt";
	public CryptogramTextView(String args[]) throws FileNotFoundException  {
		Scanner scanner;
		ArrayList<String> content= new ArrayList<String>();
		
		String[] command;
		Scanner file = new Scanner(new File(arg1));
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
