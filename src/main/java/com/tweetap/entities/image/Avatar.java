package com.tweetap.entities.image;

import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import jakarta.persistence.*;

import java.awt.image.BufferedImage;

@Entity
@Table(name = "avatars")
public class Avatar extends TwitterImage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Avatar()
    {
        super();
    }

    public Avatar(BufferedImage avatar)
    {
        super(avatar);
    }

    public Avatar(String path) throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException
    {
        super(path);
    }

    @Override
    public int getWidth()
    {
        return 400;
    }

    @Override
    public int getHeight()
    {
        return 400;
    }

    @Override
    public int getMaxWidth()
    {
        return 0;
    }

    @Override
    public int getMaxHeight()
    {
        return 0;
    }

    @Override
    public int getMaxByteSize()
    {
        return 1024 * 1024;
    }
}
