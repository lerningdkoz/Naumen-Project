package com.example.securingweb.Repository;

import com.example.securingweb.Entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByAuthor_Id(Long id);
}
