package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.client.view.ProgramState;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.ServerException;
import com.tweetap.entities.exception.io.server.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInPageController implements HasStage
{
    private static final String redTextStyle = "-fx-border-color: linear-gradient( to right top,#ff5757, #ff7429); " +
            "-fx-border-width: 0px 0px 2px 0px; " +
            "-fx-background-color: linear-gradient( to right top,#ff0404, #f26f47);";
    private static final String greenTextStyle = "-fx-border-color: linear-gradient( to right top,#facd68, #fc76b3); " +
            "-fx-border-width: 0px 0px 2px 0px; " +
            "-fx-background-color: linear-gradient( to right top,#5effb1, #47d2f2);";

    @FXML
    public TextField userNameTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Button signInButton;
    @FXML
    public Hyperlink signUpHyperLink;
    @FXML
    public Label userNameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public Label errorLabel;

    private Stage stage;

    public void initialize()
    {
    }

    private void refreshControls()
    {
        userNameTextField.setStyle(greenTextStyle);
        passwordTextField.setStyle(greenTextStyle);
        userNameLabel.setText("");
        passwordLabel.setText("");
        errorLabel.setText("");
    }

    @FXML
    public void userNameTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
            passwordTextField.requestFocus();
    }

    @FXML
    public void passwordTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
            signIn();
    }

    @FXML
    public void signInButtonOnAction(ActionEvent actionEvent)
    {
        signIn();
    }

    private void signIn()
    {
        refreshControls();
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();

        if (username.equals(""))
        {
            userNameTextField.setStyle(redTextStyle);
            return;
        }
        if (password.equals(""))
        {
            passwordTextField.setStyle(redTextStyle);
            return;
        }

        try
        {
            ControllerCommands.signIn(username, password);
            goToTimeLine();
        } catch (UserNotFoundException e)
        {
            userNameTextField.setStyle(redTextStyle);
            passwordTextField.setStyle(redTextStyle);
            userNameLabel.setText("username or password not found!");
            passwordTextField.setText("");
        } catch (ServerException e)
        {
            errorLabel.setText("Something went wrong from the server side!");
        } catch (UnknownException e)
        {
            errorLabel.setText("Some unknown error happened!");
        } catch (TwitException e)
        {
            errorLabel.setText("Some unexpected error happened!");
        }
    }

    @FXML
    public void signUpHyperLinkOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "signuppage.fxml");
    }

    private void goToTimeLine()
    {
        MainClient.loadPage(stage, "timeline.fxml");
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
