package com.reviewapi.reviewapi.controller;

import com.reviewapi.reviewapi.dto.UserReview;
import com.reviewapi.reviewapi.service.implementation.UserReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/mysql")
public class UserReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(UserReviewController.class);

    @Autowired
    private UserReviewService userReviewService;

    @GetMapping(value="/get")
    public @ResponseBody
    ResponseEntity<?> getMyReviews(@RequestParam(value ="forename", required=false) String forename,
                                   @RequestParam(value ="surname", required=false) String surname,
                                   @RequestParam(value ="inputText", required=false) String inputText) {
        LOG.info("getMyReviews() -> forename: {}, surname: {}, inputText: {}", forename, surname, inputText);

        List<UserReview> userReviews = new ArrayList<>();

        if(isValid(forename) && isValid(surname) && !isValid(inputText)){
            userReviews = userReviewService.getAllReviewByForenameAndSurname(forename, surname);
        } else if(!isValid(forename) && !isValid(surname) && isValid(inputText)){
            userReviews = userReviewService.getAllReviewByInputText(inputText);
        } else if (!isValid(forename) && !isValid(surname) && !isValid(inputText)){
            userReviews = userReviewService.getAllReviews();
        }

        LOG.info("getMyReviews() -> myLocationDtos: {}", userReviews);
        return !userReviews.isEmpty() ? new ResponseEntity<List<UserReview>>(userReviews, OK) :
                new ResponseEntity<>("Invalid Request", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public @ResponseBody
    ResponseEntity<?> postMyReviews(@RequestBody UserReview userReview){
        LOG.info("postMyReviews() -> {}", userReview.toString());

        if(userReview != null){
            UserReview tempUserReview = userReviewService.createReview(userReview);
            return new ResponseEntity<UserReview>(tempUserReview, OK);

        } else {
            LOG.warn("postMyReviews() -> Null Response");
            return new ResponseEntity<>("Invalid Request", HttpStatus.NOT_FOUND);
        }

    }

    private Boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }

}
