package com.app.springbackend.controller;

import com.app.springbackend.payload.request.AddBookmarkRequest;
import com.app.springbackend.security.services.UserDetailsImpl;
import com.app.springbackend.service.UserBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for handling operations related to bookmarks.
 * Handles requests to add, delete and fetch user's bookmarks.
 * All operations require the user to be authenticated.
 */
@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class BookmarkController {

    private final UserBookmarkService userBookmarkService;

    /**
     * Handle the request to add a bookmark for the authenticated user.
     *
     * @param request The {@link com.app.springbackend.payload.request.AddBookmarkRequest} payload containing details of the article to be bookmarked.
     * @param userDetails The {@link com.app.springbackend.security.services.UserDetailsImpl} object containing authenticated user's details.
     * @return {@link org.springframework.http.ResponseEntity} with HTTP status code 201 (CREATED) if the operation is successful.
     */
    @PostMapping("/bookmarks/add")
    public ResponseEntity<?> addBookmark(
            @RequestBody AddBookmarkRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return new ResponseEntity<>(
                userBookmarkService.createBookmark(request, userDetails.getId()),
                HttpStatus.CREATED
        );
    }

    /**
     * Handle the request to delete a bookmark for the authenticated user.
     *
     * @param bookmarkId The ID of the bookmark to be deleted.
     * @param userDetails The {@link com.app.springbackend.security.services.UserDetailsImpl} object containing authenticated user's details.
     * @return {@link org.springframework.http.ResponseEntity} with HTTP status code 204 (NO CONTENT) if the operation is successful.
     */
    @DeleteMapping("/bookmarks/delete/{bookmarkId}")
    public ResponseEntity<?> deleteBookmark(
            @PathVariable Long bookmarkId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return new ResponseEntity<>(
                userBookmarkService.deleteById(bookmarkId, userDetails.getId()),
                HttpStatus.NO_CONTENT
        );
    }

    /**
     * Handle the request to fetch all bookmarks for the authenticated user.
     *
     * @param userDetails The {@link com.app.springbackend.security.services.UserDetailsImpl} object containing authenticated user's details.
     * @return {@link org.springframework.http.ResponseEntity} with HTTP status code 200 (OK) and list of bookmarks belonging to the user.
     */
    @GetMapping("/bookmarks")
    public ResponseEntity<?> getAllBookmarks(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return new ResponseEntity<>(
                userBookmarkService.findAllByUserId(userDetails.getId()),
                HttpStatus.OK
        );
    }
}
