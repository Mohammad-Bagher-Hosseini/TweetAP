package com.tweetap.entities.tweet;

import com.tweetap.entities.exception.hashtag.HashtagException;
import com.tweetap.entities.tweet.content.ImageContent;
import com.tweetap.entities.tweet.content.TextContent;
import com.tweetap.entities.tweet.content.hashtag.Hashtags;
import com.tweetap.entities.user.MiniUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("tweet")
public class Tweet extends BaseTweet implements Serializable
{
    @Embedded
    private final TextContent textContent;
    @Embedded
    private final ImageContent imageContent;
    private int likeCount;
    private int retweetCount;
    private int quoteCount;
    private boolean isFavstar;
    @Transient
    private List<Reply> replies;
    @OneToOne(fetch = FetchType.LAZY)
    private final Hashtags hashtags;

    public Tweet(MiniUser owner, TextContent textContent, ImageContent imageContent) throws HashtagException
    {
        super(owner);
        this.textContent = textContent;
        this.imageContent = imageContent;
        likeCount = 0;
        retweetCount = 0;
        quoteCount = 0;
        replies = new ArrayList<>();
        hashtags = new Hashtags();

        hashtags.getHashtagsFromText(textContent.toString());
    }

    public Tweet(MiniUser owner, TextContent textContent, ImageContent imageContent, int id, LocalDateTime tweetDate) throws HashtagException
    {
        super(tweetDate, owner);
        this.textContent = textContent;
        this.imageContent = imageContent;
        likeCount = 0;
        retweetCount = 0;
        quoteCount = 0;
        replies = new ArrayList<>();
        hashtags = new Hashtags();

        hashtags.getHashtagsFromText(textContent.toString());
    }

    public Tweet() {

        textContent = null;
        imageContent = null;
        replies = null;
        hashtags = null;
    }

    public TextContent getTextContent()
    {
        return textContent;
    }

    public ImageContent getImageContent()
    {
        return imageContent;
    }

    public int getLikeCount()
    {
        return likeCount;
    }

    public void setLikeCount(int likeCount)
    {
        this.likeCount = likeCount;
    }

    public int getRetweetCount()
    {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount)
    {
        this.retweetCount = retweetCount;
    }

    public void setReplies(List<Reply> replies){this.replies = replies;}

    public int getQuoteCount()
    {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount)
    {
        this.quoteCount = quoteCount;
    }

    public boolean isFavstar()
    {
        return isFavstar;
    }

    public void setFavstar(boolean favstar)
    {
        isFavstar = favstar;
    }

    public List<Reply> getReplies()
    {
        return replies;
    }

    public Hashtags getHashtags()
    {
        return hashtags;
    }

    public Reply getReply(long replyId)
    {
        for (Reply reply : replies)
            if (reply.getId() == replyId)
                return reply;
        return null;
    }
}
