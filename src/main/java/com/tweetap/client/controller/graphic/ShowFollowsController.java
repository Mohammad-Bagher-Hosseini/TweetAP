package com.tweetap.client.controller.graphic;

import com.tweetap.MainClient;
import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.user.MiniUser;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowFollowsController implements HasStage
{
    @FXML
    public Button followersButton;
    @FXML
    public Button followingsButton;
    @FXML
    public Button backButton;

    public VBox followRelationsVBox;
    private Stage stage;

    public void initialize()
    {
        showFollowings();
    }

    @FXML
    public void followersButtonOnAction(ActionEvent actionEvent)
    {
        showFollowers();
    }

    @FXML
    public void followingsButtonOnAction(ActionEvent actionEvent)
    {
        showFollowings();
    }

    private void showFollowers()
    {
        try
        {
            Followers followers = ControllerCommands.showFollowers();
            for(MiniUser miniUser : followers)
            {
                MiniProfileController miniProfileController = MainClient.loadPage(followRelationsVBox, "miniprofile.fxml");
                miniProfileController.showMiniUser(miniUser);
            }
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There was some error loading your followers");
            alert.show();
        }
    }

    private void showFollowings()
    {
        try
        {
            Followings followings = ControllerCommands.showFollowings();
            for(MiniUser miniUser : followings)
            {
                MiniProfileController miniProfileController = MainClient.loadPage(followRelationsVBox, "miniprofile.fxml");
                miniProfileController.showMiniUser(miniUser);
            }
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There was some error loading your followers");
            alert.show();
        }
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        MainClient.loadPage(stage, "profile.fxml");
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
