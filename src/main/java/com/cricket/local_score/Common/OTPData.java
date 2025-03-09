package com.cricket.local_score.Common;

import lombok.Getter;

import java.time.Instant;

@Getter
public record OTPData(String otp, Instant timeStamp) {

    public boolean isExpired() {
        return Instant.now().isAfter(timeStamp.plusSeconds(600));
    }
}
