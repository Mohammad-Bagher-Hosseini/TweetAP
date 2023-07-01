package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.tweet.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TimeLineController
{
    @FXML
    public Button searchButton;
    @FXML
    public Button profileButton;
    @FXML
    public Button tweetButton;
    @FXML
    public Button exitButton;
    @FXML
    public VBox tweetsVBox;

    private Stage stage;

    public void initialize()
    {
        try
        {
            TimeLine timeLine = ControllerCommands.showTimeLine();
            for(BaseTweet baseTweet : timeLine)
            {
                if(baseTweet instanceof Tweet tweet)
                    addTweet(tweet);
                else if(baseTweet instanceof Quote quote)
                    addQuote(quote);
                else if (baseTweet instanceof Retweet retweet)
                addRetweet(retweet);
            }

        } catch (TwitException e)
        {
            // TODO
        }
    }

    private void addTweet(Tweet tweet)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("tweetview.fxml"));
            Parent tweetContainer = fxmlLoader.load();
            tweetsVBox.getChildren().add(tweetContainer);

            TweetViewController tweetViewController = fxmlLoader.getController();
            tweetViewController.showTweet(tweet);
        } catch (IOException e)
        {
            // TODO
        }
    }

    private void addRetweet(Retweet retweet)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("retweetview.fxml"));
            Parent tweetContainer = fxmlLoader.load();
            tweetsVBox.getChildren().add(tweetContainer);

            RetweetViewController retweetViewController = fxmlLoader.getController();
            retweetViewController.showRetweet(retweet);
        } catch (IOException e)
        {
            // TODO
        }
    }

    private void addQuote(Quote quote)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("quoteview.fxml"));
            Parent tweetContainer = fxmlLoader.load();
            tweetsVBox.getChildren().add(tweetContainer);

            QuoteViewController quoteViewController = fxmlLoader.getController();
            quoteViewController.showQuote(quote);
        } catch (IOException e)
        {
            // TODO
        }
    }

    @FXML
    public void searchButtonOnAction(ActionEvent actionEvent)
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
        Data.getInstance().save();
        System.exit(0);
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
