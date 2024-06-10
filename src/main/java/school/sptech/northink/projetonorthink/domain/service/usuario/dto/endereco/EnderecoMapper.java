package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;

import school.sptech.northink.projetonorthink.domain.entity.Endereco;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloAtualizacaoDto;

import java.util.List;

public class EnderecoMapper {

    public static EnderecoListagemDto toDto(Endereco endereco) {
        if (endereco == null) return null;

        EnderecoListagemDto enderecoListagemDto = new EnderecoListagemDto();

        enderecoListagemDto.setRua(endereco.getRua());
        enderecoListagemDto.setBairro(endereco.getBairro());
        enderecoListagemDto.setNumero(endereco.getNumero());


        return enderecoListagemDto;
    }

    // Adicionado instância do Estudio como argumento no método - Zapatta
    public static Endereco toEntity(EnderecoCriacaoDto enderecoCriacaoDto, Estudio estudio) {
        if (enderecoCriacaoDto == null) return null;

        Endereco endereco = new Endereco();

        endereco.setRua(endereco.getRua());
        endereco.setNumero(endereco.getNumero());
        endereco.setComplemento(endereco.getComplemento());
        endereco.setCEP(enderecoCriacaoDto.getCEP());
        endereco.setBairro(enderecoCriacaoDto.getBairro());
        endereco.setCidade(enderecoCriacaoDto.getCidade());
        endereco.setEstado(enderecoCriacaoDto.getEstado());
        // endereco.setFkEstudio(endereco.getFkEstudio());
        // Necessário puxar o estúdio pois ele está tendo relacionamento na entidade Endereco - Zapatta
        endereco.setEstudio(estudio);
        // salvar na entidade qual a fk do estudio que está vinculado ao endereco
        return endereco;
    }

    public static Estilo atualizarEstilo(Estilo estiloExistente, EstiloAtualizacaoDto estiloAtualizacaoDto) {

        estiloExistente.setNome(estiloAtualizacaoDto.getNome());

        return estiloExistente;
    }

    public static Endereco atualizarEndereco(Endereco enderecoExistente, EnderecoAtualizacaoDto enderecoAtualizacaoDto) {

        enderecoExistente.setRua(enderecoAtualizacaoDto.getRua());
        enderecoExistente.setNumero(enderecoAtualizacaoDto.getNumero());
        enderecoExistente.setComplemento(enderecoAtualizacaoDto.getComplemento());
        enderecoExistente.setCEP(enderecoAtualizacaoDto.getCEP());
        enderecoExistente.setBairro(enderecoAtualizacaoDto.getBairro());
        enderecoExistente.setCidade(enderecoAtualizacaoDto.getCidade());
        enderecoExistente.setEstado(enderecoAtualizacaoDto.getEstado());

        return enderecoExistente;
    }

    public static List<EnderecoListagemDto> toDto(List<Endereco> entities) {
        // Aqui é utilizado um método que mapea um a um e reutilizado para poder fazer a passagem de lista sem duplicar código
        return entities.stream().map(EnderecoMapper::toDto).toList();
    }
}
