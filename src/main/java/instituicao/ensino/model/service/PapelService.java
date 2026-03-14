package instituicao.ensino.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import instituicao.ensino.model.entity.Papel;
import instituicao.ensino.model.repository.PapelRepository;

@Service
public class PapelService {
    @Autowired
    private PapelRepository papelRepository;

    public void save(Papel papel) {
        papelRepository.save(papel);
    }
}
