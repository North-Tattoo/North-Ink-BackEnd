package school.sptech.northink.projetonorthink.domain.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.EstiloRepository;
import school.sptech.northink.projetonorthink.domain.repository.UsuarioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private EstiloRepository estiloRepository;

    /**
     * Construtor da classe UsuarioService.
     *
     * @param usuarioRepository O repositório de usuários.
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    /**
     * Método para criar um novo usuário.
     * Ele primeiro mapeia o DTO de criação do usuário para uma entidade de usuário, criptografa a senha do usuário,
     * salva o usuário no repositório e, em seguida, salva cada estilo do usuário no repositório de estilos.
     *
     * @param usuarioCriacaoDto O DTO contendo as informações para a criação do usuário.
     */
    public void criar(UsuarioCriacaoDto usuarioCriacaoDto) {
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        // Salva cada estilo individualmente
        novoUsuario.getEstilos().forEach(estilo -> {
            if (estilo.getId() == null) { // Verifica se o estilo já foi persistido
                estiloRepository.save(estilo);
            }
        });

        // Salva o usuário depois de salvar os estilos
        Usuario usuarioSalvo = this.usuarioRepository.save(novoUsuario);

        usuarioSalvo.setEstilos(novoUsuario.getEstilos()); // Define a lista de estilos do usuário
        usuarioRepository.save(usuarioSalvo); // Salva o usuário com a lista de estilos atualizada
    }

    /**
     * Método para salvar um usuário.
     * Ele salva o usuário fornecido no repositório de usuários.
     *
     * @param usuario O usuário a ser salvo.
     * @return O usuário salvo.
     */
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Método para autenticar um usuário.
     * Ele primeiro cria um token de autenticação com as credenciais fornecidas, autentica o token,
     * busca o usuário autenticado no repositório de usuários, gera um token JWT e retorna o usuário autenticado e o token.
     *
     * @param usuarioLoginDto O DTO contendo as credenciais de login do usuário.
     * @return O DTO contendo o usuário autenticado e o token JWT.
     */
    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado.", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    /**
     * Método para listarUsuarios todos os usuários.
     * Ele busca todos os usuários no repositório de usuários e mapeia cada usuário para um DTO de listagem de usuários.
     *
     * @return A lista de DTOs de listagem de usuários.
     */
    public List<UsuarioListagemDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioListagemDto> usuarioListagemDtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioListagemDtos.add(UsuarioMapper.toDto(usuario));
        }
        return usuarioListagemDtos;
    }

    /**
     * Método para listarUsuarios um usuário por ID.
     * Ele busca o usuário com o ID fornecido no repositório de usuários e mapeia o usuário para um DTO de listagem de usuários.
     *
     * @param id O ID do usuário a ser listado.
     * @return O DTO de listagem do usuário.
     */
    public UsuarioListagemDto listarUsuarioId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o ID: " + id));
        return UsuarioMapper.toDto(usuario);
    }

    /**
     * Método para buscar o portfólio de um usuário por ID.
     * Ele busca o usuário com o ID fornecido no repositório de usuários e mapeia o usuário para um DTO de listagem de portfólio de usuários.
     *
     * @param id O ID do usuário cujo portfólio deve ser buscado.
     * @return O DTO de listagem de portfólio do usuário.
     */
    public UsuarioListagemPortfolioDto buscaPortfolioId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com o ID: " + id));
        return UsuarioMapper.toPortfolioDto(usuario);
    }

    /**
     * Método para atualizar um usuário.
     * Ele busca o usuário com o ID fornecido no repositório de usuários, atualiza os campos do usuário com base no DTO de atualização,
     * salva o usuário atualizado no repositório de usuários, gera um novo token JWT e retorna o usuário atualizado e o token.
     *
     * @param id O ID do usuário a ser atualizado.
     * @param usuarioAtualizacaoDto O DTO contendo as informações para a atualização do usuário.
     * @return O DTO contendo o usuário atualizado e o token JWT.
     */
    public UsuarioTokenDto atualizarUsuario(Long id, UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Atualize os campos do usuário existente com base nos dados do DTO de atualização
            usuario.setNome(usuarioAtualizacaoDto.getNome());
            usuario.setSobrenome(usuarioAtualizacaoDto.getSobrenome());
            usuario.setEmail(usuarioAtualizacaoDto.getEmail());
            // Criptografe a nova senha antes de salvá-la
            String senhaCriptografada = passwordEncoder.encode(usuarioAtualizacaoDto.getNovaSenha());
            usuario.setSenha(senhaCriptografada);
            usuario.setCelular(usuarioAtualizacaoDto.getCelular());

            // Salve o usuário atualizado no banco de dados
            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            // Gere um novo token JWT para o usuário
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    usuarioSalvo.getEmail(), usuarioAtualizacaoDto.getNovaSenha());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = gerenciadorTokenJwt.generateToken(authentication);

            // Retorne o usuário atualizado e o novo token
            return UsuarioMapper.of(usuarioSalvo, token);
        } else {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
    }

    /**
     * Método para atualizar o portfólio de um usuário.
     * Ele busca o usuário com o ID fornecido no repositório de usuários, atualiza o portfólio do usuário com base no DTO de atualização do portfólio,
     * e salva o usuário atualizado no repositório de usuários.
     *
     * @param id O ID do usuário cujo portfólio deve ser atualizado.
     * @param usuarioAtualizacaoPortfolioDto O DTO contendo as informações para a atualização do portfólio do usuário.
     * @return O usuário com o portfólio atualizado.
     */
    public Usuario atualizarUsuarioPortfolio(Long id, UsuarioAtualizaçãoPortfolioDto usuarioAtualizacaoPortfolioDto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            Usuario usuarioAtualizado = UsuarioMapper.atualizarUsuarioPortfolio(usuario, usuarioAtualizacaoPortfolioDto);

            Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);

            return usuarioSalvo;
        } else {
            throw new NoSuchElementException("Usuário não encontrado com o ID: " + id);
        }
    }

    /**
     * Método para deletar um usuário.
     * Ele busca o usuário com o ID fornecido no repositório de usuários e, se o usuário for encontrado, o deleta do repositório.
     *
     * @param id O ID do usuário a ser deletado.
     * @return null.
     */
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

    /**
     * Método para ordenar usuários por nome.
     * Ele cria uma nova lista de usuários, adiciona todos os usuários à lista e, em seguida, ordena a lista por nome.
     *
     * @param usuarios A lista de usuários a ser ordenada.
     * @return A lista de usuários ordenada por nome.
     */
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

    /**
     * Método para gravar usuários ordenados por nome.
     * Ele busca todos os usuários no repositório de usuários, ordena a lista de usuários por nome e grava a lista ordenada em um arquivo CSV.
     *
     * @param nomeArquivo O nome do arquivo CSV onde a lista de usuários ordenada será gravada.
     */
    public void gravarUsuariosOrdenadosPorNome(String nomeArquivo) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        ListaObj<Usuario> listaOrdenada = ordenaPorNome(usuarios);
        GerenciadorDeArquivoCSV.gravaArquivoCsv(listaOrdenada, nomeArquivo);
    }

    /**
     * Método para retornar usuários gerais.
     * Ele busca todos os usuários no repositório de usuários e mapeia cada usuário para um DTO de listagem geral de usuários.
     *
     * @return A lista de DTOs de listagem geral de usuários.
     */
    public List<UsuarioListagemGeralDto> retornarUsuariosGeral() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toUsuarioListagemGeralDto)
                .collect(Collectors.toList());
    }

    /**
     * Método para retornar usuários por estilo.
     * Ele busca usuários no repositório de usuários que têm os estilos fornecidos e mapeia cada usuário para um DTO de listagem de usuários.
     *
     * @param usuario O DTO de listagem de usuários contendo os estilos pelos quais buscar usuários.
     * @return A lista de DTOs de listagem de usuários.
     */
    public List<UsuarioListagemDto> retornaUsuariosPorEstilo(UsuarioListagemDto usuario){
        return usuarioRepository.findUsuarioByEstilos(usuario.getEstilos());
    }

    /**
     * Método para buscar um usuário por ID.
     * Ele busca o usuário com o ID fornecido no repositório de usuários.
     *
     * @param id O ID do usuário a ser buscado.
     * @return O usuário encontrado.
     */
    public Usuario porId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<String> uploadFotoPerfil(MultipartFile[] files, Long id) throws IOException {
        List<String> fileUrls = new ArrayList<>();
//
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

    // Filtros
    /*public List<Usuario> filtroBuscaUsuarios(UsuarioFiltrosDto filter) {
        return usuarioRepository.findAllByNomeAndSobreNomeAndPrecoMinAndEstilosInAndCidade(
                filter.getNome(),
                filter.getSobreNome(),
                filter.getPrecoMin(),
                filter.getEstilos(),
                filter.getCidade()
        );
    }*/

    public List<UsuarioFiltrosDto> findByNomeAndSobrenomeAndPrecoMinAndCidadeAndEstilosIn(
            String nome,
            String cidade,
            Double precoMinimo,
            List<Estilo> estilos) {
        List<Usuario> usuarios = usuarioRepository.findUsuariosByNomeAndCidadeAndPrecoMinimo(nome, cidade, precoMinimo);
        List<UsuarioFiltrosDto> usuarioFiltrosDtos = usuarios.stream()
                .map(UsuarioFiltrosDto::from)
                .collect(Collectors.toList());
        if (estilos != null && !estilos.isEmpty()) {
            usuarioFiltrosDtos = usuarioFiltrosDtos.stream()
                    .filter(u -> u.getEstilos().stream().anyMatch(estilos::contains))
                    .collect(Collectors.toList());
        }
        return usuarioFiltrosDtos;
    }
}


