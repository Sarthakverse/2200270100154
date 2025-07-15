package com.eazybytes.urlshortnermicroservice.dto.response;

import com.eazybytes.urlshortnermicroservice.dto.request.ClickDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ShortUrlStatsResponse {
    private String shortcode;
    private String originalUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiryAt;
    private int totalClicks;
    private List<ClickDetail> clickDetails;
}