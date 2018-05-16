package com.tiki.bootway.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2018-05-16
 * Time: 19:32
 */
@RestController
@RequestMapping(name = "HelloWorld Controller",value = "/hello/*")
public class HelloController {

    @RequestMapping(value = "/world/{name}")
    public String hello(@PathVariable String name){
        return "Hello ~"+name;
    }

}
