package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ContentTrend extends JPanel {
    private BufferedImage image;

    public ContentTrend() {
        // Set a preferred size if needed
        setPreferredSize(new Dimension(800, 600));
    }

    public void displayImageFromBase64(String base64String) {
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        try {
            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            if (image != null) {
                revalidate();
                repaint();
            } else {
                System.out.println("Failed to decode image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            System.out.println("image is found! in contentTrend");
            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;
            g.drawImage(image, x, y, this);
        }
    }
}