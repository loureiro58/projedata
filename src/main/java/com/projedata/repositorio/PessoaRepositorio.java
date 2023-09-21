package com.projedata.repositorio;


import com.projedata.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer> {

    //Query Method
//    List<Funcionario> findByNameContaining(String nome);

    //Query Method
  //  Funcionario findByUsername(String username);

    @Query("DELETE FROM tb_pessoa WHERE nome LIKE %:nome%")
    void deletarPessoaPorNome(@Param("nome") String nome);


}
