package com.tweetap.client.view;

import com.tweetap.client.controller.ControllerCommands;
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
import com.tweetap.entities.tweet.*;
import com.tweetap.entities.user.*;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Command
{
    private final ControllerCommands controllerCommands;

    public Command()
    {
        controllerCommands = new ControllerCommands();
    }

    public void signUp() throws DateTimeParseException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException, PasswordConfirmException, CountryException, EmailFormatException, PasswordFormatException, EmailOrPhoneRequiredException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, UserNotFoundException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String userName, name, family, email, phoneNumber, password, passwordConfirm, country;
        int year, month, day;

        userName = TwitterLog.nextLine("Username: ");
        name = TwitterLog.nextLine("Name: ");
        family = TwitterLog.nextLine("Family Name: ");
        email = TwitterLog.nextLine("Email: ");
        phoneNumber = TwitterLog.nextLine("Phone Number: ");
        password = TwitterLog.nextLine("Password: ");
        passwordConfirm = TwitterLog.nextLine("Confirm you password: ");
        TwitterLog.println("Country List:");
        TwitterLog.println(Country.getInstance().toString());
        country = TwitterLog.nextLine("Country: ");

        String date = TwitterLog.nextLine("Birth Date: (yyyy-mm-dd) ");
        LocalDate localDate = LocalDate.parse(date);

        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();


        controllerCommands.signUp(userName, name, family, email, phoneNumber, password, passwordConfirm, country, year, month, day);

        TwitterLog.println("You successfully signed up as : " + name + " " + family + ".");
    }

    public void signIn() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String username, password;

        username = TwitterLog.nextLine("Username: ");
        password = TwitterLog.nextLine("Password: ");


        User user = controllerCommands.signIn(username, password);

        TwitterLog.println("You successfully logged in as : " + user.getName() + " " + user.getFamily() + ".");
    }

    public void showUserInfo()
    {
        User user = Data.getInstance().getUser();

        TwitterLog.println("Username: " + user.getUserName());
        TwitterLog.println("Name: " + user.getName());
        TwitterLog.println("Family: " + user.getFamily());
        TwitterLog.println("Email: " + user.getEmail());
        TwitterLog.println("Phone Number: " + user.getPhoneNumber());
        TwitterLog.println("Country: " + user.getCountry());
        TwitterLog.println("Birth Date: " + user.getBirthDate());

        if (user.getBio() != null && !user.getBio().toString().equals(""))
            TwitterLog.println("Bio: " + user.getBio());

        if (user.getLocation() != null && !user.getLocation().equals(""))
            TwitterLog.println("Location: " + user.getLocation());
    }

    public void signOut() throws PermissionDeniedException
    {
        controllerCommands.signOut();
    }

    public void setAvatar() throws ServerConnectionFailedException, ServerRespondFailedException, FileNotExistException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, FileSizeException, PermissionDeniedException, TextTooLongException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
        controllerCommands.setAvatar(path);

        TwitterLog.println("Your avatar successfully changed.");
    }

    public void setHeader() throws ServerConnectionFailedException, ServerRespondFailedException, FileNotExistException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, FileSizeException, PermissionDeniedException, TextTooLongException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
        controllerCommands.setHeader(path);

        TwitterLog.println("Your header successfully changed.");
    }

    public void changePassword() throws ServerConnectionFailedException, PasswordConfirmException, PasswordFormatException, InvalidPasswordException, ServerRespondFailedException, UnknownException, PermissionDeniedException, PasswordHashException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String oldPassword, newPassword, newPasswordConfirm;
        oldPassword = TwitterLog.nextLine("Enter your old password: ");
        newPassword = TwitterLog.nextLine("Enter your new password: ");
        newPasswordConfirm = TwitterLog.nextLine("Confirm your new password: ");

        controllerCommands.changePassword(oldPassword, newPassword, newPasswordConfirm);

        TwitterLog.println("Your password successfully changed.");
    }

    public void changeName() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String name = TwitterLog.nextLine("Enter your new name: ");
        controllerCommands.changeName(name);

        TwitterLog.println("Your name successfully changed to " + name + ".");
    }

    public void changeFamily() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String family = TwitterLog.nextLine("Enter your new family name: ");
        controllerCommands.changeFamily(family);

        TwitterLog.println("Your family name successfully changed to " + family + ".");
    }

    public void changeEmail() throws ServerConnectionFailedException, EmailFormatException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String email = TwitterLog.nextLine("Enter your new email: ");
        controllerCommands.changeEmail(email);

        TwitterLog.println("Your email successfully changed to " + email + ".");
    }

    public void changePhoneNumber() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String phoneNumber = TwitterLog.nextLine("Enter your new phone-number: ");
        controllerCommands.changePhoneNumber(phoneNumber);

        TwitterLog.println("Your phone-number successfully changed to " + phoneNumber + ".");
    }

    public void changeBirthDate() throws DateTimeParseException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String date = TwitterLog.nextLine("Enter your new birth-date: (yyyy-mm-dd) ");
        LocalDate localDate = LocalDate.parse(date);

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        controllerCommands.changeBirthDate(year, month, day);

        TwitterLog.println("Your birth-date successfully changed to " + date + ".");
    }

    public void changeCountry() throws CountryException, ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        TwitterLog.println(Country.getInstance().toString());
        String country = TwitterLog.nextLine("Enter your new country: ");
        controllerCommands.changeCountry(country);

        TwitterLog.println("Your country successfully changed to " + country + ".");
    }

    public void changeBio() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String bio = TwitterLog.nextLine("Enter your new bio: ");
        controllerCommands.changeBio(bio);

        TwitterLog.println("Your bio successfully changed.");
    }

    public void changeLocation() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String location = TwitterLog.nextLine("Enter your new location: ");
        controllerCommands.changeLocation(location);

        TwitterLog.println("Your location successfully changed.");
    }

    public void changeWebsite() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String website = TwitterLog.nextLine("Enter your new website: ");
        controllerCommands.changeWebsite(website);

        TwitterLog.println("Your website successfully changed to " + website + ".");
    }

    public void sendTweet() throws ServerConnectionFailedException, ServerRespondFailedException, FileNotExistException, ServerInvalidCommandException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, PermissionDeniedException, FileSizeException, TextTooLongException, ServerInvalidObjectException, HashtagException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String text = TwitterLog.nextLine("Enter your tweet text: ");
        String answer = TwitterLog.nextLine("Do you want your tweet to have a picture? (y/n): ");
        if (answer.equalsIgnoreCase("y"))
        {
            String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
            controllerCommands.sendTweet(text, path);
        } else if (answer.equalsIgnoreCase("n"))
            controllerCommands.sendTweet(text, null);
        else
            TwitterLog.printlnError("Invalid input!");

        TwitterLog.println("Your tweet successfully sent.");
    }

    public void sendRetweet() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        controllerCommands.sendRetweet(tweetId);

        TwitterLog.println("Your retweet successfully sent.");
    }

    public void sendQuote() throws ServerConnectionFailedException, ServerRespondFailedException, FileNotExistException, ServerInvalidCommandException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, PermissionDeniedException, FileSizeException, TextTooLongException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        String text = TwitterLog.nextLine("Enter your quote text: ");
        String answer = TwitterLog.nextLine("Do you want your quote to have a picture? (y/n): ");
        if (answer.equalsIgnoreCase("y"))
        {
            String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
            controllerCommands.sendQuote(tweetId, text, path);
        } else if (answer.equalsIgnoreCase("n"))
            controllerCommands.sendQuote(tweetId, text, null);
        else
            TwitterLog.printlnError("Invalid input!");

        TwitterLog.println("Your quote successfully sent.");
    }

    public void sendReply() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        String text = TwitterLog.nextLine("Enter your reply text: ");
        controllerCommands.sendReply(tweetId, text);

        TwitterLog.println("Your reply successfully sent.");
    }

    public void likeTweet() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        controllerCommands.likeTweet(tweetId);

        TwitterLog.println("The tweet successfully liked.");
    }

    public void dislikeTweet() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        controllerCommands.dislikeTweet(tweetId);

        TwitterLog.println("The tweet successfully disliked.");
    }

    public void showTimeLine() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        TimeLine timeLine = controllerCommands.showTimeLine();
        printTimeLine(timeLine);
    }

    private static void printTimeLine(TimeLine timeLine) throws UnknownException
    {
        if(timeLine.size() == 0)
        {
            TwitterLog.println("Your timeline is empty.");
            return;
        }
        for (BaseTweet baseTweet : timeLine)
        {
            printTweet(baseTweet);

            if(baseTweet.toTweet().getReplies().size() != 0)
            {
                TwitterLog.println("Replies:");
                for (Reply reply : baseTweet.toTweet().getReplies())
                {
                    printReply(reply);
                    TwitterLog.println("#  #  #  #  #  #  #  #  #  #  #  #  #  #  #");
                }
            }
        }
        TwitterLog.println("_________________________________");
    }

    private static void printTweet(BaseTweet baseTweet) throws UnknownException
    {
        if(baseTweet instanceof Quote)
        {
            printQuote((Quote) baseTweet);
            return;
        }

        Tweet tweet = baseTweet.toTweet();

        TwitterLog.println("_________________________________");
        if(baseTweet instanceof Retweet)
            TwitterLog.println(" -- > Retweeted by " + baseTweet.getOwner().getName() + " " + baseTweet.getOwner().getFamily() + " < --");
        // if the baseTweet is a retweet, the owner of the baseTweet is the reTweeter
        TwitterLog.println(tweet.getOwner().getName() + " " + tweet.getOwner().getFamily());
        TwitterLog.printlnNested(tweet.getTextContent().toString());
        TwitterLog.println("Likes: " + tweet.getLikeCount());
        TwitterLog.println("Retweets: " + tweet.getRetweetCount());
        TwitterLog.println("Quotes: " + tweet.getQuoteCount());
        TwitterLog.println("Date: " + tweet.getDate().toString());
        TwitterLog.println("Tweet id: " + tweet.getId());
        if(tweet.isFavstar())
            TwitterLog.println(" --- F --- A --- V --- S --- T --- A --- R ---");
    }

    private static void printQuote(Quote quote) throws UnknownException
    {
        TwitterLog.println("_________________________________");
        TwitterLog.println("\n" + quote.getOwner().getName() + " " + quote.getOwner().getFamily());
        TwitterLog.printlnNested(quote.getTextContent().toString());
        TwitterLog.println(quote.getDate().toString());
        TwitterLog.println("Quote id: " + quote.getId());

        TwitterLog.println("---|");
        TwitterLog.startNest();

        printTweet(quote.getTweet());

        TwitterLog.endNest();
        TwitterLog.println("---|");
    }

    private static void printReply(Reply reply)
    {
        TwitterLog.startNest();
        TwitterLog.println("\n" + reply.getReplier().getName() + " " + reply.getReplier().getFamily());
        TwitterLog.printlnNested(reply.getTextContent().toString());
        TwitterLog.println(reply.getDate().toString());
        TwitterLog.println("Reply id: " + reply.getId());
        TwitterLog.endNest();
    }

    public void searchForHashtag() throws ServerConnectionFailedException, HashtagException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String hashtagNames = TwitterLog.nextLine("Enter the hashtags: ");
        TimeLine timeLine = controllerCommands.searchForHashtag(hashtagNames);
        printTimeLine(timeLine);
    }

    public void showMyFollowers() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        Followers followers = controllerCommands.showFollowers();
        if(followers.size() == 0)
            TwitterLog.println("You have no follower.");
        else for(MiniUser miniUser : followers)
            TwitterLog.println(miniUser.getName() + " " + miniUser.getFamily());
    }

    public void showMyFollowings() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        Followings followings = controllerCommands.showFollowings();
        if(followings.size() == 0)
            TwitterLog.println("You haven't followed anyone.");
        else for(MiniUser miniUser : followings)
            TwitterLog.println(miniUser.getName() + " " + miniUser.getFamily());
    }

    public void showSomeoneFollowers() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String username = TwitterLog.nextLine("Enter the username: ");
        Followers followers = controllerCommands.showFollowers(username);
        if(followers.size() == 0)
            TwitterLog.println("The user doesn't have any followers.");
        else for(MiniUser miniUser : followers)
            TwitterLog.println(miniUser.getName() + " " + miniUser.getFamily());
    }

    public void showSomeoneFollowings() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String username = TwitterLog.nextLine("Enter the username: ");
        Followings followings = controllerCommands.showFollowings(username);
        if(followings.size() == 0)
            TwitterLog.println("No one follows the user.");
        else for(MiniUser miniUser : followings)
            TwitterLog.println(miniUser.getName() + " " + miniUser.getFamily());
    }

    public void follow() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String username = TwitterLog.nextLine("Enter the username you want to follow: ");
        controllerCommands.follow(username);
        TwitterLog.println("You successfully followed the user!");
    }

    public void unfollow() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String username = TwitterLog.nextLine("Enter the username you want to unfollow: ");
        controllerCommands.unfollow(username);
        TwitterLog.println("You successfully unfollowed the user!");
    }

    public void block() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String userName = TwitterLog.nextLine("Enter the username you want to block: ");
        controllerCommands.block(userName);
        TwitterLog.println("The user was successfully blocked.");
    }

    public void unblock() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        String userName = TwitterLog.nextLine("Enter the username you want to unblock: ");
        controllerCommands.unblock(userName);
        TwitterLog.println("The user was successfully unblocked.");
    }

    public void showBlackList() throws ServerConnectionFailedException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException, CountryException, UserNotFoundException, EmailFormatException, DuplicateLikeRequestException, BlockRelationNotFoundException, LikeRelationNotFoundException, DuplicateUserNameException, DuplicateBlockRequestException, DuplicateFollowRequestException, TweetNotFoundException, FollowRelationNotFoundException
    {
        BlackList blackList = controllerCommands.showBlackList();
        if(blackList.size() == 0)
            TwitterLog.println("You have no blocked user.");
        else for(MiniUser miniUser : blackList)
            TwitterLog.println(miniUser.getName() + " " + miniUser.getFamily());
    }
}
