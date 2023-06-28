package com.tweetap.entities.exception.user;

import com.tweetap.entities.exception.TwitException;

public class EmailOrPhoneRequiredException extends TwitException
{
    public EmailOrPhoneRequiredException()
    {
        super();
    }

    public EmailOrPhoneRequiredException(String message)
    {
        super(message);
    }
}
