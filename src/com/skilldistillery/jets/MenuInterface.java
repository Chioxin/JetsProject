package com.skilldistillery.jets;

import java.util.Scanner;

public class MenuInterface {
	private Scanner kb;

	public MenuInterface(Scanner kb) {
		super();
		this.kb = kb;
	}

	protected Scanner getKb() {
		return kb;
	}

	protected void setKb(Scanner kb) {
		this.kb = kb;
	}

	public boolean mainMenu(AirField myAirField) { // Will return true only when choice 8.) QUIT has been picked.
		printDivider();
		boolean inputInvalid;
		do {
			inputInvalid = false;
			System.out.println("1.) List Fleet");
			System.out.println("2.) Fly all Jets");
			System.out.println("3.) View Fastest Jet");
			System.out.println("4.) View Longest Range Jet");
			System.out.println("5.) Load All Cargo Jets");
			System.out.println("6.) Dog Fight!");
			System.out.println("7.) Add a Jet");
			System.out.println("8.) Go on Combat Mission");
			System.out.println("9.) Quit");
			System.out.print("Which would you like to perform >> ");
			int input = kb.nextInt();

			switch (input) {
			case 1:
				graphicAirField(myAirField);
				displayAirfield(myAirField.getJets());
				enterWhenDone();
				break;
			case 2:
				System.out.println();
				myAirField.flyAllJets();
				System.out.println("*****  All jets have been taken out for a spin  *****>");
				System.out.println();
				break;
			case 3:
				System.out.println();
				System.out.println("*****  This jet has the most speed *****");
				displayJet(myAirField.getFastestJet());
				System.out.println();
				break;
			case 4:
				System.out.println();
				System.out.println("*****  This jet has the longest range  *****");
				displayJet(myAirField.getLongestRangeJet());
				System.out.println();
				break;
			case 5:
				System.out.println();
				myAirField.loadCargoPlanes();
				System.out.println("***** All cargo planes loaded *****");
				System.out.println();
				break;
			case 6:
				System.out.println();
				myAirField.fightersDogFight();
				System.out.println("***** All fighters have battled it out *****");
				System.out.println();
				break;
			case 7:
				System.out.println();
				createNewJet(myAirField);
				System.out.println();
				break;
			case 8:
				System.out.println();
				launchMission(myAirField);
				System.out.println();
				break;
			case 9:
				System.out.println("Have a nice day!");
				return true;
			default:
				System.out.println("Your input is invalid, try a value between 1-8.");
				inputInvalid = true;
				break;
			}
		} while (inputInvalid);

		return false;

	}

	public AirField createAirFieldObj() {
		int input;

		do {
			System.out.print("How big is your airport? (5 - 10): ");
			input = kb.nextInt();
			if (input < 5 || input > 10) {
				System.out.println("\tInvalid input!!! ");
			}
		} while (input < 5 || input > 10);

		Jet[] jets = new Jet[input];

		AirField airField = new AirField(jets);

		return airField;
	}

	public void displayJet(Jet j) {
		System.out.println("Model: " + j.getModel() + " Speed: " + j.getSpeed() + " Range: " + j.getRange() + " Price: "
				+ j.getPrice());
	}

	public void printDivider() {
		System.out.println("--------------------      \\\\");
		System.out.println("    ******************  >======>");
		System.out.println("--------------------      //");
	}

	private void enterWhenDone() {
		System.out.println("Press N and Enter to move on.");
		String dump = kb.next();
	}

	private void displayAirfield(Jet[] jArr) {
		for (int i = 0; i < jArr.length; i++) {
			Jet jet = jArr[i];
			if (jet != null) {
				System.out.print(i + 1 + "). ");
				displayJet(jet);
			} else {
				System.out.println(i + 1 + "). EMPTY");
			}
		}
		// TODO Personal stretch goal, display the airfield graphically. Check Notepad.
	}

