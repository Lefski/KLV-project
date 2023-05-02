package com.app.springbackend.payload.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private String message;
}
