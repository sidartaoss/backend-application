package br.com.challange.backcode.dto;

import br.com.challange.backcode.repository.Categoria;

public record CategoriaDTO(
        Long id,
        String nome,
        String descricao
) {

    public static CategoriaDTO from(final Long id) {
        return new CategoriaDTO(id, null, null);
    }

    public static CategoriaDTO from(final Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }
}
