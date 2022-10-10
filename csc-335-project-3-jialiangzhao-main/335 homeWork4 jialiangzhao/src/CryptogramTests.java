import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class CryptogramTests {

	@Test
	void test() {
		CryptogramModel a=new CryptogramModel("Everyone knows"
				+ " that debugging is"
				+ " twice as hard as writing a program in the first place."
				+ " So if you're as clever as you can be when you write it, "
				+ "how will you ever debug it? - Brian Kernighan");
		 CryptogramController b=new  CryptogramController(a);
		 a.hint();
		 
		 a.getAnswer() ;
		 b. makeReplacement('A','B');
		 a.hint();
		 b.getEncryptedQuote();// it showed the current guessing situation
		 String[] command= {"help"};
		 String[] command1= {"hint"};
		 String[] command2= {"freq"};
		 String[] command3= {"A","=","B"};
		 String[] command4= {"replace","A","by","B"};
		 String[] command5= {"exit"};
		 b.work(command);
		 b.work(command1);
		 b.work(command2);
		 b.work(command3);
		 b.work(command4);
		 b.work(command5);
		 b.goOver();// it is force to exit
		 b.isGameOver();// game will be show over
		 Cryptograms c= new  Cryptograms();
		

		 
	}

}
