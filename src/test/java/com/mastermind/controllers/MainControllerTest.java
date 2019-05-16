package com.mastermind.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.ResultActions;

import com.mastermind.beans.plateau.Combinaison;
import com.mastermind.beans.rest.Reponse;
import com.mastermind.exception.CombinaisonException;
import com.mastermind.services.CombinaisonService;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {
	
	@InjectMocks
	private MainController controller;
	
	@Mock
	private HttpSession httpSession;
	@Mock
	private CombinaisonService combinaisonService;
	
	@Test
	public void debuter_should_return_reponse() throws CombinaisonException {
		//given
		
		//when
		ResponseEntity<Reponse> resultat = controller.debuter(httpSession);
		//then
		assertThat(resultat).isNotNull();
	}
	
	@Test
	public void debuter_should_return_etatEnCours() throws CombinaisonException {
		//given
		//when
		ResponseEntity<Reponse> resultat = controller.debuter(httpSession);
		//then
		assertThat(resultat.getBody().getEtat()).isEqualTo("en cours");
	}
	
	@Test
	public void debuter_should_return_numPropositionEgal1() throws CombinaisonException {
		//given
		//when
		ResponseEntity<Reponse> resultat = controller.debuter(httpSession);
		//then
		assertThat(resultat.getBody().getNumProposition()).isEqualTo(1);
	}
	
	@Test
	public void debuter_should_return_httpOK() throws CombinaisonException {
		//given
		//when
		ResponseEntity<Reponse> resultat = controller.debuter(httpSession);
		//then
		assertThat(resultat.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void debuter_should_call_genererCombinaison() throws CombinaisonException {
		//given
		//when
		controller.debuter(httpSession);
		//then
		verify(combinaisonService).genererCombinaison();
	}
	
	@Test
	public void debuter_should_save_combinaisonInSession() throws CombinaisonException {
		//given
		Combinaison combinaison = new Combinaison(null);
		when(combinaisonService.genererCombinaison()).thenReturn(combinaison);
		ArgumentCaptor<String> argCaptor0 = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Combinaison> argCaptor1 = ArgumentCaptor.forClass(Combinaison.class);
		//when
		controller.debuter(httpSession);
		//then
		verify(httpSession).setAttribute(argCaptor0.capture(), argCaptor1.capture());
		assertThat(argCaptor0.getValue()).isEqualTo("combinaison");
		assertThat(argCaptor1.getValue()).isEqualTo(combinaison);
	}
	
	@Test
	public void debuter_should_return_http500_when_raiseCombinaisonException() throws CombinaisonException {
		//given
//		Combinaison combinaison = new Combinaison(null);
		when(combinaisonService.genererCombinaison()).thenThrow(new CombinaisonException());
		//when
		ResponseEntity<Reponse> resultat = controller.debuter(httpSession);
		assertThat(resultat.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void info_should_return_portInfo() {
		//given
		ReflectionTestUtils.setField(controller, "port", 77);
		//when
		assertThat(controller.info()).isEqualTo(77);
		//then
	}

}
