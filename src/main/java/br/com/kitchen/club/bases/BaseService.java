package br.com.kitchen.club.bases;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<Entity> {

    protected final Log logger = LogFactory.getLog(BaseService.class);

    public abstract JpaRepository<Entity, Long> getRepository();

    public List<Entity> findAll(){
        return getRepository().findAll();
    }

    public Optional<Entity> findById(Long id){
        return getRepository().findById(id);
    }

    public void save(Entity entity){
        getRepository().save(entity);
    }


}
