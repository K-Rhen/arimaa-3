package de.htwg.se.arimaa.aview.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.PLAYER_NAME;
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
	Point figureSize = new Point(50, 50);
	Point offsetPitch = new Point(20, 45);

	// Mouse
	MouseFigure mouseFigure = null;

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

	private void mouseReleasedHandler(Point mouse) {
		Position temp = getCell(mouse);

		if (temp != null && mouseFigure != null && !mouseFigure.getFromPosition().equals(temp)) {
			mouseFigure.setToPosition(temp);
			System.out
					.println("From:" + mouseFigure.getFromPosition().toString() + " To:" + mouseFigure.getToPosition());

			controller.moveFigure(mouseFigure.getFromPosition(), mouseFigure.getToPosition());
		}

		mouseFigure = null;
		this.repaint();
	}

	private void mouseDraggedHandler(Point mouse) {
		if (mouseFigure == null) {
			Position fromPos = getCell(mouse);
			if (fromPos == null)
				return;

			FIGURE_NAME figureName = controller.getFigureNamebyPosition(fromPos);
			if (figureName == null)
				return;

			PLAYER_NAME playerName = controller.getPlayerNamebyPosition(fromPos);
			mouseFigure = new MouseFigure(mouse, figureName, playerName, fromPos);
		}

		if (mouseFigure != null)
			mouseFigure.setPoint(mouse);

		this.repaint();
	}

	private Position getCell(Point mouse) {
		if (!isPosInPitch(mouse))
			return null;

		double px = mouse.getX() - offsetPitch.getX();
		double py = mouse.getY() - offsetPitch.getY();

		px = px / figureSize.getX();
		py = py / figureSize.getY();
		Position p = new Position((int) px, (int) py);

		return p;
	}

	private boolean isPosInPitch(Point mouse) {
		Rectangle inPitch = new Rectangle();
		inPitch.setBounds(offsetPitch.x, offsetPitch.y, pitchSizePoint.x, pitchSizePoint.y);

		if (inPitch.contains(mouse))
			return true;

		return false;
	}

	private BufferedImage loadImage(String name) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("/" + name + ".png"));
		} catch (IOException e) {
			LOGGER.error("Images: " + name + "not found" + e.getMessage());
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
		g2d.drawImage(pitchImage, offsetPitch.x, offsetPitch.y, pitchSizePoint.x, pitchSizePoint.x, null);

		// paint gold figures
		List<IFigure> figuresGold = controller.getGoldFigures();
		drawFigures(g2d, figuresImageGold, figuresGold, offsetPitch, figureSize);
		// paint silver figures
		List<IFigure> figuresSilver = controller.getSilverFigures();
		drawFigures(g2d, figuresImageSilver, figuresSilver, offsetPitch, figureSize);

		// Draw Mouse Figure
		if (mouseFigure != null) {
			drawMouseFigure(g2d);
		}
	}

	private void drawMouseFigure(Graphics2D g2d) {
		g2d.setColor(Color.green);
		int mouseX = (int) mouseFigure.getX() - figureSize.x / 2;
		int mouseY = (int) mouseFigure.getY() - figureSize.y / 2;
		g2d.drawRect(mouseX, mouseY, figureSize.x, figureSize.y);
		FIGURE_NAME figureName = mouseFigure.getFigureName();
		BufferedImage figureImg = getFigureBufferedImage(figureName);
		g2d.drawImage(figureImg, mouseX, mouseY, figureSize.x, figureSize.y, null);
	}

	private BufferedImage getFigureBufferedImage(FIGURE_NAME figureName) {
		if (mouseFigure.getPlayer().equals(PLAYER_NAME.GOLD))
			return figuresImageGold.get(figureName);
		else
			return figuresImageSilver.get(figureName);
	}

	private void drawFigures(Graphics2D g2d, EnumMap<FIGURE_NAME, BufferedImage> figuresImage, List<IFigure> figure,
			Point offset, Point figuresize) {
		for (IFigure f : figure) {
			FIGURE_NAME fname = f.getName();
			BufferedImage fimg = figuresImage.get(fname);

			Position fpos = f.getPosition();

			// skip if figure equals moving figure
			if (mouseFigure != null && fpos.equals(mouseFigure.getFromPosition()))
				continue;

			int figureX = fpos.getX() * figuresize.x + offset.x;
			int figureY = fpos.getY() * figuresize.y + offset.y;
			g2d.drawImage(fimg, figureX, figureY, figuresize.x, figuresize.y, null);
		}
	}

	@Override
	public void update(Event e) {
		this.repaint();

		// GameStatus gs = controller.getGameStatus();
		// if (gs.equals(GameStatus.WIN_GOLD) ||
		// gs.equals(GameStatus.WIN_SILVER)) {
		// JOptionPane.showMessageDialog(null,
		// controller.getCurrentPlayerName().toString() + " win the game", ":D",
		// JOptionPane.INFORMATION_MESSAGE);
		// } else if (gs.equals(GameStatus.MOVEFIGURE)) {
		// this.repaint();
		// }

	}

}
