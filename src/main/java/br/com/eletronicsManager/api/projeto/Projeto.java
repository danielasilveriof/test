package br.com.eletronicsManager.api.projeto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projetos", schema="public")
public class Projeto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String engResponsavel;
    
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    
    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;
    
    @Column(nullable = false)
    private String microprocessador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEngResponsavel() {
        return engResponsavel;
    }

    public void setEngResponsavel(String engResponsavel) {
        this.engResponsavel = engResponsavel;
    }

    public LocalDate getDatainicio() {
        return dataInicio;
    }

    public void setDatainicio(LocalDate datainicio) {
        this.dataInicio = datainicio;
    }

    public LocalDate getDataconclusao() {
        return dataConclusao;
    }

    public void setDataconclusao(LocalDate dataconclusao) {
        this.dataConclusao = dataconclusao;
    }

    public String getMicroprocessador() {
        return microprocessador;
    }

    public void setMicroprocessador(String microprocessador) {
        this.microprocessador = microprocessador;
    }

    
}
