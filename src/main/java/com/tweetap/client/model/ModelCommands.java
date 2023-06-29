package com.tweetap.client.model;

import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.image.Avatar;
import com.tweetap.entities.image.Header;
import com.tweetap.entities.server.Respond;
import com.tweetap.entities.tweet.*;
import com.tweetap.entities.user.*;
import com.tweetap.entities.user.follow.FollowRelation;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;

import java.time.LocalDate;

public class ModelCommands
{
    public ModelCommands()
    {

    }

    public static void signUp(User user) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("sign-up");
            serverConnectionHandler.sendObject(user);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();

            // test the respond code
            System.out.println(respond.getRespondCode());
        }
    }

    public static User signIn(String userName, Password passwordHash) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("sign-in");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(passwordHash);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();

            return (User) respond.getObject();
        }
    }

    public static void setAvatar(String userName, Avatar avatar) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("set-image");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(avatar);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void setHeader(String userName, Header header) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("set-header");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(header);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changePassword(String userName, Password newPasswordHash) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-password");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(newPasswordHash);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeName(String userName, String name) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-name");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(name);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeFamily(String userName, String family) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-family");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(family);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeEmail(String userName, String email) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-email");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(email);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changePhoneNumber(String userName, String phoneNumber) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-phone-number");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(phoneNumber);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeBirthDate(String userName, LocalDate birthDate) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-birth-date");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(birthDate);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeCountry(String userName, String country) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-country");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(country);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeBio(String userName, Bio bio) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-bio");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(bio);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeLocation(String userName, String location) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-location");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(location);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void changeWebsite(String userName, String website) throws ServerRespondFailedException, ServerConnectionFailedException, ServerInvalidObjectException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("change-website");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(website);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static Followers showFollowers(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-followers");
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (Followers) respond.getObject();
        }
    }

    public static Followings showFollowings(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-followings");
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (Followings) respond.getObject();
        }
    }

    public static void follow(String userName, String followedUserName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("follow");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(followedUserName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void unfollow(String userName, String followedUserName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("unfollow");
            serverConnectionHandler.sendObject(userName);
            serverConnectionHandler.sendObject(followedUserName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static MiniUser showUser(String miniUserUsername) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-user");
            serverConnectionHandler.sendObject(miniUserUsername);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (MiniUser) respond.getObject();
        }
    }

    public static void sendTweet(Tweet tweet) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, DatabaseFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("send-tweet");
            serverConnectionHandler.sendObject(tweet);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void sendRetweet(Long id, String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("send-retweet");
            serverConnectionHandler.sendObject(id);
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void sendQuote(Long id, Quote quote) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("send-quote");
            serverConnectionHandler.sendObject(id);
            serverConnectionHandler.sendObject(quote);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void sendReply(Long id, Reply reply) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("send-reply");
            serverConnectionHandler.sendObject(id);
            serverConnectionHandler.sendObject(reply);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void likeTweet(Tweet tweet, String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("like-tweet");
            serverConnectionHandler.sendObject(tweet);
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void dislikeTweet(Tweet tweet, String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("dislike-tweet");
            serverConnectionHandler.sendObject(tweet);
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static TimeLine showTimeLine(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-time-line");
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (TimeLine) respond.getObject();
        }
    }

    public static void block(String blocker, String blocked) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("block");
            serverConnectionHandler.sendObject(blocker);
            serverConnectionHandler.sendObject(blocked);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static void unblock(String blocker, String blocked) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("unblock");
            serverConnectionHandler.sendObject(blocker);
            serverConnectionHandler.sendObject(blocked);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
        }
    }

    public static BlackList showBlackList(String userName) throws ServerConnectionFailedException, ServerInvalidObjectException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        try (ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler())
        {
            serverConnectionHandler.sendCommend("show-black-list");
            serverConnectionHandler.sendObject(userName);
            Respond respond = serverConnectionHandler.getRespond();
            respond.check();
            return (BlackList) respond.getObject();
        }
    }
}
