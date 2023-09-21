package com.projedata;

import com.projedata.model.Funcionario;
import com.projedata.model.Pessoa;
import com.projedata.repositorio.FuncionarioRepositorio;
import com.projedata.repositorio.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class IniciarTeste implements CommandLineRunner {

    @Autowired
    private FuncionarioRepositorio repositorioFuncionario;

    @Autowired
    private PessoaRepositorio repositorioPessoa;
    @Override
    public void run(String... args) throws Exception {

        //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        inserirFuncionarios();

        //3.2 – Remover o funcionário “João” da lista.
        repositorioPessoa.deletarPessoaPorNome("João");

        List<Funcionario> funcionarios = repositorioFuncionario.findAll();
        for(Funcionario f: funcionarios){
            System.out.println(f);
        }

    }

    private void inserirFuncionarios(){
        for (int i = 0; i < 10; i++) {
            Pessoa pessoa = new Pessoa();
            Funcionario funcionario = new Funcionario();

            switch (i){
                case 0:{
                    pessoa.setNome("Maria");
                    pessoa.setDataNascimento(LocalDate.of(2000,10,18));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(2009.44));
                    funcionario.setFuncao("Operador");
                    break;
                }
                case 1:{
                    pessoa.setNome("João");
                    pessoa.setDataNascimento(LocalDate.of(1990,05,12));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(2284.38));
                    funcionario.setFuncao("Operador");
                    break;
                }
                case 2:{
                    pessoa.setNome("Caio");
                    pessoa.setDataNascimento(LocalDate.of(1961,05,02));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(9836.14));
                    funcionario.setFuncao("Coordenador");
                    break;
                }
                case 3:{
                    pessoa.setNome("Miguel");
                    pessoa.setDataNascimento(LocalDate.of(1988,10,14));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(19119.88));
                    funcionario.setFuncao("Diretor");
                    break;
                }
                case 4:{
                    pessoa.setNome("Alice");
                    pessoa.setDataNascimento(LocalDate.of(1995,01,05));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(2234.68));
                    funcionario.setFuncao("Recepcionista");
                    break;
                }
                case 5:{
                    pessoa.setNome("Heitor");
                    pessoa.setDataNascimento(LocalDate.of(1999,11,19));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(1582.72));
                    funcionario.setFuncao("Operador");
                    break;
                }
                case 6:{
                    pessoa.setNome("Arthur");
                    pessoa.setDataNascimento(LocalDate.of(1993,03,31));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(4071.84));
                    funcionario.setFuncao("Contador");
                    break;
                }
                case 7:{
                    pessoa.setNome("Laura");
                    pessoa.setDataNascimento(LocalDate.of(1994,07,8));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(3017.45));
                    funcionario.setFuncao("Gerente");
                    break;
                }
                case 8:{
                    pessoa.setNome("Heloísa");
                    pessoa.setDataNascimento(LocalDate.of(2003,05,24));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(1606.85));
                    funcionario.setFuncao("Eletricista");
                    break;
                }
                case 9:{
                    pessoa.setNome("Helena");
                    pessoa.setDataNascimento(LocalDate.of(1996,9,02));
                    funcionario.setId(i);
                    funcionario.setSalario(BigDecimal.valueOf(2799.93));
                    funcionario.setFuncao("Gerente");
                    break;
                }

            }

            funcionario.setPessoa(pessoa);
            repositorioFuncionario.save(funcionario);
        }

    }


}
