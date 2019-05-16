package com.mastermind.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastermind.beans.plateau.Combinaison;
import com.mastermind.beans.rest.Reponse;
import com.mastermind.exception.CombinaisonException;
import com.mastermind.services.CombinaisonService;

@RestController
public class MainController {
	@Autowired
	CombinaisonService combinaisonService;
	
	@Value("${server.port}")
	private int port;
	
	@GetMapping("/debuter")
	public ResponseEntity<Reponse> debuter(HttpSession session) throws CombinaisonException{
		
		try {
			session.setAttribute("combinaison", combinaisonService.genererCombinaison());
		} catch (CombinaisonException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Reponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Reponse>(new Reponse("en cours", 1),HttpStatus.OK);
				
	}
	
	@GetMapping("/info")
	public int info() {
		return port;
	}
	
//	@ExceptionHandler({CombinaisonException.class})
//	public ResponseEntity<Reponse> erreur(){
//		return new ResponseEntity<Reponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
