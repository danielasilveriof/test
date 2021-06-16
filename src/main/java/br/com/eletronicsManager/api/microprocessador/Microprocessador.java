package br.com.eletronicsManager.api.microprocessador;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author aula
 */
@Entity
@Table(name = "microprocessadores", schema="public")
public class Microprocessador implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column(nullable = false)
    private String codFabricante;
    
    @Column(nullable = false)
    private double preco;
    
    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;
    
    @Column(nullable = false)
    private String arquitetura;
    
    @Column(nullable = false)
    private String fabricante;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodFabricante() {
        return codFabricante;
    }

    public void setCodFabricante(String codFabricante) {
        this.codFabricante = codFabricante;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDatalancamento() {
        return dataLancamento;
    }

    public void setDatalancamento(LocalDate datalancamento) {
        this.dataLancamento = datalancamento;
    }

    public String getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(String arquitetura) {
        this.arquitetura = arquitetura;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

}
