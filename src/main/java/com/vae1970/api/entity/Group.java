package com.vae1970.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author vae
 */
@Getter
@Setter
@Table(name = "[group]")
@Entity
public class Group extends BaseEntity {

    private String name;

    private String title;

}
