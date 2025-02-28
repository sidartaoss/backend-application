package br.com.challange.backcode.controller;

import br.com.challange.backcode.config.TestSecurityConfig;
import br.com.challange.backcode.dto.CategoriaDTO;
import br.com.challange.backcode.repository.Categoria;
import br.com.challange.backcode.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
class CategoriaControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        categoriaDTO = new CategoriaDTO(1L, "Categoria Teste", "Descrição Teste");
    }

    @Test
    void testListar() throws Exception {
        when(categoriaService.listar()).thenReturn(List.of(categoriaDTO));

        mockMvc.perform(get("/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(categoriaDTO.id().intValue())))
                .andExpect(jsonPath("$[0].nome", is(categoriaDTO.nome())))
                .andExpect(jsonPath("$[0].descricao", is(categoriaDTO.descricao())));
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(categoriaService.buscarPorId(1L)).thenReturn(Optional.of(Categoria.with(
                categoriaDTO.id(), categoriaDTO.nome(), categoriaDTO.descricao())));

        mockMvc.perform(get("/categorias/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(categoriaDTO.id().intValue())))
                .andExpect(jsonPath("$.nome", is(categoriaDTO.nome())))
                .andExpect(jsonPath("$.descricao", is(categoriaDTO.descricao())));
    }

    @Test
    void testSalvar() throws Exception {
        CategoriaDTO novaCategoriaDTO = new CategoriaDTO(2L, "Nova Categoria", "Nova Descrição");
        when(categoriaService.salvar(any(CategoriaDTO.class))).thenReturn(novaCategoriaDTO);

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Nova Categoria\",\"descricao\":\"Nova Descrição\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(novaCategoriaDTO.id().intValue())))
                .andExpect(jsonPath("$.nome", is(novaCategoriaDTO.nome())))
                .andExpect(jsonPath("$.descricao", is(novaCategoriaDTO.descricao())));
    }

    @Test
    void testAtualizar() throws Exception {
        CategoriaDTO atualizadaCategoriaDTO = new CategoriaDTO(1L, "Categoria Atualizada", "Descrição Atualizada");
        when(categoriaService.salvar(any(CategoriaDTO.class))).thenReturn(atualizadaCategoriaDTO);

        mockMvc.perform(put("/categorias/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Categoria Atualizada\",\"descricao\":\"Descrição Atualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(atualizadaCategoriaDTO.id().intValue())))
                .andExpect(jsonPath("$.nome", is(atualizadaCategoriaDTO.nome())))
                .andExpect(jsonPath("$.descricao", is(atualizadaCategoriaDTO.descricao())));
    }

    @Test
    void testDeletar() throws Exception {
        doNothing().when(categoriaService).deletar(1L);

        mockMvc.perform(delete("/categorias/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