	private void createNewJet(AirField myAirField) {
		if (myAirField.getIsFull()) { // If our airfield is full then we won't even bother adding a new jet.
			System.out.println("*****  The Air Field is full. No more jets can be added.  *****");
			return;
		}

		String model;
		double speed;
		int range;
		long price;
		int planeType;

		System.out.print("What is the Model Name? >> ");
		model = kb.next();
		System.out.println("Is the " + model + " a:");
		System.out.println("\t1.) Fighter");
		System.out.println("\t2.) Cargo Carrier");
		System.out.print("Enter either 1, or 2. >> ");
		planeType = kb.nextInt();
		System.out.print("How fast is the " + model + "? >> ");
		speed = kb.nextDouble();
		System.out.print("How far can the " + model + " fly? >> ");
		range = kb.nextInt();
		System.out.print("How much is the " + model + "'s price? >> ");
		price = kb.nextLong();

		boolean inputInvalid;
		do {
			inputInvalid = false;
			switch (planeType) {
			case 1:
				Fighter f = new Fighter(model, speed, range, price);
				myAirField.addJet(f);
				break;
			case 2:
				CargoPlane c = new CargoPlane(model, speed, range, price);
				myAirField.addJet(c);
				break;
			default:
				System.out.print("Please enter (1) for Fighter, or (2) for Cargo Carrier. >> ");
				planeType = kb.nextInt();
				inputInvalid = true;
				break;
			}
		} while (inputInvalid);

		System.out.println("*****  Added Jet " + model + " to the fleet!  *****");
	}

//	
//	
//  ANYTHING BELOW THIS DIVIDER IS FOR THE CONTRUCTION METHODS TO DISPLAY
//	THE AIR FIELD IN ASCII!
//	
//	STRETCH GOAL
//	
//	

	private void graphicAirField(AirField myAirField) {
		int numSpots;
		Jet[] jArr = myAirField.getJets();
		if (jArr.length % 2 == 0) { // Need even number of spots to print a proper air field.
			numSpots = jArr.length;
		} else {
			numSpots = jArr.length + 1;
		}

		for (int i = 0; i < numSpots; i += 2) { // Jumping by 2, because we need to start at even number to print that
												// row of 2 planes.

			if (i + 1 >= jArr.length) {
				buildSpot(jArr, i, true);
			} else {
				buildSpot(jArr, i, false);
			}
		}
		buildDivder();
		System.out.println();
	}

	private void buildSpot(Jet[] jArr, int counter, boolean blockSpot) {
		String top = "";
		String mid = "";
		String bot = ""; // Set strings to empty.

		buildDivder();
		top += buildLeftPortTop(jArr[counter]);
		mid += buildLeftPortMid(jArr[counter]);
		bot += buildLeftPortBot(jArr[counter]);
		top += buildMiddleStripeTop();
		mid += buildMiddleStripeMid();
		bot += buildMiddleStripeBot();
		if (blockSpot) {
			top += "   X X X X X X |";
			mid += "  X-- X-- X-- X|";
			bot += "   X X X X X X |";
		} else {
			top += buildRightPortTop(jArr[counter + 1]);
			mid += buildRightPortMid(jArr[counter + 1]);
			bot += buildRightPortBot(jArr[counter + 1]);
		}
		System.out.println(top);
		System.out.println(mid);
		System.out.println(bot);

	}

	private void buildDivder() {
		System.out.println("|---------------         ---------------|");
	}

	private String buildMiddleStripeTop() {
		return "         ";
	}

	private String buildMiddleStripeMid() {
		return "    |    ";
	}

	private String buildMiddleStripeBot() {
		return "         ";
	}

	private String buildLeftPortTop(Jet j) {
		if (j instanceof CargoPlane) {
			return "|   //          ";
		} else if (j instanceof Fighter) {
			return "|    /          ";
		} else { // Spot is empty.
			return "|               ";
		}
	}

	private String buildLeftPortMid(Jet j) {
		if (j instanceof CargoPlane) {
			return "|<=====<  " + shortenJetNameLeft(j) + " ";
		} else if (j instanceof Fighter) {
			return "| -----<  " + shortenJetNameLeft(j) + " ";
		} else { // Spot is empty.
			return "|  --  --  --   ";
		}
	}

	private String buildLeftPortBot(Jet j) {
		if (j instanceof CargoPlane) {
			return "|   \\\\          ";
		} else if (j instanceof Fighter) {
			return "|    \\          ";
		} else { // Spot is empty.
			return "|               ";
		}
	}

	private String buildRightPortTop(Jet j) {
		if (j instanceof CargoPlane) {
			return "          \\\\   |";
		} else if (j instanceof Fighter) {
			return "          \\    |";
		} else { // Spot is empty.
			return "               |";
		}
	}

	private String buildRightPortMid(Jet j) {
		if (j instanceof CargoPlane) {
			return "  " + shortenJetNameRight(j) + " >=====>|";
		} else if (j instanceof Fighter) {
			return "  " + shortenJetNameRight(j) + " >----- |";
		} else { // Spot is empty.
			return "   --  --  --  |";
		}
	}

