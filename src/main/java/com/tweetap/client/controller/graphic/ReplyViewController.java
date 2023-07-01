package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.tweet.Reply;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        ObserverProfileController observerProfileController = MainClient.loadPopup(stage, "observerprofile_pop_up.fxml", (controller) -> controller.setUsername(replierLabel.getText()));
    }

    @FXML
    public void repliedLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        ObserverProfileController observerProfileController = MainClient.loadPopup(stage, "observerprofile_pop_up.fxml", (controller) -> controller.setUsername(repliedLabel.getText()));
    }

    public void showReply(Reply reply)
    {
        replierLabel.setText("@" + reply.getUserName() + " replied to");
        repliedLabel.setText("@" + reply.getTweet().getUserName());
        textLabel.setText(reply.getTextContent().toString());
        likeNumberLabel.setText(Integer.toString(reply.getTweet().getLikeCount()));
        replyNumberLabel.setText(Integer.toString(reply.getTweet().getReplies().size()));
        retweetNumberLabel.setText(Integer.toString(reply.getTweet().getRetweetCount()));
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(reply.getTweet().getOwner().getAvatar().getImage(), "jpg", byteImage);
        }
        catch (IOException e)
        {
            //TODO : error
        }
        avatarImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
