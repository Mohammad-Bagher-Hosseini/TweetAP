package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.tweet.Quote;
import com.tweetap.entities.tweet.Tweet;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class QuoteViewController implements HasStage
{
    @FXML
    public Label userNameLabel;
    @FXML
    public Label textLabel;
    @FXML
    public ImageView imageView;
    @FXML
    public Label likeNumberLabel;
    @FXML
    public Label replyNumberLabel;
    @FXML
    public Label retweetNumberLabel;
    @FXML
    public VBox tweetVBox;
    private Stage stage;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        ObserverProfileController observerProfileController = MainClient.loadPopup(stage, "observerprofile_pop_up.fxml", (controller) -> controller.setUsername(userNameLabel.getText()));
    }

    public void showQuote(Quote quote)
    {
        userNameLabel.setText(quote.getUserName());
        textLabel.setText(quote.getTextContent().toString());
        // TODO: set imageview
        likeNumberLabel.setText(Integer.toString(quote.getTweet().getLikeCount()));
        replyNumberLabel.setText(Integer.toString(quote.getTweet().getReplies().size()));
        retweetNumberLabel.setText(Integer.toString(quote.getTweet().getRetweetCount()));

        Tweet tweet = quote.getTweet();
        TweetViewController tweetViewController = MainClient.loadPage(tweetVBox, stage, "tweetview.fxml");
        tweetViewController.showTweet(tweet);
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
