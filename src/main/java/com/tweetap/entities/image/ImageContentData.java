package com.tweetap.entities.image;

import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import jakarta.persistence.Embeddable;

import java.awt.image.BufferedImage;

@Embeddable
public class ImageContentData extends TwitterImage
{
    public ImageContentData()
    {
        super();
    }

    public ImageContentData(BufferedImage image)
    {
        super(image);
    }

    public ImageContentData(String path) throws ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        super(path);
    }

    @Override
    public int getWidth()
    {
        return 0;
    }

    @Override
    public int getHeight()
    {
        return 0;
    }

    @Override
    public int getMaxWidth()
    {
        return 1600;
    }

    @Override
    public int getMaxHeight()
    {
        return 900;
    }

    @Override
    public int getMaxByteSize()
    {
        return 5 * 1024 * 1024;
    }
}
