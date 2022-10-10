/**
* @author: jialiangzhao
* Classroom: csc 335
*file:CryptogramController.java
*Content: He simply realized the control function, and compare 
*and check how much remains to be completed. To change the 
*content of the corresponding string. When you are all right,
 * he will end the game.
*/
import java.util.ArrayList;
import java.util.HashMap;

public class CryptogramController {
	public CryptogramModel model;
	public int over;
	public CryptogramController(CryptogramModel model) {
		this.model=model;
		 over=0;
	}
	/**
	 * This code loops to check whether it is over and
	 *  passes a special condition 
	 * @return true end will return true
	 */
	public boolean isGameOver() {
		
		if(this.getUsersProgress().equals("0")) {
			
			if(model.j.equals(model.userGuess)) {
				
				return false;
			}
		}
		if(over==1) {
			return false;
		}
		
		return true;
	}
	/**
	 * 
	 * @param letterToReplace This is the word that needs to be replaced
	 * @param replacementLetter  This is the value 
	 * corresponding to the word to be replaced.
	 */
	public void makeReplacement(char letterToReplace, char replacementLetter) {
		
		model.setReplacement(letterToReplace,replacementLetter);
		this.getEncryptedQuote();
		}
	/**
	 * This is a very complex code, it will return complex 
	 * correspondence.
	 * @return  Then print out the answer.
	 */
	public String getEncryptedQuote() { /* for the view to display */
		String test1="";
		String test2="";
		int wordLength=0;
		String word=model.getEncryptedString();
		String word2=model.getDecryptedString();
		int j;
		int temp;
		String wordTest;
		for(int i=0;i<word.length();i++) {
			
			
			if(wordLength<9) {
				if(!(word.charAt(i)>='A' && word.charAt(i)<='Z')) {
					temp=wordLength;
					wordTest=test1;
					j=i;
					j++;
					if(j<word.length()) {
					
					
					while(word.charAt(j)>='A' && word.charAt(j)<='Z') {
					
						wordTest+=word.charAt(j);
						temp++;
						j++;
						if(j>=word.length()) {
							break;
						}
					}
					}
					if(temp>=9) {
						
						test2+=word2.charAt(i);
						test1+=word.charAt(i);
						wordLength=0;
						System.out.println(test2);
						System.out.println(test1);
						test2="";
						test1="";
						System.out.println();
						continue;
					}
				}
				test1+=word.charAt(i);
				 test2+=word2.charAt(i);
				 wordLength++;
				 if(i==word.length()-1) {
					 System.out.println(test2);
						System.out.println(test1);
						System.out.println();
					}
				 continue;
			}
			test2+=word2.charAt(i);
			test1+=word.charAt(i);
			wordLength=0;
			System.out.println(test2);
			System.out.println(test1);
			test2="";
			test1="";
			System.out.println();
		}
		return "";
	}
	/**
	 * This is a function added by myself. It will check how many unfinished 
	 * corresponding characters are still before the end of the check, and
	 *  how many uncorresponding characters are left in the return.
	 * @return how many uncorresponding characters are left in the return.
	 */
	public String getUsersProgress() { /* for the view to display */
		int remain=model.correctAnswer.size()-model.guess.size();
		String number=""+remain;
		return number;
	}
	/**
	 * This is a help to force the end code, after using it, when
	 *  isgameover checks here, you will find the exit input.
	 *   Finally, the game is forced to end.
	 */
	public void goOver() {
		this.over=1;
	}
	/**
	 * print help
	 */
	public void help() {
		 System.out.println("replace X by Y – replace letter X by"
			 		+ " letter Y in our attempted solution\n");
			 System.out.println("X = Y–ashortcutforthissamecommand\n");
			 System.out.println("freq – Display the letter frequencies in the en"
			 		+ "crypted quotation (i.e., how many of\n" + 
			 		"letter X appear) like:");
			 System.out.println("A:3 B:8 C:4 D:0 E:12F:4G:6\n");
			 System.out.println("hint – display one correct mapping"
			 		+ " that has not yet been guessed\n");
			 System.out.println("exit – Ends the game early\n");
			 System.out.println("help – List these commands");
	}
	public void work(String[] command) {
		Character read1;
		Character read2;
	if(command[0].equals("hint")) {
		 model.hint();
		 this.getEncryptedQuote();
		
 }else if(command[0].equals("freq")) {
		 
		 model.display();
		
		 
	 }else if(command[0].equals("help") ){
		this.help();
		 
	 }
	 else if(command[0].equals("exit") ) {
			this.goOver();
	 }else if(command[0].equals("replace")) {
		 read1 = command[1].charAt(0);
		 read2 = command[3].charAt(0);
		 this.makeReplacement(read1, read2);
			
		
		 
	 }else if(command[1].equals("=")) {
		 read1 =command[0].charAt(0);
		 read2 =command[2].charAt(0);
		 this.makeReplacement(read1, read2);
			
			
		 
	 }	}
}
