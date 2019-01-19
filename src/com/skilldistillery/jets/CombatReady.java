package com.skilldistillery.jets;

public interface CombatReady {
	default void fight() {
		System.out.println("Preparing to engage!");
	}

}
