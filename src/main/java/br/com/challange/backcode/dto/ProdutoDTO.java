package br.com.challange.backcode.dto;

import br.com.challange.backcode.repository.Categoria;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        CategoriaDTO categoria
) {

    public ProdutoDTO with(final Categoria categoria) {
        return new ProdutoDTO(id(), nome(), descricao(), preco(), CategoriaDTO.from(categoria));
    }
}
