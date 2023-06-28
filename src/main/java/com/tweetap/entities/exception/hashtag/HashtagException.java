package com.tweetap.entities.exception.hashtag;

import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.tweet.content.hashtag.Hashtag;

public class HashtagException extends TwitException
{
    public HashtagException()
    {
        super();
    }

    public HashtagException(String message)
    {
        super(message);
    }
}
