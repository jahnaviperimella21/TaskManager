package com.taskmanager.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private String userEmail;
    private String userFullName;
    private LocalDateTime createdAt;
}
