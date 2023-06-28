package com.tweetap.entities.tweet;

import com.tweetap.entities.tweet.content.ImageContent;
import com.tweetap.entities.tweet.content.TextContent;
import com.tweetap.entities.user.MiniUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("quote")
public class Quote extends Retweet implements Serializable
{
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "qoute_text"))
    private final TextContent textContent;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "qoute_image"))
    private final ImageContent imageContent;

    public Quote(Tweet tweet, MiniUser quotedBy, TextContent textContent, ImageContent imageContent)
    {
        super(tweet, quotedBy);
        this.textContent = textContent;
        this.imageContent = imageContent;
    }

    public Quote(Tweet tweet, Long id, LocalDateTime date, MiniUser quotedBy, TextContent textContent, ImageContent imageContent)
    {
        super(tweet, id, date, quotedBy);
        this.textContent = textContent;
        this.imageContent = imageContent;
    }

    public Quote()
    {
        super();
        textContent = null;
        imageContent = null;
    }

    public TextContent getTextContent()
    {
        return textContent;
    }

    public ImageContent getImageContent()
    {
        return imageContent;
    }

    public void setTweet(Tweet tweet)
    {
        this.tweet = tweet;
    }
}
