import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class ExceptionTest {

	@Test // expected along with test does not work with Junit 5!!!!!!!!
	void testSnake() {
		Tile testTile = new Snake(100);
		
		Assertions.assertThrows(SnakeBiteException.class ,()->{
			testTile.shake();
		});
	}
	@Test
	void testVulture() {
		Tile testTile = new Vulture(100);
		
		Assertions.assertThrows(VultureBiteException.class ,()->{
			testTile.shake();
		});
	}
	@Test
	void testCricket() {
		Tile testTile = new Cricket(100);
		
		Assertions.assertThrows(CricketBiteException.class ,()->{
			testTile.shake();
		});
	}
	@Test
	void testTrampolene() {
		Tile testTile = new Trampolene(100);
		
		Assertions.assertThrows(TrampoleneException.class ,()->{
			testTile.shake();
		});
	}
	
	@Test
	void TestGameSnake() {
		for(int i=0; i<10;++i) {
			Game testGame=new Game("Name",500,false,new SnakeBiteException("no"));
			Assertions.assertThrows(SnakeBiteException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameVulture() {
		for(int i=0; i<10;++i) {
			Game testGame=new Game("Name",500,false,new VultureBiteException("no"));
			Assertions.assertThrows(VultureBiteException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameCricket() {
		for(int i=0; i<10;++i) {
		Game testGame=new Game("Name",500,false,new CricketBiteException("no"));
			Assertions.assertThrows(CricketBiteException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameTrampolene() {
		for(int i=0; i<10;++i) {
			Game testGame=new Game("Name",500,false,new TrampoleneException("no"));
			Assertions.assertThrows(TrampoleneException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameWinner() {
		for(int i=0; i<10;++i) {	
		Game testGame=new Game("Name",500,false,new GameWinnerException("no"));
			Assertions.assertThrows(GameWinnerException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestSaveGame() throws FileNotFoundException, IOException, ClassNotFoundException {
		for(int i=0; i<10;++i) {
		Game testGame = new Game("Name",500,false,new GameSaveException("no"));
		Game toCheck = null;
		ObjectInputStream get = null;
    try{
        get = new ObjectInputStream(new FileInputStream("Name.bin"));
        toCheck  =(Game)get.readObject();		
        }finally{
        	
        	get.close();
        }
    	Assert.assertEquals(testGame, toCheck);
	}
	}
}
