package odontologica.clinica.clinicaOdont.service.validator;

import odontologica.clinica.clinicaOdont.exceptions.ConflictException;
import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.repository.DentistaRepository;
import org.springframework.stereotype.Component;

@Component
public class DentistaValidator {

    private final DentistaRepository dentistaRepository;

    public DentistaValidator(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    public void validateUniqueFieldsForUpdate(Dentista antigoDentista, Long id) {

        if (dentistaRepository.existsByCroAndIdNot(antigoDentista.getCro(), id)) {
            throw new ConflictException("o dentista já existe.\nNOME: " + antigoDentista.getNome() + "\nCRO: " + antigoDentista.getCro() + ".");
        }

        if (dentistaRepository.existsByEmailAndIdNot(antigoDentista.getEmail(), id)) {
            throw new ConflictException("o dentista já existe.\nNOME: " + antigoDentista.getNome() + "\nCRO: " + antigoDentista.getCro() + ".");
        }

        if (dentistaRepository.existsByTelefoneAndIdNot(antigoDentista.getTelefone(), id)) {
            throw new ConflictException("o dentista já existe.\nNOME: " + antigoDentista.getNome() + "\nCRO: " + antigoDentista.getCro() + ".");
        }

    }

}
