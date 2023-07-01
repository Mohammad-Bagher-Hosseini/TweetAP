package com.tweetap.client.controller.graphic;

import com.tweetap.entities.user.MiniUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MiniProfileController implements HasStage
{
    @FXML
    public Label userNameLabel;
    private Stage stage;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show the selected user profile, and check the block and follow relation and initialize the followrelation and blockrelation buttons
    }

    public void showMiniUser(MiniUser miniUser)
    {
        userNameLabel.setText(miniUser.getName() + " " + miniUser.getFamily() + "@" + miniUser.getUserName());
        // TODO: avatar
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
