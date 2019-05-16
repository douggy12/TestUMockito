package com.mastermind.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.mastermind.beans.plateau.Combinaison;
import com.mastermind.beans.plateau.Couleur;
import com.mastermind.exception.CombinaisonException;

@RunWith(MockitoJUnitRunner.class)
public class CombinaisonServiceTest {

	@InjectMocks
	@Spy
	private CombinaisonService service;
	
	private ArrayList<String> autorises = new ArrayList<String>();
	
	@Before
	public void setup() {
		
		autorises.add("bleu");
		autorises.add("jaune");
		autorises.add("rouge");
		autorises.add("vert");
		autorises.add("orange");
		autorises.add("violet");
		autorises.add("noir");
		autorises.add("blanc");
	}
	
	@Test
	public void genererCombinaison_should_returnCombinaison() throws CombinaisonException {
		assertThat(service.genererCombinaison()).isNotNull();
	}
	
	@Test
	public void genererCombinaison_should_return4Couleurs() throws CombinaisonException {
		Combinaison combinaison = service.genererCombinaison();
		assertThat(combinaison.getCouleurs()).hasSize(4);
	}
	
	@Test
	public void genererCombinaison_should_beAuthorise() throws CombinaisonException {
		//given
		//when
		Combinaison combinaison = service.genererCombinaison();
		//then
		assertThat(combinaison.getCouleurs()).allSatisfy(couleur -> {
			assertThat(couleur.getNom()).isIn(autorises);
		});
	}
	@Test(expected = CombinaisonException.class)
	public void generercombinaison_shoulnot_return_combinaisonMonoCouleurs() throws CombinaisonException {
		//given
//		when(service.getRandomCouleur()).thenReturn(new Couleur("bleu"));
		doReturn(new Couleur("bleu")).when(service).getRandomCouleur();
		//when
		Combinaison combinaison = service.genererCombinaison();
		//then
		//on y arrive pas parcequ'il y a une exception levée avant
		assertThat(combinaison.getCouleurs()).allSatisfy(couleur -> {
			assertThat(couleur.getNom()).isEqualTo("bleu");
		});
	}
}
