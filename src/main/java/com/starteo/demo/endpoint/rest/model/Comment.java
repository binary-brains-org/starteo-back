package com.starteo.demo.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Comment {
    private String id;
    private String content;
    private String user_id;
    private String idea_id;
}
