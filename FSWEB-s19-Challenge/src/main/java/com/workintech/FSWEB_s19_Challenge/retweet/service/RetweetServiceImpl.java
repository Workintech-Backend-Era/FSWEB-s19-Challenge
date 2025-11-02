package com.workintech.FSWEB_s19_Challenge.retweet.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workintech.FSWEB_s19_Challenge.retweet.dto.RetweetRequest;
import com.workintech.FSWEB_s19_Challenge.retweet.dto.RetweetResponse;
import com.workintech.FSWEB_s19_Challenge.retweet.mapper.RetweetMapper;
import com.workintech.FSWEB_s19_Challenge.retweet.model.Retweet;
import com.workintech.FSWEB_s19_Challenge.retweet.repository.RetweetRepository;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.tweet.repository.TweetRepository;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final TweetRepository tweetRepository;

    @Override
    public RetweetResponse createRetweet(User user, RetweetRequest request) {
        Tweet tweet = tweetRepository.findById(request.tweetId())
                .orElseThrow(() -> new RuntimeException("Tweet not found."));

        retweetRepository.findByUserAndOriginalTweet(user, tweet)
                .ifPresent(r -> { throw new RuntimeException("Already retweeted this tweet."); });

        Retweet retweet = Retweet.builder()
                .user(user)
                .originalTweet(tweet)
                .comment(request.comment())
                .createdAt(ZonedDateTime.now())
                .build();

        return RetweetMapper.toResponse(retweetRepository.save(retweet));
    }

    @Override
    public List<RetweetResponse> getAllByUser(User user) {
        return retweetRepository.findAllByUser(user)
                .stream()
                .map(RetweetMapper::toResponse)
                .collect(Collectors.toList());
    }



    @Override
    public RetweetResponse replaceRetweet(Long id, RetweetRequest request, User user) {
        Retweet retweet = retweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retweet not found."));
        validateOwnership(retweet, user);

        retweet.setComment(request.comment());
        return RetweetMapper.toResponse(retweetRepository.save(retweet));
    }


    @Override
    public RetweetResponse patchRetweet(Long id, RetweetRequest request, User user) {
        Retweet retweet = retweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retweet not found."));
        validateOwnership(retweet, user);

        if (request.comment() != null) {
            retweet.setComment(request.comment());
        }

        return RetweetMapper.toResponse(retweetRepository.save(retweet));
    }

    @Override
    public void deleteRetweet(Long id, User user) {
        Retweet retweet = retweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retweet not found."));
        validateOwnership(retweet, user);
        retweetRepository.delete(retweet);
    }

    private void validateOwnership(Retweet retweet, User user) {
        if (!retweet.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot modify or delete others' retweets.");
        }
    }
}
