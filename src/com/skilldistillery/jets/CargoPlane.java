package com.skilldistillery.jets;

public class CargoPlane extends Jet implements CargoCarrier {

	public CargoPlane() {
	}

	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(getModel() + "'s engines rumble to life, and the plane lifts off the launch pad!");
	}

	@Override
	public void loadCargo() {
		System.out.println(getModel() + " is loading cargo...");
	}

}
