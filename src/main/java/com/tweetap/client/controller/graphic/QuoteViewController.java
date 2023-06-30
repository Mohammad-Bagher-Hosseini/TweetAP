package com.tweetap.client.controller.graphic;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    public Button quoteViewLikeButton;
    @FXML
    public Button quoteViewReplyButton;
    @FXML
    public Button quoteViewRetweetButton;
    @FXML
    public FontAwesomeIconView quoteViewLikeIcon;
    @FXML
    public Label quoteViewLikeNumberLabel;
    @FXML
    public Label quoteViewReplyNumberLabel;
    @FXML
    public Label quoteViewRetweetNumberLabel;

    @FXML
    public void quoteViewUserNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        // TODO : show selected user's profile
    }

    @FXML
    public void quoteViewLikeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : like quote
    }

    @FXML
    public void quoteViewReplyButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendreply scene
    }

    @FXML
    public void quoteViewRetweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendretweet scene
    }
}
