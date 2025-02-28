package br.com.challange.backcode.api;

import br.com.challange.backcode.models.req.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "API para gerenciamento de autenticação")
public interface AuthApi {

    @PostMapping("/login")
    @Operation(summary = "Login de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Token gerado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Um erro interno do servidor foi lançado"),
    })
    ResponseEntity<?> login(@RequestBody final LoginRequest loginRequest);
}
