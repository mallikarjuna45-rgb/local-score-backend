package com.cricket.local_score.Common;

import java.time.Instant;

public record OTPData(String otp, Instant timeStamp) {

    public boolean isExpired() {
        return Instant.now().isAfter(timeStamp.plusSeconds(600));
    }
}
