package com.tweetap.client.view;

public enum Option
{
    BACK,

    // main menu
    SIGN_UP,
    SIGN_IN,
    SIGN_OUT,
    SHOW_USER_INFO,
    CHANGE_USER_INFO,
    CONTENT,
    FOLLOWING_FOLLOWER,

    // change user info
    SET_AVATAR,
    SET_HEADER,
    CHANGE_PASSWORD,
    CHANGE_NAME,
    CHANGE_FAMILY,
    CHANGE_EMAIL,
    CHANGE_PHONE_NUMBER,
    CHANGE_BIRTH_DATE,
    CHANGE_COUNTRY,
    CHANGE_BIO,
    CHANGE_LOCATION,
    CHANGE_WEBSITE,

    // content
    SEND_TWEET,
    SEND_RETWEET,
    SEND_QUOTE,
    SEND_REPLY,
    LIKE_TWEET,
    DISLIKE_TWEET,
    SHOW_TIME_LINE,
    SEARCH_FOR_HASHTAG,

    // following / follower
    SHOW_MY_FOLLOWERS,
    SHOW_MY_FOLLOWINGS,
    SHOW_SOMEONE_FOLLOWERS,
    SHOW_SOMEONE_FOLLOWINGS,
    FOLLOW,
    UNFOLLOW,
    BLOCK,
    UNBLOCK,
    SHOW_BLACK_LIST,
    ;


    @Override
    public String toString()
    {
        return switch (this)
        {
            case BACK -> "Back";
            case SIGN_IN -> "SignIn";
            case SIGN_UP -> "SignUP";
            case SIGN_OUT -> "SignOut";
            case SHOW_USER_INFO -> "Show my user information";
            case CHANGE_USER_INFO -> "Change my user information";
            case CONTENT -> "The contents menu";
            case FOLLOWING_FOLLOWER -> "The following/follower menu";

            case SET_AVATAR -> "Change my avatar";
            case SET_HEADER -> "Change my header";
            case CHANGE_PASSWORD -> "Change my password";
            case CHANGE_NAME -> "Change my name";
            case CHANGE_FAMILY -> "Change my family";
            case CHANGE_EMAIL -> "Change my email";
            case CHANGE_PHONE_NUMBER -> "Change my phone-number";
            case CHANGE_BIRTH_DATE -> "Change my birth-date";
            case CHANGE_COUNTRY -> "Change my country";
            case CHANGE_BIO -> "Change my bio";
            case CHANGE_LOCATION -> "Change my location";
            case CHANGE_WEBSITE -> "Change my website";

            case SEND_TWEET -> "Send a tweet";
            case SEND_RETWEET -> "Retweet a tweet";
            case SEND_QUOTE -> "Send a quote";
            case SEND_REPLY -> "Reply to a tweet";
            case LIKE_TWEET -> "Like a tweet";
            case DISLIKE_TWEET -> "Dislike a tweet";
            case SHOW_TIME_LINE -> "Show my timeline";
            case SEARCH_FOR_HASHTAG -> "Search for hashtag";

            case SHOW_MY_FOLLOWERS -> "Show my followers";
            case SHOW_MY_FOLLOWINGS -> "Show my followings";
            case SHOW_SOMEONE_FOLLOWERS -> "Show someone's followers";
            case SHOW_SOMEONE_FOLLOWINGS -> "Show someone's followings";
            case FOLLOW -> "Follow someone";
            case UNFOLLOW -> "Unfollow someone";
            case BLOCK -> "Block a user";
            case UNBLOCK -> "Unblock a user";
            case SHOW_BLACK_LIST -> "Show my black list";
        };
    }
}
