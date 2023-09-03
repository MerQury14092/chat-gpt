package com.merqury.chatgpt.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private int response_code, length;
    private String payload;
}
