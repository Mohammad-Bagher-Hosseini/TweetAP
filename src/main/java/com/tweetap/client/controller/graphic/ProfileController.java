package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ProfileController
{

    @FXML
    public Button homeButton;
    @FXML
    public Button searchButton;
    @FXML
    public Button tweetButton;
    @FXML
    public Button exitButton;
    @FXML
    public ImageView headerImageView;
    @FXML
    public Button editProfileButton;
    @FXML
    public Label nameTextField;
    @FXML
    public Label userNameTextField;
    @FXML
    public Label signInDateTextField;
    @FXML
    public Button followingsButton;
    @FXML
    public Button followersButton;
    @FXML
    public TextArea bioTextArea;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public Button signOutButton;

    @FXML
    public void homeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void searchButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void tweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void exitButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : Exit
    }

    @FXML
    public void editProfileButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to editProfile scene
    }

    @FXML
    public void followingsButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void followersButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void signOutButton(ActionEvent actionEvent)
    {
        // TODO : sign out and switch to signIn scene
    }
}
