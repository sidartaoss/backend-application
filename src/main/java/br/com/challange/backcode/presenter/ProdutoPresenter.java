package br.com.challange.backcode.presenter;

import br.com.challange.backcode.dto.ProdutoDTO;
import br.com.challange.backcode.models.res.CategoriaResponse;
import br.com.challange.backcode.models.res.ProdutoResponse;

public interface ProdutoPresenter {

    static ProdutoResponse present(ProdutoDTO produto) {
        return new ProdutoResponse(
                produto.id(),
                produto.nome(),
                produto.descricao(),
                produto.preco(),
                new CategoriaResponse(produto.categoria().id(), produto.categoria().nome())
        );
    }
}
