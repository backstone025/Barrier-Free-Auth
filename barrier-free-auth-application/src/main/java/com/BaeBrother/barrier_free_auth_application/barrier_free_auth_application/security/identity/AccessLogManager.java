package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLogManager {
    @Autowired
    private AccessLogRepository accessLogRepository;

    public AccessLog save(AccessLog accessLog) {
        return accessLogRepository.save(accessLog);
    }
}
