package io.kk.remitlyhomeexercise.exception;

public class BankNotFoundException extends IllegalArgumentException {
    public BankNotFoundException(String msg) {super(msg);}
}
