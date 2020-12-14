package com.example.demo.dto;

import com.example.demo.model.Materia;

import java.io.Serializable;
import java.util.Objects;

public class MateriaDto implements Serializable {
    private static final long  serialVersionUID=1L;

    private Long id;
    private String name;

    public MateriaDto() {
    }

    public MateriaDto(Materia entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MateriaDto that = (MateriaDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
