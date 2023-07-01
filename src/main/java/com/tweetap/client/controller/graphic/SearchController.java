package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class SearchController implements HasStage
{

    @FXML
    public Button homeButton;
    @FXML
    public Button profileButton;
    @FXML
    public Button tweetButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button searchSearchSearchButton;
    @FXML
    public TextField searchTextField;
    private Stage stage;
    public Button searchButton;
    @FXML
    public VBox findUsersVBox;

    @FXML
    public void homeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void profileButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void tweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void exitButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : Exit
    }

    @FXML
    public void searchButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : process input variables and show results
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
