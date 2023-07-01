package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.user.BlackList;
import com.tweetap.entities.user.MiniUser;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ObserverProfileController implements HasStage
{
    @FXML
    public Button followRelationButton;
    @FXML
    public Label nameTextField;
    @FXML
    public ImageView headerImageView;
    @FXML
    public Label userNameTextField;
    @FXML
    public Button followingsButton;
    @FXML
    public Label followingsNumberLabel;
    @FXML
    public Button followersButton;
    @FXML
    public Label followersNumberLabel;
    @FXML
    public Label bioLabel;
    @FXML
    public Button blockRelationButton;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public Button backButton;
    @FXML
    public Label familyTextField;
    private Stage stage;
    private MiniUser miniUser;
    private Followers followers;
    private Followings followings;

    private void onShown()
    {
        nameTextField.setText(miniUser.getUserName() + " " + miniUser.getFamily());
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(miniUser.getHeader().getImage(), "jpg", byteImage);
        }
        catch (IOException e)
        {
            //TODO : error
        }
        headerImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));
        userNameTextField.setText("@" + miniUser.getUserName());
        try
        {
            followers = ControllerCommands.showFollowers();
            followersNumberLabel.setText(Integer.toString(followers.size()));
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while counting followers count");
            alert.show();
        }
        try
        {
            followings = ControllerCommands.showFollowings();
            followersNumberLabel.setText(Integer.toString(followings.size()));
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while counting followings count");
            alert.show();
        }
        bioLabel.setText(miniUser.getBio().getText());
        byteImage.reset();
        try
        {
            ImageIO.write(miniUser.getAvatar().getImage(), "jpg", byteImage);
        } catch (IOException e)
        {
            //TODO : error
        }
        avatarImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));

        if(followers.getUserNames().contains(Data.getInstance().getUser().getUserName()))
            followRelationButton.setText("Unfollow");
        else
            followRelationButton.setText("Follow");

        try
        {
            BlackList blackList = ControllerCommands.showBlackList();
            if(blackList.contains(miniUser.getUserName()))
                blockRelationButton.setText("Unblock");
            else
                blockRelationButton.setText("Block");
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while loading the blacklist");
            alert.show();
        }
    }

    @FXML
    public void followRelationButtonOnAction(ActionEvent actionEvent)
    {
        if(followRelationButton.getText().equals("Follow"))
        {
            if (follow())
                followRelationButton.setText("Unfollow");
        }
        else
        {
            if (unfollow())
                followRelationButton.setText("Follow");
        }
    }

    @FXML
    public void followingsButtonOnAction(ActionEvent actionEvent)
    {
        ShowFollowsController showFollowsController = MainClient.loadPopup(stage, "showfollows_pop_up.fxml", (controller) ->
        {
            controller.setMiniUser(miniUser);
            controller.showFollowings();
        });
    }

    @FXML
    public void followersButtonOnAction(ActionEvent actionEvent)
    {
        ShowFollowsController showFollowsController = MainClient.loadPopup(stage, "showfollows_pop_up.fxml", (controller) ->
        {
            controller.setMiniUser(miniUser);
            controller.showFollowers();
        });
    }

    @FXML
    public void blockRelationButtonOnAction(ActionEvent actionEvent)
    {
        if(blockRelationButton.getText().equals("Block"))
        {
            if (block())
                blockRelationButton.setText("Unblock");
        }
        else
        {
            if(unblock())
                blockRelationButton.setText("Block");
        }

    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        stage.close();
    }

    private boolean follow()
    {
        try
        {
            ControllerCommands.follow(miniUser.getUserName());
            return true;
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while following @" + miniUser.getUserName() + " !");
            alert.show();
            return false;
        }
    }

    private boolean unfollow()
    {
        try
        {
            ControllerCommands.unfollow(miniUser.getUserName());
            return true;
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while unfollowing @" + miniUser.getUserName() + " !");
            alert.show();
            return false;
        }
    }

    private boolean block()
    {
        try
        {
            ControllerCommands.block(miniUser.getUserName());
            return true;
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while blocking @" + miniUser.getUserName() + " !");
            alert.show();
            return false;
        }
    }

    private boolean unblock()
    {
        try
        {
            ControllerCommands.unblock(miniUser.getUserName());
            return true;
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while unblocking @" + miniUser.getUserName() + " !");
            alert.show();
            return false;
        }
    }

    public void setUsername(String username)
    {
        try
        {
            miniUser = ControllerCommands.showUser(username);
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while getting the user profile!");
            alert.show();
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
        onShown();
    }
}
