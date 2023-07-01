package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    @FXML
    public TextField familyTextField;
    private Stage stage;

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
        // TODO : process inputs and save
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
