package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ObserverProfileController
{
    @FXML
    public Button followRelationButton;
    @FXML
    public Label nameTextField;
    @FXML
    public ImageView headerImageView;
    @FXML
    public Label userNameTextField;
    @FXML
    public Label signInDateTextField;
    @FXML
    public Button followingsButton;
    @FXML
    public Label followingsNumberLabel;
    @FXML
    public Button followersButton;
    @FXML
    public Label followersNumberLabel;
    @FXML
    public TextArea bioTextArea;
    @FXML
    public Button blockRelationButton;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public Button backButton;

    @FXML
    public void followRelationButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : follow if not follow unfollow if is follow
    }

    @FXML
    public void followingsButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show the user's followings in the showfollows scene
    }

    @FXML
    public void followersButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : show the user's followers in the showfollows scene
    }

    @FXML
    public void blockRelationButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : block if not block unblock if is block
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : close the pop_up
    }
}
