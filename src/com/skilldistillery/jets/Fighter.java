package com.skilldistillery.jets;

public class Fighter extends Jet implements CombatReady {

	public Fighter() {
	}

	public Fighter(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(getModel() + "'s engines roar to life and the plane zooms off the launch strip!");
	}

}
