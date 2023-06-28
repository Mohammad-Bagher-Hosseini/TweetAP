package com.tweetap.entities.exception;

import java.io.Serializable;

public abstract class TwitException extends Exception implements Serializable
{
    public TwitException()
    {
        super();
    }

    public TwitException(String message)
    {
        super(message);
    }
}
