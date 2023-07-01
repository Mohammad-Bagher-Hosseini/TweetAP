package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.hashtag.HashtagException;
import com.tweetap.entities.exception.io.server.UserNotFoundException;
import com.tweetap.entities.tweet.*;
import com.tweetap.entities.user.MiniUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    public TextField searchTextField;
    @FXML
    public Button searchUserButton;
    @FXML
    public Button searchHashtagButton;
    private Stage stage;
    @FXML
    public VBox findUsersVBox;

    @FXML
    public void homeButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "timeline.fxml");
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
        System.exit(0);
    }

    @FXML
    public void searchHashtagButtonOnAction(ActionEvent actionEvent)
    {
        String hashtags = searchTextField.getText();
        try
        {
            TimeLine timeLine = ControllerCommands.searchForHashtag(hashtags);
            for(BaseTweet baseTweet : timeLine)
            {
                if(baseTweet instanceof Tweet tweet)
                    addTweet(tweet);
                else if(baseTweet instanceof Quote quote)
                    addQuote(quote);
                else if (baseTweet instanceof Retweet retweet)
                    addRetweet(retweet);
            }
        } catch (HashtagException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong hashtag format");
            alert.show();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while showing the hashtags");
            alert.show();
        }
    }

    private void addTweet(Tweet tweet)
    {
        TweetViewController tweetViewController = MainClient.loadPage(findUsersVBox, stage, "tweetview.fxml");
        tweetViewController.showTweet(tweet);
    }

    private void addRetweet(Retweet retweet)
    {
        RetweetViewController retweetViewController = MainClient.loadPage(findUsersVBox, stage, "retweetview.fxml");
        retweetViewController.showRetweet(retweet);
    }

    private void addQuote(Quote quote)
    {
        QuoteViewController quoteViewController = MainClient.loadPage(findUsersVBox, stage, "quoteview.fxml");
        quoteViewController.showQuote(quote);
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    public void searchUserButtonOnAction(ActionEvent actionEvent)
    {
        try
        {
            MiniUser miniUser = ControllerCommands.showUser(searchTextField.getText());
            ObserverProfileController observerProfileController = MainClient.loadPopup(stage, "observerprofile_pop_up.fxml", (controller) -> controller.setUsername(miniUser.getUserName()));
        } catch (UserNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User not found!");
            alert.show();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while loading a user");
            alert.show();
        }
    }
}
