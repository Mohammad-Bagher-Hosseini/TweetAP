package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.tweet.Reply;
import com.tweetap.entities.tweet.Tweet;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TweetViewController implements HasStage
{

    @FXML
    public Label userNameLabel;
    @FXML
    public Label tweetTextLabel;
    @FXML
    public ImageView tweetImageView;
    @FXML
    public Button likeButton;
    @FXML
    public Button replyButton;
    @FXML
    public Button retweetButton;
    @FXML
    public FontAwesomeIconView likeIcon;
    @FXML
    public Label likeNumberLabel;
    @FXML
    public Label replyNumberLabel;
    @FXML
    public Label retweetNumberLabel;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public VBox repliesVbox;

    private Stage stage;
    private Long tweetId;

    public void initialize()
    {

    }

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        MainClient.loadPopup(stage, "observerprofile_pop_up.fxml", (controller) -> {});
    }

    @FXML
    public void likeButtonOnAction(ActionEvent actionEvent)
    {
        if(likeIcon.getGlyphName().equals("THUMBS_UP"))
        {
            if(like())
                likeIcon.setGlyphName("THUMBS_DOWN");
        }
        else
        {
            if(dislike())
                likeIcon.setGlyphName("THUMBS_UP");
        }
    }

    @FXML
    public void replyButtonOnAction(ActionEvent actionEvent)
    {
        SendReplyController sendReplyController = MainClient.loadPopup(stage, "sendreply_pop_up.fxml", (controller) -> controller.setTweetId(tweetId));
    }

    @FXML
    public void retweetButtonOnAction(ActionEvent actionEvent)
    {
        ChooseRetweetController chooseRetweetController = MainClient.loadPopup(stage, "chooseretweet_pop_up.fxml", (controller) -> controller.setTweetId(tweetId));
    }

    public void showTweet(Tweet tweet)
    {
        userNameLabel.setText(tweet.getUserName());
        tweetTextLabel.setText(tweet.getTextContent().toString());
        // TODO: tweetImageView.setImage((Image) tweet.getImageContent().getImage());
        likeNumberLabel.setText(Integer.toString(tweet.getLikeCount()));
        replyNumberLabel.setText(Integer.toString(tweet.getReplies().size()));
        retweetNumberLabel.setText(Integer.toString(tweet.getRetweetCount()));
        // TODO: set avatar imageview
        tweetId = tweet.getId();

        for(Reply reply : tweet.getReplies())
        {
            ReplyViewController replyViewController = MainClient.loadPage(repliesVbox, "replyview.fxml");
            replyViewController.showReply(reply);
        }
    }

    private boolean like()
    {
        try
        {
            ControllerCommands.likeTweet(Long.toString(tweetId));
            return true;
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while liking!");
            alert.show();
            return false;
        }
    }

    private boolean dislike()
    {
        try
        {
            ControllerCommands.dislikeTweet(Long.toString(tweetId));
            return true;
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while disliking!");
            alert.show();
            return false;
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
