package com.tweetap.entities.user;

import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.PasswordFormatException;
import com.tweetap.entities.exception.user.password.PasswordHashException;
import com.tweetap.entities.image.Avatar;
import com.tweetap.entities.image.Header;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
(
    name = "users" ,
    uniqueConstraints =
    {
            @UniqueConstraint(columnNames = "username")
    }
)
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    protected String userName;
    @Embedded
    protected Password passHash;
    protected String name;
    protected String family;
    protected String email;
    protected String phoneNumber;
    protected String country;
    protected LocalDate birthDate;
    protected LocalDate signUpDate;
    protected LocalDate lastChangeDate;
    @OneToOne
    protected Avatar avatar;
    @OneToOne
    protected Header header;
    @Embedded
    protected Bio bio;
    protected String location;
    protected String website;

    public User()
    {
        this.userName = null;
        this.passHash = null;
        this.name = "";
        this.family = null;
        this.country = "";
        this.email = null;
        this.phoneNumber = null;
        this.signUpDate = null;
        this.birthDate = null;
        this.lastChangeDate = null;
        this.avatar = null;
        this.header = null;
        this.bio = new Bio();
        this.location = "";
        this.website = "";
    }

    public String getName()
    {
        return name;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFamily()
    {
        return family;
    }

    public void setFamily(String family)
    {
        this.family = family;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) throws EmailFormatException
    {
        Verification.verifyEmail(email);
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Password getPassHash()
    {
        return passHash;
    }

    public void setPassword(String password) throws PasswordFormatException, PasswordHashException
    {
        Verification.verifyPassword(password);
        this.passHash = new Password(password);
    }

    public void setPassword(Password password)
    {
        this.passHash = password;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country) throws CountryException
    {
        Country.getInstance().validateCountry(country);
        this.country = country;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public LocalDate getSignUpDate()
    {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate)
    {
        this.signUpDate = signUpDate;
    }

    public LocalDate getLastChangeDate()
    {
        return lastChangeDate;
    }

    public Long getId(){return this.id;}

    public void setLastChangeDate(LocalDate lastChangeDate)
    {
        this.lastChangeDate = lastChangeDate;
    }

    public Avatar getAvatar()
    {
        return avatar;
    }

    public void setAvatar(Avatar avatar)
    {
        this.avatar = avatar;
    }

    public Header getHeader()
    {
        return header;
    }

    public void setHeader(Header header)
    {
        this.header = header;
    }

    public Bio getBio()
    {
        return bio;
    }

    public void setBio(Bio bio) throws TextTooLongException
    {
        this.bio = bio;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public MiniUser toMiniUser()
    {
        return new MiniUser(this);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userName, passHash, name, family, email, phoneNumber, country, birthDate, signUpDate, lastChangeDate);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(passHash, user.passHash) &&
                Objects.equals(name, user.name) &&
                Objects.equals(family, user.family) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(country, user.country) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(signUpDate, user.signUpDate) &&
                Objects.equals(lastChangeDate, user.lastChangeDate);
    }

    @Override
    public String toString()
    {
        return "username: " + userName + " " +
                "Full name: " + name + " " + family;
    }
}
