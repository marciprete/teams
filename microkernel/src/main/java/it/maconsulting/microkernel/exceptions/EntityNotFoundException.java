package it.maconsulting.microkernel.exceptions;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
public class EntityNotFoundException extends DomainException {

    public EntityNotFoundException(String entity, String id) {
        super(entity + " type Entity with id " + id + " not found");
    }
}
