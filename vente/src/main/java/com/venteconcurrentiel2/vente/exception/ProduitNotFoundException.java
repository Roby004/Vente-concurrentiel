package com.venteconcurrentiel2.vente.exception;

public class ProduitNotFoundException extends RuntimeException {
    public ProduitNotFoundException(String mess) {
        super(mess);
    }
}
