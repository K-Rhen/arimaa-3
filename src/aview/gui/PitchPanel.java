package aview.gui;

import controller.GameStatus;
import controller.IArimaaController;
import model.FIGURE_NAME;
import model.IFigure;
import model.PLAYER_NAME;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.observer.Event;
import util.observer.IObserver;
import util.position.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PitchPanel extends JPanel implements IObserver {
    private static final Logger LOGGER = LogManager.getLogger(PitchPanel.class.getName());

    private IArimaaController controller;

    private BufferedImage pitchImage;
    private Point pitchSizePoint = new Point(400, 400);
    private Map<FIGURE_NAME, BufferedImage> figuresImageGold;
    private Map<FIGURE_NAME, BufferedImage> figuresImageSilver;
    private Point figureSize = new Point(50, 50);
    private Point offsetPitch = new Point(20, 20);

    // Mouse
    private MouseFigure mouseFigure = null;

    PitchPanel(IArimaaController controller) {
        this.controller = controller;
        controller.addObserver(this);

        figuresImageGold = new EnumMap<>(FIGURE_NAME.class);
        figuresImageSilver = new EnumMap<>(FIGURE_NAME.class);

        pitchImage = loadImage("BoardStoneSmall");
        loadFiguresImage();

        initGUI();
    }

    private void initGUI() {
        this.setBorder(BorderFactory.createTitledBorder("Pitch"));

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

            FIGURE_NAME figureName = controller.getFigureName(fromPos);
            if (figureName == null)
                return;

            PLAYER_NAME playerName = controller.getPlayerName(fromPos);
            mouseFigure = new MouseFigure(mouse, figureName, playerName, fromPos);
        } else {

            mouseFigure.setPoint(mouse);
        }
        this.repaint();
    }

    private Position getCell(Point mouse) {
        if (!isPosInPitch(mouse))
            return null;

        double px = mouse.getX() - offsetPitch.getX();
        double py = mouse.getY() - offsetPitch.getY();

        px = px / figureSize.getX();
        py = py / figureSize.getY();
        return new Position((int) px, (int) py);
    }

    private boolean isPosInPitch(Point mouse) {
        Rectangle inPitch = new Rectangle();
        inPitch.setBounds(offsetPitch.x, offsetPitch.y, pitchSizePoint.x, pitchSizePoint.y);

        return inPitch.contains(mouse);
    }

    private BufferedImage loadImage(String imageName) {
        BufferedImage image = null;
        String pathName = "/img/" + imageName + ".png";
        try {
            image = ImageIO.read(getClass().getResource(pathName));
        } catch (Exception e) {
            LOGGER.error("Can't find image: " + pathName);
        }
        return image;
    }

    private void loadFiguresImage() {
        figuresImageGold.put(FIGURE_NAME.R, loadImage("GoldRabbit"));
        figuresImageGold.put(FIGURE_NAME.C, loadImage("GoldCat"));
        figuresImageGold.put(FIGURE_NAME.D, loadImage("GoldDog"));
        figuresImageGold.put(FIGURE_NAME.H, loadImage("GoldHorse"));
        figuresImageGold.put(FIGURE_NAME.M, loadImage("GoldCamel"));
        figuresImageGold.put(FIGURE_NAME.E, loadImage("GoldElephant"));

        figuresImageSilver.put(FIGURE_NAME.R, loadImage("SilverRabbit"));
        figuresImageSilver.put(FIGURE_NAME.C, loadImage("SilverCat"));
        figuresImageSilver.put(FIGURE_NAME.D, loadImage("SilverDog"));
        figuresImageSilver.put(FIGURE_NAME.H, loadImage("SilverHorse"));
        figuresImageSilver.put(FIGURE_NAME.M, loadImage("SilverCamel"));
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
            drawPossiblePosition(g2d);

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

    private void drawPossiblePosition(Graphics2D g2d) {
        List<Position> possibleMoves = controller.getPossibleMoves(mouseFigure.getFromPosition());

        if (possibleMoves == null)
            return;

        for (Position position : possibleMoves) {
            Color transparentGreenColour = new Color(0, 200, 0, 100);
            g2d.setColor(transparentGreenColour);
            int posX = (int) (position.getX() * figureSize.x + offsetPitch.getX());
            int posY = (int) (position.getY() * figureSize.y + offsetPitch.getX());
            g2d.fill(new Rectangle2D.Double(posX, posY, figureSize.x, figureSize.y));
        }
    }

    private BufferedImage getFigureBufferedImage(FIGURE_NAME figureName) {
        if (mouseFigure.getPlayer().equals(PLAYER_NAME.GOLD))
            return figuresImageGold.get(figureName);
        else
            return figuresImageSilver.get(figureName);
    }

    private void drawFigures(Graphics2D g2d, Map<FIGURE_NAME, BufferedImage> figuresImage, List<IFigure> figure,
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

        GameStatus gs = controller.getGameStatus();
        if (gs.equals(GameStatus.FINISH)) {
            JOptionPane.showMessageDialog(null, controller.getStatusText(), "Some one has won the game",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
