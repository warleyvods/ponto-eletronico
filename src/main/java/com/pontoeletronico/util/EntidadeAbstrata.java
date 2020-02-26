package com.pontoeletronico.util;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Data
@MappedSuperclass
public class EntidadeAbstrata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
