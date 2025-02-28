package br.com.challange.backcode.api;

import br.com.challange.backcode.dto.CategoriaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "API para gerenciamento de categorias")
public interface CategoriaApi {

    @GetMapping
    @Operation(summary = "Lista todas as categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    List<CategoriaDTO> listar();

    @GetMapping("/{id}")
    @Operation(summary = "Obter uma categoria pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable final Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    CategoriaDTO salvar(@RequestBody final CategoriaDTO categoriaDTO);

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    CategoriaDTO atualizar(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    void deletar(@PathVariable Long id);
}
