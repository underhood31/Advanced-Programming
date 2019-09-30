import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import java.io.*;
class ExceptionTest {
	private final String name="Manav";
	private final int pathLength=500;
	
//	@BeforeClass
	 ExceptionTest() throws IOException {
		PrintWriter out = null;
		try {
			
			out = new  PrintWriter(new  FileWriter("arguements.txt"));
			int c;
			String testArgs =  name +" "+ Integer.toString(pathLength);
			out.println(testArgs);
//			throw new RuntimeException();
		}
		finally {
			
			if (out != null)
				out.close(); // IOException
		}
	}
	
	
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
	
	//-------------------------TESTING FROM FILE---------------------------
	
	@Test
	void TestGameSnakeFILE() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("arguements.txt"));
		String[] sr = br.readLine().trim().split("\\s+");
		String plName = sr[0];
		int sz=Integer.parseInt(sr[1]);
		for(int i=0; i<10;++i) {
			
			Game testGame=new Game(plName,sz,false,new SnakeBiteException("no"));
			Assertions.assertThrows(SnakeBiteException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameVultureFILE() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("arguements.txt"));
		String[] sr = br.readLine().trim().split("\\s+");
		String plName = sr[0];
		int sz=Integer.parseInt(sr[1]);
		for(int i=0; i<10;++i) {
			Game testGame=new Game(plName,sz,false,new VultureBiteException("no"));
			Assertions.assertThrows(VultureBiteException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameCricketFILE() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("arguements.txt"));
		String[] sr = br.readLine().trim().split("\\s+");
		String plName = sr[0];
		int sz=Integer.parseInt(sr[1]);
		for(int i=0; i<10;++i) {
		Game testGame=new Game(plName,sz,false,new CricketBiteException("no"));
			Assertions.assertThrows(CricketBiteException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameTrampoleneFILE() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("arguements.txt"));
		String[] sr = br.readLine().trim().split("\\s+");
		String plName = sr[0];
		int sz=Integer.parseInt(sr[1]);
		for(int i=0; i<10;++i) {
			
			Game testGame=new Game(plName,sz,false,new TrampoleneException("no"));
			Assertions.assertThrows(TrampoleneException.class ,()->{
				testGame.startGame();
			});
		}
	}
	@Test
	void TestGameWinnerFILE() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("arguements.txt"));
		String[] sr = br.readLine().trim().split("\\s+");
		String plName = sr[0];
		int sz=Integer.parseInt(sr[1]);
		for(int i=0; i<10;++i) {	
		Game testGame=new Game(plName,sz,false,new GameWinnerException("no"));
			Assertions.assertThrows(GameWinnerException.class ,()->{
				testGame.startGame();
			});
		}
	}
	
}
