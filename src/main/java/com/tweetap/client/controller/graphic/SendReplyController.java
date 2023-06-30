package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class SendReplyController
{
    @FXML
    public TextArea sendReplyTextArea;
    @FXML
    public Button sendReplySendReplyButton;
    @FXML
    public Button sendReplyBackButton;


    @FXML
    public void sendReplySendReplyButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : send reply and close the pop_up
    }

    @FXML
    public void sendReplyBackButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : close the pop_up
    }
}
