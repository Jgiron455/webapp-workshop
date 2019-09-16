package com.reviewapi.reviewapi.repository;

import com.reviewapi.reviewapi.dto.UserReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Integer> {

    List<UserReview> findAll(Sort name);

    List<UserReview> findAllByForenameOrSurname(String forename, String surname);

//    List<UserReview> findAllByCommentContaningOrContentContaining(String inputText, String anotherInputText);

    List<UserReview> findByCommentContaining(String inputText);

}
