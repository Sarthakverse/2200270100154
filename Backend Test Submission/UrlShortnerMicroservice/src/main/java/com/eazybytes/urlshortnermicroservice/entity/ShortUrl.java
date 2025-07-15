package com.eazybytes.urlshortnermicroservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "short_url")
@Data
public class ShortUrl {

    @Id
    private String shortcode;

    private String longUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiryAt;

}
