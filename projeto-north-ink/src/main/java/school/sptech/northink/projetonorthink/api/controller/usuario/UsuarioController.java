package school.sptech.northink.projetonorthink.api.controller.usuario;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import school.sptech.northink.projetonorthink.api.util.GerenciadorDeArquivoCSV;
import school.sptech.northink.projetonorthink.api.util.ListaObj;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.UsuarioService;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioAtualizacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas aos usuários")
public class UsuarioController {

    // Nessa classe está todas as requisições que o tatuador fará
    @Autowired
    private UsuarioService usuarioService;

    // criar usuario
    @Operation(summary = "Criar um novo usuário")
    @PermitAll
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    // logar usuario
    @Operation(summary = "Autenticar usuário e obter token JWT")
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    // listar todos os usuarios
    @Operation(summary = "Listar todos os usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioListagemDto>>   listar() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    // listar usuario pelo id
    @Operation(summary = "Listar todos os usuários")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemDto> listarPeloId(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarioId(id));
    }

    // atualizar todos os dados do usuario
    @Operation(summary = "Atualizar dados do usuário pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioAtualizacaoDto> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        usuarioService.atualizarUsuario(id, usuarioAtualizacaoDto);
        return ResponseEntity.status(200).body(usuarioAtualizacaoDto);
    }

    // deletar usuario
    @Operation(summary = "Deletar usuário pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/gerar-csv")
    public ResponseEntity<Void> gerarArquivoCSV() {
        try {
            usuarioService.gravarUsuariosOrdenadosPorNome("Tatuadores.csv");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // logout do usuario
    @Operation(summary = "Logout do usuário")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Remove o token JWT do header Authorization
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove o prefixo "Bearer "
            // Lógica para invalidar ou expirar o token JWT (pode variar dependendo da implementação)
            // Exemplo: adicionar o token a uma lista de tokens inválidos
            SecurityContextHolder.clearContext();
            // tokenManager.invalidateToken(token);
            return ResponseEntity.ok("Logout realizado com sucesso.");
        }
        return ResponseEntity.badRequest().body("Falha ao realizar logout.");
    }
}
