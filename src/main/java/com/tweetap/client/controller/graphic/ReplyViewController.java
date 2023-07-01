package com.tweetap.client.controller.graphic;

import com.tweetap.entities.tweet.Reply;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReplyViewController implements HasStage
{
    @FXML
    public Label replierLabel;
    @FXML
    public Label repliedLabel;
    @FXML
    public Label textLabel;
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
    private Stage stage;

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

    public void showReply(Reply reply)
    {
        replierLabel.setText(reply.getUserName());
        repliedLabel.setText(reply.getTweet().getUserName());
        textLabel.setText(reply.getTextContent().toString());
        likeNumberLabel.setText(Integer.toString(reply.getTweet().getLikeCount()));
        replyNumberLabel.setText(Integer.toString(reply.getTweet().getReplies().size()));
        retweetNumberLabel.setText(Integer.toString(reply.getTweet().getRetweetCount()));
        // TODO: set image view
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
