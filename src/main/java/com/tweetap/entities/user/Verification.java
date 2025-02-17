package com.tweetap.entities.user;

import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.PasswordFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification
{
    public static void verifyEmail(String email) throws EmailFormatException
    {
        String regex1 = "^(.+)@(.+)$";
        String regex2 = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches())
            throw new EmailFormatException("The given email format is incorrect");
    }

    public static boolean isEmailValid(String email)
    {
        try
        {
            verifyEmail(email);
            return true;
        } catch (EmailFormatException e)
        {
            return false;
        }
    }

    public static void verifyPassword(String password) throws PasswordFormatException
    {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&*+=;:?<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if(!matcher.matches())
            throw new PasswordFormatException("The given password format is incorrect");
    }

    public static boolean isPasswordValid(String password)
    {
        try
        {
            verifyPassword(password);
            return true;
        } catch (PasswordFormatException e)
        {
            return false;
        }
    }

    public static void verifyCountry(String country) throws CountryException
    {
        Country.getInstance().validateCountry(country);
    }

    public static boolean isCountryValid(String country)
    {
        try
        {
            verifyCountry(country);
            return true;
        } catch (CountryException e)
        {
            return false;
        }
    }

    public static void verifyBio(String bio) throws TextTooLongException
    {
        new Bio(bio);
    }

    public static boolean isBioValid(String bio)
    {
        try
        {
            verifyBio(bio);
            return true;
        } catch (TextTooLongException e)
        {
            return false;
        }
    }
}
