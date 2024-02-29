package com.crud.api.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;

    public void callerURL(final String requestedURI) {
        this.requestedURI = requestedURI;
    }
}
