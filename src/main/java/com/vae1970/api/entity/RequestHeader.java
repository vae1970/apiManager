package com.vae1970.api.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author vae
 */
@Getter
@Setter
@Table
@Entity
@EqualsAndHashCode(callSuper = false)
public class RequestHeader extends BaseEntity {

    @ManyToOne
    private Api api;

    @Column(name = "[key]")
    private String key;

    @Column(name = "[value]")
    private String value;

    @Column(name = "[index]")
    private Integer index;

}
