package com.skilldistillery.jets;

public class Jetimpl extends Jet {

	public Jetimpl() {
	}

	public Jetimpl(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		System.out.println(getModel() + "'s engines sputter a bit before spooling up, and the plane takes off from the launch pad.");
	}

}
