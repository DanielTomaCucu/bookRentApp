package com.book.rent.feedback;

import jakarta.validation.constraints.*;

public record FeedbackRequest(
        @Positive(message = "200")
        @Min(message = "201", value = 0)
        @Max(message = "202", value = 5)
        Double note,

        @Positive(message = "203")
        @NotEmpty(message = "203")
        @NotBlank(message = "203")
        String comments,

        @Positive(message = "204")
        @NotEmpty(message = "204")
        @NotBlank(message = "204")
        Integer bookId
        ) {
}
