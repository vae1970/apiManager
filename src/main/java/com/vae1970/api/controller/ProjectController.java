package com.vae1970.api.controller;

import com.vae1970.api.entity.Project;
import com.vae1970.api.entity.enums.ErrorCode;
import com.vae1970.api.entity.pojo.dto.ResultBean;
import com.vae1970.api.service.ProjectService;
import com.vae1970.api.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author vae
 */
@RestController
@RequestMapping("/projects")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public Page page(Pageable pageable) {
        return projectService.page(pageable);
    }

    @GetMapping("/{id}")
    public ResultBean getProject(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResultUtil.toResult(ErrorCode.NO_ERROR, project);
    }

}
