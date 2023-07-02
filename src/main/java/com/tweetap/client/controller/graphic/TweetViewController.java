package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.io.server.DuplicateLikeRequestException;
import com.tweetap.entities.tweet.Reply;
import com.tweetap.entities.tweet.Tweet;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    @FXML
    private FontAwesomeIconView starIconView;

    private Stage stage;
    private Long tweetId;

    public void initialize()
    {

    }

    @FXML
    public void userNameLabelOnMouseClicked(MouseEvent mouseEvent)
    {
        ObserverProfileController observerProfileController = MainClient.loadPopup(stage, "observerprofile_pop_up.fxml", (controller) -> controller.setUsername(userNameLabel.getText()));
    }

    @FXML
    public void likeButtonOnAction(ActionEvent actionEvent)
    {
        if(likeIcon.getGlyphName().equals("THUMBS_UP"))
        {
            if(like())
            {
                likeIcon.setGlyphName("THUMBS_DOWN");
                int likeCount = Integer.parseInt(likeNumberLabel.getText());
                likeCount++;
                likeNumberLabel.setText(Integer.toString(likeCount));
            }
        }
        else
        {
            if(dislike())
            {
                likeIcon.setGlyphName("THUMBS_UP");
                int likeCount = Integer.parseInt(likeNumberLabel.getText());
                likeCount--;
                likeNumberLabel.setText(Integer.toString(likeCount));
            }
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
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        try
        {
            if(tweet.getImageContent() != null && tweet.getImageContent().getImage() != null)
                ImageIO.write(tweet.getImageContent().getImage(), "jpg", byteImage);
        }
        catch (IOException e)
        {
            System.out.println("TweetView -> showTweet -> writeImage in byteArray error : can't work with tweetImage!");
        }
        tweetImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));
        tweetImageView.setPreserveRatio(true);
        // TODO: tweetImageView.setImage((Image) tweet.getImageContent().getImage());
        likeNumberLabel.setText(Integer.toString(tweet.getLikeCount()));
        replyNumberLabel.setText(Integer.toString(tweet.getReplies().size()));
        retweetNumberLabel.setText(Integer.toString(tweet.getRetweetCount()));
        byteImage.reset();
        try
        {
            if(tweet.getOwner().getAvatar() != null)
                ImageIO.write(tweet.getOwner().getAvatar().getImage(), "jpg", byteImage);
        } catch (IOException e)
        {
            System.out.println("TweetView -> showTweet -> writeImage in byteArray error : can't work with userAvatar!");
        }
        avatarImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));
        Circle circle = new Circle();
        circle.setCenterX(avatarImageView.getFitWidth() / 2);
        circle.setCenterY(avatarImageView.getFitHeight() / 2);
        circle.setRadius(avatarImageView.getFitWidth() / 2);
        avatarImageView.setClip(circle);
        // TODO: set avatar imageview
        tweetId = tweet.getId();

        for(Reply reply : tweet.getReplies())
        {
            ReplyViewController replyViewController = MainClient.loadPage(repliesVbox, stage,"replyview.fxml");
            replyViewController.showReply(reply);
        }

        try
        {
            ControllerCommands.likeTweet(Long.toString(tweetId));
            ControllerCommands.dislikeTweet(Long.toString(tweetId));
        } catch (DuplicateLikeRequestException e)
        {
            likeIcon.setGlyphName("THUMBS_DOWN");
        } catch (TwitException ignored){}

        if(tweet.isFavstar())
            starIconView.setVisible(true);
    }

    private boolean like()
    {
        try
        {
            ControllerCommands.likeTweet(Long.toString(tweetId));
            return true;
        } catch (TwitException e)
        {
            e.printStackTrace(); // TODO: delete this line
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
