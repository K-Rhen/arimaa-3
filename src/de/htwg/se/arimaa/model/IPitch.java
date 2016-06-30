package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.model.IPlayer;

public interface IPitch {

	IPlayer getP2();

	IPlayer getP1();

	boolean pitchEquals(IPitch b);
	
	

}
