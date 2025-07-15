package com.eazybytes.urlshortnermicroservice.controller;

import com.eazybytes.urlshortnermicroservice.dto.request.ShortUrlRequest;
import com.eazybytes.urlshortnermicroservice.dto.response.ShortUrlResponse;
import com.eazybytes.urlshortnermicroservice.dto.response.ShortUrlStatsResponse;
import com.eazybytes.urlshortnermicroservice.entity.ShortUrl;
import com.eazybytes.urlshortnermicroservice.service.Interface.UrlServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    @Autowired
    private UrlServiceInterface urlService;

    @PostMapping("/shorturls")
    public ResponseEntity<ShortUrlResponse> generateShortUrl(
            @RequestBody ShortUrlRequest shortUrlRequest)  {
        String longUrl = shortUrlRequest.getUrl();
        Integer validity = shortUrlRequest.getValidity();
        String customShortcode = shortUrlRequest.getShortcode();
        ShortUrl shortUrl = urlService.createShortUrl(longUrl, validity, customShortcode);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ShortUrlResponse(shortUrl.getShortcode(),shortUrl.getExpiryAt()));
    }

    @GetMapping("/shorturls/{shortcode}")
    public ResponseEntity<String> getLongUrl(@PathVariable String shortcode) {
        String longUrl = urlService.getOriginalUrl(shortcode);
        return ResponseEntity.ok(longUrl);
    }

    @GetMapping("/shorturls/{shortcode}/stats")
    public ResponseEntity<ShortUrlStatsResponse> getStats(@PathVariable String shortcode) {
        ShortUrlStatsResponse stats = urlService.getStatsForShortcode(shortcode);
        return ResponseEntity.ok(stats);
    }
}
