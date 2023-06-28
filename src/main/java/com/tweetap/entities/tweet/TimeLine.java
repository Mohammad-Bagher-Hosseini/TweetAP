package com.tweetap.entities.tweet;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TimeLine implements Iterable<BaseTweet>, Serializable
{
    private final ArrayList<BaseTweet> tweets;

    public TimeLine()
    {
        tweets = new ArrayList<>();
    }

    public void add(BaseTweet tweet)
    {
        tweets.add(tweet);
    }

    public void add(List<BaseTweet> tweetList)
    {
        for (BaseTweet b : tweetList)
        {
            add(b);
        }
    }

    public void remove(BaseTweet tweet)
    {
        tweets.remove(tweet);
    }

    public BaseTweet getBaseTweet(long id)
    {
        for (BaseTweet tweet : tweets)
            if (tweet.getId() == id)
                return tweet;
        return null;
    }

    public Tweet getTweet(long id)
    {
        for (BaseTweet tweet : tweets)
            if(tweet.getId() == id)
            {
                if(tweet instanceof Tweet)
                    return (Tweet) tweet;
                else if(tweet instanceof Retweet)
                    return ((Retweet) tweet).getTweet();
            }
        return null;
    }

    public Tweet getTweetIdOfRetweet(long retweetId)
    {
        for (BaseTweet tweet : tweets)
            if (tweet.getId() == retweetId && tweet instanceof Retweet)
                return ((Retweet) tweet).getTweet();
        return null;
    }

    public Reply getReply(long tweetId, long replyId)
    {
        for (BaseTweet tweet : tweets)
            if (tweet.getId() == tweetId && tweet instanceof Tweet)
                return ((Tweet) tweet).getReply(replyId);
        return null;
    }

    public void sort()
    {
        Comparator<BaseTweet> comparator = Comparator.comparing(BaseTweet::getDate);
        this.tweets.sort(comparator);
    }

    @NotNull
    @Override
    public Iterator<BaseTweet> iterator()
    {
        return tweets.iterator();
    }

    public int size()
    {
        return tweets.size();
    }
}
