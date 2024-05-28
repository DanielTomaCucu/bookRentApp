package com.book.rent.feedback;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
