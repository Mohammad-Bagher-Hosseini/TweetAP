package com.tweetap.entities.exception.user.email;

import com.tweetap.entities.exception.TwitException;

public class EmailException extends TwitException
{
    public EmailException()
    {
        super();
    }

    public EmailException(String message)
    {
        super(message);
    }
}
