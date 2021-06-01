package ru.aetokmakov.test;

import java.awt.image.BufferedImage;

public class CardTemplate {
    private BufferedImage image;
    private String name;

    public CardTemplate(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
