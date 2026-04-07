package abstraction.eq6Transformateur3;

import abstraction.eqXRomu.encheres.IVendeurAuxEncheres;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eqXRomu.bourseCacao.BourseCacao;
import abstraction.eqXRomu.encheres.Enchere;
import abstraction.eqXRomu.encheres.SuperviseurVentesAuxEncheres;
import abstraction.eqXRomu.filiere.Filiere;
import abstraction.eqXRomu.general.Journal;
import abstraction.eqXRomu.produits.ChocolatDeMarque;
import abstraction.eqXRomu.produits.Feve;

public class Transformateur3VendeurAuxEncheres extends Transformateur3VendeurCCadre implements IVendeurAuxEncheres{

    private HashMap<ChocolatDeMarque, List<Double>> prixRetenus;
	private SuperviseurVentesAuxEncheres supEncheres;
	protected Journal journalEncheres;

    public Transformateur3VendeurAuxEncheres() {
        super();
        this.journalEncheres = new Journal(" journal Encheres Eq6", this);
    }

    public void initialiser() {
        super.initialiser();
        this.supEncheres = (SuperviseurVentesAuxEncheres)(Filiere.LA_FILIERE.getActeur("Sup.Encheres"));
        this.prixRetenus = new HashMap<ChocolatDeMarque, List<Double>>();
    }

    public void next() {
        super.next();
        this.journalEncheres.ajouter("Etape "+Filiere.LA_FILIERE.getEtape());
    }

    public void 
}
