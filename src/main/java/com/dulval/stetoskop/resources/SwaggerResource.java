/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author Diego Dulval
 */
@ApiIgnore
@Controller
public class SwaggerResource {

    @GetMapping("/")
    public ModelAndView showDoc() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
