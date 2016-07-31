package de.htwg.se.arimaa.model.impl;

import java.util.List;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.position.Position;

public class Player implements IPlayer {
	private List<IFigure> figures;
	private PLAYER_NAME playerName;

	public Player(PLAYER_NAME playerName, List<IFigure> figures) {
		this.playerName = playerName;
		this.figures = figures;
	}

	@Override
	public PLAYER_NAME getPlayerName() {
		return playerName;
	}

	@Override
	public FIGURE_NAME getFigure(Position pos) {
		for (IFigure cr : figures) {
			if (pos.equals(cr.getPosition()))
				return cr.getName();
		}
		return null;
	}
   
	@Override
	public void moveFigure(Position start, Position end) {
		if(getFigure(start) == null)
			throw new IllegalArgumentException("No figure on "+ start.toString());
		
		for (IFigure cr : figures) {
			if (cr.getPosition().equals(start)) {
				cr.setPosition(end);
				break;
			}
		}
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
