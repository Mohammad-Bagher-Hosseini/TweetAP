package com.tweetap.entities.user;

import com.tweetap.entities.exception.text.TextTooLongException;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Bio implements Serializable
{
    private String text;

    public Bio()
    {
        text = "";
    }

    public Bio(String text) throws TextTooLongException
    {
        setText(text);
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text) throws TextTooLongException
    {
        if(text.length() > 160)
            throw new TextTooLongException("The bio must be at most 160 char long!");
        this.text = text;
    }

    @Override
    public String toString()
    {
        return text;
    }
}
