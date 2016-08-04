package de.htwg.se.arimaa.model;

public enum PLAYER_NAME {
	GOLD, SILVER;

	@Override
	  public String toString() {
	    switch(this) {
	      case GOLD: return "Gold";
	      case SILVER: return "Silver";
	      default: throw new IllegalArgumentException();
	    }
	}
}
