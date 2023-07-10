package com.example.securingweb.Service;


import com.example.securingweb.Entity.Review;
import com.example.securingweb.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {


    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getList(){
        return (List<Review>) reviewRepository.findAll();
    }
}
