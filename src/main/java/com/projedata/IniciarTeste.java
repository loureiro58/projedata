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
import java.util.*;
import java.util.stream.Collectors;

@Component
public class IniciarTeste implements CommandLineRunner {

    @Autowired
    private FuncionarioRepositorio repositorioFuncionario;

    @Autowired
    private PessoaRepositorio repositorioPessoa;
    @Override
    public void run(String... args) throws Exception {

        System.out.println("\n\nINICIANDO TESTE\n");

        //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        inserirFuncionarios();
        System.out.println("3.1 – Funcionários inseridos.");

        //3.2 – Remover o funcionário “João” da lista.
        Pessoa joao = repositorioPessoa.findByNome("João");
        repositorioPessoa.delete(joao);
        System.out.println("3.2 – Funcionário “João” removido da lista.");

        //Recuperando lista de funcionários
        List<Funcionario> funcionarios = repositorioFuncionario.findAll();

        //3.3 – Imprimir todos os funcionários com todas suas informações
        System.out.println("3.3 – Imprimindo todos os funcionários com todas suas informações:");
        imprimirLista(funcionarios);

        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        atualizarSalario(BigDecimal.valueOf(10), funcionarios);
        //Mostrando que os salários foram atualizados no banco de dados
        funcionarios = repositorioFuncionario.findAll();
        System.out.println("3.4 – Salários dos funcionários atualizados com 10% de aumento:");
        imprimirLista(funcionarios);

        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<String>> agrupamentoPorFuncao = funcionarios.stream().collect(
            Collectors.groupingBy(
                    Funcionario::getFuncao,
                    Collectors.mapping(funcionario -> funcionario.getPessoa().getNome(), Collectors.toList())
            )
        );
        System.out.println("3.5 – Funcionários agrupados por função.");

        //3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("3.6 – Imprimindo os funcionários agrupados por função:");
        System.out.println(agrupamentoPorFuncao);

        System.out.println("\n3.7 – Não existe na especificação\n");

        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        List<Funcionario> funcionariosAniversariantes = funcionarios.stream()
                .filter(funcionario ->
                        funcionario.getPessoa().getDataNascimento().getMonth().getValue() == 10
                        || funcionario.getPessoa().getDataNascimento().getMonth().getValue() == 12
                )
                .collect(Collectors.toList());
        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12:");
        imprimirLista(funcionariosAniversariantes);

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Optional<Funcionario> funcionarioMaisVelho = funcionarios
                .stream()
                .min(Comparator.comparing(
                        funcionario -> funcionario.getPessoa().getDataNascimento().getYear()
                    )
                );
        if(funcionarioMaisVelho.isPresent()){
            System.out.println("3.9 – Imprimindo o funcionário com a maior idade:");
            System.out.println(
                    "Nome: " + funcionarioMaisVelho.get().getPessoa().getNome()
                    + ", Idade: " +  (  LocalDate.now().getYear() -
                                        funcionarioMaisVelho.get().getPessoa().getDataNascimento().getYear() )
            );
        }

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\n3.10 – Imprimindo a lista de funcionários por ordem alfabética:");
        Collections.sort(funcionarios);
        imprimirLista(funcionarios);

        //3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(funcionario -> funcionario.getSalario())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("3.11 – Imprimindo o total dos salários dos funcionários: ");
        System.out.println(totalSalarios);

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("\n3.12 – Imprimindo quantos salários mínimos ganha cada funcionário (considerando que o salário mínimo é R$1212.00): ");
        funcionarios.stream().forEach(funcionario -> {
            BigDecimal quantidadeSalarioMinino = funcionario.getSalario().divide(BigDecimal.valueOf(1212), 2);
            System.out.println(
                    "Nome: " + funcionario.getPessoa().getNome() +
                    ", Quantidade de salários minimos recebidos: " + quantidadeSalarioMinino.setScale(2)
            );
        });

        System.out.println("\nTESTE FINALIZADO\n\n");
    }

    private void atualizarSalario(BigDecimal porcentagem, List<Funcionario> funcionarios) {
        funcionarios.stream().forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(porcentagem).divide(BigDecimal.valueOf(100));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
            repositorioFuncionario.save(funcionario);
        });
    }

    private void imprimirLista(List<Funcionario> funcionarios){
        for(Funcionario f: funcionarios){
            System.out.println(f);
        }
        System.out.println("\n");
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
