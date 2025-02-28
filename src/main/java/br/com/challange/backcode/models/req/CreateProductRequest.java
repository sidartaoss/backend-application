package br.com.challange.backcode.models.req;

import java.math.BigDecimal;

public record CreateProductRequest(
        String nome,
        String descricao,
        BigDecimal preco,
        Long categoriaId
) {
}
