package com.tweetap;

import com.tweetap.client.controller.graphic.SignInPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application
{
    public static void main(String[] args)
    {
//        Program.start();
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception
    {
        // TODO : specify state of sign up of user
        //if (isUserSignUp(user)) do something
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signinpage.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("TweetAP");
        stage.setScene(new Scene(root));

        SignInPageController signInPageController = fxmlLoader.getController();
        signInPageController.setStage(stage);

        stage.show();
    }
}