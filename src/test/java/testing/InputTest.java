package testing;

import org.junit.Assert;
import org.junit.Test;

import game.Game;
import game.Player;


public class InputTest {

	@Test
	public void goNorthTest() {
		Game game = new Game();
		Player player = new Player();
		
		int pXLoc = game.playerLocation[0];		
		player.goNorth(game.playerLocation);	
		
		Assert.assertEquals(pXLoc+1, game.playerLocation[0]);
	}
	
	@Test
	public void goSouthTest() {
		Game game = new Game();
		Player player = new Player();
		
		int pXLoc = game.playerLocation[0];		
		player.goSouth(game.playerLocation);	
		
		Assert.assertEquals(pXLoc-1, game.playerLocation[0]);
	}
		
	@Test
	public void goEastTest() {
		Game game = new Game();
		Player player = new Player();
		
		int pYLoc = game.playerLocation[1];		
		player.goEast(game.playerLocation);	
		
		Assert.assertEquals(pYLoc+1, game.playerLocation[1]);
	}
	
	@Test
	public void goWestTest() {
		Game game = new Game();
		Player player = new Player();
		
		int pYLoc = game.playerLocation[1];		
		player.goWest(game.playerLocation);	
		
		Assert.assertEquals(pYLoc-1, game.playerLocation[1]);
	}

}
