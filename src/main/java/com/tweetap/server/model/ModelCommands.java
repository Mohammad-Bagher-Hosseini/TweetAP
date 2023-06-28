package com.tweetap.server.model;

import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.image.Avatar;
import com.tweetap.entities.image.Header;
import com.tweetap.entities.server.Respond;
import com.tweetap.entities.server.RespondCode;
import com.tweetap.entities.tweet.*;
import com.tweetap.entities.user.*;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;
import com.tweetap.server.model.DatabaseCommands;
import com.tweetap.server.model.DatabaseManager;

import java.io.ObjectInputStream;
import java.time.LocalDate;

public class ModelCommands
{
    private final DatabaseManager databaseManager;
    private final DatabaseCommands databaseCommands;

    public ModelCommands()
    {
        databaseManager = new DatabaseManager();
        databaseCommands = new DatabaseCommands();
//        databaseManager = null;
    }

    public Respond signUp(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, DuplicateUserNameException
    {
        User user = ObjectGetter.getObject(objectInputStream, User.class);

        // TODO: save to database, throw exception if failed
        databaseCommands.signUp(user);

        // TODO: add the newly created exceptions to both the client and the server project!!!

        // show the user as a test
        System.out.println(user);

        return new Respond(RespondCode.SUCCESS);
    }

    public Respond signIn(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, InvalidPasswordException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Password passwordHash = ObjectGetter.getObject(objectInputStream, Password.class);

        // TODO: get user from database, throw exception if not found or some other error
        User user = databaseCommands.signIn(userName, passwordHash);
        return new Respond(RespondCode.SUCCESS, user);
        // TODO: add the newly created exceptions to both the client and the server project!!!

        // temporary user (because we don't have a database yet)
//        User user = new User();
//        user.setUserName(userName);
//        user.setPassword(passwordHash);

//        return new Respond(RespondCode.SUCCESS, user);
    }

    public Respond setAvatar(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Avatar avatar = ObjectGetter.getObject(objectInputStream, Avatar.class);

        // TODO: save to database, throw exception if failed
        databaseCommands.setAvatar(userName, avatar);
        // TODO: add the newly created exceptions to both the client and the server project!!!

        // temporary showing the username (because we don't have a database yet)
        System.out.println(userName);

        return new Respond(RespondCode.SUCCESS);
    }

    public Respond setHeader(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Header header = ObjectGetter.getObject(objectInputStream, Header.class);

        // TODO: save to database, throw exception if failed
        databaseCommands.setHeader(userName, header);
        // TODO: add the newly created exceptions to both the client and the server project!!!

        // temporary showing the username (because we don't have a database yet)
        System.out.println(userName);

        return new Respond(RespondCode.SUCCESS);
    }

//    public Respond changeUserInformation(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, DataNotFoundException, TextTooLongException
//    {
//        String userName = ObjectGetter.getObject(objectInputStream, String.class);
//        Bio bio = ObjectGetter.getObject(objectInputStream, Bio.class);
//        String location = ObjectGetter.getObject(objectInputStream, String.class);
//        String website = ObjectGetter.getObject(objectInputStream, String.class);
//
//        // TODO: change user information in database, throw exception if not found or some other error
//        databaseCommands.changeUserInformation(userName, bio, location, website);
//        // TODO: add the newly created exceptions to both the client and the server project!!!
//
//        // temporary showing the user information (because we don't have a database yet)
//        System.out.println(userName);
//        System.out.println(bio);
//        System.out.println(location);
//
//        return new Respond(RespondCode.SUCCESS);
//    }

    public Respond changePassword(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        // TODO
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Password newpass = ObjectGetter.getObject(objectInputStream, Password.class);
        databaseCommands.changeUserPassword(userName, newpass);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeName(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        // TODO
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String newName = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.changeName(userName, newName);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeFamily(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        // TODO
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String newFamily = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.changeFamily(userName, newFamily);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeEmail(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, EmailFormatException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String newEmail = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.changeEmail(userName, newEmail);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changePhoneNumber(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, EmailFormatException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String newPhone = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.changePhone(userName, newPhone);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeBirthDate(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        LocalDate newBirth = ObjectGetter.getObject(objectInputStream, LocalDate.class);
        databaseCommands.changeBirthDate(userName, newBirth);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeCountry(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, CountryException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String newCountry = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.changeCountry(userName, newCountry);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeBio(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, TextTooLongException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Bio newBio = ObjectGetter.getObject(objectInputStream, Bio.class);
        databaseCommands.changeBio(userName, newBio);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeLocation(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String newLocation = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.changeLocation(userName, newLocation);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond changeWebsite(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String newWebsite = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.changeWebsite(userName, newWebsite);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond showFollowers(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Followers followers = databaseCommands.showFollowers(userName);
        return new Respond(RespondCode.SUCCESS, followers);
    }

    public Respond showFollowings(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        Followings followings = databaseCommands.showFollowings(userName);
        return new Respond(RespondCode.SUCCESS, followings);
    }

    public Respond follow(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException, DuplicateFollowRequestException
    {
        // FIXME
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String followedUserName = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.follow(userName, followedUserName);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond unfollow(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException, FollowRelationNotFoundException
    {
        // FIXME
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        String followedUserName = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.unfollow(userName, followedUserName);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond showUser(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        MiniUser miniUser = databaseCommands.showUser(userName);
        return new Respond(RespondCode.SUCCESS, miniUser);
    }

    public Respond sendTweet(ObjectInputStream objectInputStream) throws ServerInvalidObjectException
    {
        Tweet tweet = ObjectGetter.getObject(objectInputStream, Tweet.class);
        databaseCommands.sendTweet(tweet);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond sendRetweet(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, TweetNotFoundException, UserNotFoundException
    {
        Long id = ObjectGetter.getObject(objectInputStream, Long.class);
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.sendRetweet(id, userName);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond sendQuote(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, TweetNotFoundException
    {
        Long id = ObjectGetter.getObject(objectInputStream, Long.class);
        Quote quote = ObjectGetter.getObject(objectInputStream, Quote.class);
        databaseCommands.sendQuote(id, quote);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond sendReply(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, TweetNotFoundException
    {
        Long id = ObjectGetter.getObject(objectInputStream, Long.class);
        Reply reply = ObjectGetter.getObject(objectInputStream, Reply.class);
        databaseCommands.sendReply(id, reply);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond likeTweet(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, DuplicateLikeRequestException, UserNotFoundException, TweetNotFoundException
    {
        Tweet tweet = ObjectGetter.getObject(objectInputStream, Tweet.class);
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.likeTweet(tweet, userName);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond dislikeTweet(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException, LikeRelationNotFoundException, TweetNotFoundException
    {
        Tweet tweet = ObjectGetter.getObject(objectInputStream, Tweet.class);
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.dislikeTweet(tweet, userName);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond showTimeLine(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UnknownException, UserNotFoundException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        TimeLine timeLine = databaseCommands.showTimeLine(userName);
        return new Respond(RespondCode.SUCCESS, timeLine);
    }

    public Respond block(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException, DuplicateBlockRequestException
    {
        String blocker = ObjectGetter.getObject(objectInputStream, String.class);
        String blocked = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.block(blocker, blocked);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond unblock(ObjectInputStream objectInputStream) throws ServerInvalidObjectException, UserNotFoundException, BlockRelationNotFoundException
    {
        String blocker = ObjectGetter.getObject(objectInputStream, String.class);
        String blocked = ObjectGetter.getObject(objectInputStream, String.class);
        databaseCommands.unblock(blocker, blocked);
        return new Respond(RespondCode.SUCCESS);
    }

    public Respond showBlackList(ObjectInputStream objectInputStream) throws ServerInvalidObjectException
    {
        String userName = ObjectGetter.getObject(objectInputStream, String.class);
        BlackList blackList = databaseCommands.showBlackList(userName);
        return new Respond(RespondCode.SUCCESS, blackList);
    }
}
