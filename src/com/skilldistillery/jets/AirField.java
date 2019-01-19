package com.skilldistillery.jets;

public class AirField {
	private Jet[] jets;
	private boolean isFull;

	public AirField(Jet[] jets) {
		super();
		for (Jet jet : jets) {
			if (jet == null) {
				isFull = false;
				break;
			} else {
				isFull = true;
			}
		}
		this.jets = jets;

		this.jets[0] = new Fighter("F-15 Eagle", 1000, 5000, 10_000_000);
		this.jets[1] = new Fighter("F-22 Raptor", 1600, 7500, 23_000_000);
		this.jets[2] = new Fighter("F-14 Tomcat", 1000, 5000, 15_000_000);
		this.jets[3] = new CargoPlane("C-97 Boeing", 400, 10000, 27_000_000);
		this.jets[4] = new CargoPlane("DC-3 Douglas", 600, 8000, 8_000_000);

	}

	public boolean getIsFull() {
		return isFull;
	}

	private void setIsFull(boolean isFull) { // I don't want objects outside of this class to change this.
		this.isFull = isFull;
	}

	public Jet[] getJets() {
		return jets;
	}

	public void setJets(Jet[] jets) {
		this.jets = jets;
	}

	public Jet getFastestJet() {
		Jet fastestJet = null;

		for (Jet jet : jets) {
			if (jet != null) {
				if (fastestJet == null) {
					fastestJet = jet;
					continue;
				} else {
					if (fastestJet.getSpeed() < jet.getSpeed()) {
						fastestJet = jet;
					}
				}
			}
		}

		return fastestJet;
	}
	
	public Jet getLongestRangeJet() {
		Jet longRangeJet = null;
		
		for (Jet jet : jets) {
			if (jet != null) {
				if (longRangeJet == null) {
					longRangeJet = jet;
					continue;
				} else {
					if (longRangeJet.getRange() < jet.getRange()) {
						longRangeJet = jet;
					}
				}
			}
		}
		
		return longRangeJet;
	}

	public boolean addJet(Jet j) {
		if (isFull) {
			return false;
		}
		for (int i = 0; i < jets.length; i++) {
			if (jets[i] == null) {
				jets[i] = j;
				break;
			}
		}
		return true;
	}

	public boolean removeJet(Jet j) {
		for (int i = 0; i < jets.length; i++) {
			if (jets[i].equals(j)) {
				jets[i] = null;
				setIsFull(false);
				return true;
			}
		}
		return false;
	}

	public Jet createRandomJet() {
		int randomNum = 1 + ((int) Math.random() * 2);
		String model;
		double speed = 150 + Math.random() * 2050; // Black Bird record speed at 2197mph.
		int range = 1000 + ((int) (Math.random() * 7555)); // Boeing Airliner record range of 8555 nmi.
		long price = 100_000 + ((long) (Math.random() * 100_000_000));

		switch (randomNum) {
		case 1: // fighter models
			String[] fModelArr = { "F22", "F15", "F16", "MiG-29", "Su-47" };
			model = pickRandomModel(fModelArr);
			Fighter fJ = new Fighter(model, speed, range, price);
			return fJ;
		case 2: // cargo models
			String[] cModelArr = { "C-97 Boeing", "An-225 Mriya", "C-2 Grumman", "C-82 Fairchild", "DC-3 Douglas" };
			model = pickRandomModel(cModelArr);
			CargoPlane cP = new CargoPlane(model, speed, range, price);
			return cP;
		default:
			System.out.println("Something went horribly wrong, returning null!");
			return null;
		}

	}

	private String pickRandomModel(String[] arr) {
		int num = (int) (Math.random() * arr.length);
		return arr[num];
	}

	public void flyAllJets() {
		for (Jet jet : jets) {
			if (jet instanceof Jet)
				jet.fly();
		}
		System.out.println("<=======  All jets have been taken out for a spin!  =======>");
		System.out.println();
	}
	
	public void loadCargoPlanes() {
		
	}
	
	public void fightersDogFight() {
		
	}

	public void displayAirfield() {
		for (int i = 0; i < jets.length; i++) {
			Jet jet = jets[i];
			if (jet != null) {
				System.out.println(i + 1 + "). Model: " + jet.getModel() + " Speed: " + jet.getSpeed() + " Range: "
						+ jet.getRange() + " Price: " + jet.getPrice());
			} else {
				System.out.println(i + 1 + "). EMPTY");
			}
		}
		// TODO Personal stretch goal, display the airfield graphically. Check Notepad.
	}

}
