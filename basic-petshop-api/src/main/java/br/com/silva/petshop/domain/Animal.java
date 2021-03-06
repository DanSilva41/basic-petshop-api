package br.com.silva.petshop.domain;

import br.com.silva.petshop.domain.enums.SexoAnimal;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Danilo Silva
 */
@Entity
@Table(name = "animal")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull(message = "{animal.nome.not.null}")
    @Size(min = 3, max = 60)
    @Column(name = "nome", length = 60)
    private String nome;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @Size(min = 3, max = 60)
    @Column(name = "cor", length = 60)
    private String cor;

    @NotNull(message = "{animal.sexo.not.null}")
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false, length = 5)
    private SexoAnimal sexo;

    @NotNull(message = "{animal.especie.not.null}")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="codigo_especie")
    private Especie especie;

    public Animal() {
    }

    public Animal(Long codigo, String nome, LocalDate dataNascimento, SexoAnimal sexo, String cor, Especie especie) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cor = cor;
        this.especie = especie;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoAnimal getSexo() {
        return sexo;
    }

    public void setSexo(SexoAnimal sexo) {
        this.sexo = sexo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(getCodigo(), animal.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }


}
