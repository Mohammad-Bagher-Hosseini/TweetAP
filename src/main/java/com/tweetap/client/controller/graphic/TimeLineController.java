package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.tweet.*;
import com.tweetap.server.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TimeLineController implements HasStage
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
    @FXML
    public AnchorPane rootPane;

    private Stage stage;
    private double x = 0, y = 0;

    public void initialize()
    {

    }

    public void onShown()
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while loading the timeline");
            alert.show();
        }
    }

    private void addTweet(Tweet tweet)
    {
        TweetViewController tweetViewController = MainClient.loadPage(tweetsVBox, stage, "tweetview.fxml");
        tweetViewController.showTweet(tweet);
    }

    private void addRetweet(Retweet retweet)
    {
        RetweetViewController retweetViewController = MainClient.loadPage(tweetsVBox, stage, "retweetview.fxml");
        retweetViewController.showRetweet(retweet);
    }

    private void addQuote(Quote quote)
    {
        QuoteViewController quoteViewController = MainClient.loadPage(tweetsVBox, stage, "quoteview.fxml");
        quoteViewController.showQuote(quote);
    }

    @FXML
    public void searchButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "search.fxml");
    }

    @FXML
    public void profileButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "profile.fxml");
    }

    @FXML
    public void tweetButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "sendtweet.fxml");
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
        onShown();
    }

    @FXML
    public void rootPaneOnMouseDragged(MouseEvent mouseEvent)
    {
//        x = mouseEvent.getSceneX();
//        y = mouseEvent.getSceneY();
    }

    @FXML
    public void rootPaneOnMousePressed(MouseEvent mouseEvent)
    {
//        stage.setX(mouseEvent.getX() - x);
//        stage.setY(mouseEvent.getY() - y);
    }
}
