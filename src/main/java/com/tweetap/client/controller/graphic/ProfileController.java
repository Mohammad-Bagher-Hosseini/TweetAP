package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.client.controller.Data;
import com.tweetap.entities.exception.user.PermissionDeniedException;
import com.tweetap.entities.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private Stage stage;

    private void onShown()
    {
        User user = Data.getInstance().getUser();
        // TODO: headerImageView
        nameTextField.setText(user.getName());
        familyTextField.setText(user.getFamily());
        userNameTextField.setText(user.getUserName());
        signUpDateTextField.setText(user.getSignUpDate().toString());
        bioLabel.setText(user.getBio().getText());
        // TODO: avatarImageView
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
        MainClient.loadPopup(stage, "showfollows", ShowFollowsController::showFollowings);
    }

    @FXML
    public void followersButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPopup(stage, "showfollows", ShowFollowsController::showFollowers);
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
