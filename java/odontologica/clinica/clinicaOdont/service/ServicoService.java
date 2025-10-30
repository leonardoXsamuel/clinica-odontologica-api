package odontologica.clinica.clinicaOdont.service;

import odontologica.clinica.clinicaOdont.model.Servico;
import odontologica.clinica.clinicaOdont.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    // GET
    public Optional<Servico> getServicoById(Long id) {
        return servicoRepository.findById(id);
    }

    public List<Servico> getServicos() {
        return servicoRepository.findAll();
    }

    //semelhante ao (SQL)LIKE '%%'
    public List<Servico> getServicoByNome(String nome) {
        return servicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    // POST
    public Servico createServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> createServicos(List<Servico> servicoList) {
        return servicoRepository.saveAll(servicoList);
    }

    // PUT
    @Transactional
    public Servico updateServicoById(Long id, Servico novosDados) throws Exception {

        Servico servicoAntigo = servicoRepository.findById(id).orElseThrow(() -> new Exception("falha ao localizar servico. o id requisitado não existe."));


        servicoAntigo.setDescricao(novosDados.getDescricao());
        servicoAntigo.setNome(novosDados.getNome());
        servicoAntigo.setValor(novosDados.getValor());

        return servicoRepository.save(servicoAntigo);

    }

    @Transactional
    public Servico updateServicoByNome(String nome, Servico novosDados) throws Exception {

        Servico servicoAntigo = servicoRepository.findByNome(nome).orElseThrow(() -> new Exception("Falha ao localizar serviço. O nome requisitado não existe."));

        servicoAntigo.setDescricao(novosDados.getDescricao());
        servicoAntigo.setNome(novosDados.getNome());
        servicoAntigo.setValor(novosDados.getValor());

        return servicoRepository.save(servicoAntigo);
    }

    // DELETE

    @Transactional
    public void deleteServicoById(Long id) throws Exception {

        servicoRepository.findById(id)
                .orElseThrow(() -> new Exception("falha ao localizar servico. o id requisitado não existe."));

        servicoRepository.deleteById(id);
    }

    @Transactional
    public void deleteServicoByNome(String nome) throws Exception {
        servicoRepository.findByNome(nome).
                orElseThrow(() -> new Exception("falha ao localizar servico. o nome requisitado não existe"));

        servicoRepository.deleteByNome(nome);
    }

}



