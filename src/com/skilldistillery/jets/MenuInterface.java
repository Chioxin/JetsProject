package com.skilldistillery.jets;

import java.util.Scanner;

public class MenuInterface {
	private Scanner kb;
	private boolean inputInvalid;

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

	public boolean mainMenu(AirField myAirField) { //Will return true only when choice 8.) QUIT has been picked.
		printDivider();
		do {
			System.out.println("1.) List Fleet");
			System.out.println("2.) Fly all Jets");
			System.out.println("3.) View Fastest Jet");
			System.out.println("4.) View Longest Range Jet");
			System.out.println("5.) Load All Cargo Jets");
			System.out.println("6.) Dog Fight!");
			System.out.println("7.) Add a Jet");
			System.out.println("8.) Quit");
			System.out.print("Which would you like to perform >> ");
			int input = kb.nextInt();
			
			switch (input) {
				case 1:
					myAirField.displayAirfield();
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
					break;
				case 8:
					System.out.println("Have a nice day!");
					return true;
				default:
					System.out.println("Your input is invalid, try a value between 1-8.");
					inputInvalid = true;
					break;
			}
		}while (inputInvalid);
		
		return false;
		
	}

	public AirField createAirFieldObj() {
		int input;

		do {
			System.out.print("How big is your airport? (5 - 20): ");
			input = kb.nextInt();
			if (input < 5 || input > 20) {
				System.out.println("\tInvalid input!!! ");
			}
		} while (input < 5 || input > 20);

		Jet[] jets = new Jet[input];

		AirField airField = new AirField(jets);

		return airField;
	}
	
	public void displayJet(Jet j) {
		System.out.println("Model: " + j.getModel() + " Speed: " + j.getSpeed() + " Range: " + j.getRange() + " Price: " + j.getPrice());
	}

	public void printDivider() {
		System.out.println("--------------------      \\");
		System.out.println("    ******************  >======>");
		System.out.println("--------------------      /");
	}

	private void enterWhenDone() {
		System.out.println("Press N and Enter to move on.");
		String dump = kb.next();
	}
	
}
