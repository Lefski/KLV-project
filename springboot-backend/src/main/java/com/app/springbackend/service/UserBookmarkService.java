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

/**
 * Service for managing bookmarks of users.
 * <p>
 * Provides methods to add, delete, and retrieve bookmarks for a specific user.
 */
@Service
@RequiredArgsConstructor
public class UserBookmarkService {

    private final UserRepository userRepository;
    private final UserBookmarkRepository userBookmarkRepository;

    /**
     * Creates a new bookmark for the specified user.
     *
     * @param request The {@link com.app.springbackend.payload.request.AddBookmarkRequest} containing details of the article to be bookmarked.
     * @param userId The ID of the user for whom the bookmark is to be created.
     * @return {@link com.app.springbackend.model.bookmark.UserBookmark} object representing the newly created bookmark.
     */
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

    /**
     * Deletes a specified bookmark for a given user.
     *
     * @param bookmarkId The ID of the bookmark to be deleted.
     * @param userId The ID of the user for whom the bookmark is to be deleted.
     * @return {@link com.app.springbackend.payload.response.MessageResponse} object containing a success message upon deletion.
     */
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

    /**
     * Retrieves all bookmarks for a specified user.
     *
     * @param userId The ID of the user for whom the bookmarks are to be retrieved.
     * @return A List of {@link com.app.springbackend.model.bookmark.UserBookmark} objects representing all bookmarks of the user.
     */
    public List<UserBookmark> findAllByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Error: User not found"));

        return userBookmarkRepository.findAllByUserId(user.getId());
    }
}
