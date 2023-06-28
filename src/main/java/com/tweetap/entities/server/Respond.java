package com.tweetap.entities.server;

import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Respond implements Serializable
{
    private final RespondCode respondCode;
    private final Object object;
    private final TwitException exception;

    public Respond(RespondCode respondCode)
    {
        this.respondCode = respondCode;
        object = null;
        exception = null;
    }

    public Respond(RespondCode respondCode, String message)
    {
        this.respondCode = respondCode;
        object = null;
        exception = null;
    }

    public Respond(RespondCode respondCode, Object object)
    {
        this.respondCode = respondCode;
        this.object = object;
        exception = null;
    }

    public Respond(RespondCode respondCode, Object object, TwitException e)
    {
        this.respondCode = respondCode;
        this.object = object;
        this.exception = e;
    }

    public Respond(TwitException e)
    {
        this.respondCode = RespondCode.FAIL;
        object = null;
        this.exception = e;
    }

    public RespondCode getRespondCode()
    {
        return respondCode;
    }

    public Object getObject()
    {
        return object;
    }

    public void send(ObjectOutputStream objectOutputStream) throws ServerInvalidObjectException
    {
        try
        {
            objectOutputStream.writeObject(this);
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new ServerInvalidObjectException("Invalid object sent to client!");
        }
    }

    public void check() throws UnknownException, DatabaseFailedException, ServerInvalidObjectException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, EmailFormatException, DuplicateUserNameException, DuplicateLikeRequestException, DuplicateFollowRequestException, UserNotFoundException, FollowRelationNotFoundException, DuplicateBlockRequestException, BlockRelationNotFoundException, TweetNotFoundException, LikeRelationNotFoundException
    {
        if(respondCode == RespondCode.FAIL)
        {
            if(exception == null)
                throw new UnknownException();

            // throw the exact type of the exception
            if(exception instanceof ServerInvalidObjectException)
                throw (ServerInvalidObjectException) exception;
            else if(exception instanceof DatabaseFailedException)
                throw (DatabaseFailedException) exception;
            else if(exception instanceof InvalidPasswordException)
                throw (InvalidPasswordException) exception;
            else if(exception instanceof CountryException)
                throw (CountryException) exception;
            else if(exception instanceof EmailFormatException)
                throw (EmailFormatException) exception;
            else if(exception instanceof TextTooLongException)
                throw (TextTooLongException) exception;
            else if(exception instanceof ServerInvalidCommandException)
                throw (ServerInvalidCommandException) exception;
            else if(exception instanceof DuplicateUserNameException)
                throw (DuplicateUserNameException) exception;
            else if(exception instanceof DuplicateLikeRequestException)
                throw (DuplicateLikeRequestException) exception;
            else if(exception instanceof UserNotFoundException)
                throw (UserNotFoundException) exception;
            else if(exception instanceof DuplicateFollowRequestException)
                throw (DuplicateFollowRequestException) exception;
            else if(exception instanceof FollowRelationNotFoundException)
                throw (FollowRelationNotFoundException) exception;
            else if(exception instanceof DuplicateBlockRequestException)
                throw (DuplicateBlockRequestException) exception;
            else if(exception instanceof BlockRelationNotFoundException)
                throw (BlockRelationNotFoundException) exception;
            else if(exception instanceof TweetNotFoundException)
                throw (TweetNotFoundException) exception;
            else if(exception instanceof LikeRelationNotFoundException)
                throw (LikeRelationNotFoundException) exception;
            else if (exception instanceof UnknownException)
                throw (UnknownException) exception;
            else
                throw new UnknownException();
        }
    }
}
