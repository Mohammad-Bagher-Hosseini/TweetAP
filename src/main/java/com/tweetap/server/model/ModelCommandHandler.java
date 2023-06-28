package com.tweetap.server.model;

import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.server.Respond;

import java.io.ObjectInputStream;

public class ModelCommandHandler
{
    private String command;
    private final ModelCommands modelCommands;

    public ModelCommandHandler()
    {
        modelCommands = new ModelCommands();
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public Respond runCommand(ObjectInputStream objectInputStream)
    {
        try
        {
            return switch (command)
            {
                case "sign-up" -> modelCommands.signUp(objectInputStream);
                case "sign-in" -> modelCommands.signIn(objectInputStream);
                case "set-avatar" -> modelCommands.setAvatar(objectInputStream);
                case "set-header" -> modelCommands.setHeader(objectInputStream);

                //case "change-user-information" -> modelCommands.changeUserInformation(objectInputStream);
                case "change-password" -> modelCommands.changePassword(objectInputStream);
                case "change-name" -> modelCommands.changeName(objectInputStream);
                case "change-family" -> modelCommands.changeFamily(objectInputStream);
                case "change-email" -> modelCommands.changeEmail(objectInputStream);
                case "change-phone-number" -> modelCommands.changePhoneNumber(objectInputStream);
                case "change-birth-date" -> modelCommands.changeBirthDate(objectInputStream);
                case "change-country" -> modelCommands.changeCountry(objectInputStream);
                case "change-bio" -> modelCommands.changeBio(objectInputStream);
                case "change-location" -> modelCommands.changeLocation(objectInputStream);
                case "change-website" -> modelCommands.changeWebsite(objectInputStream);

                case "show-followers" -> modelCommands.showFollowers(objectInputStream);
                case "show-followings" -> modelCommands.showFollowings(objectInputStream);
                case "follow" -> modelCommands.follow(objectInputStream);
                case "unfollow" -> modelCommands.unfollow(objectInputStream);
                case "show-user" -> modelCommands.showUser(objectInputStream);
                case "block" -> modelCommands.block(objectInputStream);
                case "unblock" -> modelCommands.unblock(objectInputStream);
                case "show-black-list" -> modelCommands.showBlackList(objectInputStream);

                case "send-tweet" -> modelCommands.sendTweet(objectInputStream);
                case "send-retweet" -> modelCommands.sendRetweet(objectInputStream);
                case "send-quote" -> modelCommands.sendQuote(objectInputStream);
                case "send-reply" -> modelCommands.sendReply(objectInputStream);
                case "like-tweet" -> modelCommands.likeTweet(objectInputStream);
                case "dislike-tweet" -> modelCommands.dislikeTweet(objectInputStream);
                case "show-time-line" -> modelCommands.showTimeLine(objectInputStream);


                default -> new Respond(new ServerInvalidCommandException());
            };
        }
        catch (ServerInvalidObjectException | InvalidPasswordException | CountryException | EmailFormatException |
               TextTooLongException | UnknownException | DuplicateUserNameException | DuplicateLikeRequestException |
               UserNotFoundException | DuplicateFollowRequestException | FollowRelationNotFoundException |
               DuplicateBlockRequestException | BlockRelationNotFoundException | TweetNotFoundException |
               LikeRelationNotFoundException e)
        {
            return new Respond(e);
        }

    }
}
