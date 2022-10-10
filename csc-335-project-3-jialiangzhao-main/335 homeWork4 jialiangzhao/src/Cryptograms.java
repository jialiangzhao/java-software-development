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

import javafx.application.Application;
public class Cryptograms {
	/**
	 * 
	 * @param args This is the program's own input, used to scan files
	 * @throws FileNotFoundException This is an error that the file cannot be found anyway.
	 */
	public static void main(String[] args) throws FileNotFoundException {
	
	if(args[0].equals("-text")) {
		CryptogramTextView a=new CryptogramTextView(args);
	}else if(args[0].equals("-window")) {
		Application.launch(CryptogramGUIView.class, args);
	}
		
		 
	}

}
