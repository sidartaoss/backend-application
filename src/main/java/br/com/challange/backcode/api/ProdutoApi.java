package br.com.challange.backcode.api;

import br.com.challange.backcode.dto.ProdutoDTO;
import br.com.challange.backcode.models.req.CreateProductRequest;
import br.com.challange.backcode.models.req.UpdateProductRequest;
import br.com.challange.backcode.models.res.CreateProductResponse;
import br.com.challange.backcode.models.res.ProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public interface ProdutoApi {

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    List<ProdutoResponse> listar();

    @GetMapping("/{id}")
    @Operation(summary = "Obter um produto pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto recuperado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable final Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    CreateProductResponse salvar(@RequestBody final CreateProductRequest request);

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    ProdutoResponse atualizar(@PathVariable Long id, @RequestBody UpdateProductRequest request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar um produto pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    void deletar(@PathVariable final Long id);
}
