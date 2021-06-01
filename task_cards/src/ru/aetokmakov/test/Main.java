package ru.aetokmakov.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static final Color GRAY_COLOR = new Color(120, 120, 120);

    public static void main(String[] args) throws URISyntaxException, IOException {
        File templatesDirValues = new File("./resources/values");
        CardTemplates cardValueTemplates = new CardTemplates(templatesDirValues);
        File templatesDirSuits = new File("./resources/suits");
        CardTemplates cardSuitTemplates = new CardTemplates(templatesDirSuits);

        File dir = new File(args[0]);
        File fileList[] = dir.listFiles();
        for (File file: fileList) {
            String fileName = file.getName();

            BufferedImage image = ImageIO.read(file);
            BufferedImage newImage = image.getSubimage(140, 580, 400, 100);

            int x = 7;
            int y = 10;

            Color c = new Color(newImage.getRGB(x, y));
            String cardsOnDesk = "";
            while (c.equals(Color.WHITE) || c.equals(GRAY_COLOR)) {
                BufferedImage cardImage = newImage.getSubimage(x, y, 35, 45);

                Card card = new Card(cardImage);

                TemplateMatcher templateMatcherValue = new TemplateMatcher(cardValueTemplates, card.getValueImage());
                card.setValue(templateMatcherValue.getMatchingTemplateName());

                TemplateMatcher templateMatcherSuit = new TemplateMatcher(cardSuitTemplates, card.getSuitImage());
                card.setSuit(templateMatcherSuit.getMatchingTemplateName());

                x += 72;
                c = new Color(newImage.getRGB(x, y));

                cardsOnDesk = cardsOnDesk + card.getName();
            }

            System.out.println(fileName + "-" + cardsOnDesk);
        }
    }

}
