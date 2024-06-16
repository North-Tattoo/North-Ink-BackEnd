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
import org.springframework.web.multipart.MultipartFile;
import school.sptech.northink.projetonorthink.api.util.GerenciadorDeArquivoCSV;
import school.sptech.northink.projetonorthink.api.util.ListaObj;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.UsuarioService;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.*;

import java.io.IOException;
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

    // procurar usuario pelo id
    @Operation(summary = "Procurar usuário pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemDto> procurarPeloId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarioId(id));
    }

    // endpoint para chamar portfólio do usuário
    @Operation(summary = "Retornar portfólio do usuário")
    @GetMapping("/portfolio/{id}")
    public ResponseEntity<UsuarioListagemPortfolioDto> buscaPortfolioId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscaPortfolioId(id));
    }

    // endpoint para atualizar portfólio do usuário
    @Operation(summary = "Atualizar portfólio do usuário")
    @PutMapping("/portfolioAtualizar/{id}")
    public ResponseEntity<UsuarioAtualizaçãoPortfolioDto> atualizarPortfolioDto(@PathVariable Long id, @RequestBody UsuarioAtualizaçãoPortfolioDto usuarioAtualizacaoPortfolioDto) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuarioPortfolio(id, usuarioAtualizacaoPortfolioDto);
        // Aqui você pode precisar converter o usuário atualizado para um DTO antes de retorná-lo
        UsuarioAtualizaçãoPortfolioDto usuarioAtualizadoDto = UsuarioMapper.toAtualizacaoPortfolioDto(usuarioAtualizado);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizadoDto);
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

            SecurityContextHolder.clearContext();
            // tokenManager.invalidateToken(token);
            return ResponseEntity.ok("Logout realizado com sucesso.");
        }
        return ResponseEntity.badRequest().body("Falha ao realizar logout.");
    }


    @GetMapping("/{id}/portfolio")
    public ResponseEntity<UsuarioListagemPortfolioDto> retornarPortfolioUsuario(@PathVariable Long id) {
        UsuarioListagemPortfolioDto portfolioUsuario = usuarioService.retornarPortfolioUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body(portfolioUsuario);
    }


    @PostMapping("/por-estilo")
    public ResponseEntity<List<UsuarioListagemDto>> retornaUsuariosPorEstilo(@RequestBody UsuarioListagemDto usuario) {
        List<UsuarioListagemDto> usuariosPorEstilo = usuarioService.retornaUsuariosPorEstilo(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuariosPorEstilo);
    }

    public ResponseEntity<List<UsuarioListagemGeralDto>> retornarUsuariosGeral() {
        List<UsuarioListagemGeralDto> usuarios = usuarioService.retornarUsuariosGeral();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping("/uploadFotoPerfil")
    public ResponseEntity<Object> uploadFile(@RequestParam("images") MultipartFile[] files, @RequestParam Long id) throws IOException {
        if (files.length == 0) {
            return ResponseEntity.badRequest().body("No files were provided.");
        }

        try {
            List<String> fileUrls = usuarioService.uploadFotoPerfil(files, id);
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading files.");
        }
    }
}
