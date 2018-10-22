package com.vae1970.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Table
@Entity
public class ProjectUser extends BaseEntity {

    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;

}
