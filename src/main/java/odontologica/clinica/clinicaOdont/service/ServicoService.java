package odontologica.clinica.clinicaOdont.service;

import odontologica.clinica.clinicaOdont.dto.servico.ServicoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.ResourceNotFoundException;
import odontologica.clinica.clinicaOdont.model.Servico;
import odontologica.clinica.clinicaOdont.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<ServicoResponseDTO> getServicos() {

        List<Servico> servicoList = servicoRepository.findAll();

        if (servicoList.isEmpty()) {
            throw new ResourceNotFoundException("Não existem tasks registradas no Banco de Dados.");
        }

        return servicoList
                .stream()
                .map(ServicoResponseDTO::new)
                .toList();
    }

    public ServicoResponseDTO getServicoById(Long id) {
        Servico servico = servicoRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não localizado. Id solicitado: " + id));

        return new ServicoResponseDTO(servico);
    }

    public List<ServicoResponseDTO> getServicoByNome(String nome) {

        List<Servico> servicoList = servicoRepository.findByNomeContainingIgnoreCase(nome);

        if (servicoList.isEmpty()) {
            throw new ResourceNotFoundException("nome não localizado no banco de dados.");
        }

        return servicoList.stream().map(ServicoResponseDTO::new).toList();
    }

    public ServicoResponseDTO createServico(ServicoCreateDTO dto) {
        Servico servico = new Servico(dto);
        Servico servioSv = servicoRepository.save(servico);
        return new ServicoResponseDTO(servioSv);
    }

    public List<ServicoResponseDTO> createServicos(List<ServicoCreateDTO> dtoList) {

        List<Servico> servicos = dtoList.stream().map(dto -> {
            return new Servico(dto);
        }).toList();

        List<Servico> servicoList = servicoRepository.saveAll(servicos);

        return servicoList.stream()
                .map(ServicoResponseDTO::new)
                .toList();
    }

    @Transactional
    public Servico updateServicoById(Long id, ServicoUpdateDTO dto) {

        Servico servicoAntigo = servicoRepository.
                findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("falha ao localizar servico. o id requisitado não existe."));

        atualizarAtributos(servicoAntigo, dto);

        return servicoRepository.save(servicoAntigo);

    }

    @Transactional
    public Servico updateServicoByNome(String nome, ServicoUpdateDTO dto) {

        Servico servicoAntigo = servicoRepository
                .findByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Falha ao localizar serviço. O nome requisitado não existe."));

        atualizarAtributos(servicoAntigo, dto);

        return servicoRepository.save(servicoAntigo);
    }

    private void atualizarAtributos(Servico servicoAntigo, ServicoUpdateDTO dto){
        servicoAntigo.setDescricao(dto.descricao());
        servicoAntigo.setNome(dto.nome());
        servicoAntigo.setValor(dto.valor());
    }

    @Transactional
    public void deleteServicoById(Long id) {

        servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("falha ao localizar servico. o id requisitado não existe."));

        servicoRepository.deleteById(id);
    }

    @Transactional
    public void deleteServicoByNome(String nome) {
        servicoRepository.findByNome(nome).orElseThrow(() -> new ResourceNotFoundException("falha ao localizar servico. o nome requisitado não existe"));

        servicoRepository.deleteByNome(nome);
    }

}



