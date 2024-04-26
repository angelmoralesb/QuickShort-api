package org.dev.quickshortapi.infraestructure.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("UrlCache")
@Getter
@Setter
public class UrlCache implements Serializable {

    private String id;
    private String idDbUrl;
    private String originalUrl;

    public UrlCache() {
    }
    public UrlCache(String id, String idDbUrl, String originalUrl) {
        this.id = id;
        this.idDbUrl = idDbUrl;
        this.originalUrl = originalUrl;
    }
}