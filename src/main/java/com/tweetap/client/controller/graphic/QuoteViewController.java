package com.tweetap.client.controller.graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class QuoteViewController
{
    @FXML
    public Label quoteViewUserNameLabel;
    @FXML
    public TextArea quoteViewTextArea;
    @FXML
    public ImageView quoteViewImageView;

    @FXML
    public void quoteViewUserNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        // TODO : show selected user's profile
    }
}
