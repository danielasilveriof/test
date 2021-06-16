package br.com.eletronicsManager.api.fabricante;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fabricantes", schema="public")
public class Fabricante implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String pais;
    
    @Column(name = "data_fundacao")
    private LocalDate dataFundacao;
    
    @Column(nullable = false)
    private String logo;
    
    @Column(nullable = false)
    private Boolean reprBr;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getDatafundacao() {
        return dataFundacao;
    }

    public void setDatafundacao(LocalDate datafundacao) {
        this.dataFundacao = datafundacao;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getReprBr() {
        return reprBr;
    }

    public void setReprBr(Boolean reprBr) {
        this.reprBr = reprBr;
    }
    
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

}
