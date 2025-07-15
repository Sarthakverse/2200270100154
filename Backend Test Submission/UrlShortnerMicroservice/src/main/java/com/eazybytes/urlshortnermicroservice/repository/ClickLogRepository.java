package com.eazybytes.urlshortnermicroservice.repository;

import com.eazybytes.urlshortnermicroservice.entity.ClickLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClickLogRepository extends JpaRepository<ClickLog, Long> {
    List<ClickLog> findByShortcode(String shortcode);
}
