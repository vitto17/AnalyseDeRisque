package fr.gouv.agriculture.pro.demo.business.agriculteur;

import fr.gouv.agriculture.pro.demo.business.agriculteur.rule.ParcelleRule;
import fr.gouv.agriculture.pro.demo.business.agriculteur.rule.AddressRule;
import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.gouv.agriculture.pro.demo.business.agriculteur.rule.CiviliteGroup;
import fr.gouv.agriculture.orion.business.BaseEntity;
import fr.gouv.agriculture.orion.business.rule.ConstraintSeverity;
import fr.gouv.agriculture.pro.demo.business.agriculteur.rule.AddressGroup;

/**
 * @author Hibernate CodeGenerator
 * @version $Id : $
 */
public class Agriculteur extends BaseEntity implements Serializable {

    /**
     * identifier field
     */
    private Long id;

    /**
     * persistent field
     */
    @Size(min = 4, max = 20, groups = {CiviliteGroup.class})
    private String nom;

    /**
     * persistent field
     */
    @Size(min = 4, max = 20, groups = {CiviliteGroup.class})
    private String prenom;

    /**
     * nullable persistent field
     */
    @NotNull(groups = {AddressGroup.class})
    @Size(min = 4, max = 30, groups = {AddressGroup.class})
    private String adresse1;

    /**
     * nullable persistent field
     */
    @NotNull(payload = ConstraintSeverity.Warning.class, groups = {AddressGroup.class})
    @Size(min = 1, max = 30, payload = ConstraintSeverity.Warning.class, groups = {AddressGroup.class})
    private String adresse2;

    /**
     * nullable persistent field
     */
    private boolean actif;

    /**
     * nullable persistent field
     */
    @NotNull
    private String codePostal;

    private int agrVersion;

    /**
     * nullable persistent field
     */
    private Agriculteur agriculteur;

    /**
     * nullable persistent field
     */
    private EtatAgriculteur etatAgriculteur;

    /**
     * nullable persistent field
     */
    private TypeAgriculteur typeAgriculteur;

    /**
     * persistent field
     */
    @Valid
    private List parcelles;

    /**
     * Attribut non persiste : utile pour des besoins UI.
     */
    private int nbParcelles;

    /**
     * Attribut non persiste : utile pour des besoins UI.
     */
    private Long surfaceTotale;

    /**
     * full constructor
     */
    public Agriculteur(String nom, String prenom, String adresse1,
            String adresse2, boolean actif, String codePostal, int agrVersion,
            Agriculteur agriculteur,
            EtatAgriculteur etatAgriculteur,
            TypeAgriculteur typeAgriculteur,
            List parcelles) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.actif = actif;
        this.codePostal = codePostal;
        this.agrVersion = agrVersion;
        this.agriculteur = agriculteur;
        this.etatAgriculteur = etatAgriculteur;
        this.typeAgriculteur = typeAgriculteur;
        this.parcelles = parcelles;
    }

     /**
     * full constructor
     */
    public Agriculteur(String nom, String prenom, String adresse1,
             boolean actif, String codePostal) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse1 = adresse1;
        this.actif = actif;
        this.codePostal = codePostal;
    }
    
    /**
     * default constructor
     */
    public Agriculteur() {
    }

    /**
     * minimal constructor
     */
    public Agriculteur(String nom, String prenom, List parcelles) {
        this.nom = nom;
        this.prenom = prenom;
        this.parcelles = parcelles;
    }

    @Override
    public void buildRules() {
        super.buildRules();
        addRule(new AddressRule());
        addRule(new ParcelleRule());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public int getAgrVersion() {
        return agrVersion;
    }

    public void setAgrVersion(int agrVersion) {
        this.agrVersion = agrVersion;
    }

    public int getNbParcelles() {
        return nbParcelles;
    }

    public void setNbParcelles(int nbParcelles) {
        this.nbParcelles = nbParcelles;
    }

    public Agriculteur getAgriculteur() {
        return agriculteur;
    }

    public void setAgriculteur(Agriculteur agriculteur) {
        this.agriculteur = agriculteur;
    }

    public EtatAgriculteur getEtatAgriculteur() {
        return etatAgriculteur;
    }

    public void setEtatAgriculteur(
            EtatAgriculteur etatAgriculteur) {
        this.etatAgriculteur = etatAgriculteur;
    }

    public TypeAgriculteur getTypeAgriculteur() {
        return typeAgriculteur;
    }

    public void setTypeAgriculteur(
            TypeAgriculteur typeAgriculteur) {
        this.typeAgriculteur = typeAgriculteur;
    }

    public List getParcelles() {
        return parcelles;
    }

    public void setParcelles(List parcelles) {
        this.parcelles = parcelles;
    }

    @Override
    public String toString() {
        return getNom();
    }

    /**
     * {@inheritDoc}
     */
    public Serializable getIdentifier() {
        return getId();
    }

    public boolean isInactif() {
        return !isActif();
    }

    public void setInactif(boolean value) {
        setActif(!value);
    }

    @Override
    public void breakAssociations() {
    }

    public Long getSurfaceTotale() {
        return surfaceTotale;
    }

    public void setSurfaceTotale(Long surfaceTotale) {
        this.surfaceTotale = surfaceTotale;
    }
}
