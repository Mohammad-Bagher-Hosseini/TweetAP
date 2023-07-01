package com.tweetap.client.controller.graphic;

import com.tweetap.entities.user.MiniUser;
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

public class MiniProfileController implements HasStage
{
    @FXML
    public Label userNameLabel;
    @FXML
    public ImageView avatarImageView;
    private Stage stage;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show the selected user profile, and check the block and follow relation and initialize the followrelation and blockrelation buttons
    }

    public void showMiniUser(MiniUser miniUser)
    {
        userNameLabel.setText(miniUser.getName() + " " + miniUser.getFamily() + "@" + miniUser.getUserName());
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(miniUser.getAvatar().getImage(), "jpg", byteImage);
        } catch (IOException e)
        {
            System.out.println("MiniProfile -> showMiniUser -> writeImage in byteArray error : can't work with userAvatar!");
        }
        avatarImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));
        // TODO: avatar
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
