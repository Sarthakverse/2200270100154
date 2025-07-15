package com.eazybytes.urlshortnermicroservice.service;

import com.eazybytes.loggingmiddleware.service.LoggingService;
import com.eazybytes.urlshortnermicroservice.dto.request.ClickDetail;
import com.eazybytes.urlshortnermicroservice.dto.response.ShortUrlStatsResponse;
import com.eazybytes.urlshortnermicroservice.entity.ClickLog;
import com.eazybytes.urlshortnermicroservice.entity.ShortUrl;
import com.eazybytes.urlshortnermicroservice.repository.ClickLogRepository;
import com.eazybytes.urlshortnermicroservice.repository.ShortUrlRepository;
import com.eazybytes.urlshortnermicroservice.service.Interface.UrlServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService implements UrlServiceInterface {

    @Autowired
    private ShortUrlRepository shortUrlRepository;
    @Autowired
    private ClickLogRepository clickLogRepository;

    @Autowired
   private LoggingService loggingService;
    @Override
    public ShortUrl createShortUrl(String longUrl, Integer validityMinutes, String customShortcode) {
        String shortcode;

        if (customShortcode != null && !customShortcode.isEmpty()) {
            if (shortUrlRepository.existsByShortcode(customShortcode)) {
                loggingService.log("backend", "error", "service", "Shortcode already exists");
                throw new RuntimeException("Shortcode already taken.");
            }
            shortcode = customShortcode;
        } else {
            do {
                shortcode = generateShortcode();
            } while (shortUrlRepository.existsByShortcode(shortcode));
        }

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setShortcode(shortcode);
        shortUrl.setLongUrl(longUrl);
        shortUrl.setCreatedAt(LocalDateTime.now());

        if (validityMinutes == null || validityMinutes <= 0) {
            validityMinutes = 30;
        }

        shortUrl.setExpiryAt(LocalDateTime.now().plusMinutes(validityMinutes));
        shortUrlRepository.save(shortUrl);

        loggingService.log("backend", "info", "service", "Short URL created: " + shortcode);
        return shortUrl;
    }

    @Override
    public String getOriginalUrl(String shortcode) {
        Optional<ShortUrl> optional = shortUrlRepository.findById(shortcode);
        if (optional.isEmpty()) {
            loggingService.log("backend", "warn", "service", "Shortcode not found: " + shortcode);
            throw new RuntimeException("Shortcode not found.");
        }

        ShortUrl shortUrl = optional.get();

        if (LocalDateTime.now().isAfter(shortUrl.getExpiryAt())) {
            loggingService.log("backend", "info", "service", "Shortcode expired: " + shortcode);
            throw new RuntimeException("Shortcode expired.");
        }

        loggingService.log("backend", "info", "service", "Redirecting shortcode: " + shortcode);
        return shortUrl.getLongUrl();
    }

    @Override
    public ShortUrlStatsResponse getStatsForShortcode(String shortcode) {
        ShortUrl shortUrl = shortUrlRepository.findById(shortcode)
                .orElseThrow(() -> new RuntimeException("Shortcode not found"));

        List<ClickLog> logs = clickLogRepository.findByShortcode(shortcode);

        List<ClickDetail> details = logs.stream().map(log ->
                new ClickDetail(log.getTimestamp(), log.getReferrer(), log.getLocation())
        ).toList();

        return new ShortUrlStatsResponse(
                shortcode,
                shortUrl.getLongUrl(),
                shortUrl.getCreatedAt(),
                shortUrl.getExpiryAt(),
                logs.size(),
                details
        );
    }

    private String generateShortcode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
