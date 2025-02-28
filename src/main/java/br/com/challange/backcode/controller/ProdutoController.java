package br.com.challange.backcode.controller;

import br.com.challange.backcode.api.ProdutoApi;
import br.com.challange.backcode.dto.CategoriaDTO;
import br.com.challange.backcode.dto.ProdutoDTO;
import br.com.challange.backcode.exceptions.NotFoundException;
import br.com.challange.backcode.models.req.CreateProductRequest;
import br.com.challange.backcode.models.req.UpdateProductRequest;
import br.com.challange.backcode.models.res.CreateProductResponse;
import br.com.challange.backcode.models.res.ProdutoResponse;
import br.com.challange.backcode.presenter.ProdutoPresenter;
import br.com.challange.backcode.repository.Categoria;
import br.com.challange.backcode.repository.Produto;
import br.com.challange.backcode.service.CategoriaService;
import br.com.challange.backcode.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@RestController
public class ProdutoController implements ProdutoApi {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(
            final ProdutoService produtoService,
            final CategoriaService categoriaService
    ) {
        this.produtoService = Objects.requireNonNull(produtoService);
        this.categoriaService = Objects.requireNonNull(categoriaService);
    }

    @Override
    public List<ProdutoResponse> listar() {
        return produtoService.listar().stream()
                .map(ProdutoPresenter::present)
                .toList();
    }

    @Override
    public ResponseEntity<ProdutoDTO> buscarPorId(final Long id) {
        final var produto = this.produtoService.buscarPorId(id)
                .map(Produto::convertToDTO);
        return produto
                .map(ResponseEntity::ok)
                .orElseThrow(notFound(Produto.class, id));
    }

    @Override
    public CreateProductResponse salvar(final CreateProductRequest request) {
        final var produtoDTO = new ProdutoDTO(
                null,
                request.nome(),
                request.descricao(),
                request.preco(),
                CategoriaDTO.from(request.categoriaId())
        );
        this.buscarCategoria(produtoDTO);
        return CreateProductResponse.present(produtoService.salvar(produtoDTO));
    }

    @Override
    public ProdutoResponse atualizar(final Long id, final UpdateProductRequest request) {
        final var produtoDTO = new ProdutoDTO(
                id,
                request.nome(),
                request.descricao(),
                request.preco(),
                CategoriaDTO.from(request.categoriaId())
        );
        this.buscarPorId(id);
        final var categoria = this.buscarCategoria(produtoDTO);
        return ProdutoPresenter.present(produtoService.salvar(new ProdutoDTO(
                id,
                produtoDTO.nome(),
                produtoDTO.descricao(),
                produtoDTO.preco(),
                categoria.convertToDTO())));
    }

    @Override
    public void deletar(final Long id) {
        produtoService.deletar(id);
    }

    private Categoria buscarCategoria(final ProdutoDTO produtoDTO) {
        final var categoriaId = produtoDTO.categoria().id();
        return categoriaService.buscarPorId(categoriaId)
                .orElseThrow(notFound(Categoria.class, categoriaId));
    }

    private Supplier<NotFoundException> notFound(final Class<?> aClazz, final Long identifier) {
        return () -> NotFoundException.with(aClazz, identifier);
    }
}

