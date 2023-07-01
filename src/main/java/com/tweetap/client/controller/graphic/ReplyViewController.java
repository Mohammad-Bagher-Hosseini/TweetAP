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
    public Label replierLabel;
    @FXML
    public Label repliedLabel;
    @FXML
    public TextArea textArea;
    @FXML
    public Button likeButton;
    @FXML
    public FontAwesomeIconView likeIcon;
    @FXML
    public Label likeNumberLabel;
    @FXML
    public Button replyButton;
    @FXML
    public Label replyNumberLabel;
    @FXML
    public Button retweetButton;
    @FXML
    public Label retweetNumberLabel;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public void replierLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show the selected user's profile
    }

    @FXML
    public void repliedLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show the selected user's profile
    }

    @FXML
    public void likeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : Like if likable
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
}
