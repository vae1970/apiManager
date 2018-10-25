package com.vae1970.api.service;

import com.vae1970.api.entity.Project;
import com.vae1970.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @author vae
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Page page(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public void save(Project project) {
        projectRepository.save(project);
    }
}
