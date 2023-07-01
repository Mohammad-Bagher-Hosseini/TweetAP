package com.tweetap.client.controller.graphic;

import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private Long tweetId;


    @FXML
    public void sendReplyButtonOnAction(ActionEvent actionEvent)
    {
        try
        {
            ControllerCommands.sendReply(Long.toString(tweetId), textArea.getText());
            stage.close();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while sending your reply");
            alert.show();
        }
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        stage.close();
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setTweetId(Long tweetId)
    {
        this.tweetId = tweetId;
    }
}
