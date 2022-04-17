package de.mgeldi.thiccthocc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {

    @RequestMapping("/auth")
    public Principal user(Principal user) {
        return user;
    }
}
