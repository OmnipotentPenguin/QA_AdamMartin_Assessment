package game;

public class Player {	

	public int[] goNorth(int[] playerLocation) {	
		
		int xLocation = playerLocation[0];
		xLocation++;
		playerLocation[0] = xLocation;
		return playerLocation;	
		
	}

	public int[] goSouth(int[] playerLocation) {
		
		int xLocation = playerLocation[0];
		xLocation--;
		playerLocation[0] = xLocation;
		return playerLocation;	
		
	}

	public int[] goEast(int[] playerLocation) {

		int yLocation = playerLocation[1];
		yLocation++;
		playerLocation[1] = yLocation;
		return playerLocation;
		
	}

	public int[] goWest(int[] playerLocation) {

		int yLocation = playerLocation[1];
		yLocation--;
		playerLocation[1] = yLocation;
		return playerLocation;
		
	}

}
