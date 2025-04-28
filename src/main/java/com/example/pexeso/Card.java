package com.example.pexeso;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private int id;
    private Button tlacitko;
    private boolean matched = false;
    private boolean flipped = false;

    private final Image frontImage;
    private final Image backImage;

    public Card(int id, String imagePath) {
        this.id = id;
        this.tlacitko = new Button();
        this.tlacitko.setMinSize(80, 80);

        this.frontImage = new Image(getClass().getResourceAsStream("/images/"+imagePath));
        this.backImage = new Image(getClass().getResourceAsStream("/images/cover.png"));
        flipBack();
    }

    public void flip(){
        tlacitko.setGraphic(new ImageView(frontImage));
    }

    public void flipBack(){
        tlacitko.setGraphic(new ImageView(backImage));
    }





    public int getId() {
        return id;
    }

    public Button getTlacitko() {
        return tlacitko;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public void setTlacitko(Button tlacitko) {
        this.tlacitko = tlacitko;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public Image getFrontImage() {
        return frontImage;
    }

    public Image getBackImage() {
        return backImage;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
