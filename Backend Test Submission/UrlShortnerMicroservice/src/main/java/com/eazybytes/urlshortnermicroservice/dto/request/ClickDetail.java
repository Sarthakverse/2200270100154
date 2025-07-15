package com.eazybytes.urlshortnermicroservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ClickDetail {
    private LocalDateTime timestamp;
    private String referrer;
    private String location;
}