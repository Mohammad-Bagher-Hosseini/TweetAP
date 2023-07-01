package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SignUpPageController
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
    public TextField nameTextField;
    @FXML
    public TextField familyTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField phoneTextField;
    @FXML
    public ComboBox<String> countryComboText;
    @FXML
    public DatePicker birthDayDatePicker;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public Button signUpButton;
    @FXML
    public Button backButton;
    @FXML
    public Label userNameLabel;
    @FXML
    public Label nameLabel;
    @FXML
    public Label familyLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label phoneLabel;
    @FXML
    public Label countryLabel;
    @FXML
    public Label birthDateLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public Label confirmPasswordLabel;
    @FXML
    public Label signUpLabel;

    private void refreshControls()
    {
        userNameTextField.setStyle(greenTextStyle);
        nameTextField.setStyle(greenTextStyle);
        familyTextField.setStyle(greenTextStyle);
        emailTextField.setStyle(greenTextStyle);
        phoneTextField.setStyle(greenTextStyle);
        countryComboText.setStyle(greenTextStyle);
        birthDayDatePicker.setStyle(greenTextStyle);
        passwordField.setStyle(greenTextStyle);
        confirmPasswordField.setStyle(greenTextStyle);
    }

    @FXML
    public void userNameTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (userNameTextField.getText().equals(""))
                userNameTextField.setStyle(redTextStyle);
            else
                nameTextField.requestFocus();
        }
    }

    @FXML
    public void nameTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (nameTextField.getText().equals(""))
                nameTextField.setStyle(redTextStyle);
            else
                nameTextField.requestFocus();
        }
    }

    public void familyTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (familyTextField.getText().equals(""))
                familyTextField.setStyle(redTextStyle);
            else
                emailTextField.requestFocus();
        }
    }

    @FXML
    public void emailTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (emailTextField.getText().equals(""))
                emailTextField.setStyle(redTextStyle);
            else
                phoneTextField.requestFocus();
        }
    }

    @FXML
    public void phoneTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (phoneTextField.getText().equals(""))
                phoneTextField.setStyle(redTextStyle);
            else
                countryComboText.requestFocus();
        }
    }

    @FXML
    public void countryComboTextOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            birthDayDatePicker.requestFocus();
        }
    }

    @FXML
    public void birthDatePickerOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            passwordField.requestFocus();
        }
    }

    @FXML
    public void passwordFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (passwordField.getText().equals(""))
                passwordField.setStyle(redTextStyle);
            else
                confirmPasswordField.requestFocus();
        }
    }

    @FXML
    public void confirmPasswordFieldOnKeyPressed(KeyEvent keyEvent)
    {
        // TODO : handle when user pressed 'Enter' switch to next field and save this text to suitable variable
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (confirmPasswordField.getText().equals(""))
                confirmPasswordField.setStyle(redTextStyle);
            else
                signUp();
        }
    }

    @FXML
    public void signUpButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : process input variables and show result and switch in SignInScene
        signUp();
    }

    public void signUp()
    {

    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to signIn scene
    }
}
