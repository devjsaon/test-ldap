package br.edu.puccampinas.pioxiiauthenticationapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/jon")
public class Hello {

	@RequestMapping("/hello")
    public String hello(){
    	return "Olá Spring!";
    }
}
