package com.tweetap.entities.tweet.content;

import com.tweetap.entities.exception.text.TextTooLongException;
import jakarta.persistence.Embeddable;

@Embeddable
public class TextContent extends Content
{
    private final String text;

    public TextContent(String text) throws TextTooLongException
    {
        if(text.length() > 280)
            throw new TextTooLongException("Text is too long");
        this.text = text;
    }

    public TextContent() {
        text = null;
    }

    @Override
    public String toString()
    {
        return text;
    }
}
