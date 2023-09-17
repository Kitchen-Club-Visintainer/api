package br.com.kitchen.club.repository;

import br.com.kitchen.club.entity.ItensReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensReceitaRepository extends JpaRepository<ItensReceita, Long> {
}
