package com.skilldistillery.jets;

import java.util.Scanner;

public class JetsApplication {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		MenuInterface myMenu = new MenuInterface(kb);
		AirField myAirField = myMenu.createAirFieldObj();
		boolean quitProgram;
		
		do {
		quitProgram = myMenu.mainMenu(myAirField);
		} while (!quitProgram);
		
		kb.close();
		
	}
	
}
