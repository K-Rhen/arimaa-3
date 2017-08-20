package model;

public enum PLAYER_NAME {
	GOLD, SILVER;

	@Override
	public String toString() {
		switch (this) {
		case GOLD:
			return "Gold";
		case SILVER:
			return "Silver";
		default:
			throw new IllegalArgumentException();
		}
	}

	public static PLAYER_NAME invers(PLAYER_NAME playerName) {
		if (playerName.equals(PLAYER_NAME.GOLD))
			return PLAYER_NAME.SILVER;
		else if (playerName.equals(PLAYER_NAME.SILVER))
			return PLAYER_NAME.GOLD;
		else
			return null;
	}
}
