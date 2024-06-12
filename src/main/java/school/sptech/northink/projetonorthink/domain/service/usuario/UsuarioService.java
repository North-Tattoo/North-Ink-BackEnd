package school.sptech.northink.projetonorthink.domain.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.northink.projetonorthink.api.configuration.security.jwt.GerenciadorTokenJWT;
import school.sptech.northink.projetonorthink.api.util.GerenciadorDeArquivoCSV;
import school.sptech.northink.projetonorthink.api.util.ListaObj;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.TatuagemRepository;
import school.sptech.northink.projetonorthink.domain.repository.UsuarioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.*;

import java.io.IOException;
import java.util.ArrayList;
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

//    @Autowired
//    private BlobContainerClient blobContainerClient;


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


            Usuario usuarioAtualizado = UsuarioMapper.atualizarUsuario(usuario, usuarioAtualizacaoDto);


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
        usuarioRepository.findAll();
        return null;
    }

    public UsuarioListagemPortfolioDto retornarPortfolioUsuario(Long portifolioId) {

        Usuario usuario = usuarioRepository.findById(portifolioId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show não encontrado"));

        return UsuarioMapper.toPortfolioDto(usuario);
    }

    public List<UsuarioListagemDto> retornaUsuariosPorEstilo(UsuarioListagemDto usuario){
        return usuarioRepository.findUsuarioByEstilos(usuario.getEstilos());
    }

    public List<String> uploadFotoPerfil(MultipartFile[] files, Long id) throws IOException {
        List<String> fileUrls = new ArrayList<>();

//        for (MultipartFile file : files) {
//            if (!file.isEmpty()) {
//                String fileName = id + "/" + file.getOriginalFilename(); // You can customize the file naming strategy here
//                try {
//                    blobContainerClient.getBlobClient(fileName).upload(file.getInputStream(), file.getSize(), true);
//                    BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(file.getContentType());
//                    blobContainerClient.getBlobClient(fileName).setHttpHeaders(headers);
//
//                    String fileUrl = blobContainerClient.getBlobClient(fileName).getBlobUrl();
//                    fileUrls.add(fileUrl);
//                } catch (BlobStorageException e) {
//                    e.printStackTrace();
//                    throw new IOException("Error uploading file to Azure Blob Storage", e);
//                }
//            }
//        }
        return fileUrls;
    }

    public Usuario porId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
}


