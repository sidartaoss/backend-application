package br.com.challange.backcode.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException with(final Class<?> aClazz, final Long id) {
        final var anError = "%s com ID %s não foi encontrado"
                .formatted(aClazz.getSimpleName(), id);
        return new NotFoundException(anError);
    }
}
