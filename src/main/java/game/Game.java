package game;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Game {
	
	public int[] targetCoords = {0,0};
	public int[] playerLocation = {0,0};
	public double distance;
	boolean travelling;
	int moveCount = 0;
	
	public void begin() {
		
		randomLocation();
		System.out.println(
				"You wake up, finding yourself in a misty, grey swampland that stretches as far as the eye can see."
				+ "\nWith no idea of where you are, you have no choice but to WANDER.");
				
		Scanner s = new Scanner(System.in);
		travelling = true;
		
		do {
			
			Actions action = getAction(s.nextLine());
			if (action != null) {
				if (action != Actions.WANDER && moveCount == 0) {
					System.out.println("You don't know what direction that is in.");
				}
				else{
					reactions(action);
					narration(moveCount);
					moveCount++;
					findDistance();
				}
				
			}			
		} while (travelling);
		
		s.close();		
	}	
	
	private void narration(int i) {
		switch(i) {
		case 0:
			System.out.println("You feel something humming lightly on your arm. Looking down, you notice a compass strapped to your wrist."
					+ "\nThe four cardinal points are on on its face, with a small dial near the bottom reading a distance and the needle is pointing off into the gloom.");
		}
		
	}

	private void randomLocation() {		
		targetCoords[0] = 1;//(rand.nextInt(5)+3
		targetCoords[1] = 1;//rand.nextInt(5)+3		
	}

	private void findDistance() {
		
		int xDist = targetCoords[0] - playerLocation[0];
		int yDist = targetCoords[1] - playerLocation[1];		
		distance = Math.hypot(xDist, yDist);
		
		DecimalFormat df = new DecimalFormat("#.##");
		String shortDist = df.format(distance);
		
		if (distance == 0) {
			travelling = false;
			foundLocation();
		}
		
		System.out.println("The compass indicactes there is "+shortDist+"m left.\n\nWhat would you like to do:\n1. Go NORTH\n2. Go EAST\n3. Go SOUTH\n4. Go WEST\n");
		
	}

	private void foundLocation() {
		System.out.println("Emerging through the gloom of the swamp, you stumble upon a old house that looks like it's been through better days. As you get closer, you also notice a pair of suspiciously placed glittery red slippers and an attached note. \"Put me on and click your heels thrice while thinking of your greatest wish!\"");
		
	}

	public Actions getAction(String input) {			
		Actions action = doAction(input);		
		return action;
	}

	private Actions doAction(String action) {
		Player p = new Player();
		switch (action.toLowerCase()) {
		case "north":			
			p.goNorth(playerLocation);			
			return Actions.NORTH;
		case "south":
			
			p.goSouth(playerLocation);
			return Actions.SOUTH; 
		case "east":			
			p.goEast(playerLocation);
			return Actions.EAST;
		case "west":
			p.goWest(playerLocation);
			return Actions.WEST;
		case "wander":
			p.wanderAround(playerLocation);
			return Actions.WANDER;
		default:
			System.out.println("Sorry, you can't do that here");
			return null; 
		}
	}
	
	private void reactions(Actions act) {
		
		switch (act) {
			case NORTH:
				System.out.println("\nYou move North\n");
				break;
			case SOUTH:
				System.out.println("\nYou move South\n");
				break;
			case EAST:
				System.out.println("\nYou move East\n");
				break;
			case WEST:
				System.out.println("\nYou move West\n");
				break;
			case WANDER:
				System.out.println("\nYou wander aimlessly\n");
				break;				
			default:
				break;
				
		}		
	}

}
