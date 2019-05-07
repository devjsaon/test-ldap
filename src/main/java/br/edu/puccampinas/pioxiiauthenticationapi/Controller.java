package br.edu.puccampinas.pioxiiauthenticationapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Controller {

	@Autowired
	private Repo repo;
	
    @PostMapping("/login")
    public boolean auth(@RequestBody User user){
        return repo.auth(user.getUid(), user.getPassword());
    }
}
