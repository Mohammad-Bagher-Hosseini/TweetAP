package com.tweetap.entities.exception.user;

import com.tweetap.entities.exception.TwitException;

public class CountryException extends TwitException
{
    public CountryException()
    {
        super();
    }

    public CountryException(String message)
    {
        super(message);
    }
}
