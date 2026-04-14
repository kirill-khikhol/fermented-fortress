package com.kirkhi.fermented_fortress.fermentation.domain.exception;


public class InvalidFermentationStateException extends RuntimeException {
    public InvalidFermentationStateException(String message) {
        super(message);
    }

    public InvalidFermentationStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
