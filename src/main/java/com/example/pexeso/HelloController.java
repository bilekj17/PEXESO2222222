package com.example.pexeso;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

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
    private Label thirdPlace;
    @FXML
    private Label fourthPlace;
    @FXML
    private Label fifthPlace;
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
    private int score3 = 0;
    private int score4 = 0;
    private int score5 = 0;


    @FXML
    public void initialize() {
        generateCards();
        Collections.shuffle(cards);
        displayCards();
    }

    private void generateCards() {
        cards.add(new Card(1, "lemur.jpg"));
        cards.add(new Card(2, "lev.jpg"));
        cards.add(new Card(3, "liska.jpg"));
        cards.add(new Card(4, "papousek.jpg"));
        cards.add(new Card(5, "pes.jfif"));
        cards.add(new Card(6, "rys.jpg"));
        cards.add(new Card(7, "tygr.jpg"));
        cards.add(new Card(8, "veverka.jpg"));


        cards.add(new Card(1, "lemur.jpg"));
        cards.add(new Card(2, "lev.jpg"));
        cards.add(new Card(3, "liska.jpg"));
        cards.add(new Card(4, "papousek.jpg"));
        cards.add(new Card(5, "pes.jfif"));
        cards.add(new Card(6, "rys.jpg"));
        cards.add(new Card(7, "tygr.jpg"));
        cards.add(new Card(8, "veverka.jpg"));
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
        if (!canFlip || card.isMatched() || card.isFlipped()){
            return;
        }
        card.flip();

        if (firstCard == null) {
            firstCard = card;
        }else {
            secondCard = card;
            canFlip = false;

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> checkCards());
            pause.play();
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
                firstPlace.setText("player: "+currentPlayer+" má "+score1+" body/ů");
            }else if (currentPlayer == 2){
                score2++;
                secondPlace.setText("player: "+currentPlayer+" má "+score2+" body/ů");
            }
            if (gameOver() == true){
                if (score1>score2){
                    vitez.setText("player: "+currentPlayer+" je vítěz");
                } else if (score1 == score2) {
                    vitez.setText("oba hráči jsou vítezové: "+score1+", "+score2);
                }else if(score2>score1){
                    vitez.setText("player: "+currentPlayer+" je vítěz");
                }
            }
        }else{
            firstCard.flipBack();
            secondCard.flipBack();
            firstCard = null;
            secondCard = null;
            if (currentPlayer == 1){
                currentPlayer = 2;
                onlinePlayer.setText("player: "+currentPlayer);
            } else if (currentPlayer == 2){
                currentPlayer = 1;
                onlinePlayer.setText("player: "+currentPlayer);
            }
        }
        canFlip = true;

        System.out.println(score1);
        System.out.println(score2);
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