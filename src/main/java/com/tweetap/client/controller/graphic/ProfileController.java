package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.user.PermissionDeniedException;
import com.tweetap.entities.user.User;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileController implements HasStage
{

    @FXML
    public Button homeButton;
    @FXML
    public Button searchButton;
    @FXML
    public Button tweetButton;
    @FXML
    public Button exitButton;
    @FXML
    public ImageView headerImageView;
    @FXML
    public Button editProfileButton;
    @FXML
    public Label nameTextField;
    @FXML
    public Label userNameTextField;
    @FXML
    public Label signUpDateTextField;
    @FXML
    public Button followingsButton;
    @FXML
    public Button followersButton;
    @FXML
    public Label bioLabel;
    @FXML
    public ImageView avatarImageView;
    @FXML
    public Button signOutButton;
    @FXML
    public Label familyTextField;
    public Label followingNumberLabel;
    public Label followerNumberLabel;
    private Stage stage;

    private void onShown()
    {
        User user = Data.getInstance().getUser();
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        try
        {
            if(user.getHeader() != null)
                ImageIO.write(user.getHeader().getImage(), "jpg", byteImage);
        } catch (IOException e)
        {
            //TODO : error
        }
        headerImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));

        nameTextField.setText(user.getName());
        familyTextField.setText(user.getFamily());
        userNameTextField.setText(user.getUserName());
        signUpDateTextField.setText(user.getSignUpDate().toString());
        bioLabel.setText(user.getBio().getText());
        byteImage.reset();
        try
        {
            if(user.getAvatar() != null)
                ImageIO.write(user.getAvatar().getImage(), "jpg", byteImage);
        } catch (IOException e)
        {
            //TODO : error
        }
        avatarImageView.setImage(new Image(new ByteArrayInputStream(byteImage.toByteArray())));
        Circle circle = new Circle();
        circle.setCenterX(avatarImageView.getFitWidth() / 2);
        circle.setCenterY(avatarImageView.getFitHeight() / 2);
        circle.setRadius(avatarImageView.getFitWidth() / 2);
        avatarImageView.setClip(circle);

        try
        {
            Followers followers = ControllerCommands.showFollowers();
            followerNumberLabel.setText(Integer.toString(followers.size()));
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while counting followers count");
            alert.show();
        }
        try
        {
            Followings followings = ControllerCommands.showFollowings();
            followingNumberLabel.setText(Integer.toString(followings.size()));
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while counting followings count");
            alert.show();
        }
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
    public void tweetButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "sendtweet.fxml");
    }

    @FXML
    public void exitButtonOnAction(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    @FXML
    public void editProfileButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPopup(stage, "editprofile_pop_up.fxml", (controller) -> {});
    }

    @FXML
    public void followingsButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPopup(stage, "showfollows_pop_up.fxml", ShowFollowsController::showFollowings);
    }

    @FXML
    public void followersButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPopup(stage, "showfollows_pop_up.fxml", ShowFollowsController::showFollowers);
    }

    @FXML
    public void signOutButton(ActionEvent actionEvent)
    {
        try
        {
            ControllerCommands.signOut();
            MainClient.loadPage(stage, "signinpage.fxml");
        } catch (PermissionDeniedException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while signing out!");
            alert.show();
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
        onShown();
    }
}
