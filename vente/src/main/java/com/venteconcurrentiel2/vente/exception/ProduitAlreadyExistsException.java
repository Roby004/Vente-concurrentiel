package com.venteconcurrentiel2.vente.exception;

public class ProduitAlreadyExistsException extends RuntimeException {
    public ProduitAlreadyExistsException(String mess){
        super(mess);
    }
}
