package com.eazybytes.urlshortnermicroservice.service.Interface;

import com.eazybytes.urlshortnermicroservice.dto.response.ShortUrlStatsResponse;
import com.eazybytes.urlshortnermicroservice.entity.ShortUrl;

public interface UrlServiceInterface {
    public ShortUrl createShortUrl(String longUrl, Integer validityMinutes, String customShortcode);
    public String getOriginalUrl(String shortcode);
    ShortUrlStatsResponse getStatsForShortcode(String shortcode);
}
