package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.user.MiniUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SendTweetController implements HasStage
{

    private static final String redTextStyle = "-fx-border-color: linear-gradient( to right top,#ff5757, #ff7429); " +
            "-fx-border-width: 0px 0px 2px 0px; " +
            "-fx-background-color: linear-gradient( to right top,#ff0404, #f26f47);";
    private static final String greenTextStyle = "-fx-border-color: linear-gradient( to right top,#facd68, #fc76b3); " +
            "-fx-border-width: 0px 0px 2px 0px; " +
            "-fx-background-color: linear-gradient( to right top,#5effb1, #47d2f2);";

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
    private Stage stage;

    public void initialize()
    {
        userNameLabel.setText(Data.getInstance().getUser().getUserName());
    }

    @FXML
    public void homeButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "timeline.fxml");
    }

    @FXML
    public void searchButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "search.fxml");
    }

    @FXML
    public void profileButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "profile.fxml");
    }

    @FXML
    public void exitButtonOnAction(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    @FXML
    public void searchImagePathButtonOnAction(ActionEvent actionEvent)
    {
        Image image = new Image(imagePathTextFiled.getText());
        inputImageView.setImage(image);
    }

    @FXML
    public void sendTweetButtonOnAction(ActionEvent actionEvent)
    {
        try
        {
            String text = inputTextArea.getText();
            String imagePath = imagePathTextFiled.getText();
            if(imagePath.equals(""))
                imagePath = null;
            ControllerCommands.sendTweet(text, imagePath);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Hooray!");
            alert.setContentText("Your tweet was sent successfully!");
            alert.show();
        } catch (TextTooLongException e)
        {
            inputTextArea.setStyle(redTextStyle);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Text too long!");
            alert.show();
        } catch (ServerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Some server problem happened!");
            alert.show();
        }
        catch (UnknownException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Some unknown error happened");
            alert.show();
        } catch (ImageSizeException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The image size not matching!");
            alert.show();
        } catch (FileSizeException e)
        {
            throw new RuntimeException(e);
        } catch (FileNotExistException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file path does not exist!");
            alert.show();
        } catch (FileNotImageException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file was not a jpg image!");
            alert.show();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Some unexpected error happened!");
            alert.show();
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
