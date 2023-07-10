package com.example.securingweb.Service;


import com.example.securingweb.Entity.Feedback;
import com.example.securingweb.Repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public List<Feedback> getList(){
        return (List<Feedback>) feedbackRepository.findAll();
    }
}
