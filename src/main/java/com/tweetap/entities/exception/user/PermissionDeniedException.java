package com.tweetap.entities.exception.user;

import com.tweetap.entities.exception.TwitException;

public class PermissionDeniedException extends TwitException
{
    public PermissionDeniedException()
    {
        super();
    }

    public PermissionDeniedException(String message)
    {
        super(message);
    }
}
