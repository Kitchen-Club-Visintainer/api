package br.com.kitchen.club.bases;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService {

    public abstract JpaRepository getService();

    public List findAll(){
        return getService().findAll();
    }

    public Optional findById(Long id){
        return getService().findById(id);
    }

    public void save(Object object){
        getService().save(object);
    }


}
