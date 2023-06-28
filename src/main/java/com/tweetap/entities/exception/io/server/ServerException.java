package com.tweetap.entities.exception.io.server;

import com.tweetap.entities.exception.io.TwitIOException;

public class ServerException extends TwitIOException
{
    public ServerException()
    {
        super();
    }

    public ServerException(String message)
    {
        super(message);
    }
}
