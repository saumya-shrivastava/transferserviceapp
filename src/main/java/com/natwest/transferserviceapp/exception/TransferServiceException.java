package com.natwest.transferserviceapp.exception;
/**
 * Custom Exception Handler Class.
 */
public class TransferServiceException extends RuntimeException {

    /** The error code. */
    private String code;

    public TransferServiceException(final String message) {
        super(message);
    }

    /**
     * Create an instance of TransferServiceException.
     * @param pCode p code
     * @param msg message
     */
    public TransferServiceException(final String pCode, final String msg) {
        super(msg);
        this.code = pCode;
    }

    @Override
    public String toString() {
        return "code: " + code + " " + super.toString();
    }

}
