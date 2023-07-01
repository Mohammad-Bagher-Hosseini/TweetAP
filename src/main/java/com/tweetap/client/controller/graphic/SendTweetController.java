package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SendTweetController
{

    @FXML
    public Button homeButton;
    @FXML
    public Button searchButton;
    @FXML
    public Button profileButton;
    @FXML
    public Button exitButton;
    @FXML
    public Label userNameLabel;
    @FXML
    public TextArea inputTextArea;
    @FXML
    public ImageView inputImageView;
    @FXML
    public TextField imagePathTextFiled;
    @FXML
    public Button searchImagePathButton;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public Button sendTweetButton;

    @FXML
    public void homeButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void searchButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void profileButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : switch to suitable scene
    }

    @FXML
    public void exitButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : Exit
    }

    @FXML
    public void searchImagePathButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : process input variables
    }

    @FXML
    public void sendTweetButtonOnAction(ActionEvent actionEvent)
    {
        // TODO : process input variables
    }
}
