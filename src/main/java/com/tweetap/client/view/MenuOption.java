package com.tweetap.client.view;

import com.tweetap.client.controller.Data;
import com.tweetap.client.view.TwitterLog;
import com.tweetap.entities.exception.hashtag.HashtagException;
import com.tweetap.entities.exception.user.EmailOrPhoneRequiredException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.PermissionDeniedException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.exception.user.password.PasswordConfirmException;
import com.tweetap.entities.exception.user.password.PasswordFormatException;
import com.tweetap.entities.exception.user.password.PasswordHashException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class MenuOption
{
    private final ArrayList<Option> options;
    private Option command;
    private boolean showNestedOptions = false;

    public MenuOption()
    {
        options = new ArrayList<>();
    }

    public void showOptions()
    {
        if(!showNestedOptions)
            switch (Data.getInstance().getProgramState())
            {
                case LOGGED_OUT -> setLoggedOutOptions();
                case MAIN_MENU -> setMainMenuOptions();
            }

        for(int i = 0; i < options.size(); i++)
        {
            TwitterLog.print((i + 1) + " : ");
            TwitterLog.println(options.get(i).toString());
        }
    }

    public void getCommand() throws IllegalArgumentException
    {
        String rawCommand = TwitterLog.nextLine("||>  ");

        int optionIndex;
        try
        {
            optionIndex = Integer.parseInt(rawCommand);
        }
        catch (NumberFormatException e)
        {
            TwitterLog.println("The given option should be a number!");
            throw new IllegalArgumentException();
        }

        if(optionIndex > options.size())
        {
            TwitterLog.println("sorry, we don't have such an option!");
            throw new IllegalArgumentException();
        }

        command = options.get(optionIndex - 1);
    }

    public void doCommand()
    {
        // TODO: check for different commands and call the proper function from Command.java
        Command commandObject = new Command();

        try
        {
            switch (command)
            {
                case BACK -> back();

                case SIGN_UP -> commandObject.signUp();
                case SIGN_IN -> commandObject.signIn();

                case SHOW_USER_INFO -> commandObject.showUserInfo();
                case CHANGE_USER_INFO -> setChangeUserInformationOptions();
                case CONTENT -> setContentOptions();
                case FOLLOWING_FOLLOWER -> setFollowingFollowerOptions();
                case SIGN_OUT -> commandObject.signOut();

                case SET_AVATAR -> commandObject.setAvatar();
                case SET_HEADER -> commandObject.setHeader();
                case CHANGE_PASSWORD -> commandObject.changePassword();
                case CHANGE_NAME -> commandObject.changeName();
                case CHANGE_FAMILY -> commandObject.changeFamily();
                case CHANGE_EMAIL -> commandObject.changeEmail();
                case CHANGE_PHONE_NUMBER -> commandObject.changePhoneNumber();
                case CHANGE_BIRTH_DATE -> commandObject.changeBirthDate();
                case CHANGE_COUNTRY -> commandObject.changeCountry();
                case CHANGE_BIO -> commandObject.changeBio();
                case CHANGE_LOCATION -> commandObject.changeLocation();
                case CHANGE_WEBSITE -> commandObject.changeWebsite();

                case SEND_TWEET -> commandObject.sendTweet();
                case SEND_RETWEET -> commandObject.sendRetweet();
                case SEND_QUOTE -> commandObject.sendQuote();
                case SEND_REPLY -> commandObject.sendReply();
                case LIKE_TWEET -> commandObject.likeTweet();
                case DISLIKE_TWEET -> commandObject.dislikeTweet();
                case SHOW_TIME_LINE -> commandObject.showTimeLine();
                case SEARCH_FOR_HASHTAG -> commandObject.searchForHashtag();

                case SHOW_MY_FOLLOWERS -> commandObject.showMyFollowers();
                case SHOW_MY_FOLLOWINGS -> commandObject.showMyFollowings();
                case SHOW_SOMEONE_FOLLOWERS -> commandObject.showSomeoneFollowers();
                case SHOW_SOMEONE_FOLLOWINGS -> commandObject.showSomeoneFollowings();
                case FOLLOW -> commandObject.follow();
                case UNFOLLOW -> commandObject.unfollow();
                case BLOCK -> commandObject.block();
                case UNBLOCK -> commandObject.unblock();
                case SHOW_BLACK_LIST -> commandObject.showBlackList();
                // TODO: show user

            }
        }
        catch (DateTimeParseException e)
        {
            TwitterLog.printlnError("Date format is not valid!");
        } catch (ServerConnectionFailedException e)
        {
            TwitterLog.printlnError("Server connection failed!");
        } catch (ServerRespondFailedException e)
        {
            TwitterLog.printlnError("Server respond failed!");
        } catch (DatabaseFailedException e)
        {
            TwitterLog.printlnError("Database failed!");
        } catch (ServerInvalidObjectException e)
        {
            TwitterLog.printlnError("Server got/sent invalid object!");
        } catch (PasswordHashException e)
        {
            TwitterLog.printlnError("Password hash failed!");
        } catch (PasswordConfirmException e)
        {
            TwitterLog.printlnError("Password confirm failed!");
        } catch (CountryException e)
        {
            TwitterLog.printlnError("Country is not valid!");
        } catch (EmailFormatException e)
        {
            TwitterLog.printlnError("Email format is not valid!");
        } catch (PasswordFormatException e)
        {
            TwitterLog.printlnError("Password format is not valid!");
        } catch (EmailOrPhoneRequiredException e)
        {
            TwitterLog.printlnError("Email or phone is required!");
        } catch (UnknownException e)
        {
            TwitterLog.printlnError("Unknown error!");
        } catch (InvalidPasswordException e)
        {
            TwitterLog.printlnError("Invalid password!");
        } catch (TextTooLongException e)
        {
            TwitterLog.printlnError("Text is too long!");
        } catch (PermissionDeniedException e)
        {
            TwitterLog.printlnError("Permission denied!");
        } catch (ImageSizeException e)
        {
            TwitterLog.printlnError("Image size is not valid!");
        } catch (FileNotExistException e)
        {
            TwitterLog.printlnError("File doesn't exist!");
        } catch (FileSizeException e)
        {
            TwitterLog.printlnError("File size is not valid!");
        } catch (FileNotImageException e)
        {
            TwitterLog.printlnError("File is not an image!");
        } catch (ServerInvalidCommandException e)
        {
            TwitterLog.printlnError("Server got invalid command!");
        } catch (NumberFormatException e)
        {
            TwitterLog.printlnError("The given option should be a number!");
        } catch (HashtagException e)
        {
            TwitterLog.printlnError("Something went wrong with the hashtag processing!");
        } catch (UserNotFoundException e)
        {
            TwitterLog.printlnError("User not found!");
        } catch (DuplicateLikeRequestException e)
        {
            TwitterLog.printlnError("You have already liked this tweet!");
        } catch (BlockRelationNotFoundException e)
        {
            TwitterLog.printlnError("The user is unblocked already!");
        } catch (LikeRelationNotFoundException e)
        {
            TwitterLog.printlnError("The user is not liked yet!");
        } catch (DuplicateUserNameException e)
        {
            TwitterLog.printlnError("This username is already taken!");
        } catch (DuplicateBlockRequestException e)
        {
            TwitterLog.printlnError("You have already blocked this user!");
        } catch (DuplicateFollowRequestException e)
        {
            TwitterLog.printlnError("You have already followed this user!");
        } catch (TweetNotFoundException e)
        {
            TwitterLog.printlnError("Tweet not found!");
        } catch (FollowRelationNotFoundException e)
        {
            TwitterLog.printlnError("The user is not followed yet!");
        }
    }

    private void setLoggedOutOptions()
    {
        options.clear();
        options.add(Option.SIGN_UP);
        options.add(Option.SIGN_IN);

        showNestedOptions = false;
    }

    private void setMainMenuOptions()
    {
        options.clear();
        options.add(Option.SHOW_USER_INFO);
        options.add(Option.CHANGE_USER_INFO);
        options.add(Option.CONTENT);
        options.add(Option.FOLLOWING_FOLLOWER);
        options.add(Option.SIGN_OUT);

        showNestedOptions = false;
    }

    private void setChangeUserInformationOptions()
    {
        options.clear();
        options.add(Option.SET_AVATAR);
        options.add(Option.SET_HEADER);
        options.add(Option.CHANGE_PASSWORD);
        options.add(Option.CHANGE_NAME);
        options.add(Option.CHANGE_FAMILY);
        options.add(Option.CHANGE_EMAIL);
        options.add(Option.CHANGE_PHONE_NUMBER);
        options.add(Option.CHANGE_BIRTH_DATE);
        options.add(Option.CHANGE_COUNTRY);
        options.add(Option.CHANGE_BIO);
        options.add(Option.CHANGE_LOCATION);
        options.add(Option.CHANGE_WEBSITE);

        options.add(Option.BACK);

        showNestedOptions = true;
    }

    private void setContentOptions()
    {
        options.clear();
        options.add(Option.SEND_TWEET);
        options.add(Option.SEND_RETWEET);
        options.add(Option.SEND_QUOTE);
        options.add(Option.SEND_REPLY);
        options.add(Option.LIKE_TWEET);
        options.add(Option.DISLIKE_TWEET);
        options.add(Option.SHOW_TIME_LINE);
        options.add(Option.SEARCH_FOR_HASHTAG);

        options.add(Option.BACK);

        showNestedOptions = true;
    }

    private void setFollowingFollowerOptions()
    {
        options.clear();
        options.add(Option.SHOW_MY_FOLLOWERS);
        options.add(Option.SHOW_MY_FOLLOWINGS);
        options.add(Option.SHOW_SOMEONE_FOLLOWERS);
        options.add(Option.SHOW_SOMEONE_FOLLOWINGS);
        options.add(Option.FOLLOW);
        options.add(Option.UNFOLLOW);
        options.add(Option.BLOCK);
        options.add(Option.UNBLOCK);
        options.add(Option.SHOW_BLACK_LIST);

        options.add(Option.BACK);

        showNestedOptions = true;
    }

    private void back()
    {
        showNestedOptions = false;
    }
}
