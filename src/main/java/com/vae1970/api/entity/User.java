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
@Table
@Entity
public class User extends BaseEntity {

    private String username;

    private String password;

}
