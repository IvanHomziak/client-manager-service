package com.ihomziak.clientmanagerservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ErrorDTO {
    private String error;

    public ErrorDTO(String error) {
        this.error = error;
    }

}
