package com.tweetap.client.controller;

import com.tweetap.client.view.ProgramState;
import com.tweetap.entities.exception.hashtag.HashtagException;
import com.tweetap.entities.exception.user.EmailOrPhoneRequiredException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.PermissionDeniedException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.io.*;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.exception.user.password.PasswordConfirmException;
import com.tweetap.entities.exception.user.password.PasswordFormatException;
import com.tweetap.entities.exception.user.password.PasswordHashException;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.image.Avatar;
import com.tweetap.entities.image.Header;
import com.tweetap.entities.tweet.*;
import com.tweetap.entities.tweet.content.ImageContent;
import com.tweetap.entities.tweet.content.TextContent;
import com.tweetap.entities.tweet.content.hashtag.Hashtags;
import com.tweetap.entities.user.*;
import com.tweetap.client.model.ModelCommands;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;

import java.time.LocalDate;
import java.util.Iterator;

public class ControllerCommands
{
    private static User getCurrentUser() throws PermissionDeniedException
    {
        User user = Data.getInstance().getUser();
        if (user == null || Data.getInstance().getProgramState() == ProgramState.LOGGED_OUT)
            throw new PermissionDeniedException();

        return user;
    }

    public static void signUp(String userName, String name, String family,
                       String email, String phoneNumber, String password, String passwordConfirm,
                       String country, int year, int month, int day)
            throws EmailOrPhoneRequiredException, PasswordConfirmException, EmailFormatException, PasswordFormatException,
            PasswordHashException, ServerConnectionFailedException, ServerRespondFailedException, ServerInvalidObjectException, DatabaseFailedException, CountryException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, UserNotFoundException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        if (email.equals("") && phoneNumber.equals(""))
            throw new EmailOrPhoneRequiredException();

        if (!password.equals(passwordConfirm))
            throw new PasswordConfirmException();

        User user = new User();
        user.setUserName(userName);
        user.setName(name);
        user.setFamily(family);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setCountry(country);
        user.setBirthDate(LocalDate.of(year, month, day));
        user.setSignUpDate(LocalDate.now());
        user.setLastChangeDate(LocalDate.now());

        ModelCommands.signUp(user);
    }

    public static User signIn(String userName, String password) throws PasswordHashException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        Password passwordHash = new Password(password);

        User user = ModelCommands.signIn(userName, passwordHash);

        Data.getInstance().setUser(user);
        Data.getInstance().setProgramState(ProgramState.MAIN_MENU);
        return user;
    }

    public static void signOut() throws PermissionDeniedException
    {
        getCurrentUser();
        Data.getInstance().setUser(null);
        Data.getInstance().setProgramState(ProgramState.LOGGED_OUT);
    }

    public static Avatar setAvatar(String path)
            throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        Avatar avatar = new Avatar(path);
        ModelCommands.setAvatar(user.getUserName(), avatar);

        user.setAvatar(new Avatar(path));
        return avatar;
    }

    public static Header setHeader(String path)
            throws ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        Header header = new Header(path);
        ModelCommands.setHeader(user.getUserName(), header);

        user.setHeader(new Header(path));
        return header;
    }

    public static void changePassword(String oldPassword, String newPassword, String newPasswordConfirm) throws PermissionDeniedException, PasswordFormatException, PasswordHashException, InvalidPasswordException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, PasswordConfirmException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        if (!user.getPassHash().equals(new Password(oldPassword)))
            throw new InvalidPasswordException();
        Verification.verifyPassword(newPassword);
        if (!newPassword.equals(newPasswordConfirm))
            throw new PasswordConfirmException();

        ModelCommands.changePassword(user.getUserName(), new Password(newPassword));

        user.setPassword(new Password(newPassword));
    }

    public static void changeName(String newName) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        ModelCommands.changeName(user.getUserName(), newName);

        user.setName(newName);
    }

    public static void changeFamily(String newFamily) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        ModelCommands.changeFamily(user.getUserName(), newFamily);

        user.setFamily(newFamily);
    }

    public static void changeEmail(String newEmail) throws PermissionDeniedException, EmailFormatException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Verification.verifyEmail(newEmail);

        ModelCommands.changeEmail(user.getUserName(), newEmail);

        user.setEmail(newEmail);
    }

    public static void changePhoneNumber(String newPhoneNumber) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        ModelCommands.changePhoneNumber(user.getUserName(), newPhoneNumber);

        user.setPhoneNumber(newPhoneNumber);
    }

    public static void changeBirthDate(int year, int month, int day) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        ModelCommands.changeBirthDate(user.getUserName(), LocalDate.of(year, month, day));

        user.setBirthDate(LocalDate.of(year, month, day));
    }

    public static void changeCountry(String newCountry) throws PermissionDeniedException, CountryException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Verification.verifyCountry(newCountry);

        ModelCommands.changeCountry(user.getUserName(), newCountry);

        user.setCountry(newCountry);
    }

    public static void changeBio(String newBio) throws PermissionDeniedException, TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Verification.verifyBio(newBio);

        ModelCommands.changeBio(user.getUserName(), new Bio(newBio));

        user.setBio(new Bio(newBio));
    }

    public static void changeLocation(String newLocation) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        ModelCommands.changeLocation(user.getUserName(), newLocation);

        user.setLocation(newLocation);
    }

    public static void changeWebsite(String newWebsite) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        ModelCommands.changeWebsite(user.getUserName(), newWebsite);

        user.setWebsite(newWebsite);
    }

