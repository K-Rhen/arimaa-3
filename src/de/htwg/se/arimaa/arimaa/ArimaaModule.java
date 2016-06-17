package de.htwg.se.arimaa.arimaa;
import com.google.inject.AbstractModule;

import src.de.htwg.sudoku.IGridFactory;
import src.de.htwg.sudoku.ISudokuController;
import src.de.htwg.sudoku.de;

public class ArimaaModule extends AbstractModule{

	@Override
	protected void configure() {
	    bind(IGridFactory.class)
        .to(de.htwg.sudoku.model.impl.GridFactory.class);
	    
	    bind(ISudokuController.class).to(
        de.htwg.sudoku.controller.logwrapper.SudokuController.class);
		
	}

}
