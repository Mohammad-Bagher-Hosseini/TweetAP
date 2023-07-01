package com.tweetap.entities.image;

import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@MappedSuperclass
public abstract class TwitterImage implements Serializable
{
    @Transient
    private transient BufferedImage image;
    @Lob
    private String encodedImage;

    public TwitterImage()
    {
        this.image = null;
        this.encodedImage = null;
    }

    public TwitterImage(BufferedImage image)
    {
        this.image = image;
        generateEncodedImage();
    }

    // return 0 if you want to use WIDTH and HEIGHT instead of MAX_WIDTH and MAX_HEIGHT

    public abstract int getWidth();
    public abstract int getHeight();


    public abstract int getMaxWidth();

    public abstract int getMaxHeight();

    public abstract int getMaxByteSize();

    public TwitterImage(String path) throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException
    {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotExistException("File does not exist");

        if (file.length() > getMaxByteSize())
            throw new FileSizeException("File size is too large (max size : " + getMaxByteSize() / 1024 / 1024 + "MB)");

        try
        {
            setImage(ImageIO.read(file));
        } catch (IOException e)
        {
            throw new FileNotImageException("File is not an image");
        }

        if (image == null)
            throw new FileNotImageException("File is not an image");

        if (getMaxWidth() == 0 && getMaxHeight() == 0)
        {
            if (image.getWidth() != getWidth() || image.getHeight() != getHeight())
                throw new ImageSizeException("Image size is not " + getWidth() + "X" + getHeight());
        } else
        {
            if (image.getWidth() > getMaxWidth() || image.getHeight() > getMaxHeight())
                throw new ImageSizeException("Image size should be less than or equal " + getMaxWidth() + "X" + getMaxHeight());
        }
    }

    private void generateEncodedImage()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(image,"jpg",byteArrayOutputStream);
            encodedImage = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            if(!encodedImage.equals(""))
                return;

            ImageIO.write(image,"png",byteArrayOutputStream);
            encodedImage = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            if(!encodedImage.equals(""))
                return;

            ImageIO.write(image,"gif",byteArrayOutputStream);
            encodedImage = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e)
        {
            // TODO : handle the exception
            encodedImage = null;
        }
    }

    private void generateDecodedImage()
    {
        byte[] imageBytes = Base64.getDecoder().decode(encodedImage);
        try
        {
            this.image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e)
        {
            // TODO : handle the exception
            image = null;
        }
    }

    public String getEncodedImage()
    {
        generateEncodedImage();
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage)
    {
        this.encodedImage = encodedImage;
        generateDecodedImage();
    }

    public BufferedImage getImage()
    {
        generateDecodedImage();
        return image;
    }

    private void setImage(BufferedImage image)
    {
        this.image = image;
        generateEncodedImage();
    }
}