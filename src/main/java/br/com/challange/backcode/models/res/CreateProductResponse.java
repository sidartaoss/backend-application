package br.com.challange.backcode.models.res;

import br.com.challange.backcode.dto.ProdutoDTO;

import java.math.BigDecimal;

public record CreateProductResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Long categoriaId
) {

    public static CreateProductResponse present(final ProdutoDTO produtoDTO) {
        return new CreateProductResponse(produtoDTO.id(),
                produtoDTO.nome(),
                produtoDTO.descricao(),
                produtoDTO.preco(),
                produtoDTO.categoria().id());
    }
}
