package de.htwg.se.arimaa.model.impl;

import java.util.List;

import de.htwg.se.arimaa.model.IFIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.position.Position;

public class Player implements IPlayer {
	private List<IFigure> figures;
	private String playerName;

	public Player(String playerName, List<IFigure> figures) {
		this.playerName = playerName;
		this.figures = figures;
	}

	@Override
	public void setFigures(List<IFigure> figures) {
		this.figures = figures;
	}
	
	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public IFIGURE_NAME getFigure(Position pos) {
		for (IFigure cr : figures) {
			if (pos.equals(cr.getPosition()))
				return cr.getName();
		}
		return null;
	}
   
	@Override
	public boolean moveFigure(Position start, Position end) {
		for (IFigure cr : figures) {
			if (cr.getPosition().equals(start)) {
				cr.setPosition(end);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteFigure(Position pos){
		if(getFigure(pos) == null)
			return false;
		for(int i = 0; i < figures.size(); i++){
			if(figures.get(i).getPosition().equals(pos))
				figures.remove(i);
		}
		return true;
	}

    @Override
	public List<IFigure> getFigures(){
		return figures;
	}

}
