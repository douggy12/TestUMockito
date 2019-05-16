package com.mastermind.beans.rest;

public class Reponse {
	
	private String etat;
	private int numProposition;
	
	public Reponse(String etat, int numProposition) {
		super();
		this.etat = etat;
		this.numProposition = numProposition;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getNumProposition() {
		return numProposition;
	}

	public void setNumProposition(int numProposition) {
		this.numProposition = numProposition;
	}
	
	

}
