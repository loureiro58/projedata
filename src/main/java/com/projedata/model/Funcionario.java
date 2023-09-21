package com.projedata.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_funcionario")
public class Funcionario extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "salario")
    private BigDecimal salario;

    @Column(name = "funcao")
    private String funcao;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa")
    private Pessoa pessoa;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", salario=" + salario +
                ", funcao='" + funcao + '\'' +
                pessoa.toString() +
                '}';
    }
}
