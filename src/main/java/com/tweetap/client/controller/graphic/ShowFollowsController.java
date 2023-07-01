package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ShowFollowsController
{
    @FXML
    public Button followersButton;
    @FXML
    public Button followingsButton;
    @FXML
    public Button backButton;

    @FXML
    public void followersButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show user's followers miniProfiles
    }

    @FXML
    public void followingsButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show user's followings miniProfiles
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to user's profile scene
    }
}
