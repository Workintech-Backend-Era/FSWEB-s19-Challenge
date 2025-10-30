package com.workintech.FSWEB_s19_Challenge.like.model;

import java.time.ZonedDateTime;

import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comment_like", schema="tweeter",
                                uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "comment_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "comment_id", nullable = false)
    private Comment comment;

    @Column(name = "created_at")
    private ZonedDateTime createdAt = ZonedDateTime.now();


}
