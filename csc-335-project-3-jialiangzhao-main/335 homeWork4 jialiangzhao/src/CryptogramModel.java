/**
 * @author: jialiangzhao
*Classroom: csc 335
*file:CryptogramModel.java
*Content: This is an implementation of obtaining a string and randomly creating 
*different letters corresponding to different letters. Bring in the previous string 
*again to shuffle it. At the same time, this class also contains the hashmap 
*modified by the user, which can bring the corresponding letter into the answer
*for the user to check.
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
	private String a ;
	public String j;
	public String b="";
	public String userGuess="";
	public ArrayList<Character> AZ ;
	public Character[] newAZ = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };
	private HashMap<Character,Character> begin;
	public  HashMap<Character,Character> guess;
	public HashMap<Character,Character> correctAnswer;
	 public  HashMap<Character,Integer> forDis;
	 private  ArrayList<Character> correctWord;
	 public  int noSelf;
	
	/**
	 * @param a It is a piece of text, and checking 
	 * this text can produce the corresponding password.
	 */
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
	
		noSelf=0;
		while(noSelf==0) {
			Collections.shuffle(AZ);
		for (int i = 0; i < newAZ.length; i++) {
			if(newAZ[i].equals(AZ.get(i))) {
				Collections.shuffle(AZ);
				noSelf=0;
				break;
			}
			noSelf=1;
		}
	}
		
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
	/**
	 * 
	 * @param encryptedChar This is the word that needs to be replaced
	 * @param replacementChar This is the value
	 *  corresponding to the word to be replaced.
	 */
	public void setReplacement(char encryptedChar, char replacementChar) {
		/* add to our decryption attempt */
		if(correctAnswer.containsKey(Character.toUpperCase(encryptedChar))) {
		guess.put(Character.toUpperCase(encryptedChar),Character.toUpperCase(replacementChar));
		}
	}
	/**
	 * 
	 * @return Return the encrypted text for easy observation.
	 */
	public String getEncryptedString() {
		/* return the fully encrypted string */
		return b;
	}
	/**
	 * 
	 * @return Return the answer guessed by the user.
	 */
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
	/**
	 * 
	 * @return return correct answer
	 */
	public String getAnswer() {
		/* return the answer */
		return a;
	}
	/**
	 * Show how many guesses the user needs to complete
	 */
	public void display() {
	 forDis =new HashMap<Character,Integer>();
	 for(int i=0;i<newAZ.length;i++) {
		 forDis.put(newAZ[i],0);
	 }
		for(int i=0;i<b.length();i++) {
			if(forDis.containsKey(b.charAt(i)) ) {
				forDis.put(b.charAt(i),forDis.get(b.charAt(i))+1);
		}
			}
		for(int i=0;i<newAZ.length;i++) {
		System.out.print(newAZ[i]+":"+forDis.get(newAZ[i])+" ");
		if(i==6 || i==13 || i==20) {
			System.out.println();
		}
			}
		
	}
	/**
	 * Use hints to get a correct answer directly.
	 */
	public void hint() {
		correctWord=new ArrayList<Character>();
		for(int i=0;i<newAZ.length;i++) {
			if(correctAnswer.containsKey(newAZ[i])) {
				correctWord.add(newAZ[i]);
			}
		}
		for(int i=0;i<correctWord.size();i++) {
			if(!guess.containsKey(correctWord.get(i))) {
				this.setReplacement(correctWord.get(i),correctAnswer.get(correctWord.get(i)));
				break;
			}
	}
}
	
}