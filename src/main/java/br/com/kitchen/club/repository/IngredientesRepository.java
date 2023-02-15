package br.com.kitchen.club.repository;

import br.com.kitchen.club.entity.Ingredientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingredientes, Long> {
}
