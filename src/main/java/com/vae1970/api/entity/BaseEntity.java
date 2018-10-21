package com.vae1970.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * base entity
 *
 * @author vae
 */
@Getter
@Setter
@MappedSuperclass
@Accessors(chain = true)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED")
    protected Long id;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(updatable = false, columnDefinition = "BIGINT(20) UNSIGNED")
    private Long createdBy;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL")
    private Date updatedAt;

    @Column(columnDefinition = "BIGINT(20) UNSIGNED")
    private Long updatedBy;

    @Version
    @Column(columnDefinition = "INT(10) UNSIGNED DEFAULT 0")
    private Integer version;

}
