package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SendQuoteController
{
    @FXML
    public TextField sendQuoteImagePathTextFiled;
    @FXML
    public Button sendQuoteSearchImagePathButton;
    @FXML
    public ImageView sendQuoteImageView;
    @FXML
    public Button sendQuoteSendQuoteButton;
    @FXML
    public TextArea sendQuoteTextArea;
    @FXML
    public Button sendQuoteBackButton;

    @FXML
    public void sendQuoteSearchImagePathButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : set image
    }

    @FXML
    public void sendQuoteSendQuoteButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : send quote and close the pop_up
    }

    @FXML
    public void sendQuoteBackButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : close the pop_up
    }
}
