package com.tweetap.client.controller.graphic;

import com.tweetap.entities.tweet.Quote;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class QuoteViewController
{
    @FXML
    public Label userNameLabel;
    @FXML
    public TextArea textArea;
    @FXML
    public ImageView imageView;
    @FXML
    public Button likeButton;
    @FXML
    public Button replyButton;
    @FXML
    public Button quoteViewRetweetButton;
    @FXML
    public FontAwesomeIconView likeIcon;
    @FXML
    public Label likeNumberLabel;
    @FXML
    public Label replyNumberLabel;
    @FXML
    public Label retweetNumberLabel;
    public VBox tweetVBox;

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        // TODO : show selected user's profile
    }

    @FXML
    public void likeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : like quote
    }

    @FXML
    public void replyButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendreply scene
    }

    @FXML
    public void retweetButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : show sendretweet scene
    }

    public void showQuote(Quote quote)
    {
        userNameLabel.setText(quote.getUserName());

    }
}
