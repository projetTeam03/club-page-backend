package com.projet.clubpage.common;

public class ApiUtils {
    public static <T> CommonResponse<T> success(boolean result, T status, T output) {
        return new CommonResponse<>(result, status, output);
    }
}
