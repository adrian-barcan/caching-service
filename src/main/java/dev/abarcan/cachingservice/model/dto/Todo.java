package dev.abarcan.cachingservice.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Todo implements Serializable {

    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;

}
