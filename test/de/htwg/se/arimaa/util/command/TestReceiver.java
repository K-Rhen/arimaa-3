package de.htwg.se.arimaa.util.command;

public class TestReceiver {

	int sum;

	public int getSum() {
		return sum;
	}

	public void add(int num) {
		sum = sum + num;
	}

	@Override
	public String toString() {
		return "" + sum;
	}

}
