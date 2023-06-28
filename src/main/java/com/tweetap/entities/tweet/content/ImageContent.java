package com.tweetap.entities.tweet.content;

import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import com.tweetap.entities.image.ImageContentData;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

import java.awt.image.BufferedImage;

@Embeddable
public class ImageContent extends Content
{
    private final ImageContentData image;

    public ImageContent(String path) throws ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        this.image = new ImageContentData(path);
    }

    public ImageContent()
    {
        image = null;
    }

    @Transient
    public BufferedImage getImage()
    {
        return image.getImage();
    }
}
