package odontologica.clinica.clinicaOdont.service;

import jakarta.transaction.Transactional;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaCreateDTO;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaResponseDTO;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.ResourceNotFoundException;
import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;
import odontologica.clinica.clinicaOdont.repository.DentistaRepository;
import odontologica.clinica.clinicaOdont.service.validator.DentistaValidator;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;
    private final DentistaValidator dentistaValidator;

    public DentistaService(DentistaRepository dentistaRepository, DentistaValidator dentistaValidator) {
        this.dentistaRepository = dentistaRepository;
        this.dentistaValidator = dentistaValidator;
    }

    public List<DentistaResponseDTO> getDentistas() {

        List<Dentista> dentistaList = dentistaRepository.findAll();

        if (dentistaList.isEmpty()) {
            throw new ResourceNotFoundException("Não existe nenhum registro de dentistas no Banco de Dados.");
        }

        return dentistaList.stream()
                .map(dto -> new DentistaResponseDTO(dto))
                .toList();
    }

    public DentistaResponseDTO getDentistaById(Long id) {
        Dentista dentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não localizado."));

        return new DentistaResponseDTO(dentista);
    }

    public List<DentistaResponseDTO> getDentistaByNome(String nome) {

        List<Dentista> listaDentistas = dentistaRepository.findByNomeContainingIgnoreCase(nome);

        if (listaDentistas.isEmpty()) {
            throw new ResourceNotFoundException("Dentista não localizado. O NOME requerido não existe");
        }

        return listaDentistas.stream()
                .map(DentistaResponseDTO::new)
                .toList();
    }

    public DentistaResponseDTO getDentistaByCro(String cro) {

        Dentista dentista = dentistaRepository.findByCro(cro)
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não localizado. O CRO " + cro + " não foi localizado em nossa base de dados."));

        return new DentistaResponseDTO(dentista);

    }

    public List<DentistaResponseDTO> getDentistaByEspecialidade(Especialidade especialidade) {

            Optional<Dentista> listaDentistas = dentistaRepository.findByEspecialidade(especialidade);

            if (listaDentistas.isEmpty()) {
                throw new ResourceNotFoundException("Nenhum dentista foi localizado.");
            }

            return listaDentistas.stream()
                    .map(DentistaResponseDTO::new)
                    .toList();
        }

    public DentistaResponseDTO createDentista(DentistaCreateDTO dto) {
        Dentista dentista = new Dentista(dto);
        dentistaRepository.save(dentista);
        return new DentistaResponseDTO(dentista);
    }

    public List<DentistaResponseDTO> createDentistas(List<DentistaCreateDTO> dtosList) {

        List<Dentista> dentistaList = dtosList.stream()
                .map(Dentista::new)
                .toList();

        return dentistaRepository.saveAll(dentistaList).stream()
                .map(DentistaResponseDTO::new)
                .toList();
    }

    private void atualizarDentista(@NotNull Dentista antigoDentista, @NotNull DentistaUpdateDTO novoDentista) {
        antigoDentista.setCro(novoDentista.cro());
        antigoDentista.setNome(novoDentista.nome());
        antigoDentista.setEmail(novoDentista.email());
        antigoDentista.setTelefone(novoDentista.telefone());
        antigoDentista.setEspecialidade(novoDentista.especialidade());
    }

    @Transactional
    public DentistaResponseDTO updateDentistaById(Long id, DentistaUpdateDTO novoDentista) {

        Dentista antigoDentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID não localizado. ID solicitado: " + id));
        atualizarDentista(antigoDentista, novoDentista);

        dentistaValidator.validateUniqueFieldsForUpdate(antigoDentista, antigoDentista.getId());
        Dentista dentista = dentistaRepository.save(antigoDentista);
        return new DentistaResponseDTO(dentista);
    }

    @Transactional
    public DentistaResponseDTO updateDentistaByNome(String nome, DentistaUpdateDTO novoDentista) {

        Dentista antigoDentista = dentistaRepository.findByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não localizado. Nome solicitado: " + nome));
        atualizarDentista(antigoDentista, novoDentista);

        dentistaValidator.validateUniqueFieldsForUpdate(antigoDentista, antigoDentista.getId());
        Dentista dentista = dentistaRepository.save(antigoDentista);
        return new DentistaResponseDTO(dentista);
    }

    @Transactional
    public DentistaResponseDTO updateDentistaByCro(String cro, DentistaUpdateDTO novoDentista) {

        Dentista antigoDentista = dentistaRepository.findByCro(cro)
                .orElseThrow(() -> new ResourceNotFoundException("CRO não localizado. CRO solicitado: " + cro));
        atualizarDentista(antigoDentista, novoDentista);

        Dentista dentista = dentistaRepository.save(antigoDentista);
        return new DentistaResponseDTO(dentista);
    }

    @Transactional
    public void deleteDentistaById(Long id) {

        dentistaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("o ID requerido não foi localizado."));

        dentistaRepository.deleteById(id);
    }

    @Transactional
    public void deleteDentistaByNome(String nome) {
        dentistaRepository.findByNome(nome).orElseThrow(() -> new ResourceNotFoundException("o NOME requerido não foi localizado."));
        dentistaRepository.deleteByNome(nome);
    }

    @Transactional
    public void deleteDentistaByEspecialidade(Especialidade especialidade) {
        dentistaRepository.findByEspecialidade(especialidade)
                .orElseThrow(() -> new ResourceNotFoundException("a especialidade solicitada não existe."));

        dentistaRepository.deleteByEspecialidade(especialidade);
    }

}
