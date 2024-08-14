package com.quickbook.playground.bo;

public record ApiResponse(int statusCode, String message, String currentDate) {
}
