/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Xuanphuc
 */
public class Captcha {

    public String generateCaptchaText() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captchaText.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captchaText.toString();
    }

    public BufferedImage generateCaptchaImage(String text) {
        int width = 200;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        // Add noise lines to the image
        for (int i = 0; i < 10; i++) {
            g.setColor(getRandomColor());
            int x1 = (int) (Math.random() * width);
            int y1 = (int) (Math.random() * height);
            int x2 = (int) (Math.random() * width);
            int y2 = (int) (Math.random() * height);
            g.drawLine(x1, y1, x2, y2);
        }

        // Draw the text with a random color
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        for (int i = 0; i < text.length(); i++) {
            g.setColor(g.getColor());
            int x = 20 + i * 30;
            int y = 40 - (int) (Math.random() * 10); // Add some vertical variation
            g.drawString(String.valueOf(text.charAt(i)), x, y);
        }

        return image;
    }

    public Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