	private String buildRightPortBot(Jet j) {
		if (j instanceof CargoPlane) {
			return "          //   |";
		} else if (j instanceof Fighter) {
			return "          /    |";
		} else { // Spot is empty.
			return "               |";
		}
	}

	private String shortenJetNameLeft(Jet j) {
		String model = j.getModel();
		String returnName = "";

		for (int i = 0; i < 5; i++) {
			if (model.length() < i) { // making sure we don't access a position outside of the given string.
				returnName += " ";
			} else {
				returnName += model.charAt(i);
			}
		}
		return returnName;
	}

	private String shortenJetNameRight(Jet j) {
		String model = j.getModel();
		String returnName = "";
		int spacesNeeded = 0;

		if (model.length() < 5) { // if model length is less than 5, we need to preload the returnName with
									// spaces.
			spacesNeeded = model.length() % 5;
			for (int i = 0; i < spacesNeeded; i++) {
				returnName += " ";
			}
		}

		for (int i = 0; i < (5 - spacesNeeded); i++) {
			returnName += model.charAt(i);
		}

		return returnName;
	}
//	
//	
//  ANYTHING BELOW THIS DIVIDER IS FOR THE MISSION STRETCH GOAL
//	
//	

	private void launchMission(AirField myAirField) {
		Jet myJet = chooseJet(myAirField);
		CombatEngine myMission = new CombatEngine();
		boolean playerWon = false;
		boolean enemyWon = false;
		boolean playerTurn = true;

		do {
			if (playerTurn) {
				playerWon = myMission.performRound(true, myJet, attackMenu());
				playerTurn = !playerTurn;
			} else {
				enemyWon = myMission.performRound(false, myJet, reactMenu());
				playerTurn = !playerTurn;
			}
			System.out.println();
		} while (!playerWon && !enemyWon);

		if (!playerWon) {
			System.out.println("*****  " + myJet.getModel() + " was lost in a dogfight!  *****");
			myAirField.removeJet(myJet);
		} else {
			System.out.println("*****  " + myJet.getModel() + " was victorious in a dogfight! *****");
		}
	}

	private Jet chooseJet(AirField myAirField) {
		int input;
		int c = 0;

		Jet[] combatJets = new Fighter[10];
		Jet[] jTemp = myAirField.getJets();

		for (int i = 0; i < jTemp.length; i++) {
			if (jTemp[i] instanceof CombatReady) {
				combatJets[c] = jTemp[i];
				c++;
			}
		}

		boolean inputInvalid;
		do {
			inputInvalid = false;
			System.out.println("These are the current jets capable of combat.");

			c = 0;
			for (Jet jet : combatJets) {
				if (jet instanceof CombatReady) { // If it's null, we don't want to print it out.
					c++;
					System.out.println(c + ".) " + jet.getModel());
				}
			}

			System.out.print("Which combat ready jet would you like to fly? >> ");
			input = kb.nextInt();
			input -= 1; // formats int to work with array structures

			if (input < 0 || input >= c) {
				System.out.println("Invalid input, you must choose a jet between 1 and " + c);
				inputInvalid = true;
			}
			System.out.println();

		} while (inputInvalid);

		return combatJets[input];
	}

	public int attackMenu() {
		int input;

		boolean inputInvalid;
		do {
			System.out.println("1.) Fire Machine Gun.");
			System.out.println("2.) Launch Missile.");
			System.out.println("3.) Manuever Into Position.");
			System.out.print("Choose your action. >> ");
			input = kb.nextInt();
			System.out.println();

			inputInvalid = false;
			switch (input) {
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				return 3;
			default:
				System.out.println("Input invalid, you must choose between 1 and 3.");
				inputInvalid = true;
				break;
			}
		} while (inputInvalid);
		return -1; // Something went terribly wrong if we got here.
	}

	public int reactMenu() {
		int input;

		boolean inputInvalid;
		do {
			System.out.println("1.) Perform a Barrel Roll.");
			System.out.println("2.) Perform a Juke.");
			System.out.println("3.) Launch a Flare.");
			System.out.print("Choose your action. >> ");
			input = kb.nextInt();
			System.out.println();

			inputInvalid = false;
			switch (input) {
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				return 3;
			default:
				System.out.println("Input invalid, you must choose between 1 and 3.");
				inputInvalid = true;
				break;
			}
		} while (inputInvalid);
		return -1; // Something went terribly wrong if we got here.

	}

}
