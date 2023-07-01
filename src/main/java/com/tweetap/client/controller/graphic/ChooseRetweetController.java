package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        //TODO : retweet the selected tweet
    }

    @FXML
    public void quoteButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : close this pop_up and open the sendquote_pop_up
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
