package com.twitter.server.model;

import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.hashtag.HashtagException;
import com.tweetap.entities.exception.io.FileNotExistException;
import com.tweetap.entities.exception.io.FileNotImageException;
import com.tweetap.entities.exception.io.FileSizeException;
import com.tweetap.entities.exception.io.ImageSizeException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.exception.user.password.PasswordFormatException;
import com.tweetap.entities.exception.user.password.PasswordHashException;
import com.tweetap.entities.image.Avatar;
import com.tweetap.entities.image.Header;
import com.tweetap.entities.tweet.Retweet;
import com.tweetap.entities.tweet.TimeLine;
import com.tweetap.entities.tweet.Tweet;
import com.tweetap.entities.tweet.content.TextContent;
import com.tweetap.entities.user.MiniUser;
import com.tweetap.entities.user.Password;
import com.tweetap.entities.user.User;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;
import com.tweetap.server.model.DatabaseCommands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class DatabaseCommandsTest
{
    public static final String TEST_USER_NAME_1 = "parsa2201";
    private static final String TEST_USER_NAME_2 = "hehehe";

    private static final String TEST_PASSWORD_1 = "NaNoOOl;#329";
    private static final String TEST_PASSWORD_2 = "NaNoOOl;#329";

    private static final String CHANGED_PASSWORD = "1234!@#$abAB";
    User makeUser1() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
    {
        User user = new User();
        user.setUserName(TEST_USER_NAME_1);
        user.setPassword(TEST_PASSWORD_1);
        user.setName("HIUG");
        user.setFamily("Salamatipour");
        user.setEmail("sa.parsa20@email.com");
        user.setPhoneNumber("09124978928");
        user.setCountry("Iran");
        user.setBirthDate(LocalDate.of(2003, 11, 3));

        return user;
    }

    User makeUser2() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
    {
        User user = new User();
        user.setUserName(TEST_USER_NAME_2);
        user.setPassword(TEST_PASSWORD_2);
        user.setName("he");
        user.setFamily("hehe");
        user.setEmail("he.hehe20@hemail.hem");
        user.setPhoneNumber("091111111");
        user.setCountry("Zambia");
        user.setBirthDate(LocalDate.of(2096, 5, 29));

        return user;
    }

    @Test
    void signUp1() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DuplicateUserNameException
    {
        User user = makeUser1();

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.signUp(user);
    }

    @Test
    void signUp2() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DuplicateUserNameException
    {
        User user = makeUser2();

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.signUp(user);
    }

    @Test
    void signIn() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, InvalidPasswordException, IOException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        User user = databaseCommands.signIn(TEST_USER_NAME_1, new Password(TEST_PASSWORD_1));
        Assertions.assertEquals(user, makeUser1());

        Avatar avatar = user.getAvatar();
        BufferedImage bufferedImage = avatar.getImage();
        Assertions.assertNotNull(bufferedImage);
        ImageIO.write(bufferedImage, "jpg", new File("Parsa Salamatipour 400X400 - from database.jpg"));

        Header header = user.getHeader();
        bufferedImage = header.getImage();
        Assertions.assertNotNull(bufferedImage);
        ImageIO.write(bufferedImage, "jpg", new File("Parsa Salamatipour 1500X500 - from database.jpg"));

    }

    @Test
    void setAvatar() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.setAvatar(TEST_USER_NAME_1, new Avatar("src/main/java/assets/Parsa Salamatipour 400X400.jpg"));
    }

    @Test
    void setHeader() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        Header header = new Header("src/main/java/assets/Parsa Salamatipour 1500X500.jpg");
        databaseCommands.setHeader(TEST_USER_NAME_1, header);
    }

    @Test
    void changeUserPassword() throws PasswordHashException, DataNotFoundException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeUserPassword(TEST_USER_NAME_1, new Password(CHANGED_PASSWORD));
        databaseCommands.signIn(TEST_USER_NAME_1, new Password(CHANGED_PASSWORD));
    }

    @Test
    void changeName() throws DataNotFoundException, PasswordHashException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeName(TEST_USER_NAME_1, "Mammad");
        User user = databaseCommands.signIn(TEST_USER_NAME_1, new Password(CHANGED_PASSWORD));
        Assertions.assertEquals(user.getName(), "Mammad");
    }

    @Test
    void changeFamily() throws DataNotFoundException, PasswordHashException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeFamily(TEST_USER_NAME_1, "Mammadi");
        User user = databaseCommands.signIn(TEST_USER_NAME_1, new Password(CHANGED_PASSWORD));
        Assertions.assertEquals(user.getFamily(), "Mammadi");
    }

    // TODO: changeEmail()

    // TODO: changePhone()

    // TODO: changeBirthDate()

    // TODO: changeCounty()

    // TODO: changeLocation()

    // TODO: changeWebsite()

    // TODO: changeBio()

    // TODO: showFollowers()

    // TODO: showFollowings()

    // TODO: follow()

    // TODO: unfollow()

    // TODO: showUser()

    @Test
    void sendTweet1() throws TextTooLongException, HashtagException
    {
        // FIXME
        MiniUser miniUser = new MiniUser();
        miniUser.setUserName(TEST_USER_NAME_1);

        Tweet tweet = new Tweet(miniUser, new TextContent("this is a test text"), null);

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.sendTweet(tweet);
    }

    @Test
    void sendTweet2() throws TextTooLongException, HashtagException
    {
        // FIXME
        MiniUser miniUser = new MiniUser();
        miniUser.setUserName(TEST_USER_NAME_1);

        Tweet tweet = new Tweet(miniUser, new TextContent("this is another test text"), null);

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.sendTweet(tweet);
    }

    @Test
    void sendRetweet() throws HashtagException, TextTooLongException, DataNotFoundException
    {
        // FIXME
        MiniUser miniUser2 = new MiniUser();
        miniUser2.setUserName(TEST_USER_NAME_2);

        MiniUser miniUser1 = new MiniUser();
        miniUser1.setUserName(TEST_USER_NAME_1);

        Tweet tweet = new Tweet(miniUser1, new TextContent("this is a test text"), null);

        Retweet retweet = new Retweet(tweet, miniUser2);

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.sendRetweet(retweet.getTweet().getId(), retweet.getUserName());
    }

    // TODO: sendQuote()

    @Test
    void likeTweet() throws DataNotFoundException, DuplicateLikeRequestException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        Tweet tweet = databaseCommands.findTweet(1L);
        int likes = tweet.getLikeCount();
        databaseCommands.likeTweet(tweet, TEST_USER_NAME_1);
        tweet = databaseCommands.findTweet(1L);
        Assertions.assertEquals(tweet.getLikeCount(), likes + 1);
    }

    // TODO: dislikeTweet()

    @Test
    void showTimeLine() throws UserNotFoundException, UnknownException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        TimeLine timeLine = databaseCommands.showTimeLine(TEST_USER_NAME_1);
        int notImportant = 5; // add breakpoint here
    }

    @Test
    void block() throws DataNotFoundException, DuplicateBlockRequestException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.block(TEST_USER_NAME_1, TEST_USER_NAME_2);

        try
        {
            Followers followers = databaseCommands.showFollowers(TEST_USER_NAME_1);
            for(MiniUser miniUser : followers)
                Assertions.assertEquals(miniUser.getUserName(),TEST_USER_NAME_2);

            Followings followings = databaseCommands.showFollowings(TEST_USER_NAME_2);
            for(MiniUser miniUser : followings)
                Assertions.assertEquals(miniUser.getUserName(),TEST_USER_NAME_1);
        }
        catch (DataNotFoundException ignored){}
    }

    // TODO: unblock()

    // TODO: showBlackList()
}
