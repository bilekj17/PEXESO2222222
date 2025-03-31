package com.example.pexeso;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;

public class HelloController {
    @FXML
    private Label vitez;
    @FXML
    private Label firstPlace;
    @FXML
    private Label secondPlace;
    @FXML
    private Label onlinePlayer;
    @FXML
    private GridPane grid;

    private ArrayList<Card> cards = new ArrayList<>();
    private Card firstCard = null;
    private Card secondCard = null;
    private boolean canFlip = true;

    private int currentPlayer = 1;
    private int score1 = 0;
    private int score2 = 0;


    @FXML
    public void initialize() {
        generateCards();
        Collections.shuffle(cards);
        displayCards();
    }

    private void generateCards() {
        for (int i = 1; i <= 8; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));
        }
    }

    private void displayCards(){
        grid.getChildren().clear();
        int index = 0;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Card card = cards.get(index++);
                Button btn = card.getTlacitko();
                btn.setOnAction((e -> handleCardClick(card)));
                grid.add(btn, col, row);
            }
        }
    }

    private void handleCardClick(Card card) {
        if (!canFlip || card.isMatched() || card.getTlacitko().getText() != "?"){
            return;
        }
        card.flip();

        if (firstCard == null) {
            firstCard = card;
        }else {
            secondCard = card;
            canFlip = false;

            checkCards();
        }
    }

    private void checkCards() {
        if (firstCard.getId() == secondCard.getId()){
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            firstCard = null;
            secondCard = null;
            if (currentPlayer == 1){
                score1++;
            }else if (currentPlayer == 2){
                score2++;
            }
            if (gameOver() == true){
                if (score1>score2){
                    firstPlace.setText("player 1 has won "+score1);
                    secondPlace.setText("player 2 has lost "+score2);
                } else if (score1 == score2) {
                    firstPlace.setText("its draw"+score2+", "+score1);
                    secondPlace.setText("No player is second");
                }else if(score2>score1){
                    firstPlace.setText("player 2 has won "+score2);
                    secondPlace.setText("player 1 has lost "+score1);
                }
            }
        }else{
            firstCard.flipBack();
            secondCard.flipBack();
            firstCard = null;
            secondCard = null;
        }
        canFlip = true;
    }

    private boolean gameOver(){
        for (Card card : cards) {
           if (card.isMatched() == false) {
               return false;
           }
        }
        return true;
    }
}