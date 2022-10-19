package br.com.vr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vr.domain.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	public Optional<Cartao> findByNumero(Long numero);
}
