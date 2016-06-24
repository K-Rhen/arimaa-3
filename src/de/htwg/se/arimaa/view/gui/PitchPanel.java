package de.htwg.se.arimaa.view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import de.htwg.se.arimaa.controller.ArimaaController;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class PitchPanel extends JPanel {
	ArimaaController controller;

	BufferedImage pitchImage;
	Point pitchSizePoint = new Point(400, 400);
	EnumMap<CHARAKTER_NAME, BufferedImage> figuresImage;
	Point figuresize = new Point(50, 50);
	Point offset = new Point(20, 15);

	// BUTTON
	JButton commitButton;
	Point commitPoint = new Point(20, 430);
	JButton playerChangeButton;
	Point playerChangePoint = new Point(330, 430);

	// Info
	JLabel actPlayerLabel;
	Point actPlayerPoint = new Point(160, 425);
	JLabel moveRemainLabel;
	Point moveRemainPoint = new Point(200, 450);

	// Mouse
	Point mousePoint = null;
	boolean mouseMove = false; //remove?

	public PitchPanel(ArimaaController controller) {
		this.controller = controller;
		figuresImage = new EnumMap<>(CHARAKTER_NAME.class);

		pitchImage = loadImage("BoardStoneSmall");
		initFigures();

		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);

		commitButton = new JButton("commit");
		commitButton.setBounds(commitPoint.x, commitPoint.y, 90, 30);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// Todo commit
			}
		});
		this.add(commitButton);

		playerChangeButton = new JButton("change");
		playerChangeButton.setBounds(playerChangePoint.x, playerChangePoint.y, 90, 30);
		playerChangeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// Todo change Player
			}
		});
		this.add(playerChangeButton);

		actPlayerLabel = new JLabel("Player: -");
		actPlayerLabel.setBounds(actPlayerPoint.x, actPlayerPoint.y, 100, 20);
		this.add(actPlayerLabel);
		moveRemainLabel = new JLabel("Moves: -");
		moveRemainLabel.setBounds(moveRemainPoint.x, moveRemainPoint.y, 100, 20);
		this.add(moveRemainLabel);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point mouse = new Point(e.getX(), e.getY());
				mousegetCellHandler(mouse);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//mouseMove = false;
				Point mouse = new Point(e.getX(), e.getY());
				Point p = getCell(mouse);
				p.setLocation(offset.getX() +p.getX()* figuresize.x,offset.getY()+ p.getY()*figuresize.getY());
				System.out.println(p.toString());
				mousePoint.setLocation(p);
				repaintPanel();
			}
		});

		this.addMouseMotionListener(new MouseInputAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (mousePoint == null)
					mousePoint = new Point(e.getX(), e.getY());
				else
					mousePoint.setLocation(e.getX(), e.getY());

				mouseMove = true;
				repaintPanel();
			}

		});
	}

	private void repaintPanel() {
		this.repaint();
	}

	private void mousegetCellHandler(Point mouse) {
		Point cell = getCell(mouse);
		if (cell != null)
			System.out.println("Cell:" + cell.x + " " + cell.y);
		// TODO
	}

	private Point getCell(Point mouse) {
		Rectangle inPitch = new Rectangle();
		inPitch.setBounds(offset.x, offset.y, pitchSizePoint.x, pitchSizePoint.y);

		if (!inPitch.contains(mouse)) {
			return null;
		}

		Rectangle cell = new Rectangle();
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				cell.setBounds(offset.x + figuresize.x * x, offset.y + figuresize.y * y, figuresize.x, figuresize.y);
				if (cell.contains(mouse))
					return new Point(x, y);
			}
		}

		return null;
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
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getSize().width, getSize().height);

		// Paint pitch
		g.drawImage(pitchImage, offset.x, offset.y, pitchSizePoint.x, pitchSizePoint.x, null);

		// Paint Player1
		List<ICharacter> p1figure = controller.getPlayer1().getFigures();
		printFigures(g, p1figure, offset, figuresize);

		// Paint Player2
		List<ICharacter> p2figure = controller.getPlayer2().getFigures();
		printFigures(g, p2figure, offset, figuresize);

		// Test Mouse
		if (mouseMove == true) {
			g.setColor(Color.green);
			g.drawRect(mousePoint.x, mousePoint.y, figuresize.x, figuresize.y);
		}

		// remove?
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void printFigures(Graphics g, List<ICharacter> figure, Point offset, Point figuresize) {
		for (ICharacter f : figure) {
			CHARAKTER_NAME fname = f.getName();
			BufferedImage fimg = figuresImage.get(fname);

			Position fpos = f.getPosition();
			g.drawImage(fimg, fpos.getX()*figuresize.x + offset.x, fpos.getY()*figuresize.y + offset.y, figuresize.x, figuresize.y, null);
		}
	}

}
