package game;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Game {

	public int[] targetCoords = { 0, 0 };
	public int[] playerLocation = { 0, 0 };
	public int[] hunterLocation = { 0, 0 };
	
	int moveCount = 0;
	private boolean travelling;	
	private boolean caughtByHunter;
	
	public void init() {
		Scanner s = new Scanner(System.in);
		begin (s);
	}

	public void begin(Scanner s) {

		randomLocation(targetCoords);
		randomLocation(hunterLocation);
		hunterLocation[0] = hunterLocation[0]*2;
		hunterLocation[1] = hunterLocation[1]*2;

		narration("intro");		
		
		//Scanner s = new Scanner(System.in);
		travelling = true;
		caughtByHunter = false;
		moveCount = 0;

		while (travelling) {

			Actions action = getAction(s.nextLine());
			if (action != null) {
				if (action != Actions.WANDER && moveCount == 0) {
					narration("noDirection");
				} else {
					reactions(action);
					hunterMove(playerLocation);
					narration(String.valueOf(moveCount));
					moveCount++;
					
					if (findDistance(targetCoords,playerLocation) == 0) {
						travelling = false;
					} else {
						narration("compassDistance");
						narration("hunterDistance");
						narration("nextDirection");
					}
				}
			}
		}

		if (caughtByHunter == true) {
			narration("caught");
		} else {
			narration("foundShoes");
		}
		
		narration("replay?");
		if (replay(s.nextLine())) {
			begin(s);
		} else {
			narration("goodbye");
			s.close();
		}
	}

	private boolean replay(String choice) {		
		
		switch (choice.toLowerCase()) {
		
			case "y":
			case "yes":
				return true;
				
			default:
				return false;
		}
				
	}

	private void randomLocation(int[] random) {
		Random rand = new Random();
		int posVal0 = rand.nextInt(2);
		int posVal1 = rand.nextInt(2);

		random[0] = rand.nextInt(5) + 3;
		random[1] = rand.nextInt(5) + 3;

		if (posVal0 == 0) {
			random[0] = random[0] * (-1);
		}
		if (posVal1 == 0) {
			random[1] = random[1] * (-1);
		}

	}

	private double findDistance(int[] from, int[] to) {		
		int xDist = from[0] - to[0];
		int yDist = from[1] - to[1];
		double distance = Math.hypot(xDist, yDist);		
		return distance;
	}
	
	private String printDistanceTwoDP(double distance) {
		DecimalFormat df = new DecimalFormat("#.##");
		String shortDist = df.format(distance);		
		return shortDist;
	}
	
	private String printDistanceInt(double distance) {
		DecimalFormat df = new DecimalFormat("#");
		String shortDist = df.format(distance);		
		return shortDist;
	}

	public Actions getAction(String input) {
		Actions action = doAction(input);
		return action;
	}

	private Actions doAction(String action) {
		Player p = new Player();
		switch (action.toLowerCase()) {
		case "n":
		case "north":
		case "go north":
			p.goNorth(playerLocation);
			return Actions.NORTH;
		case "s":
		case "south":
		case "go south":
			p.goSouth(playerLocation);
			return Actions.SOUTH;
		case "e":
		case "east":
		case "go east":
			p.goEast(playerLocation);
			return Actions.EAST;
		case "w":
		case "west":
		case "go west":
			p.goWest(playerLocation);
			return Actions.WEST;
		case "wander":
		case "go wander":
			p.wanderAround(playerLocation);
			return Actions.WANDER;
		default:
			narration("cantDoThat");
			return null;
		}
	}

	private void hunterMove(int[] playerLocation) {

		Hunter h = new Hunter();
		int xDist = playerLocation[0] - hunterLocation[0];
		int yDist = playerLocation[1] - hunterLocation[1];

		if (xDist <= 0 && yDist <= 0) { // --

			if ((xDist * (-1)) > (yDist * (-1))) {
				h.goSouth(hunterLocation);
			} else {
				h.goWest(hunterLocation);
			}

		} else if (xDist <= 0 && yDist >= 0) { // -+

			if ((xDist * (-1)) > yDist) {
				h.goSouth(hunterLocation);
			} else {
				h.goEast(hunterLocation);
			}

		} else if (xDist >= 0 && yDist <= 0) { // +-

			if (xDist > (yDist * (-1))) {
				h.goNorth(hunterLocation);
			} else {
				h.goWest(hunterLocation);
			}

		} else if (xDist >= 0 && yDist >= 0) { // ++

			if (xDist > yDist) {
				h.goNorth(hunterLocation);
			} else {
				h.goEast(hunterLocation);
			}

		} else {
			throw new HunterIsLostException();
		}

		if ((xDist <= 1 && xDist >= -1) || (yDist <= 1 && yDist >= -1)) {
			travelling = false;
			caughtByHunter = true;
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

	private void narration(String i) {
		switch (i) {
		case "0":
			System.out.println(
					"You feel something humming lightly on your arm. Looking down, you notice a compass strapped to your wrist.\nThe four cardinal points are on on its face, with a small dial near the bottom reading a distance and the needle is pointing off into the gloom.\n");
			break;

		case "5":
			System.out.println("The gloom seems to loom closer to you, pushing in fronm all sides...\n");
			break;

		case "10":
			System.out.println(
					"You see movement in the corner of your vision, but when you turn to get a proper look, nothing is there...\n");
			break;

		case "caught":
			System.out.println(
					"You stop, staring into the gloom, certain you saw something just beyond your view.\nAfter nothing but swirling mists appear for a few seconds, you turn to move on, and immediately collide with something solid.\nYou fall back, just in time to see the large toothy maw looming over you.\nIt's not the most pretty sight to see as a last memory... \n\nYOU DIED");
			break;

		case "noDirection":
			System.out.println("You don't know what direction that is in.");
			break;

		case "intro":
			System.out.println(
					"You wake up, finding yourself in a misty, grey swampland that stretches as far as the eye can see.\nWith no idea of where you are, you have no choice but to wander.\n1. Go WANDER");
			break;

		case "nextDirection":
			System.out.println(
					"What would you like to do:\n1. Go NORTH\n2. Go EAST\n3. Go SOUTH\n4. Go WEST\n5. Go WANDER\n");
			break;

		case "foundShoes":
			System.out.println(
					"Emerging through the gloom of the swamp, you stumble upon a old house that looks like it's been through better days.\nAs you get closer, you also notice a pair of suspiciously placed glittery red slippers and an attached note.\n\"Put me on and click your heels thrice while thinking of your greatest wish!\"");
			break;

		case "cantDoThat":
			System.out.println("Sorry, you can't do that here");
			break;
			
		case "compassDistance":
			System.out.println("The compass indicactes there is " + printDistanceTwoDP(findDistance(targetCoords,playerLocation)) + "m left.\n");
			break;
			
		case "hunterDistance":
			System.out.println("You hear a noise through the gloom. It sounds like it's roughly " + printDistanceInt(findDistance(playerLocation,hunterLocation)) + "m away.\n");
			break;
			
		case "replay?":
			System.out.println("Hit Y to play again.");
			break;
			
		case "goodbye":
			System.out.println("Goodbye!");
			break;

		default:
			break;

		}

	}

}
