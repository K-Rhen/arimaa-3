package de.htwg.se.arimaa.arimaa;

import com.google.inject.AbstractModule;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.controller.impl.ArimaaController;

public class ArimaaModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(IArimaaController.class).to(ArimaaController.class);

	}

}
