package com.tweetap.client.controller.graphic;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ReplyViewController
{
    @FXML
    public Label replyViewReplierLabel;
    @FXML
    public Label replyViewRepliedLabel;
    @FXML
    public TextArea replyViewTextArea;
    @FXML
    public Button replyViewLikeButton;
    @FXML
    public FontAwesomeIconView replyViewLikeIcon;
    @FXML
    public Label replyViewLikeNumberLabel;
    @FXML
    public Button replyViewReplyButton;
    @FXML
    public Label replyViewReplyNumberLabel;
    @FXML
    public Button replyViewRetweetButton;
    @FXML
    public Label replyViewRetweetNumberLabel;
    @FXML
    public ImageView replyViewAvatarImageView;
    @FXML
    public void replyViewReplierLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show the selected user's profile
    }

    @FXML
    public void replyViewRepliedLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show the selected user's profile
    }

    @FXML
    public void replyViewLikeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : Like if likable
    }

    @FXML
    public void replyViewReplyButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show reply scene
    }

    @FXML
    public void replyViewRetweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show retweet scene
    }
}
