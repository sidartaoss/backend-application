package br.com.challange.backcode.controller;

import br.com.challange.backcode.api.CategoriaApi;
import br.com.challange.backcode.dto.CategoriaDTO;
import br.com.challange.backcode.repository.Categoria;
import br.com.challange.backcode.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class CategoriaController implements CategoriaApi {

    private final CategoriaService categoriaService;

    public CategoriaController(final CategoriaService categoriaService) {
        this.categoriaService = Objects.requireNonNull(categoriaService);
    }

    @Override
    public List<CategoriaDTO> listar() {
        return categoriaService.listar();
    }

    @Override
    public ResponseEntity<CategoriaDTO> buscarPorId(final Long id) {
        final var categoria = this.categoriaService.buscarPorId(id)
                .map(Categoria::convertToDTO);
        return categoria
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    public CategoriaDTO salvar(final CategoriaDTO categoriaDTO) {
        return categoriaService.salvar(categoriaDTO);
    }

    @Override
    public CategoriaDTO atualizar(final Long id, final CategoriaDTO categoriaDTO) {
        this.buscarPorId(id);
        return categoriaService.salvar(new CategoriaDTO(id, categoriaDTO.nome(), categoriaDTO.descricao()));
    }

    @Override
    public void deletar(final Long id) {
        categoriaService.deletar(id);
    }
}

