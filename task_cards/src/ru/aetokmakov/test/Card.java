package ru.aetokmakov.test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Card {
    private BufferedImage image;

    private String value;
    private BufferedImage valueImage;

    private String suit;
    private BufferedImage suitImage;

    public Card(BufferedImage image) {
        this.image = image;
        setValueImage();
        setSuitImage();
    }

    public BufferedImage getValueImage() {
        return valueImage;
    }

    private void setValueImage() {
        BufferedImage cardValue = this.image.getSubimage(0, 0, 35, 25);
        int xValue = findFirstColorX(cardValue);
        this.valueImage = cardValue.getSubimage(xValue, 0, 25, 25);
    }

    public BufferedImage getSuitImage() {
        return this.suitImage;
    }

    private void setSuitImage() {
        BufferedImage cardSuit = this.image.getSubimage(0, 25, 35, 20);
        int xSuit = findFirstColorX(cardSuit);
        this.suitImage = cardSuit.getSubimage(xSuit, 0, 20, 20);
    }

    public String getName() {
        return this.value + this.suit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public static int findFirstColorX(BufferedImage image) {
        int firstColorX = 0;
        Color backgroundColor = new Color(image.getRGB(0, 0));
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color c = new Color(image.getRGB(x, y));
                if (!c.equals(backgroundColor)) {
                    firstColorX = x;
                    break;
                }
            }
            if (firstColorX != 0) break;
        }

        return firstColorX;
    }
}
