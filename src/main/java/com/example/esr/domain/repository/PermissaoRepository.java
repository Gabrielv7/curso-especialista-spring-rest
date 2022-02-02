package com.example.esr.domain.repository;
import com.example.esr.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
