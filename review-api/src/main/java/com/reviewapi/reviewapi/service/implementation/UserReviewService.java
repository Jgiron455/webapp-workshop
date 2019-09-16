package com.reviewapi.reviewapi.service.implementation;

import com.reviewapi.reviewapi.dto.UserReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reviewapi.reviewapi.repository.UserReviewRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(UserReviewService.class);

    @Autowired
    private UserReviewRepository userReviewRepository;


    @Transactional
    public List<UserReview> getAllReviews(){
        LOG.info("getAllReviews() -> Retrieving all the reviews");

        List<UserReview> userReviews = new ArrayList<>();

        userReviews = (List<UserReview>) userReviewRepository.findAll();

        LOG.info("getAllReviews() -> {}", userReviews);
        return userReviews;
    }


    @Transactional
    public List<UserReview> getAllReviewByForenameAndSurname(String forename, String surname){
        LOG.info("getAllReviewByInputText() -> Retrieving all the reviews by forename {} and surname {}", forename, surname);
        List<UserReview> userReviews = new ArrayList<>();

        userReviews = userReviewRepository.findAllByForenameOrSurname(forename, surname);

        LOG.info("getAllReviewByInputText() -> {}", userReviews);
        return userReviews;
    }


    @Transactional
    public List<UserReview> getAllReviewByInputText(String inputText){
        LOG.info("getAllReviewByInputText() -> Retrieving all the reviews by inputText {}", inputText);
        List<UserReview> userReviews = new ArrayList<>();

        userReviews = userReviewRepository.findByCommentContaining(inputText);

        LOG.info("getAllReviewByInputText() -> {}", userReviews);
        return userReviews;
    }

    @Transactional
    public UserReview createReview(UserReview userReview){
        LOG.info("createReview() -> Creating userReview {}", userReview.toString());
        return userReviewRepository.save(userReview);
    }

}
