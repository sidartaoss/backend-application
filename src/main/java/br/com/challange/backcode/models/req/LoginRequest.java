package br.com.challange.backcode.models.req;

public record LoginRequest(
        String username,
        String password
) {
}
