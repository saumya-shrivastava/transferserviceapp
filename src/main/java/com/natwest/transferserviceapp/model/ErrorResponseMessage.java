package com.natwest.transferserviceapp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Class to have error response messages for build index.
 *
 * @author n.dubey
 *
 */
@Getter
@Setter
@Builder
public class ErrorResponseMessage {
    private Date timestamp;
    private String message;
}