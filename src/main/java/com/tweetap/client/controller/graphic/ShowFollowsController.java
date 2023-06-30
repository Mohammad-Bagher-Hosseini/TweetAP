package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ShowFollowsController
{
    @FXML
    public Button showFollowsFollowersButton;
    @FXML
    public Button showFollowsFollowingsButton;
    @FXML
    public Button showFollowsBackButton;

    @FXML
    public void showFollowsFollowersButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show user's followers miniProfiles
    }

    @FXML
    public void showFollowsFollowingsButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show user's followings miniProfiles
    }

    @FXML
    public void showFollowsBackButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to user's profile scene
    }
}
