/*Author: jialiangzhao
Classroom: csc 335
file:CryptogramModel.java
Content: This is an implementation of obtaining a string and randomly creating 
different letters corresponding to different letters. Bring in the previous string 
again to shuffle it. At the same time, this class also contains the hashmap 
modified by the user, which can bring the corresponding letter into the answer
for the user to check.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CryptogramModel {
	// private variable(s) to store the answer, encryption key, decryption key
	String a ;
	String j;
	String b="";
	String userGuess="";
	ArrayList<Character> AZ ;
	Character[] newAZ = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };
	 HashMap<Character,Character> begin;
	 HashMap<Character,Character> guess;
	 HashMap<Character,Character> correctAnswer;

	public CryptogramModel(String a)  {
		/*
		 * Read a random line from the quotes.txt file. Make the keys and set the answer
		 */
		this.a=a.toUpperCase();
		 j=this.a;
		AZ=new ArrayList<Character>();
		for (int i = 0; i < newAZ.length; i++) {
			AZ.add(newAZ[i]);
		}
		Collections.shuffle(AZ);
		begin=new HashMap<Character,Character>();
		guess=new HashMap<Character,Character>();
		correctAnswer=new HashMap<Character,Character>();
		
		for (int i = 0; i < newAZ.length; i++) {
			int index=j.indexOf(newAZ[i]);
			if (index!=-1) {
				begin.put(newAZ[i],AZ.get(i));
				correctAnswer.put(AZ.get(i),newAZ[i]);
			}
			}
	
		for (int i = 0; i < a.length(); i++) {
			if (begin.containsKey(j.charAt(i))) {
				
				b+=begin.get(j.charAt(i));
			}else {
				b+=j.charAt(i);
			}
		}
		

	}

	public void setReplacement(char encryptedChar, char replacementChar) {
		/* add to our decryption attempt */
		if(correctAnswer.containsKey(Character.toUpperCase(encryptedChar))) {
		guess.put(Character.toUpperCase(encryptedChar),Character.toUpperCase(replacementChar));
		}
	}

	public String getEncryptedString() {
		/* return the fully encrypted string */
		return b;
	}

	public String getDecryptedString() {
		/*
		 * return the decrypted string with the guessed replacements or spaces
		 */
		userGuess="";
		for (int i = 0; i < a.length(); i++) {
			if (guess.containsKey(b.charAt(i))) {
				
				userGuess+=guess.get(b.charAt(i));
			}else {
				if(Character.isLetter(b.charAt(i))) {
					userGuess+=" ";}else {
						userGuess+=b.charAt(i);
				}
			}
		}
		return userGuess;
	}

	public String getAnswer() {
		/* return the answer */
		return a;
	}
}
