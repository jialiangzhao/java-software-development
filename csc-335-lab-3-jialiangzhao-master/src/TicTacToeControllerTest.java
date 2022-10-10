import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTacToeControllerTest {

	@Test
	void testIsGameOver() {
		TicTacToeModel board = new TicTacToeModel();
		TicTacToeController ticTacToe = new TicTacToeController(board);
		assertFalse(ticTacToe.isGameOver());
	}
	@Test
	void testIsGameOver1() {
		TicTacToeModel board = new TicTacToeModel();
		TicTacToeController ticTacToe = new TicTacToeController(board);
		board.placeX(1,1);
		board.placeO(0,0);
		board.placeX(0,2);
		board.placeO(2,0);
		board.placeX(1,0);
		board.placeO(1,2);
		board.placeX(2,1);
		board.placeO(2,2);
		board.placeX(0,1);
	
		assertTrue(ticTacToe.isGameOver());
	}
	@Test
	void testIsGameOver2() {
		TicTacToeModel board = new TicTacToeModel();
		TicTacToeController ticTacToe = new TicTacToeController(board);
		board.placeX(0,1);
		board.placeX(0,2);
		board.placeX(0,0);
	
		assertTrue(ticTacToe.isGameOver());
	}
	@Test
	void testIsGameOver3() {
		TicTacToeModel board = new TicTacToeModel();
		TicTacToeController ticTacToe = new TicTacToeController(board);
		board.placeX(1,0);
		board.placeX(2,0);
		board.placeX(0,0);
	
		assertTrue(ticTacToe.isGameOver());
	}
	@Test
	void testIsGameOver4() {
		TicTacToeModel board = new TicTacToeModel();
		TicTacToeController ticTacToe = new TicTacToeController(board);
		board.placeO(0,0);
		board.placeO(1,1);
		board.placeO(2,2);
	
		assertTrue(ticTacToe.isGameOver());
	}
	@Test
	void testIsGameOver5() {
		TicTacToeModel board = new TicTacToeModel();
		TicTacToeController ticTacToe = new TicTacToeController(board);
		board.placeO(0,2);
		board.placeO(1,1);
		board.placeO(2,0);
	
		assertTrue(ticTacToe.isGameOver());
	}
	

}
