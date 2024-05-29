package school.sptech.northink.projetonorthink.domain.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.northink.projetonorthink.api.configuration.security.jwt.GerenciadorTokenJWT;
import school.sptech.northink.projetonorthink.api.util.GerenciadorDeArquivoCSV;
import school.sptech.northink.projetonorthink.api.util.ListaObj;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.UsuarioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorTokenJWT gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto) {
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email od usuário não cadastrado.", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }


    public List<UsuarioListagemDto> listarUsuarios() {
        return UsuarioMapper.toDto(usuarioRepository.findAll());
    }

    public UsuarioListagemDto listarUsuarioId(Long id) {
        return UsuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(404, "Id não localizado", null)));
    }

    public Usuario atualizarUsuario(Long id, UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Atualize os dados do usuário com base nos dados do DTO de atualização
            Usuario usuarioAtualizado = UsuarioMapper.atualizarUsuario(usuario, usuarioAtualizacaoDto);

            // Salve as mudanças no banco de dados
            Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);

            return usuarioSalvo;
        } else {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
    }

    public Usuario deletarUsuario(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {

            Usuario usuario = optionalUsuario.get();

            usuarioRepository.delete(usuario);

        } else {

            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
        return null;
    }

    public ListaObj<Usuario> ordenaPorNome(List<Usuario> usuarios) {
        ListaObj<Usuario> listaObj = new ListaObj<>(usuarios.size());

        for (Usuario usuario : usuarios) {
            listaObj.adiciona(usuario);
        }

        for (int i = 0; i < listaObj.getTamanho() - 1; i++) {
            int indMenor = i;
            for (int j = i + 1; j < listaObj.getTamanho(); j++) {
                if (listaObj.getElemento(j).getNome().compareToIgnoreCase(listaObj.getElemento(indMenor).getNome()) < 0) {
                    indMenor = j;
                }
            }
            Usuario aux = listaObj.getElemento(i);
            listaObj.adiciona(i, listaObj.getElemento(indMenor));
            listaObj.adiciona(indMenor, aux);
        }

        return listaObj;
    }

    public void gravarUsuariosOrdenadosPorNome(String nomeArquivo) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        ListaObj<Usuario> listaOrdenada = ordenaPorNome(usuarios);
        GerenciadorDeArquivoCSV.gravaArquivoCsv(listaOrdenada, nomeArquivo);
    }

    public List<UsuarioListagemGeralDto> retornarUsuariosGeral(){

        return null;
    }

    public List<UsuarioListagemPortfolioDto> retornarPortfolioUsuario() {

        return null;
    }
}