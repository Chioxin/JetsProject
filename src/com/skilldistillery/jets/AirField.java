package com.skilldistillery.jets;

public class AirField {
	private Jet[] jets;
	private boolean isFull;

	public AirField(Jet[] jets) {
		super();
		this.jets = jets;

		this.jets[0] = new Fighter("F-15 Eagle", 1000, 5000, 10_000_000);
		this.jets[1] = new Fighter("F-22 Raptor", 1600, 7500, 23_000_000);
		this.jets[2] = new Fighter("F-14 Tomcat", 1000, 5000, 15_000_000);
		this.jets[3] = new CargoPlane("C-97 Boeing", 400, 10000, 27_000_000);
		this.jets[4] = new CargoPlane("DC-3 Douglas", 600, 8000, 8_000_000);

		for (Jet jet : jets) { //We need to check to see if the Air Field is now full.
			if (jet == null) { //If we find one null spot in the array, then Air Field is not full.
				isFull = false;
				break;
			} else {
				isFull = true;
			}
		}
	}

	public boolean getIsFull() {
		return isFull;
	}

	private void setIsFull(boolean isFull) { // I don't want objects outside of this class to change this.
		this.isFull = isFull;
	}

	public Jet[] getJets() {
		Jet[] arrCopy = new Jet[jets.length];
		for (int i = 0; i < jets.length; i++) {
			if (jets[i] != null) {
				arrCopy[i] = jets[i];
			}
		}
		
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
		
		for (Jet jet : jets) { //We need to check to see if the Air Field is now full.
			if (jet == null) { //If we find one null spot in the array, then Air Field is not full.
				isFull = false;
				break;
			} else {
				isFull = true;
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

	public void flyAllJets() {
		for (Jet jet : jets) {
			if (jet instanceof Jet) {
				jet.fly();
			}
		}
	}
	
	public void loadCargoPlanes() {
		for (Jet jet : jets) {
			if (jet instanceof CargoCarrier) {
				((CargoCarrier) jet).loadCargo();
			}
		}
		
	}
	
	public void fightersDogFight() {
		for (Jet jet : jets) {
			if (jet instanceof CombatReady) {
				((CombatReady) jet).fight();
			}
		}
	}
}
