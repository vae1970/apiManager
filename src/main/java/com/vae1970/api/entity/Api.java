package com.vae1970.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author vae
 */
@Getter
@Setter
@Table
@Entity
public class Api extends BaseEntity {

    private String name;

    @Column(name = "[usage]")
    private String usage;

    private String method;

    private String location;

    @OneToMany(mappedBy = "api", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RequestHeader> requestHeaderSet;

    @OneToMany(mappedBy = "api", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RequestParameter> requestParameterSet;

    private String responseBody;

    private String remark;

}
