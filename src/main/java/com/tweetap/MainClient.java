package com.tweetap;

import com.tweetap.client.controller.Data;
import com.tweetap.client.controller.graphic.*;
import com.tweetap.client.view.ProgramState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        stage.setTitle("TweetAP");
        stage.show();
    }

    private void loadTimeLine(Stage stage) throws Exception
    {
        TimeLineController timeLineController = loadPage(stage, "timeline.fxml");
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
//            form(stage, scene);
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

    public static <T extends HasStage> T loadPage(Pane pane, Stage stage, String page)
    {
        T t = null;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource(page));
            Parent root = fxmlLoader.load();
            pane.getChildren().add(root);
            t = fxmlLoader.getController();
//            form(stage, stage.getScene());
            t.setStage(stage);
        } catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while loading " + page + " file");
            alert.show();
        }

        return t;
    }

    public static <T extends HasStage> T loadPopup(Stage stage, String page, MiddleFunction<T> middleFunction)
    {
        T t = null;
        try
        {
            Stage popupStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainClient.class.getResource(page));
            Parent root = fxmlLoader.load();
            popupStage.setScene(new Scene(root));
//            form(popupStage, popupStage.getScene());
            t = fxmlLoader.getController();
            middleFunction.function(t);
            t.setStage(popupStage);

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

    public static void form(Stage stage, Scene scene)
    {
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
    }
}