package com.tweetap.client.controller;

import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.ServerException;
import com.tweetap.entities.exception.io.server.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.text.Style;
import java.io.IOException;

public class SignInPageController
{
    private static final String redTextStyle = "-fx-border-color: linear-gradient( to right top,#ff5757, #ff7429); " +
            "-fx-border-width: 0px 0px 2px 0px; " +
            "-fx-background-color: linear-gradient( to right top,#ff0404, #f26f47);";

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

    private Stage stage;

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

        if (username.equals(""))
        {
            signInUserNameTextField.setStyle(redTextStyle);
            return;
        }
        if (password.equals(""))
        {
            signInPasswordTextField.setStyle(redTextStyle);
            return;
        }

        try
        {
            ControllerCommands.signIn(username, password);
            goToTimeLine();
        } catch (UserNotFoundException e)
        {
            // TODO: warn the user that username or password was incorrect
            signInUserNameTextField.setStyle(redTextStyle);
            signInPasswordTextField.setStyle(redTextStyle);
            signInUserNameLabel.setText("username or password not found!");
        } catch (ServerException e)
        {
            // TODO: warn that something happened with the server side
        } catch (UnknownException e)
        {
            // TODO: warn that some unknown error happened
        } catch (TwitException e)
        {
            // TODO: warn that some unexpected error happened
        }
    }

    @FXML
    public void signInSignUpHyperLinkOnAction(ActionEvent actionEvent)
    {
        // TODO : switch to signUpScene
    }

    private void goToTimeLine()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("timeline.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e)
        {
            // TODO
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
