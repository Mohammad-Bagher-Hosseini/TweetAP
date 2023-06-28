package com.tweetap.entities.exception.user.password;

import com.tweetap.entities.exception.TwitException;

public class PasswordException extends TwitException
{
    public PasswordException()
    {
        super();
    }

    public PasswordException(String message)
    {
        super(message);
    }
}
