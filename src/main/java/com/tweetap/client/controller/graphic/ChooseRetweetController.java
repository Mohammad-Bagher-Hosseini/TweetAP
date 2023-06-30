package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChooseRetweetController
{
    @FXML
    public Button chooseRetweetRetweetButton;
    @FXML
    public Button chooseRetweetQuoteButton;

    @FXML
    public void chooseRetweetRetweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : retweet the selected tweet
    }

    @FXML
    public void chooseRetweetQuoteButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : close this pop_up and open the sendquote_pop_up
    }
}
