package br.com.challange.backcode.service;

import br.com.challange.backcode.dto.ProdutoDTO;
import br.com.challange.backcode.repository.Categoria;
import br.com.challange.backcode.repository.Produto;
import br.com.challange.backcode.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(final ProdutoRepository produtoRepository) {
        this.produtoRepository = Objects.requireNonNull(produtoRepository);
    }

    public List<ProdutoDTO> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(Produto::convertToDTO)
                .toList();
    }

    public Optional<Produto> buscarPorId(final Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoDTO salvar(final ProdutoDTO produtoDTO) {
        final var produto = this.convertToEntity(produtoDTO);
        return produtoRepository.save(produto)
                .convertToDTO();
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    private Produto convertToEntity(ProdutoDTO produtoDTO) {
        return Produto.with(produtoDTO.id(), produtoDTO.nome(), produtoDTO.descricao(), produtoDTO.preco(),
                produtoDTO.categoria() == null ? null :
                        Categoria.with(produtoDTO.categoria().id(),
                                produtoDTO.categoria().nome(),
                                produtoDTO.categoria().descricao()));
    }
}
