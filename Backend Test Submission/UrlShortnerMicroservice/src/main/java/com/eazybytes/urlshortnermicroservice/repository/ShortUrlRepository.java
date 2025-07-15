package com.eazybytes.urlshortnermicroservice.repository;

import com.eazybytes.urlshortnermicroservice.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository  extends JpaRepository<ShortUrl, String> {
    boolean existsByShortcode(String shortcode);
}
