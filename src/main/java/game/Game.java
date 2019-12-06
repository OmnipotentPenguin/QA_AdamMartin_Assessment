package game;

import java.util.Scanner;

public class Game {
	
	public int[] targetCoords = {0,0};
	public int[] playerLocation = {0,0};
	public double distance;
	boolean travelling;
	
	public void begin() {	
		
		targetCoords[0] = (int) ((Math.random()*10)+5);
		targetCoords[1] = (int) ((Math.random()*10)+5);
		
		System.out.println("Which way do you go?");
		
		
		Scanner s = new Scanner(System.in);
		travelling = true;
		
		do {
			getAction(s.nextLine());		
			findDistance();
		} while (travelling);
		
		s.close();
		
		
		
		
	}
		
	private void findDistance() {
		
		int xDist = targetCoords[0] - playerLocation[0];
		int yDist = targetCoords[1] - playerLocation[1];		
		distance = Math.hypot(xDist, yDist);
		
		if (distance == 0) {
			travelling = false;
		}
		
		System.out.println("Distance left: "+distance);
		
	}

	public Actions getAction(String input) {			
		Actions action = doAction(input);		
		return action;
	}

	private Actions doAction(String action) {
		Player p = new Player();
		switch (action.toLowerCase()) {
		case "north":
			System.out.println("You move North");
			p.goNorth(playerLocation);			
			return Actions.NORTH;
		case "south":
			System.out.println("You move South");
			p.goSouth(playerLocation);
			return Actions.SOUTH; 
		case "east":
			System.out.println("You move East");
			p.goEast(playerLocation);
			return Actions.EAST;
		case "west":
			System.out.println("You move West");
			p.goWest(playerLocation);
			return Actions.WEST;
		default:
			System.out.println("Sorry, you can't do that here");
			return null; 
		}
	}

}
