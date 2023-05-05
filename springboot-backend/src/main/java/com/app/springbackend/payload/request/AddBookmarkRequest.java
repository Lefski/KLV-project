package com.app.springbackend.payload.request;

import com.app.springbackend.model.user.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookmarkRequest {

    private String articleUrl, articleTitle;
}
