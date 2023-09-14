package br.com.kitchen.club.repository;

import br.com.kitchen.club.entity.LivroReceita;
import br.com.kitchen.club.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroReceitaRepository extends JpaRepository<LivroReceita, Long> {

    Optional<LivroReceitaRepository> findByUsuario(Usuario usuario);

}
