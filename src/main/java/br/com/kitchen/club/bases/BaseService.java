package br.com.kitchen.club.bases;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService {

    protected final Log logger = LogFactory.getLog(BaseService.class);

    public abstract JpaRepository getRepository();

    public List findAll(){
        return getRepository().findAll();
    }

    public Optional findById(Long id){
        return getRepository().findById(id);
    }

    public void save(Object object){
        getRepository().save(object);
    }


}
