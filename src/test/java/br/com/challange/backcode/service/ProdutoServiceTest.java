package br.com.challange.backcode.service;

import br.com.challange.backcode.dto.ProdutoDTO;
import br.com.challange.backcode.repository.Produto;
import br.com.challange.backcode.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Produto produto = Produto.with(1L,
                "Produto Teste",
                "Descrição Teste",
                new BigDecimal("100.0"),
                null);
        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        List<ProdutoDTO> result = produtoService.listar();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals("Produto Teste", result.get(0).nome());
        assertEquals("Descrição Teste", result.get(0).descricao());
        assertEquals(new BigDecimal("100.0"), result.get(0).preco());
    }

    @Test
    void testBuscarPorId() {
        Produto produto = Produto.with(1L,
                "Produto Teste",
                "Descrição Teste",
                new BigDecimal("100.0"),
                null);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> result = produtoService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Produto Teste", result.get().getNome());
        assertEquals("Descrição Teste", result.get().getDescricao());
        assertEquals(new BigDecimal("100.0"), result.get().getPreco());
    }

    @Test
    void testSalvar() {
        ProdutoDTO produtoDTO = new ProdutoDTO(
                1L,
                "Novo Produto",
                "Nova Descrição",
                new BigDecimal("200.0"),
                null);
        Produto produto = Produto.with(1L, "Novo Produto", "Nova Descrição",
                new BigDecimal("200.0"), null);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoDTO result = produtoService.salvar(produtoDTO);

        assertEquals(1L, result.id());
        assertEquals("Novo Produto", result.nome());
        assertEquals("Nova Descrição", result.descricao());
        assertEquals(new BigDecimal("200.0"), result.preco());
    }

    @Test
    void testDeletar() {
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deletar(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
    }
}