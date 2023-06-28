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
    protected final ModelCommands modelCommands;

    public ControllerCommands()
    {
        modelCommands = new ModelCommands();
    }

    private User getCurrentUser() throws PermissionDeniedException
    {
        User user = Data.getInstance().getUser();
        if (user == null || Data.getInstance().getProgramState() == ProgramState.LOGGED_OUT)
            throw new PermissionDeniedException();

        return user;
    }

    public void signUp(String userName, String name, String family,
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

        modelCommands.signUp(user);
    }

    public User signIn(String userName, String password) throws PasswordHashException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        Password passwordHash = new Password(password);

        User user = modelCommands.signIn(userName, passwordHash);

        Data.getInstance().setUser(user);
        Data.getInstance().setProgramState(ProgramState.MAIN_MENU);
        return user;
    }

    public void signOut() throws PermissionDeniedException
    {
        getCurrentUser();
        Data.getInstance().setUser(null);
        Data.getInstance().setProgramState(ProgramState.LOGGED_OUT);
    }

    public Avatar setAvatar(String path)
            throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        Avatar avatar = new Avatar(path);
        modelCommands.setAvatar(user.getUserName(), avatar);

        user.setAvatar(new Avatar(path));
        return avatar;
    }

    public Header setHeader(String path)
            throws ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        Header header = new Header(path);
        modelCommands.setHeader(user.getUserName(), header);

        user.setHeader(new Header(path));
        return header;
    }

    public void changePassword(String oldPassword, String newPassword, String newPasswordConfirm) throws PermissionDeniedException, PasswordFormatException, PasswordHashException, InvalidPasswordException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, PasswordConfirmException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        if (!user.getPassHash().equals(new Password(oldPassword)))
            throw new InvalidPasswordException();
        Verification.verifyPassword(newPassword);
        if (!newPassword.equals(newPasswordConfirm))
            throw new PasswordConfirmException();

        modelCommands.changePassword(user.getUserName(), new Password(newPassword));

        user.setPassword(new Password(newPassword));
    }

    public void changeName(String newName) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        modelCommands.changeName(user.getUserName(), newName);

        user.setName(newName);
    }

    public void changeFamily(String newFamily) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        modelCommands.changeFamily(user.getUserName(), newFamily);

        user.setFamily(newFamily);
    }

    public void changeEmail(String newEmail) throws PermissionDeniedException, EmailFormatException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Verification.verifyEmail(newEmail);

        modelCommands.changeEmail(user.getUserName(), newEmail);

        user.setEmail(newEmail);
    }

    public void changePhoneNumber(String newPhoneNumber) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        modelCommands.changePhoneNumber(user.getUserName(), newPhoneNumber);

        user.setPhoneNumber(newPhoneNumber);
    }

    public void changeBirthDate(int year, int month, int day) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        modelCommands.changeBirthDate(user.getUserName(), LocalDate.of(year, month, day));

        user.setBirthDate(LocalDate.of(year, month, day));
    }

    public void changeCountry(String newCountry) throws PermissionDeniedException, CountryException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Verification.verifyCountry(newCountry);

        modelCommands.changeCountry(user.getUserName(), newCountry);

        user.setCountry(newCountry);
    }

    public void changeBio(String newBio) throws PermissionDeniedException, TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Verification.verifyBio(newBio);

        modelCommands.changeBio(user.getUserName(), new Bio(newBio));

        user.setBio(new Bio(newBio));
    }

    public void changeLocation(String newLocation) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        modelCommands.changeLocation(user.getUserName(), newLocation);

        user.setLocation(newLocation);
    }

    public void changeWebsite(String newWebsite) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        modelCommands.changeWebsite(user.getUserName(), newWebsite);

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

    public Followers showFollowers(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        return modelCommands.showFollowers(userName);
    }

    public Followers showFollowers() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        return modelCommands.showFollowers(user.getUserName());
    }

    public Followings showFollowings(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        return modelCommands.showFollowings(userName);
    }

    public Followings showFollowings() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        return modelCommands.showFollowings(user.getUserName());
    }

    public void follow(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        // TODO : give the userName and followedUserName instead of FollowRelation
        User user = getCurrentUser();
        modelCommands.follow(user.getUserName(), userName);
    }

    public void unfollow(String userName) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        // TODO : give the userName and followedUserName instead of FollowRelation
        User user = getCurrentUser();
        modelCommands.unfollow(user.getUserName(), userName);
    }

    public MiniUser showUser(MiniUser miniUser) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        return modelCommands.showUser(miniUser.getUserName());
    }

    public void sendTweet(String text, String imagePath) throws TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, UnknownException, InvalidPasswordException, PermissionDeniedException, ServerInvalidCommandException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, HashtagException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();

        Tweet tweet;
        if (imagePath == null)
            tweet = new Tweet(user.toMiniUser(), new TextContent(text), null);
        else
            tweet = new Tweet(user.toMiniUser(), new TextContent(text), new ImageContent(imagePath));

        modelCommands.sendTweet(tweet);
    }

    public void sendRetweet(String tweetId) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        // Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);
        // Retweet retweet = new Retweet(tweet, user.toMiniUser());

        modelCommands.sendRetweet(id, user.getUserName());
    }

    public void sendQuote(String tweetId, String text, String imagePath) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        //Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        ImageContent imageContent = imagePath == null ? null : new ImageContent(imagePath);
        Quote quote = new Quote(null, user.toMiniUser(), new TextContent(text), imageContent);

        modelCommands.sendQuote(id, quote);
    }

    public void sendReply(String tweetId, String text) throws PermissionDeniedException, TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        Reply reply = new Reply(null, user.toMiniUser(), new TextContent(text));

        modelCommands.sendReply(id, reply);
    }

    public void likeTweet(String tweetId) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        modelCommands.likeTweet(tweet, user.getUserName());
    }

    public void dislikeTweet(String tweetId) throws NumberFormatException, PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        long id = Long.parseLong(tweetId);
        Tweet tweet = Data.getInstance().getTimeLine().getTweet(id);

        modelCommands.dislikeTweet(tweet, user.getUserName());
    }

    public TimeLine showTimeLine() throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        TimeLine timeLine = modelCommands.showTimeLine(user.getUserName());
        Data.getInstance().setTimeLine(timeLine);

        return timeLine;
    }

    public TimeLine searchForHashtag(String hashtagNames) throws HashtagException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
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

    public void block(String userName) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        modelCommands.block(user.getUserName(), userName);
    }

    public void unblock(String userName) throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        modelCommands.unblock(user.getUserName(), userName);
    }

    public BlackList showBlackList() throws PermissionDeniedException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        User user = getCurrentUser();
        return modelCommands.showBlackList(user.getUserName());
    }
}
