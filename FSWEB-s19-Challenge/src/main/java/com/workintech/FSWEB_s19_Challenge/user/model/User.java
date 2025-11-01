package com.workintech.FSWEB_s19_Challenge.user.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.like.model.CommentLike;
import com.workintech.FSWEB_s19_Challenge.like.model.TweetLike;
import com.workintech.FSWEB_s19_Challenge.retweet.model.Retweet;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user", schema="tweeter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", unique = true, length=50)
    private String username;

    @Column(name="email", unique=true, length=100)
    private String email;

    @Column(name="password", unique=true, length=255)
    private String password;

    @Column(name="bio")
    private String bio;

    @Column(name="created_at")
    private ZonedDateTime createdAt = ZonedDateTime.now();

    //Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tweet> tweets = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<TweetLike> tweetLikes = new HashSet<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<CommentLike> commentLikes = new HashSet<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Retweet> retweets = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "followers",
        schema = "tweeter",
        joinColumns = @JoinColumn(name = "following_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set<User> followings = new HashSet<>();

    //User ve Spring security den devam edeceğim

}
