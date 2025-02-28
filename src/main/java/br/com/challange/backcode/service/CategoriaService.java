package br.com.challange.backcode.service;

import br.com.challange.backcode.dto.CategoriaDTO;
import br.com.challange.backcode.repository.Categoria;
import br.com.challange.backcode.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(final CategoriaRepository categoriaRepository) {
        this.categoriaRepository = Objects.requireNonNull(categoriaRepository);
    }

    public List<CategoriaDTO> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(Categoria::convertToDTO)
                .toList();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        final var categoria = this.convertToEntity(categoriaDTO);
        return categoriaRepository.save(categoria)
                .convertToDTO();
    }

    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }

    private Categoria convertToEntity(final CategoriaDTO categoriaDTO) {
        return Categoria.with(categoriaDTO.id(), categoriaDTO.nome(), categoriaDTO.descricao());
    }
}

