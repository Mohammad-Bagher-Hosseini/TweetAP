package com.tweetap.client.controller.graphic;

import com.tweetap.client.controller.ControllerCommands;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SendQuoteController implements HasStage
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
    private Long tweetId;

    @FXML
    public void searchImagePathButtonOnAction(ActionEvent actionEvent)
    {
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
        try
        {
            String text = textArea.getText();
            String imagePath = imagePathTextFiled.getText();
            ControllerCommands.sendQuote(Long.toString(tweetId), text, imagePath);
            stage.close();
        } catch (TwitException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Something went wrong while sending your quote!");
            alert.show();
        } catch (ImageSizeException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Image size not accepted!");
            alert.show();
        } catch (FileSizeException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File size too big!");
            alert.show();
        } catch (FileNotExistException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file doesn't exist");
            alert.show();
        } catch (FileNotImageException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The file was not an image");
            alert.show();
        }
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent)
    {
        stage.close();
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setTweetId(Long tweetId)
    {
        this.tweetId = tweetId;
    }
}
