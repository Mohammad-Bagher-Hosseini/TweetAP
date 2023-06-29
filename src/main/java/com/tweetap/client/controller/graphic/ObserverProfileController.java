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
    public Button observerProfileFollowRelationButton;
    @FXML
    public Label observerProfileNameTextField;
    @FXML
    public ImageView observerProfileHeaderImageView;
    @FXML
    public Label observerProfileUserNameTextField;
    @FXML
    public Label observerProfileSignInDateTextField;
    @FXML
    public Button observerProfileFollowingsButton;
    @FXML
    public Label observerProfileFollowingsNumberLabel;
    @FXML
    public Button observerProfileFollowersButton;
    @FXML
    public Label observerProfileFollowersNumberLabel;
    @FXML
    public TextArea observerProfileBioTextArea;
    @FXML
    public Button observerProfileBlockRelationButton;
    @FXML
    public ImageView observerProfileAvatarImageView;

    @FXML
    public void observerProfileFollowRelationButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : follow if not follow unfollow if is follow
    }

    @FXML
    public void observerProfileFollowingsButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show the user's followings in the showfollows scene
    }

    @FXML
    public void observerProfileFollowersButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : show the user's followers in the showfollows scene
    }

    @FXML
    public void observerProfileBlockRelationButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : block if not block unblock if is block
    }
}
