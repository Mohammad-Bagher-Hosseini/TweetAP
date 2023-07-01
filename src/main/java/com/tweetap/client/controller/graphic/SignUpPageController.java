package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.EmailOrPhoneRequiredException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.PasswordConfirmException;
import com.tweetap.entities.exception.user.password.PasswordFormatException;
import com.tweetap.entities.exception.user.password.PasswordHashException;
import com.tweetap.entities.user.Country;
import com.tweetap.entities.user.Verification;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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
    public ComboBox<String> countryComboBox;
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
    public Label errorLabel;

    private Stage stage;

    public void initialize()
    {
        countryComboBox.setItems(FXCollections.observableArrayList(Country.getInstance().getCountries()));
    }

    private void refreshControls()
    {
        userNameTextField.setStyle(greenTextStyle);
        nameTextField.setStyle(greenTextStyle);
        familyTextField.setStyle(greenTextStyle);
        emailTextField.setStyle(greenTextStyle);
        phoneTextField.setStyle(greenTextStyle);
        countryComboBox.setStyle(greenTextStyle);
        birthDayDatePicker.setStyle(greenTextStyle);
        passwordField.setStyle(greenTextStyle);
        confirmPasswordField.setStyle(greenTextStyle);

        userNameLabel.setText("");
        nameLabel.setText("");
        familyLabel.setText("");
        emailLabel.setText("");
        phoneLabel.setText("");
        countryLabel.setText("");
        birthDateLabel.setText("");
        passwordLabel.setText("");
        confirmPasswordLabel.setText("");
        errorLabel.setText("");
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
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (nameTextField.getText().equals(""))
                nameTextField.setStyle(redTextStyle);
            else
                familyTextField.requestFocus();
        }
    }

    public void familyTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
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
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if(!emailTextField.getText().equals("") && !Verification.isEmailValid(emailTextField.getText()))
            {
                emailTextField.setStyle(redTextStyle);
                emailLabel.setText("Email format not accepted");
            }
            else
                phoneTextField.requestFocus();
        }
    }

    @FXML
    public void phoneTextFieldOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            countryComboBox.requestFocus();
        }
    }

    @FXML
    public void countryComboBoxOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            birthDayDatePicker.requestFocus();
        }
    }

    @FXML
    public void birthDatePickerOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            passwordField.requestFocus();
        }
    }

    @FXML
    public void passwordFieldOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (passwordField.getText().equals(""))
                passwordField.setStyle(redTextStyle);
            else if(Verification.isPasswordValid(passwordField.getText()))
            {
                passwordField.setStyle(redTextStyle);
                passwordLabel.setText("Password format not accepted");
            }
            else
                confirmPasswordField.requestFocus();
        }
    }

    @FXML
    public void confirmPasswordFieldOnKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            refreshControls();
            if (!confirmPasswordField.getText().equals(passwordField.getText()))
                confirmPasswordField.setStyle(redTextStyle);
            else
                signUp();
        }
    }

    @FXML
    public void signUpButtonOnAction(ActionEvent actionEvent)
    {
        signUp();
    }

    public void signUp()
    {
        refreshControls();

        String username = userNameTextField.getText();
        String name = nameTextField.getText();
        String family = familyTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String country = countryComboBox.getValue();
        LocalDate birthdate = birthDayDatePicker.getValue();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        try
        {
            ControllerCommands.signUp(username, name, family, email, phone, password, confirmPassword, country,
                    birthdate.getYear(), birthdate.getMonthValue(), birthdate.getDayOfMonth());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Hooray!!!");
            alert.setContentText("You signed up successfully");
            alert.show();
        } catch (PasswordConfirmException e)
        {
            passwordField.setStyle(redTextStyle);
            confirmPasswordField.setText("Confirm-password should be the same as password");
        } catch (EmailFormatException e)
        {
            emailTextField.setStyle(redTextStyle);
            emailLabel.setText("Email format not accepted");
        } catch (PasswordFormatException e)
        {
            passwordField.setStyle(redTextStyle);
            passwordLabel.setText("Password format not accepted");
        } catch (EmailOrPhoneRequiredException e)
        {
            emailTextField.setStyle(redTextStyle);
            phoneTextField.setStyle(redTextStyle);
            phoneLabel.setText("Email or phone-number required");
        } catch (DuplicateUserNameException e)
        {
            userNameTextField.setStyle(redTextStyle);
            userNameLabel.setText("The username is already taken");
        } catch (PasswordHashException e)
        {
            errorLabel.setText("Something went wrong with password hashing");
        } catch (CountryException e)
        {
            countryComboBox.setStyle(redTextStyle);
            countryLabel.setText("Country not accepted");
        } catch (UnknownException e)
        {
            errorLabel.setText("Some unknown error happened!");
        } catch (ServerException e)
        {
            errorLabel.setText("Something went wrong from the server side!");
        } catch (TwitException e)
        {
            errorLabel.setText("Some unexpected error happened!");
        }
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource("signinpage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            SignInPageController signInPageController = fxmlLoader.getController();
            signInPageController.setStage(stage);

            stage.show();
        } catch (IOException e)
        {
            errorLabel.setText("Something went wrong while going to signin page!");
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
