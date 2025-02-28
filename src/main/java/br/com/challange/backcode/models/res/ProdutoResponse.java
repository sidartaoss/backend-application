package br.com.challange.backcode.models.res;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        CategoriaResponse categoria
) {
}
