package com.mastermind.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mastermind.beans.plateau.Combinaison;
import com.mastermind.beans.plateau.Couleur;
import com.mastermind.exception.CombinaisonException;

@Service
public class CombinaisonService {
	
	private static final int valeurMin = 1;
	private static final int valeurMax = 7;

	public Combinaison genererCombinaison() throws CombinaisonException{
		List<Couleur> couleurs = new ArrayList<>();
		boolean different = false;
		for(int i=0; i<4 ;i++ ) {
			couleurs.add(getRandomCouleur());
			if(!couleurs.get(i).equals(couleurs.get(0))) {
				different = true;
			}
		}
		if (!different) {
			throw new CombinaisonException();
		}
		
		return new Combinaison(couleurs);
	}
	
	protected Couleur getRandomCouleur() {
		ArrayList<String> choix = new ArrayList<String>();
		choix.add("bleu");
		choix.add("jaune");
		choix.add("rouge");
		choix.add("vert");
		choix.add("orange");
		choix.add("violet");
		choix.add("noir");
		choix.add("blanc");
		
		Random r = new Random();
		int index = valeurMin + r.nextInt(valeurMax - valeurMin);
		
		return new Couleur(choix.get(index));
	}
}