//    public void changeUserInformation(String bioText, String location, String website) throws TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, PermissionDeniedException
//    {
//        User user = getCurrentUser();
//        Bio bio = new Bio(bioText);
//        modelCommands.changeUserInformation(user.getUserName(), bio, location, website);
//
//        user.setBio(bio);
//        user.setLocation(location);
//        user.setWebsite(website);
//    }

    public static Followers showFollowers(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        return ModelCommands.showFollowers(userName);
    }

    public static Followers showFollowers() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        return ModelCommands.showFollowers(user.getUserName());
    }

    public static Followings showFollowings(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        return ModelCommands.showFollowings(userName);
    }

    public static Followings showFollowings() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        return ModelCommands.showFollowings(user.getUserName());
    }

    public static void follow(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        // TODO : give the userName and followedUserName instead of FollowRelation
        User user = getCurrentUser();
        ModelCommands.follow(user.getUserName(), userName);
    }

    public static void unfollow(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        // TODO : give the userName and followedUserName instead of FollowRelation
        User user = getCurrentUser();
        ModelCommands.unfollow(user.getUserName(), userName);
    }

    public static MiniUser showUser(String username) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        return ModelCommands.showUser(username);
    }

    public static void sendTweet(String text, String imagePath) throws TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, PermissionDeniedException, ServerInvalidCommandException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, HashtagException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Tweet tweet;
        if (imagePath == null)
            tweet = new Tweet(user.toMiniUser(), new TextContent(text), null);
        else
            tweet = new Tweet(user.toMiniUser(), new TextContent(text), new ImageContent(imagePath));

        ModelCommands.sendTweet(tweet);
    }

    public static void sendRetweet(String tweetId) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        // Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);
        // Retweet retweet = new Retweet(tweet, user.toMiniUser());

        ModelCommands.sendRetweet(id, user.getUserName());
    }

    public static void sendQuote(String tweetId, String text, String imagePath) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        //Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        ImageContent imageContent = imagePath == null ? null : new ImageContent(imagePath);
        Quote quote = new Quote(null, user.toMiniUser(), new TextContent(text), imageContent);

        ModelCommands.sendQuote(id, quote);
    }

    public static void sendReply(String tweetId, String text) throws PermissionDeniedException, TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        Reply reply = new Reply(null, user.toMiniUser(), new TextContent(text));

        ModelCommands.sendReply(id, reply);
    }

    public static void likeTweet(String tweetId) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        ModelCommands.likeTweet(tweet, user.getUserName());
    }

    public static void dislikeTweet(String tweetId) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        ModelCommands.dislikeTweet(tweet, user.getUserName());
    }

    public static TimeLine showTimeLine() throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        TimeLine timeLine = ModelCommands.showTimeLine(user.getUserName());
        Data.getInstance().setTimeLine(timeLine);

        return timeLine;
    }

    public static TimeLine searchForHashtag(String hashtagNames) throws HashtagException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        Hashtags hashtags = new Hashtags(hashtagNames);
        TimeLine timeLine = showTimeLine();

        Iterator<BaseTweet> iterator = timeLine.iterator();
        while (iterator.hasNext())
        {
            BaseTweet tweet = iterator.next();
            if(!tweet.toTweet().getHashtags().contains(hashtags))
                iterator.remove();
        }

        return timeLine;
    }

    public static void block(String userName) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        ModelCommands.block(user.getUserName(), userName);
    }

    public static void unblock(String userName) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        ModelCommands.unblock(user.getUserName(), userName);
    }

    public static BlackList showBlackList() throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        return ModelCommands.showBlackList(user.getUserName());
    }
}
