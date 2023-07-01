package com.tweetap.client.controller.graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MiniProfileController
{
    @FXML
    public Label userNameLabel;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO : show the selected user profile, and check the block and follow relation and initialize the followrelation and blockrelation buttons
    }
}
