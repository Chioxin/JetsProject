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

	public boolean mainMenu(AirField myAirField) { //Will return true only when choice 8.) QUIT has been picked.
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
			System.out.println("8.) Quit");
			System.out.print("Which would you like to perform >> ");
			int input = kb.nextInt();
			
			switch (input) {
				case 1:
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
				displayJet(jet);
			} else {
				System.out.println(i + 1 + "). EMPTY");
			}
		}
		// TODO Personal stretch goal, display the airfield graphically. Check Notepad.
	}
	private void createNewJet(AirField myAirField) {
		if (myAirField.getIsFull()) { //If our airfield is full then we won't even bother adding a new jet.
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
		System.out.print("How fast is the " + model + "? >> ");
		speed = kb.nextDouble();
		System.out.print("How far can the " + model + " fly? >> ");
		range = kb.nextInt();
		System.out.print("How much is the " + model + "'s price? >> ");
		price = kb.nextLong();
		System.out.println("Is the " + model + " a:");
		System.out.println("\t1.) Fighter");
		System.out.println("\t2.) Cargo Carrier");
		System.out.print("Enter either 1, or 2. >> ");
		planeType = kb.nextInt();
		
		
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
				System.out.println("Please enter (1) for Fighter, or (2) for Cargo Carrier. >> ");
				planeType = kb.nextInt();
				inputInvalid = true;
				break;
			}
		} while (inputInvalid);
		
		System.out.println("*****  Added Jet " + model + " to the fleet!  *****");
	}
	
}
