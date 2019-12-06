package testing;

import org.junit.Assert;
import org.junit.Test;

import game.Player;


public class InputTest {

	@Test
	public void goNorthTest() {
		Player player = new Player();	
		
		int[] pLoc = player.playerLocation;
		int xLoc = pLoc[0];
		
		player.goNorth();
		
		int [] pMoved = player.playerLocation;
		int xMoved = pMoved[0];
		
		Assert.assertEquals(xLoc+1, xMoved);
	}
	
	@Test
	public void goSouthTest() {
		Player player = new Player();	
		
		int[] pLoc = player.playerLocation;
		int xLoc = pLoc[0];
		
		player.goSouth();
		
		int [] pMoved = player.playerLocation;
		int xMoved = pMoved[0];
		
		Assert.assertEquals(xLoc-1, xMoved);
	}
		
	@Test
	public void goEastTest() {
		Player player = new Player();	
		
		int[] pLoc = player.playerLocation;
		int yLoc = pLoc[0];
		
		player.goEast();
		
		int [] pMoved = player.playerLocation;
		int yMoved = pMoved[0];
		
		Assert.assertEquals(yLoc+1, yMoved);
	}
}
	
	@Test
	public void goWestTest() {
		Player player = new Player();	
		
		int[] pLoc = player.playerLocation;
		int yLoc = pLoc[0];
		
		player.goWest();
		
		int [] pMoved = player.playerLocation;
		int yMoved = pMoved[0];
		
		Assert.assertEquals(yLoc-1, yMoved);
	}

}
