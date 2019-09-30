import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class SerializationTest {
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
