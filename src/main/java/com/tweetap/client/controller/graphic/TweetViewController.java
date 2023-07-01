package com.tweetap.client.controller.graphic;

import com.tweetap.entities.tweet.Tweet;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TweetViewController
{

    @FXML
    public Label userNameLabel;
    @FXML
    public TextArea tweetTextArea;
    @FXML
    public ImageView tweetImageView;
    @FXML
    public Button likeButton;
    @FXML
    public Button replyButton;
    @FXML
    public Button tweetViewRetweetButton;
    @FXML
    public FontAwesomeIconView tweetViewLikeIcon;
    @FXML
    public Label tweetViewLikeNumberLabel;
    @FXML
    public Label tweetViewReplyNumberLabel;
    @FXML
    public Label tweetViewRetweetNumberLabel;
    @FXML
    public ImageView tweetViewAvatarImageView;

    @FXML
    public void tweetViewUserNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show selected user's profile
    }

    @FXML
    public void tweetViewLikeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : Like tweet if is was likable
    }

    @FXML
    public void tweetViewReplyButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendreply scene, this is a pop-up
    }

    @FXML
    public void tweetViewRetweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendretweet scene, this is a pop-up
    }

    public void showTweet(Tweet tweet)
    {
        // TODO
    }
}
