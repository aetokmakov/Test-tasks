package ru.aetokmakov.test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class CardTemplates {
    private ArrayList<CardTemplate> templates;
    private File templatesDir;

    public CardTemplates(File templatesDir) throws URISyntaxException, IOException {
        this.templatesDir = templatesDir;

        this.templates = new ArrayList<>();
        for (File file: templatesDir.listFiles()) {
            CardTemplate cardTemplate = new CardTemplate(ImageIO.read(file), file.getName().substring(0, file.getName().indexOf('-')));
            this.templates.add(cardTemplate);
        }
    }

    public ArrayList<CardTemplate> getTemplates() {
        return this.templates;
    }
}
