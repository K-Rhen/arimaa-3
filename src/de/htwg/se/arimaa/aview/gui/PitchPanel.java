package de.htwg.se.arimaa.aview.gui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.impl.Figure;
import de.htwg.se.arimaa.util.observer.Event;
import de.htwg.se.arimaa.util.observer.IObserver;
import de.htwg.se.arimaa.util.position.Position;

public class PitchPanel extends JPanel implements IObserver {
	private static final Logger LOGGER = LogManager.getLogger(PitchPanel.class.getName());
	
	IArimaaController controller;

	BufferedImage pitchImage;
	Point pitchSizePoint = new Point(400, 400);
	EnumMap<FIGURE_NAME, BufferedImage> figuresImageGold;
	EnumMap<FIGURE_NAME, BufferedImage> figuresImageSilver;
	Point figuresize = new Point(50, 50);
	Point offset = new Point(20, 45);

//	// Info
//	JLabel actPlayerLabel;
//	Point actPlayerPoint = new Point(25, 10);
//	JLabel moveRemainLabel;
//	Point moveRemainPoint = new Point(340, 10);

	// Mouse
	Point mousePoint = new Point(0, 0);
	IFigure mouseFigureFrom = null; // startposition
	Position mouseFigureTo = null; // endposition
	boolean figureSet = false;

	public PitchPanel(IArimaaController controller) {
		this.controller = controller;
		controller.addObserver(this);

		figuresImageGold = new EnumMap<>(FIGURE_NAME.class);
		figuresImageSilver = new EnumMap<>(FIGURE_NAME.class);

		pitchImage = loadImage("BoardStoneSmall");
		loadFiguresImage();

		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		
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

		if (mouseFigureFrom == null)
			initMoveFigure(mouse);

		return true;
	}

	private void initMoveFigure(Point mouse) {
		if (mouseFigureFrom != null)
			return;

		Point cell = getCell(mouse);
		if (cell == null)
			return;

		Position pos = new Position((int) cell.getX(), (int) cell.getY());
		FIGURE_NAME figureName = (FIGURE_NAME) getCharacter(pos);
		mouseFigureFrom = new Figure(pos, figureName);
	}

	private void mouseReleasedHandler(Point mouse) {
		if (figureSet || mouseFigureFrom == null||controller.getRemainingMoves() == 0)
			return;

		Point cell = getCell(mouse);
		if (cell == null) {
			mouseFigureFrom = null;
			figureSet = false;
			repaintPanel();
			return;
		}
		mouseFigureTo = new Position(cell.x, cell.y);

		double mx = offset.getX() + cell.getX() * figuresize.x;
		double my = offset.getY() + cell.getY() * figuresize.getY();

		Point mp = new Point();
		mp.setLocation(mx, my);
		updateMousePos(mp);

		repaintPanel();

		figureSet = true;
	}

	private void mouseDraggedHandler(Point mouse) {
		if (figureSet||controller.getRemainingMoves() == 0)
			return;

		updateMousePos(mouse);

		repaintPanel();
	}

	private FIGURE_NAME getCharacter(Position cell) {
		FIGURE_NAME name = null;
		//TODO refactor
//		name = controller.getPlayer1().getFigure(cell);
//		if (name == null)
//			name = controller.getPlayer2().getFigure(cell);
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
			LOGGER.error("Images: "+ name + "not found" + e.getMessage());
		}
		return image;
	}

	private void loadFiguresImage() {
		figuresImageGold.put(FIGURE_NAME.R, loadImage("GoldRabbit"));
		figuresImageGold.put(FIGURE_NAME.C, loadImage("GoldCat"));
		figuresImageGold.put(FIGURE_NAME.D, loadImage("GoldDog"));
		figuresImageGold.put(FIGURE_NAME.H, loadImage("GoldCat"));
		figuresImageGold.put(FIGURE_NAME.L, loadImage("GoldCamel"));
		figuresImageGold.put(FIGURE_NAME.E, loadImage("GoldElephant"));

		figuresImageSilver.put(FIGURE_NAME.R, loadImage("SilverRabbit"));
		figuresImageSilver.put(FIGURE_NAME.C, loadImage("SilverCat"));
		figuresImageSilver.put(FIGURE_NAME.D, loadImage("SilverDog"));
		figuresImageSilver.put(FIGURE_NAME.H, loadImage("SilverCat"));
		figuresImageSilver.put(FIGURE_NAME.L, loadImage("SilverCamel"));
		figuresImageSilver.put(FIGURE_NAME.E, loadImage("SilverElephant"));
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


		//paint gold figures
		List<IFigure> figuresGold = controller.getGoldFigures();
		printFigures(g2d, figuresImageGold,figuresGold, offset, figuresize);
		//paint silver figures
		List<IFigure> figuresSilver = controller.getSilverFigures();
		printFigures(g2d, figuresImageSilver,figuresSilver, offset, figuresize);
		
		
		// Draw Mouse Figure
		if (mouseFigureFrom != null) {
			g2d.setColor(Color.green);
			g2d.drawRect(mousePoint.x, mousePoint.y, figuresize.x, figuresize.y);
			FIGURE_NAME fname = mouseFigureFrom.getName();
			BufferedImage fimg = figuresImageSilver.get(fname); //TODO
			g2d.drawImage(fimg, mousePoint.x, mousePoint.y, figuresize.x, figuresize.y, null);
		}
	}

	private void printFigures(Graphics2D g2d,EnumMap<FIGURE_NAME, BufferedImage> figuresImage, List<IFigure> figure, Point offset, Point figuresize) {
		for (IFigure f : figure) {
			FIGURE_NAME fname = f.getName();
			BufferedImage fimg = figuresImage.get(fname);

			Position fpos = f.getPosition();

			// If figure is the moving figure
			if (mouseFigureFrom != null && fpos.equals(mouseFigureFrom.getPosition()))
				continue;

			g2d.drawImage(fimg, fpos.getX() * figuresize.x + offset.x, fpos.getY() * figuresize.y + offset.y,
					figuresize.x, figuresize.y, null);
		}
	}

	@Override
	public void update(Event e) {
		GameStatus gs = controller.getGameStatus();
		if (gs.equals(GameStatus.WIN_GOLD)|| gs.equals(GameStatus.WIN_SILVER)) {
			JOptionPane.showMessageDialog(null,controller.getCurrentPlayer().toString() +" win the game",":D",JOptionPane.INFORMATION_MESSAGE);
		} else if (gs.equals(GameStatus.MOVEFIGURE)) {
			repaintPanel();
		} 

	}

}
