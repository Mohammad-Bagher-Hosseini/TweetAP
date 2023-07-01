package com.tweetap.client.controller.graphic;

import com.tweetap.entities.tweet.Retweet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class RetweetViewController
{
    @FXML
    public Label retweetViewUserNameLabel;
    @FXML
    public VBox tweetVbox;

    @FXML
    public void retweetViewUserNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show selected user's profile
    }

    public void showRetweet(Retweet retweet)
    {
        // TODO
    }
}
