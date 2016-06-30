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

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.ICHARAKTER_NAME;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.ICharacterFactory;
import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class PitchPanel extends JPanel {
	IArimaaController controller;

	BufferedImage pitchImage;
	Point pitchSizePoint = new Point(400, 400);
	EnumMap<CHARAKTER_NAME, BufferedImage> figuresImage;
	Point figuresize = new Point(50, 50);
	Point offset = new Point(20, 45);

	// BUTTON
	JButton commitButton;
	Point commitPoint = new Point(20, 460);
	JButton playerChangeButton;
	Point playerChangePoint = new Point(270, 460);

	// Info
	JLabel actPlayerLabel;
	Point actPlayerPoint = new Point(25,10 );
	JLabel moveRemainLabel;
	Point moveRemainPoint = new Point(340, 10);

	// Mouse
	Point mousePoint = new Point(0, 0);
	ICharacter mouseFigure = null; // startposition
	boolean figureSet = false;

	public PitchPanel(IArimaaController controller) {
		this.controller = controller;
		figuresImage = new EnumMap<>(CHARAKTER_NAME.class);

		pitchImage = loadImage("BoardStoneSmall");
		loadFiguresImage();

		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);

		commitButton = new JButton("bestaetigen");
		commitButton.setBounds(commitPoint.x, commitPoint.y, 130, 30);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (figureSet) {
					
					mouseFigure = null;
					figureSet = false;
				}
			repaint();
				// Todo commit
			
			}
		});
		this.add(commitButton);

		playerChangeButton = new JButton("Runde beenden");
		playerChangeButton.setBounds(playerChangePoint.x, playerChangePoint.y, 150, 30);
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
			public void mouseReleased(MouseEvent e) {
				Point mouse = new Point(e.getX(), e.getY());
				mouseReleasedHandler(mouse);
			}
		});

		this.addMouseMotionListener(new MouseInputAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point mouse = new Point(e.getX(), e.getY());
				mouseDraggedHandler(mouse);
			}

		});
	}

	private void repaintPanel() {
		this.repaint();
	}

	private boolean updateMousePos(Point mouse) {
		if (!isPosInPitch(mouse))
			return false;

		mousePoint.setLocation(mouse);

		if (mouseFigure == null)
			initMoveFigure(mouse);

		return true;
	}

	private void initMoveFigure(Point mouse) {
		if (mouseFigure != null)
			return;

		Point cell = getCell(mouse);
		if (cell == null)
			return;

		Position pos = new Position((int) cell.getX(), (int) cell.getY());
		ICHARAKTER_NAME figureName = getCharacter(pos);
		mouseFigure = ICharacterFactory.getInstance(pos, figureName);
	}

	private void mouseReleasedHandler(Point mouse) {
		if (figureSet|| mouseFigure == null)
			return;

		Point cell = getCell(mouse);
		if (cell == null){
			mouseFigure = null;
			figureSet = false;
			repaint();
			return;
		}
			

		System.out.println("Cell:" + cell.x + " " + cell.y);
		// TODO

		double mx = offset.getX() + cell.getX() * figuresize.x;
		double my = offset.getY() + cell.getY() * figuresize.getY();

		Point mp = new Point();
		mp.setLocation(mx, my);
		updateMousePos(mp);

		repaintPanel();

		figureSet = true;
	}

	private void mouseDraggedHandler(Point mouse) {
		if (figureSet)
			return;

		updateMousePos(mouse);
		
		repaintPanel();
	}

	private ICHARAKTER_NAME getCharacter(Position cell) {
		ICHARAKTER_NAME name = null;
		name = controller.getPlayer1().getFigur(cell);
		if (name == null)
			name = controller.getPlayer2().getFigur(cell);
		return name;
	}

	private boolean isPosInPitch(Point mouse) {
		Rectangle inPitch = new Rectangle();
		inPitch.setBounds(offset.x, offset.y, pitchSizePoint.x, pitchSizePoint.y);

		if (inPitch.contains(mouse))
			return true;

		return false;
	}

	private Point getCell(Point mouse) {
		if (!isPosInPitch(mouse))
			return null;

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

	private void loadFiguresImage() {
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
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Paint the background.
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, getSize().width, getSize().height);

		// Paint pitch
		g2d.drawImage(pitchImage, offset.x, offset.y, pitchSizePoint.x, pitchSizePoint.x, null);

		// Paint Player1
		List<ICharacter> p1figure = controller.getPlayer1().getFigures();
		printFigures(g2d, p1figure, offset, figuresize);

		// Paint Player2
		List<ICharacter> p2figure = controller.getPlayer2().getFigures();
		printFigures(g2d, p2figure, offset, figuresize);

		// Draw Mouse Figure
		if (mouseFigure != null) {
			g2d.setColor(Color.green);
			g2d.drawRect(mousePoint.x, mousePoint.y, figuresize.x, figuresize.y);
			ICHARAKTER_NAME fname = mouseFigure.getName();
			BufferedImage fimg = figuresImage.get(fname);
			g2d.drawImage(fimg, mousePoint.x, mousePoint.y, figuresize.x, figuresize.y, null);
		}



	}

	public void printFigures(Graphics2D g2d, List<ICharacter> figure, Point offset, Point figuresize) {
		for (ICharacter f : figure) {
			ICHARAKTER_NAME fname = f.getName();
			BufferedImage fimg = figuresImage.get(fname);

			Position fpos = f.getPosition();
			
			//If figure is the moving figure
			if(mouseFigure != null && fpos.equals(mouseFigure.getPosition()))
			  continue;
			
			g2d.drawImage(fimg, fpos.getX() * figuresize.x + offset.x, fpos.getY() * figuresize.y + offset.y,
					figuresize.x, figuresize.y, null);
		}
	}

}
