package br.com.kitchen.club.bases;

import br.com.kitchen.club.config.exception.ParametroException;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<Entity, Filter, ShallowDto, EntityService extends BaseService<Entity>> {

    protected abstract EntityService getEntityService();

    private final EntityService entityService = getEntityService();

    @GetMapping(value = "/listAll")
    abstract public ResponseEntity<List<ShallowDto>> listAll();

    @GetMapping(value = "/{id}")
    abstract public ResponseEntity<Filter> listById(@PathVariable Long id);

    @PostMapping(value = "/new")
    abstract public ResponseEntity<String> createNew(@RequestBody @Valid Filter filter) throws NotImplementedException;

    @PutMapping(value = "/{id}")
    abstract public ResponseEntity<String> update(@RequestBody @Valid Filter filter);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Entity> entity = entityService.findById(id);
        entity.ifPresentOrElse(entityService::delete,
                () -> {
                    throw new ParametroException("Não foi possível encontrar o parâmetro de id: " + id);
                });
        return new ResponseEntity<>("Objeto deletado", HttpStatus.OK);
    }

    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
