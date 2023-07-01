package com.tweetap.client.controller.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SendQuoteController
{
    @FXML
    public TextField imagePathTextFiled;
    @FXML
    public Button searchImagePathButton;
    @FXML
    public ImageView imageView;
    @FXML
    public Button sendQuoteButton;
    @FXML
    public TextArea textArea;
    @FXML
    public Button backButton;

    private Stage stage;

    @FXML
    public void searchImagePathButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : set image
        if(imagePathTextFiled.getText() == null)
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File selected = fileChooser.showOpenDialog(stage);
            if(selected != null)
            {
                imageView.setImage(new Image(selected.toURI().toString()));
            }
            else
            {
                //TODO : do suitable work
            }
        }
        else
        {
            try
            {
                BufferedImage bImage = ImageIO.read(new File(imagePathTextFiled.getText()));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bImage, "jpg", byteArrayOutputStream);
                InputStream inputImage = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                imageView.setImage(new Image(inputImage));
            } catch (IOException e)
            {
                //TODO : do suitable work
            }
        }
    }

    @FXML
    public void sendQuoteButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : send quote and close the pop_up
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        //TODO : close the pop_up
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
