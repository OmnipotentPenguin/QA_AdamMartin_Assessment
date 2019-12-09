package game;

public class Hunter {
	
	public int[] goNorth(int[] hunterLocation) {

		int xLocation = hunterLocation[0];
		xLocation++;
		hunterLocation[0] = xLocation;
		return hunterLocation;

	}

	public int[] goSouth(int[] hunterLocation) {

		int xLocation = hunterLocation[0];
		xLocation--;
		hunterLocation[0] = xLocation;
		return hunterLocation;

	}

	public int[] goEast(int[] hunterLocation) {

		int yLocation = hunterLocation[1];
		yLocation++;
		hunterLocation[1] = yLocation;
		return hunterLocation;

	}

	public int[] goWest(int[] hunterLocation) {

		int yLocation = hunterLocation[1];
		yLocation--;
		hunterLocation[1] = yLocation;
		return hunterLocation;
	}
}
