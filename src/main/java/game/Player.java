package game;

import java.util.Random;

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

	public int[] wanderAround(int[] playerLocation) {

		Random rand = new Random();
		int direction = rand.nextInt(4);

		switch (direction) {
		case 0:
			goNorth(playerLocation);
			break;
		case 1:
			goSouth(playerLocation);
			break;
		case 2:
			goEast(playerLocation);
			break;
		case 3:
			goWest(playerLocation);
			break;
		}
		return playerLocation;
	}
}
