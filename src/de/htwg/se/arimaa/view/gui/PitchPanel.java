package de.htwg.se.arimaa.view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class PitchPanel extends JPanel{

	Image bimage;
	
	public PitchPanel() {
		
		try {
			System.out.println("PATH"+getClass().getResource("lib").getPath());
			   bimage =   ImageIO.read(getClass().getResource("/img/a.jpg"));
			  //bimage = ImageIO.read(getClass().getResourceAsStream("/img/BoardStoneMed.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Paint the background.
		g.setColor(Color.green);
		g.drawImage(bimage, 0, 0, 40, 40, null);
		g.fillRect(0, 0, getSize().width, getSize().height);

		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
