package br.com.challange.backcode.models.req;

import java.math.BigDecimal;

public record UpdateProductRequest(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Long categoriaId
) {
}
