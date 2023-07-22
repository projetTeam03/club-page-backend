package com.projet.clubpage.common;

import lombok.Data;

public class ApiUtils {




    public static <T> CommonResponse<T> success(boolean result,int code, String message, T output) {

        @Data
        class Status {
            private int code;
            private String message;
        }

        Status status = new Status();
        status.setCode(code);
        status.setMessage(message);

        return new CommonResponse<>(result, (T) status, output);
    }
}
