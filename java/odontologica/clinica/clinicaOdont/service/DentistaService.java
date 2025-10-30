package odontologica.clinica.clinicaOdont.service;

import jakarta.transaction.Transactional;
import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.repository.DentistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;

    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    // GET
    public List<Dentista> getDentistas() {
        return dentistaRepository.findAll();
    }

    public Optional<Dentista> getDentistaById(Long id) {
        return dentistaRepository.findById(id);
    }

    public List<Dentista> getDentistaByNome(String nome) throws Exception {

        List<Dentista> listaDentistas = dentistaRepository.findByNomeContainingIgnoreCase(nome);

        if (listaDentistas.isEmpty()) {
            throw new Exception("Dentista não localizado. O NOME requerido não existe");
        }

        return listaDentistas;
    }

    public Optional<Dentista> getDentistaByCro(String cro) throws Exception {
        Optional<Dentista> listaDentistas = dentistaRepository.findByCroContainingIgnoreCase(cro);

        if (listaDentistas.isEmpty()) {
            throw new Exception("Dentista não localizado. O CRO requerido não existe");
        }

        return listaDentistas;
    }

    public Optional<Dentista> getDentistaByEspecialidade(Dentista.Especialidade especialidade) throws Exception {

        dentistaRepository.findByEspecialidade(especialidade).
                orElseThrow(() -> new Exception("Dentista não localizado. A ESPECIALIDADE requerido não existe"));

        return dentistaRepository.findByEspecialidade(especialidade);


    }

    // POST
    public Dentista createDentista(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public List<Dentista> createDentistas(List<Dentista> listDentistas) {
        return dentistaRepository.saveAll(listDentistas);
    }

    // PUT
    @Transactional
    public Dentista updateDentistaById(Long id, Dentista novoDentista) throws Exception {
        Dentista antigoDentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new Exception("o ID requerido não foi localizado."));

        antigoDentista.setCro(novoDentista.getCro());
        antigoDentista.setNome(novoDentista.getNome());
        antigoDentista.setEmail(novoDentista.getEmail());
        antigoDentista.setTelefone(novoDentista.getTelefone());
        antigoDentista.setEspecialidade(novoDentista.getEspecialidade());

        return dentistaRepository.save(antigoDentista);
    }

    @Transactional
    public Dentista updateDentistaByCro(String cro, Dentista novoDentista) throws Exception {
        Dentista antigoDentista = dentistaRepository.findByCro(cro)
                .orElseThrow(() -> new Exception("o CRO requerido não foi localizado."));

        antigoDentista.setCro(novoDentista.getCro());
        antigoDentista.setNome(novoDentista.getNome());
        antigoDentista.setTelefone(novoDentista.getTelefone());
        antigoDentista.setEmail(novoDentista.getEmail());
        antigoDentista.setEspecialidade(novoDentista.getEspecialidade());

        return dentistaRepository.save(antigoDentista);
    }

    @Transactional
    public Dentista updateDentistaByNome(String nome, Dentista novoDentista) throws Exception {
        Dentista antigoDentista = dentistaRepository.findByNome(nome)
                .orElseThrow(() -> new Exception("o NOME requerido não foi localizado."));

        antigoDentista.setCro(novoDentista.getCro());
        antigoDentista.setNome(novoDentista.getNome());
        antigoDentista.setTelefone(novoDentista.getTelefone());
        antigoDentista.setEmail(novoDentista.getEmail());
        antigoDentista.setEspecialidade(novoDentista.getEspecialidade());

        return dentistaRepository.save(antigoDentista);
    }

    // DELETE - @transactional
    @Transactional
    public void deleteDentistaById(Long id) throws Exception {

        dentistaRepository.findById(id)
                .orElseThrow(() -> new Exception("o ID requerido não foi localizado."));

        dentistaRepository.deleteById(id);
    }

    @Transactional
    public void deleteDentistaByNome(String nome) throws Exception {
        dentistaRepository.findByNome(nome).
                orElseThrow(() -> new Exception("o NOME requerido não foi localizado."));

        dentistaRepository.deleteByNome(nome);
    }

    @Transactional
    public void deleteDentistaByEspecialidade(Dentista.Especialidade especialidade) throws Exception {
        dentistaRepository.findByEspecialidade(especialidade)
                .orElseThrow(() -> new Exception("a especialidade solicitada não existe."));

        dentistaRepository.deleteByEspecialidade(especialidade);
    }

}
