package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseRetweetController implements HasStage
{
    @FXML
    public Button retweetButton;
    @FXML
    public Button quoteButton;
    private Stage stage;

    @FXML
    public void retweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : retweet the selected tweet
    }

    @FXML
    public void quoteButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : close this pop_up and open the sendquote_pop_up
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
