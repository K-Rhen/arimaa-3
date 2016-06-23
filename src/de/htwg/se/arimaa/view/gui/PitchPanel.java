package de.htwg.se.arimaa.view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class PitchPanel extends JPanel {
	ArimaaController controller;

	BufferedImage pitchImage;
	EnumMap<CHARAKTER_NAME, BufferedImage> figuresImage;
	Point figuresize = new Point(40, 40);
	Point offset = new Point(80,50);
	
	public PitchPanel(ArimaaController controller) {
		this.controller = controller;
		figuresImage = new EnumMap<>(CHARAKTER_NAME.class);

		pitchImage = loadImage("BoardStoneSmall");
		initFigures();
	}

	private BufferedImage loadImage(String name) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(name + "not found");
		}
		return image;
	}

	private void initFigures() {
		figuresImage.put(CHARAKTER_NAME.R, loadImage("GoldRabbit"));
		figuresImage.put(CHARAKTER_NAME.C, loadImage("GoldCat"));
		figuresImage.put(CHARAKTER_NAME.D, loadImage("GoldDog"));
		figuresImage.put(CHARAKTER_NAME.H, loadImage("GoldCat"));
		figuresImage.put(CHARAKTER_NAME.L, loadImage("GoldCamel"));
		figuresImage.put(CHARAKTER_NAME.E, loadImage("GoldElephant"));

		figuresImage.put(CHARAKTER_NAME.r, loadImage("SilverRabbit"));
		figuresImage.put(CHARAKTER_NAME.c, loadImage("SilverCat"));
		figuresImage.put(CHARAKTER_NAME.d, loadImage("SilverDog"));
		figuresImage.put(CHARAKTER_NAME.h, loadImage("SilverCat"));
		figuresImage.put(CHARAKTER_NAME.l, loadImage("SilverCamel"));
		figuresImage.put(CHARAKTER_NAME.e, loadImage("SilverElephant"));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Paint the background.
		g.setColor(Color.green);
		g.fillRect(0, 0, getSize().width, getSize().height);

		// Paint pitch
		 g.drawImage(pitchImage, 80, 50, 400, 400, null);

		// Paint Player1
		List<ICharacter> p1figure = controller.getPitch().getP1().getFigures();
		printFigures(g,p1figure,offset,figuresize);
		
		// Paint Player2
		List<ICharacter> p2figure = controller.getPitch().getP2().getFigures();
		printFigures(g,p2figure,offset,figuresize);

		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public void printFigures(Graphics g,List<ICharacter> figure, Point offset, Point figuresize){
		for (ICharacter f : figure) {
			CHARAKTER_NAME fname = f.getName();
			BufferedImage fimg = figuresImage.get(fname);

			Position fpos = f.getPosition();
			g.drawImage(fimg,fpos.getX()+ offset.x,fpos.getY() + offset.y, figuresize.x, figuresize.y, null);
		}		
	}
	
}
