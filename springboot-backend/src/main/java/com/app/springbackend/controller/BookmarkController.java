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

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class BookmarkController {

    private final UserBookmarkService userBookmarkService;

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
