package com.eazybytes.urlshortnermicroservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrlRequest {
    private String url;
    private Integer validity;
    private String shortcode;

}
