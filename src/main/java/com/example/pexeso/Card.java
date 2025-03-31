package com.example.pexeso;

import javafx.scene.control.Button;

public class Card {
    private int id;
    private Button tlacitko;
    private boolean matched = false;

    public Card(int id) {
        this.id = id;
        this.tlacitko = new Button("?");
        this.tlacitko.setMinSize(60, 60);
    }

    public void flip(){
        tlacitko.setText(String.valueOf(id));
    }

    public void flipBack(){
        tlacitko.setText("?");
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
}
