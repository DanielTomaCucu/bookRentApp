package com.book.rent.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {
@Query("""
        SELECT history
        FROM bookTransactionHistory history
        WHERE history.user.id =:userId
        """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);

    @Query("""
        SELECT history
        FROM bookTransactionHistory history
        WHERE history.book.owner.id =:userId
        """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer userId);

}
