package com.vae1970.api.repository;

import com.vae1970.api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vae
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
