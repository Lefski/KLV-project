package com.app.springbackend.service;

import com.app.springbackend.model.bookmark.UserBookmark;
import com.app.springbackend.model.user.User;
import com.app.springbackend.payload.request.AddBookmarkRequest;
import com.app.springbackend.payload.response.MessageResponse;
import com.app.springbackend.repo.UserBookmarkRepository;
import com.app.springbackend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBookmarkService {

    private final UserRepository userRepository;
    private final UserBookmarkRepository userBookmarkRepository;

    public UserBookmark createBookmark(
            AddBookmarkRequest request,
            Long userId
    ) {

        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("Error: User not found"));

        return userBookmarkRepository.save(
                UserBookmark
                        .builder()
                        .user(user)
                        .articleUrl(request.getArticleUrl().getBytes())
                        .articleTitle(request.getArticleTitle())
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .build()
        );
    }

    @Transactional
    public MessageResponse deleteById(Long bookmarkId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Error: User not found"));

        UserBookmark bookmark = userBookmarkRepository.findById(bookmarkId)
                        .orElseThrow(() -> new IllegalArgumentException("Error: Bookmark not found with id: " + bookmarkId));

        if (!bookmark.getUser().equals(user)) {
            throw new RuntimeException("Error: User is not authorized to remove this bookmark");
        }

        userBookmarkRepository.deleteById(bookmarkId);

        return MessageResponse
                .builder()
                .message("Bookmark deleted successfully")
                .build();
    }

    public List<UserBookmark> findAllByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Error: User not found"));

        return userBookmarkRepository.findAllByUserId(user.getId());
    }
}
