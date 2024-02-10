package com.starteo.demo.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
//TODO : enum
public class CreateIdea {
    private String id;
    private String name;
    private String status;
    private String description;
    private String founder;
    private String image;
}
