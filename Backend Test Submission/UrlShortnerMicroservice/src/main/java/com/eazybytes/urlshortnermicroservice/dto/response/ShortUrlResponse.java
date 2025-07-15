package com.eazybytes.urlshortnermicroservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrlResponse {
    private String shortLink;
    private LocalDateTime expiry;
}
