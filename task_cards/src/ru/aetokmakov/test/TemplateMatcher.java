package ru.aetokmakov.test;

import java.awt.image.BufferedImage;

public class TemplateMatcher {
    private static double MIN_DIFF = 100.0;

    private CardTemplates cardTemplates;
    private BufferedImage matchingImage;

    public TemplateMatcher(CardTemplates cardTemplates, BufferedImage matchingImage) {
        this.cardTemplates = cardTemplates;
        this.matchingImage = matchingImage;
    }

    public String getMatchingTemplateName() {
        double minDiff = MIN_DIFF;
        double diff;
        String result = "";
        for (CardTemplate template: this.cardTemplates.getTemplates()) {
            diff = diffPercent(template.getImage(), this.matchingImage);
            if (diff < minDiff) {
                minDiff = diff;
                result = template.getName();
            }
        }

        return result;
    }

    public static double diffPercent(BufferedImage template, BufferedImage target) {
        int widthTemplate = template.getWidth();
        int heightTemplate = template.getHeight();

        long difference = 0;
        for (int x = 0; x < widthTemplate; x++) {
            for (int y = 0; y < heightTemplate; y++) {
                int rgbA = template.getRGB(x, y);
                int rgbB = target.getRGB(x, y);
                int redA = (rgbA >> 16) & 0xff;
                int greenA = (rgbA >> 8) & 0xff;
                int blueA = (rgbA) & 0xff;
                int redB = (rgbB >> 16) & 0xff;
                int greenB = (rgbB >> 8) & 0xff;
                int blueB = (rgbB) & 0xff;
                difference += Math.abs(redA - redB);
                difference += Math.abs(greenA - greenB);
                difference += Math.abs(blueA - blueB);
            }
        }

        double totalPixels = widthTemplate * heightTemplate * 3;

        double avgDifferentPixels = difference /
                totalPixels;

        double percentage = (avgDifferentPixels /
                255) * 100;

        return percentage;
    }

}
