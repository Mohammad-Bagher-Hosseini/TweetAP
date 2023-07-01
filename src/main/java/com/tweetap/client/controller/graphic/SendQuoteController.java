package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SendQuoteController implements HasStage
{
    @FXML
    public TextField imagePathTextFiled;
    @FXML
    public Button searchImagePathButton;
    @FXML
    public ImageView imageView;
    @FXML
    public Button sendQuoteButton;
    @FXML
    public TextArea textArea;
    @FXML
    public Button backButton;
    private Stage stage;

    @FXML
    public void searchImagePathButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : set image
    }

    @FXML
    public void sendQuoteButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : send quote and close the pop_up
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : close the pop_up
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
