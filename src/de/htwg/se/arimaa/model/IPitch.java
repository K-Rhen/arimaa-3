package de.htwg.se.arimaa.model;

import java.util.ArrayList;

import de.htwg.se.arimaa.model.IPlayer;

public interface IPitch {

	IPlayer getP2();

	IPlayer getP1();

	boolean pitchEquals(IPitch b);

	void setP1Figures(ArrayList<ICharacter> figures1);

	void setP2Figures(ArrayList<ICharacter> figures2);
	
	

}
