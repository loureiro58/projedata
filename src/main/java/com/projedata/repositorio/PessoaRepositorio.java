package com.projedata.repositorio;


import com.projedata.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer> {

    @Query("Select p FROM com.projedata.model.Pessoa p WHERE p.nome LIKE %:nome%")
    Pessoa findByNome(@Param("nome") String nome);

}
