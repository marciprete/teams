package it.maconsulting.microkernel.exceptions;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public class DomainException extends RuntimeException {

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }
}
