package com.skilldistillery.jets;

public class CombatEngine {
	private int playerScore;
	private int enemyScore;
	private String enemyName;

	public CombatEngine() {
		String[] models = { "F-22 Raptor", "F-15 Eagle", "MiG-29", "Su-27", "Mirage 2000" };
		this.enemyName = models[(int) (Math.random() * models.length)];
		this.playerScore = 0;
		this.enemyScore = 0;
	}

	public boolean performRound(boolean isPlayer, Jet playerJet, int playerChoice) {
		int computerAI = 1 + (int) (Math.random() * 3);

		if (isPlayer) {
			printAttackType(playerChoice, playerJet.getModel(), enemyName);
			incrementPlayerScore((didAttackHit(playerChoice, computerAI, playerJet.getModel(), enemyName)));
			reportScore(playerJet.getModel());
			return didPlayerWin();
		} else {
			printAttackType(computerAI, enemyName, playerJet.getModel());
			incrementEnemyScore(didAttackHit(computerAI, playerChoice, enemyName, playerJet.getModel()));
			reportScore(playerJet.getModel());
			return didEnemyWin();
		}
	}

	private boolean didAttackHit(int attackerChoice, int defenderChoice, String attackerModel, String defenderModel) {
		if (attackerChoice == 1 && defenderChoice != 1) {
			printDamageTaken(attackerChoice, attackerModel, defenderModel);
			return true;
		} else if (attackerChoice == 2 && defenderChoice != 2) {
			printDamageTaken(attackerChoice, attackerModel, defenderModel);
			return true;
		} else if (attackerChoice == 3 && defenderChoice != 3) {
			printDamageTaken(attackerChoice, attackerModel, defenderModel);
			return true;
		} else {
			printReactType(defenderChoice, attackerModel, defenderModel);
			return false;
		}
	}

	private void printAttackType(int choice, String attacker, String defender) {

		switch (choice) {
		case 1:
			System.out.println("The " + attacker + " sends a spray of bullets at the " + defender + "!!!");
			break;
		case 2:
			System.out.println("The " + attacker + " launches a missile at the " + defender + "!!!");
			break;
		case 3:
			System.out
					.println("The " + attacker + " attempts to get into a better position on the " + defender + "!!!");
			break;
		default:
			break;
		}
	}

	private void printReactType(int choice, String attacker, String defender) {

		switch (choice) {
		case 1:
			System.out.println("\tThe " + defender + " barrel rolls through the spray of bullets!!!");
			break;
		case 2:
			System.out.println("\tThe " + defender + " releases a flare!!!");
			break;
		case 3:
			System.out.println("\tThe " + defender + " jukes the attempt to manuever into a better position!!!");
			break;
		default:
			break;
		}
	}

	private void printDamageTaken(int choice, String attacker, String defender) {

		switch (choice) {
		case 1:
			System.out.println("\tThe " + defender + " hull gains a few more holes in it!!!");
			break;
		case 2:
			System.out.println(
					"\tThe " + defender + " suffers considerable damage from the missile exploding near it!!!");
			break;
		case 3:
			System.out.println("\tThe " + defender + " fails to shake the " + attacker
					+ " allowing for a free attack of opportunity!!!");
			break;
		default:
			break;
		}
	}

	private void incrementPlayerScore(boolean wasHit) {
		if (wasHit) {
			playerScore++;
		}
	}

	private void incrementEnemyScore(boolean wasHit) {
		if (wasHit) {
			enemyScore++;
		}
	}

	private boolean didPlayerWin() {
		if (playerScore >= 3) {
			return true;
		}
		return false;
	}

	private boolean didEnemyWin() {
		if (enemyScore >= 3) {
			return true;
		}
		return false;
	}
	
	private void reportScore(String playerName) {
		System.out.println();
		System.out.println(playerName + " has scored " + playerScore + " of 3 hits!");
		System.out.println(enemyName + " has scored " + enemyScore + " of 3 hits!");
		System.out.println();
	}
}
