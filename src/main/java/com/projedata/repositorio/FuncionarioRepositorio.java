package com.projedata.repositorio;


import com.projedata.model.Funcionario;
import com.projedata.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Integer> {

    //Query Method
//    List<Funcionario> findByNameContaining(String nome);

    //Query Method
  //  Funcionario findByUsername(String username);

    //Query Override
    //@Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    //List<Funcionario> filtrarPorNome(@Param("name") String name);


}
