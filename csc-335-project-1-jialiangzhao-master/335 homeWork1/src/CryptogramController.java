/*Author: jialiangzhao
Classroom: csc 335
file:CryptogramController.java
Content: He simply realized the control function, and compare 
and check how much remains to be completed. To change the 
content of the corresponding string. When you are all right,
 he will end the game.
*/
import java.util.ArrayList;
import java.util.HashMap;

public class CryptogramController {
	CryptogramModel model;
	public CryptogramController(CryptogramModel model) {
		this.model=model;
	}

	public boolean isGameOver() {
		
		if(this.getUsersProgress().equals("0")) {
			
			if(model.j.equals(model.userGuess)) {
				
				return false;
			}
		}
		return true;
	}

	public void makeReplacement(char letterToReplace, char replacementLetter) {
		
		model.setReplacement(letterToReplace,replacementLetter);}

	public String getEncryptedQuote() { /* for the view to display */
		
		return model.getDecryptedString();
	}

	public String getUsersProgress() { /* for the view to display */
		int remain=model.correctAnswer.size()-model.guess.size();
		String number=""+remain;
		return number;
	}

}
