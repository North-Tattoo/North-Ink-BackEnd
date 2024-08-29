package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;

import school.sptech.northink.projetonorthink.domain.entity.Endereco;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.EstudioService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloAtualizacaoDto;

import java.util.List;

public class EnderecoMapper {

    public static EnderecoListagemDto toDto(Endereco endereco) {
        if (endereco == null) return null;

        EnderecoListagemDto enderecoListagemDto = new EnderecoListagemDto();

        enderecoListagemDto.setRua(endereco.getRua());
        enderecoListagemDto.setBairro(endereco.getBairro());
        enderecoListagemDto.setNumero(endereco.getNumero());
        enderecoListagemDto.setCep(endereco.getCep());
        enderecoListagemDto.setCidade(endereco.getCidade());
        enderecoListagemDto.setEstado(endereco.getEstado());



        return enderecoListagemDto;
    }

    public static Endereco toEntity(EnderecoCriacaoDto enderecoDto, EstudioService estudioService) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setComplemento(enderecoDto.getComplemento());
        endereco.setCep(enderecoDto.getCep());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(enderecoDto.getEstado());
        return endereco;
    }

    public static Endereco atualizarEndereco(Endereco enderecoExistente, EnderecoAtualizacaoDto enderecoAtualizacaoDto) {

        enderecoExistente.setRua(enderecoAtualizacaoDto.getRua());
        enderecoExistente.setNumero(enderecoAtualizacaoDto.getNumero());
        enderecoExistente.setComplemento(enderecoAtualizacaoDto.getComplemento());
        enderecoExistente.setCep(enderecoAtualizacaoDto.getCEP());
        enderecoExistente.setBairro(enderecoAtualizacaoDto.getBairro());
        enderecoExistente.setCidade(enderecoAtualizacaoDto.getCidade());
        enderecoExistente.setEstado(enderecoAtualizacaoDto.getEstado());

        return enderecoExistente;
    }
}
