package com.tweetap;

import com.tweetap.client.controller.Data;
import com.tweetap.client.controller.graphic.HasStage;
import com.tweetap.client.controller.graphic.ObserverProfileController;
import com.tweetap.client.controller.graphic.SignInPageController;
import com.tweetap.client.controller.graphic.TimeLineController;
import com.tweetap.client.view.ProgramState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
        ProgramState programState = Data.getInstance().getProgramState();
        if(programState == ProgramState.MAIN_MENU)
            loadTimeLine(stage);
        else
            loadSignInPage(stage);


    }

    private void loadSignInPage(Stage stage) throws Exception
    {
        SignInPageController signInPageController = loadPage(stage, "signinpage.fxml");
        signInPageController.setStage(stage);
        stage.setTitle("TweetAP");
        stage.show();
    }

    private void loadTimeLine(Stage stage) throws Exception
    {
        TimeLineController timeLineController = loadPage(stage, "timeline.fxml");
        timeLineController.setStage(stage);
        stage.show();
    }

    public static <T extends HasStage> T loadPage(Stage stage, String page)
    {
        T t = null;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource(page));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            t = fxmlLoader.getController();
            t.setStage(stage);
        } catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while loading " + page + " file");
            alert.show();
        }

        return t;
    }

    public static <T extends HasStage> T loadPage(Pane pane, String page)
    {
        T t = null;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource(page));
            Parent root = fxmlLoader.load();
            pane.getChildren().add(root);
            t = fxmlLoader.getController();
        } catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while loading " + page + " file");
            alert.show();
        }

        return t;
    }

    public static <T extends HasStage> T loadPopup(Stage stage, String page)
    {
        T t = null;
        try
        {
            Stage popupStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource(page));
            Parent root = fxmlLoader.load();
            popupStage.setScene(root.getScene());

            t = fxmlLoader.getController();
            t.setStage(stage);

            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(stage);
            popupStage.showAndWait();
        } catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while loading " + page + " file");
            alert.show();
        }

        return t;
    }
}