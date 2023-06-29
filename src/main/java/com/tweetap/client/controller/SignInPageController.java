package com.tweetap.client.controller;

import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.ServerException;
import com.tweetap.entities.exception.io.server.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    public Label signInUserNameLabel;
    @FXML
    public Label signInPasswordLabel;

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
        String username = signInUserNameTextField.getText();
        String password = signInPasswordTextField.getText();
        try
        {
            ControllerCommands.signIn(username, password);
        }
        catch (UserNotFoundException e)
        {
            // TODO: warn the user that username or password was incorrect
        }
        catch (ServerException e)
        {
            // TODO: warn that something happened with the server side
        }
        catch (UnknownException e)
        {
            // TODO: warn that some unknown error happened
        }
        catch (TwitException e)
        {
            // TODO: warn that some unexpected error happened
        }
    }

    private void goToTimeLine()
    {

    }

    public void signInSignUpHyperLinkOnAction(ActionEvent actionEvent)
    {
        // TODO : switch to signUpScene
    }
}
