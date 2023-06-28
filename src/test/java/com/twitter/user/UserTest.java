package com.twitter.user;

import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.PasswordFormatException;
import com.tweetap.entities.exception.user.password.PasswordHashException;
import com.tweetap.entities.user.User;
import org.junit.jupiter.api.Test;

/**
 * @author Parsa Salamatipour
 */
class UserTest
{
    @Test
    void setEmailValidInputs()
    {
        User user = new User();

        String[] emails = {
                "sa.parsa20@gmail.com",
                "abc@hello.co",
                "hhhhhh@ap.ab"};

        for (String email : emails)
        {
            try
            {
                user.setEmail(email);
            } catch (EmailFormatException e)
            {
                System.err.println("email: " + email);
                assert (false);
            }
        }
    }

    @Test
    void setEmailInvalidInputs()
    {
        User user = new User();

        String[] emails = {
                "hello",
                "@.",
                "@a.a",
                "1.@",
                "@@.",
                ""};

        for (String email : emails)
        {
            try
            {
                user.setEmail(email);
                System.err.println("email: " + email);
                assert (false);
            } catch (EmailFormatException ignored)
            {
            }
        }
    }

    @Test
    void getPassHash()
    {
    }

    @Test
    void setPasswordValidInputs()
    {
        User user = new User();

        String[] passwords = {
                "AbcD123456@",
                "HeLow68@",
                "NaNoOOl;#329",
                "tTTTTttTTT777&&&77",
                ";wrelfgkj09OIHOI"};

        for (String password : passwords)
        {
            try
            {
                user.setPassword(password);
            } catch (PasswordFormatException | PasswordHashException e)
            {
                System.err.println("password: " + password);
                assert(false);
            }
        }
    }

    @Test
    void setPasswordInvalidInputs()
    {
        User user = new User();

        String[] passwords = {
                "",
                "A",
                "aaaaaaaaaa",
                "Aa67*",
                "11111111111aa;",
                "AbcDlkjhihOLJHG213134."};

        for (String password : passwords)
        {
            try
            {
                user.setPassword(password);
                System.err.println("password: " + password);
                assert (false);
            } catch (PasswordFormatException | PasswordHashException ignored)
            {
            }
        }
    }
}