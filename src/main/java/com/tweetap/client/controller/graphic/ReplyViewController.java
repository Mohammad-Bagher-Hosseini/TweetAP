package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.entities.tweet.Reply;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        try
        {
            if(reply.getTweet().getOwner().getAvatar() != null)
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
