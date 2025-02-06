package com.exa_aop.aopService;

import org.springframework.stereotype.Component;

@Component
public class ProjectService {

    public void add() {
        System.out.println("add...");
    }

    @AnnLog1("修改 project")
    public void edit() {
        System.out.println("edit...");
    }

    public void delete() {
        System.out.println("delete...");
    }
}
