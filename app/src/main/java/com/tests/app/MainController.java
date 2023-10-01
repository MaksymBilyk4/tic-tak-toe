package com.tests.app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class MainController {

    private char currentSymbol = 'X';

    private final char[][] gameField = new char[3][3];

    private boolean isGame = true;

    private int stepsCount = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void btnClick(ActionEvent event) {
        if (!isGame) return;

        stepsCount++;

        Button btn = (Button) event.getSource();
        btn.setDisable(true);
        btn.setText(String.valueOf(currentSymbol));
        btn.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");

        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int colIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);

        gameField[rowIndex][colIndex] = currentSymbol;

        if (checkWin(currentSymbol)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Победитель " + btn.getText() + "!", ButtonType.OK);
            alert.showAndWait();
            isGame = false;
        }
        else if (!checkWin(currentSymbol) && stepsCount == 9){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Победителя нету, произошла ничья!", ButtonType.OK);
            alert.showAndWait();
            isGame = false;
        }


        currentSymbol = currentSymbol == 'X' ? 'O' : 'X';
    }

    @FXML
    void initialize() {

    }

    private boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0] == symbol && gameField[i][1] == symbol && gameField[i][2] == symbol) return true;
            if (gameField[0][i] == symbol && gameField[1][i] == symbol && gameField[2][i] == symbol) return true;
        }

        if (gameField[0][0] == symbol && gameField[1][1] == symbol && gameField[2][2] == symbol) return true;
        if (gameField[0][2] == symbol && gameField[1][1] == symbol && gameField[2][0] == symbol) return true;

        return false;
    }

}
