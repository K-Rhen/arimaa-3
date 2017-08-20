package main;

import com.google.inject.AbstractModule;
import controller.IArimaaController;
import controller.impl.ArimaaController;

public class ArimaaModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IArimaaController.class).to(ArimaaController.class);
	}

}
