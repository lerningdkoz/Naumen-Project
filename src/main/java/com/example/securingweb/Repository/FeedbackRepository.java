package com.example.securingweb.Repository;

import com.example.securingweb.Entity.Feedback;
import com.example.securingweb.Entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    List<Feedback> findByReview_Id(Long id);
    List<Feedback> findByUser_Id(Long id);

    List<Feedback> findByReview(Review review);
}

