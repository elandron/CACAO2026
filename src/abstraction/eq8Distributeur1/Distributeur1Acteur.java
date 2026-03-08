package abstraction.eq8Distributeur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eqXRomu.filiere.Filiere;
import abstraction.eqXRomu.filiere.IActeur;
import abstraction.eqXRomu.general.Journal;
import abstraction.eqXRomu.general.Variable;
import abstraction.eqXRomu.produits.ChocolatDeMarque;
import abstraction.eqXRomu.produits.IProduit;

public class Distributeur1Acteur implements IActeur {
	
	protected Journal journal0;
	protected Variable volumeStock;/** @author Alexandre Cornet */
	protected Variable RayonHauteGamme;/** @author Alexandre Cornet */
	protected int cryptogramme;/** @author Alexandre Cornet */
	protected HashMap<IProduit, Double> Stock;/** @author Alexandre Cornet */

	public Distributeur1Acteur() {
		this.journal0 = new Journal("Journal Eq 8 numéro étape ", this);
		this.volumeStock=new Variable("volumeStock", this); /** @author Alexandre Cornet */
		this.RayonHauteGamme = new Variable("RayonHauteGamme",this); /** @author Alexandre Cornet */
		this.Stock = new HashMap<IProduit,Double>();/** @author Alexandre Cornet */
	}
	
	public void initialiser() {
		/** @author Alexandre Cornet */
		List<ChocolatDeMarque> p=Filiere.LA_FILIERE.getChocolatsProduits();
		for (int i=0; i<p.size(); i++){
			this.Stock.put((IProduit)(p.get(i)),10.0);
			this.volumeStock.ajouter(this,getQuantiteEnStock((IProduit)(p.get(i)),this.cryptogramme));
		}
	}

	public String getNom() {// NE PAS MODIFIER
		return "EQ8";
	}
	
	public String toString() {// NE PAS MODIFIER
		return this.getNom();
	}

	////////////////////////////////////////////////////////
	//         En lien avec l'interface graphique         //
	////////////////////////////////////////////////////////

	public void next() {
		this.journal0.ajouter("Numéro de tour : " + Filiere.LA_FILIERE.getEtape());
		this.getvolumestock();/** @author Alexandre Cornet */
	}
	public Color getColor() {// NE PAS MODIFIER
		return new Color(209, 179, 221); 
	}
	/** @author Alexandre Cornet */
	public Variable getvolumestock(){
		List<ChocolatDeMarque> p=Filiere.LA_FILIERE.getChocolatsProduits();
		this.volumeStock.setValeur(this,0.0);
		for (int i=0; i<p.size(); i++){
			this.volumeStock.ajouter(this,getQuantiteEnStock((IProduit)(p.get(i)),this.cryptogramme));
		}
		return this.volumeStock;
	}

	public String getDescription() {
		return "Bla bla bla";
	}

	// Renvoie les indicateurs
	/** @author Alexandre Cornet */
	public List<Variable> getIndicateurs() {
		List<Variable> res = new ArrayList<Variable>();
		res.add(this.volumeStock);
		res.add(RayonHauteGamme);
		return res;
	}

	// Renvoie les parametres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.journal0);
		return res;
	}

	////////////////////////////////////////////////////////
	//               En lien avec la Banque               //
	////////////////////////////////////////////////////////

	// Appelee en debut de simulation pour vous communiquer 
	// votre cryptogramme personnel, indispensable pour les
	// transactions.
	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}

	// Appelee lorsqu'un acteur fait faillite (potentiellement vous)
	// afin de vous en informer.
	public void notificationFaillite(IActeur acteur) {
	}

	// Apres chaque operation sur votre compte bancaire, cette
	// operation est appelee pour vous en informer
	public void notificationOperationBancaire(double montant) {
	}
	
	// Renvoie le solde actuel de l'acteur
	protected double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

	////////////////////////////////////////////////////////
	//        Pour la creation de filieres de test        //
	////////////////////////////////////////////////////////

	// Renvoie la liste des filieres proposees par l'acteur
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		return(filieres);
	}

	// Renvoie une instance d'une filiere d'apres son nom
	public Filiere getFiliere(String nom) {
		return Filiere.LA_FILIERE;
	}
	/** @author Alexandre Cornet */
	public double getQuantiteEnStock(IProduit p, int cryptogramme) {
		if (this.cryptogramme==cryptogramme) { // c'est donc bien un acteur assermente qui demande a consulter la quantite en stock
			return this.Stock.get(p);
		} else {
			return 0; // Les acteurs non assermentes n'ont pas a connaitre notre stock
		}
	}
}
