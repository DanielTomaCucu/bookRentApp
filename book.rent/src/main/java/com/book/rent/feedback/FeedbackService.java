package com.book.rent.feedback;

import com.book.rent.book.Book;
import com.book.rent.book.BookRepository;
import com.book.rent.book.BookResponse;
import com.book.rent.common.PageResponse;
import com.book.rent.exception.OperationNotPermitedException;
import com.book.rent.history.BookTransactionHistory;
import com.book.rent.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId()).orElseThrow(() -> new EntityNotFoundException("No book was found with id ::" + request.bookId()));
        User user = ((User) connectedUser.getPrincipal());
        if (!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermitedException("You cannot give feedback to your own books");
        }
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermitedException("You cannot give feedback for an archived or non shareable book");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) connectedUser.getPrincipal());
        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId, pageable);
        List<FeedbackResponse> feedbackResponses = feedbacks.stream().map(f -> feedbackMapper.toFeedbackResponse(f, user.getId())).toList();
        return new PageResponse<>(feedbackResponses, feedbacks.getNumber(), feedbacks.getSize(), feedbacks.getTotalElements(), feedbacks.getTotalPages(), feedbacks.isFirst(), feedbacks.isLast());
    }
}
