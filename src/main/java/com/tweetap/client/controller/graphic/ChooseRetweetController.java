package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseRetweetController implements HasStage
{
    @FXML
    public Button retweetButton;
    @FXML
    public Button quoteButton;
    private Stage stage;
    private Long tweetId;

    @FXML
    public void retweetButtonOnAction(ActionEvent actionEvent)
    {
        try
        {
            ControllerCommands.sendRetweet(Long.toString(tweetId));
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while retweeting");
            alert.show();
        }
    }

    @FXML
    public void quoteButtonOnAction(ActionEvent actionEvent)
    {
        SendQuoteController sendQuoteController = MainClient.loadPopup(stage, "sendquote_pop_up.fxml", (controller) -> controller.setTweetId(tweetId));
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
