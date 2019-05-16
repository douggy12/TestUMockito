package com.mastermind.beans.plateau;

import java.util.List;

public class Combinaison {
	
	private List<Couleur> couleurs;

	public Combinaison(List<Couleur> couleurs) {
		super();
		this.couleurs = couleurs;
	}

	public List<Couleur> getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(List<Couleur> couleurs) {
		this.couleurs = couleurs;
	}
	
	
}
