package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditProfileController
{
    @FXML
    public Button editProfileBackButton;
    @FXML
    public TextField editProfileHeaderImagePathTextFiled;
    @FXML
    public Button editProfileHeaderImagePathSearchButton;
    @FXML
    public TextField editProfileAvatarImagePathTextFiled;
    @FXML
    public Button editProfileAvatarImagePathSearchButton;
    @FXML
    public TextArea editProfileBioTextArea;
    @FXML
    public TextField editProfileNameTextField;
    @FXML
    public ComboBox editProfileCountryComboBox;
    @FXML
    public DatePicker editProfileBirthDateDatePicker;
    @FXML
    public Button editProfileSaveButton;

    @FXML
    public void editProfileBackButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : close pop_up
    }

    @FXML
    public void editProfileHeaderImagePathSearchButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : search and show image or error
    }

    @FXML
    public void editProfileAvatarImagePathSearchButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : search and show image or error
    }

    @FXML
    public void editProfileSaveButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : process inputs and save
    }
}
