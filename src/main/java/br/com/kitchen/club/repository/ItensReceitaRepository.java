package br.com.kitchen.club.repository;

import br.com.kitchen.club.entity.ItensReceita;
import br.com.kitchen.club.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensReceitaRepository extends JpaRepository<ItensReceita, Long> {

    List<ItensReceita> findByReceita(Receita receita);
}
