package com.tweetap;

import com.tweetap.client.controller.Program;
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
        Parent root = new FXMLLoader(getClass().getResource("signinpage.fxml")).load();
        stage.setTitle("TweetAP");
        stage.setScene(new Scene(root));
        stage.show();
    }
}