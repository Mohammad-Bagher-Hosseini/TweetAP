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

import java.io.IOException;

public class RetweetViewController
{
    @FXML
    public Label userNameLabel;
    @FXML
    public VBox tweetVbox;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show selected user's profile
    }

    public void showRetweet(Retweet retweet)
    {
        userNameLabel.setText(retweet.getUserName());
        Tweet tweet = retweet.getTweet();
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("tweetview.fxml"));
            Parent tweetContainer = fxmlLoader.load();
            tweetVbox.getChildren().add(tweetContainer);

            TweetViewController tweetViewController = fxmlLoader.getController();
            tweetViewController.showTweet(tweet);
        } catch (IOException e)
        {
            // TODO
        }
    }
}
