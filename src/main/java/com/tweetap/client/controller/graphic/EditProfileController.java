package com.tweetap.client.controller.graphic;

import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.user.Country;
import com.tweetap.entities.user.User;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditProfileController implements HasStage
{
    @FXML
    public Button backButton;
    @FXML
    public TextField headerImagePathTextFiled;
    @FXML
    public Button headerImagePathSearchButton;
    @FXML
    public TextField avatarImagePathTextFiled;
    @FXML
    public Button avatarImagePathSearchButton;
    @FXML
    public TextArea bioTextArea;
    @FXML
    public TextField nameTextField;
    @FXML
    public ComboBox<String> countryComboBox;
    @FXML
    public DatePicker birthDateDatePicker;
    @FXML
    public Button saveButton;
    private Stage stage;
    private User user;

    private void onShown()
    {
        user = Data.getInstance().getUser();
        bioTextArea.setText(user.getBio().getText());
        nameTextField.setText(user.getName());
        // TODO: Family
        countryComboBox.setItems(FXCollections.observableArrayList(Country.getInstance().getCountries()));
        countryComboBox.setValue(user.getCountry());
        birthDateDatePicker.setValue(LocalDate.of(user.getBirthDate().getYear(), user.getBirthDate().getMonthValue(), user.getBirthDate().getDayOfMonth()));
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        stage.close();
    }

    @FXML
    public void headerImagePathSearchButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : search and show image or error
    }

    @FXML
    public void avatarImagePathSearchButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : search and show image or error
    }

    @FXML
    public void saveButtonOnAction(ActionEvent actionEvent)
    {
        if(!headerImagePathSearchButton.getText().equals(""))
            changeHeader();
        if(!avatarImagePathSearchButton.getText().equals(""))
            changeAvatar();
        if(!bioTextArea.getText().equals(user.getBio().getText()))
            changeBio();
        if(!nameTextField.getText().equals(user.getName()))
            changeName();
        // TODO if...
        changeFamily();
        if(!countryComboBox.getValue().equals(user.getCountry()))
            changeCountry();
        if(!birthDateDatePicker.getValue().equals(LocalDate.of(user.getBirthDate().getYear(), user.getBirthDate().getMonthValue(), user.getBirthDate().getDayOfMonth())))
            changeBirthDate();
    }

    private void changeHeader()
    {
        try
        {
            ControllerCommands.setHeader(headerImagePathTextFiled.getText());
            stage.close();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while changing header!");
            alert.show();
        } catch (ImageSizeException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The image size is not valid!");
            alert.show();
        } catch (FileSizeException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file size is too big!");
            alert.show();
        } catch (FileNotExistException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file does not exist!");
            alert.show();
        } catch (FileNotImageException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file is not in jpg format!");
            alert.show();
        }
    }

    private void changeAvatar()
    {
        try
        {
            ControllerCommands.setAvatar(avatarImagePathTextFiled.getText());
            stage.close();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while changing avatar!");
            alert.show();
        } catch (ImageSizeException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The image size is not valid!");
            alert.show();
        } catch (FileSizeException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file size is too big!");
            alert.show();
        } catch (FileNotExistException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file does not exist!");
            alert.show();
        } catch (FileNotImageException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file is not in jpg format!");
            alert.show();
        }
    }

    private void changeBio()
    {
        try
        {
            ControllerCommands.changeBio(bioTextArea.getText());
        } catch (TextTooLongException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The text is too long!");
            alert.show();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while changing your bio!");
            alert.show();
        }
    }

    private void changeName()
    {
        try
        {
            ControllerCommands.changeName(nameTextField.getText());
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while changing your name!");
            alert.show();
        }
    }

    private void changeFamily()
    {
        try
        {
            ControllerCommands.changeFamily(nameTextField.getText()); // TODO: change name to family
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while changing your family-name!");
            alert.show();
        }
    }

    private void changeCountry()
    {
        try
        {
            ControllerCommands.changeCountry(countryComboBox.getValue());
        } catch (CountryException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The country is not valid!");
            alert.show();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while changing your name!");
            alert.show();
        }
    }

    private void changeBirthDate()
    {
        try
        {
            ControllerCommands.changeBirthDate(birthDateDatePicker.getValue().getYear(), birthDateDatePicker.getValue().getMonthValue(), birthDateDatePicker.getValue().getDayOfMonth()); // TODO: change name to family
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while changing your birth-date!");
            alert.show();
        }
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
        onShown();
    }
}
