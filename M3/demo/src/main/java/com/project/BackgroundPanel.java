package com.project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                backgroundImage = ImageIO.read(imgURL);
            } else {
                System.err.println("Couldn't find file: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int width = getWidth();
            int height = getHeight();
            g.drawImage(backgroundImage, 0, 0, width, height, this);
        }
    }
}
