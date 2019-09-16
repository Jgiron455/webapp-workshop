package repository;

import com.reviewapi.reviewapi.dto.UserReview;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("userReviewRepository")
@Qualifier("userReviewRepository")
public interface UserReviewRepository extends JpaRepository<UserReview, Integer> {

    List<UserReview> findAll(Sort name);

    List<UserReview> findAllByForenameOrSurname(String forename, String surname);

    List<UserReview> findByCommentContaningOrContentContaining(String inputText, String anotherInputText);

}
