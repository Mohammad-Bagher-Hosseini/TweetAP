package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SendReplyController implements HasStage
{
    @FXML
    public TextArea textArea;
    @FXML
    public Button sendReplyButton;
    @FXML
    public Button backButton;
    private Stage stage;


    @FXML
    public void sendReplyButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : send reply and close the pop_up
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
