package com.legoaggelos.objects.rating;

public class IncorrectValueTypeForRatingException extends RuntimeException {
    public IncorrectValueTypeForRatingException(String message) {
        super(message);
    }
}
