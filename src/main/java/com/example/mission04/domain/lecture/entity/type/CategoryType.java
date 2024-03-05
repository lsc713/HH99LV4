package com.example.mission04.domain.lecture.entity.type;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {

    @NotNull
    SPRING("SPRING"),
    @NotNull
    REACT("REACT"),
    @NotNull
    NODE("NODE");
    private final String category;

    public String getCategory(){
        return this.category;}

}
