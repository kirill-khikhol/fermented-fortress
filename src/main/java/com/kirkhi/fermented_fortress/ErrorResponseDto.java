package com.kirkhi.fermented_fortress;

import java.time.LocalDateTime;

public record ErrorResponseDto(String message, String detailedMessage, LocalDateTime errorTime) {

}
