package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TypeMachine")
public class TypeMachine {

    @Id
    private String typeMachine;


    public TypeMachine() {
    }

}

