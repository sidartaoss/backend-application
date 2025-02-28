package br.com.challange.backcode.service;

import br.com.challange.backcode.dto.CategoriaDTO;
import br.com.challange.backcode.repository.Categoria;
import br.com.challange.backcode.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Categoria categoria = Categoria.with(1L, "Categoria Teste", "Descrição Teste");
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        List<CategoriaDTO> result = categoriaService.listar();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals("Categoria Teste", result.get(0).nome());
        assertEquals("Descrição Teste", result.get(0).descricao());
    }

    @Test
    void testBuscarPorId() {
        Categoria categoria = Categoria.with(1L, "Categoria Teste", "Descrição Teste");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Optional<Categoria> result = categoriaService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Categoria Teste", result.get().getNome());
        assertEquals("Descrição Teste", result.get().getDescricao());
    }

    @Test
    void testSalvar() {
        CategoriaDTO categoriaDTO = new CategoriaDTO(1L, "Nova Categoria", "Nova Descrição");
        Categoria categoria = Categoria.with(1L, "Nova Categoria", "Nova Descrição");

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        CategoriaDTO result = categoriaService.salvar(categoriaDTO);

        assertEquals(1L, result.id());
        assertEquals("Nova Categoria", result.nome());
        assertEquals("Nova Descrição", result.descricao());
    }

    @Test
    void testDeletar() {
        doNothing().when(categoriaRepository).deleteById(1L);

        categoriaService.deletar(1L);

        verify(categoriaRepository, times(1)).deleteById(1L);
    }
}