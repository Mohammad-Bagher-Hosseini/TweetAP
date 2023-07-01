package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.entities.tweet.Retweet;
import com.tweetap.entities.tweet.Tweet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RetweetViewController implements HasStage
{
    @FXML
    public Label userNameLabel;
    @FXML
    public VBox tweetVbox;
    private Stage stage;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        ObserverProfileController observerProfileController = MainClient.loadPopup(stage, "observerprofile_pop_up.fxml", (controller) -> controller.setUsername(userNameLabel.getText()));}

    public void showRetweet(Retweet retweet)
    {
        userNameLabel.setText(retweet.getUserName());
        Tweet tweet = retweet.getTweet();
        TweetViewController tweetViewController = MainClient.loadPage(tweetVbox, stage, "tweetview.fxml");
        tweetViewController.showTweet(tweet);
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
