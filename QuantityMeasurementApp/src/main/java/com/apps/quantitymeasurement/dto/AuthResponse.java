package com.apps.quantitymeasurement.dto;

public record AuthResponse(
        String token,
        String username,
        String message
) {
}
