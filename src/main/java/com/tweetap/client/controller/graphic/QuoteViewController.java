package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
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
    public Button likeButton;
    @FXML
    public Button replyButton;
    @FXML
    public FontAwesomeIconView likeIcon;
    @FXML
    public Label likeNumberLabel;
    @FXML
    public Label replyNumberLabel;
    @FXML
    public Label retweetNumberLabel;
    @FXML
    public VBox tweetVBox;
    @FXML
    public Button retweetButton;
    private Stage stage;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        // TODO : show selected user's profile
    }

    @FXML
    public void likeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : like quote
    }

    @FXML
    public void replyButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendreply scene
    }

    @FXML
    public void retweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendretweet scene
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
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("tweetview.fxml"));
            Parent tweetContainer = fxmlLoader.load();
            tweetVBox.getChildren().add(tweetContainer);

            TweetViewController tweetViewController = fxmlLoader.getController();
            tweetViewController.showTweet(tweet);
        } catch (IOException e)
        {
            // TODO
        }
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
