package com.tweetap.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class SignInPageController
{

    @FXML
    public TextField signInUserNameTextField;
    @FXML
    public PasswordField signInPasswordTextField;
    @FXML
    public Button signInSignInButton;
    @FXML
    public Hyperlink signInSignUpHyperLink;

    @FXML
    public void signInUserNameTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
    }

    @FXML
    public void signInPasswordTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
    }

    @FXML
    public void signInSignInButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : process input variables and show result and switch in timelineScene
    }

    public void signInSignUpHyperLinkOnAction(ActionEvent actionEvent)
    {
        // TODO : switch to signUpScene
    }
}
