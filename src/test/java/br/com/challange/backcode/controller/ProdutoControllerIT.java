package br.com.challange.backcode.controller;

import br.com.challange.backcode.config.TestSecurityConfig;
import br.com.challange.backcode.dto.CategoriaDTO;
import br.com.challange.backcode.dto.ProdutoDTO;
import br.com.challange.backcode.repository.Categoria;
import br.com.challange.backcode.repository.Produto;
import br.com.challange.backcode.service.CategoriaService;
import br.com.challange.backcode.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
class ProdutoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private CategoriaService categoriaService;

    private ProdutoDTO produtoDTO;

    @BeforeEach
    void setUp() {
        produtoDTO = new ProdutoDTO(1L, "Produto Teste", "Descrição Teste", new BigDecimal("100.0"),
                new CategoriaDTO(1L, "Nova Categoria", "Nova Descrição"));
    }

    @Test
    void testListar() throws Exception {
        when(produtoService.listar()).thenReturn(List.of(produtoDTO));

        mockMvc.perform(get("/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(produtoDTO.id().intValue())))
                .andExpect(jsonPath("$[0].nome", is(produtoDTO.nome())))
                .andExpect(jsonPath("$[0].descricao", is(produtoDTO.descricao())))
                .andExpect(jsonPath("$[0].preco", is(produtoDTO.preco().doubleValue())));
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(Produto.with(
                produtoDTO.id(), produtoDTO.nome(), produtoDTO.descricao(), produtoDTO.preco(),
                Categoria.with(1L, "Nova Categoria", "Nova Descrição"))));

        mockMvc.perform(get("/produtos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(produtoDTO.id().intValue())))
                .andExpect(jsonPath("$.nome", is(produtoDTO.nome())))
                .andExpect(jsonPath("$.descricao", is(produtoDTO.descricao())))
                .andExpect(jsonPath("$.preco", is(produtoDTO.preco().doubleValue())));
    }

    @Test
    void testSalvar() throws Exception {
        ProdutoDTO novoProdutoDTO = new ProdutoDTO(2L, "Novo Produto", "Nova Descrição",
                new BigDecimal("200.0"),
                new CategoriaDTO(1L, "Nova Categoria", "Nova Descrição"));
        when(categoriaService.buscarPorId(1L)).thenReturn(Optional.of(
                Categoria.with(1L, "Nova Categoria", "Nova Descrição")));
        when(produtoService.salvar(any(ProdutoDTO.class))).thenReturn(novoProdutoDTO);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo Produto\",\"descricao\":\"Nova Descrição\",\"preco\":200.0,\"categoriaId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(novoProdutoDTO.id().intValue())))
                .andExpect(jsonPath("$.nome", is(novoProdutoDTO.nome())))
                .andExpect(jsonPath("$.descricao", is(novoProdutoDTO.descricao())))
                .andExpect(jsonPath("$.preco", is(novoProdutoDTO.preco().doubleValue())));
    }

    @Test
    void testDeletar() throws Exception {
        doNothing().when(produtoService).deletar(1L);

        mockMvc.perform(delete("/produtos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}