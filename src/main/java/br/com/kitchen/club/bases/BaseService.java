package br.com.kitchen.club.bases;

import br.com.kitchen.club.config.webclient.RestClient;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<Entity> {

    protected final RestClient restClient;

    protected final Log logger = LogFactory.getLog(this.getClass());

    protected BaseService(RestClient restClient) {
        this.restClient = restClient;
    }

    public abstract JpaRepository<Entity, Long> getRepository();

    public List<Entity> findAll() {
        return getRepository().findAll();
    }

    public Optional<Entity> findById(Long id) {
        return getRepository().findById(id);
    }

    public void save(Entity entity) {
        getRepository().save(entity);
    }

    public void delete(Entity entity) {
        getRepository().delete(entity);
    }

    public abstract void validateUser(String username);

}
