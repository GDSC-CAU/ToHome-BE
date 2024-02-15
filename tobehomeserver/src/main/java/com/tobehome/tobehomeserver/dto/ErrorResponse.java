package com.tobehome.tobehomeserver.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ErrorResponse {
    private String message;
}
