package fr.gouv.agriculture.pro.demo.business.parcelle;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.gouv.agriculture.orion.business.BaseEntity;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;

/**
 * @author Hibernate CodeGenerator
 */
public class Parcelle extends BaseEntity implements Serializable {

    /**
     * identifier field
     */
    private Long id;

    /**
     * persistent field
     */
    @NotNull
    @Size(min = 4, max = 20)
    private String libelle;

    /**
     * persistent field
     */
    private int surface;

    /**
     * nullable persistent field
     */
    @NotNull
    @Size(min = 4, max = 20)
    private String libelleCadastre;

    /**
     * nullable persistent field
     */
    private int parVersion;

    /**
     * persistent field
     */
    private Agriculteur agriculteur;

    /**
     * persistent field
     */
    private List productions;

    /**
     * full constructor
     */
    public Parcelle(String libelle, int surface, String libelleCadastre, int parVersion, Agriculteur agriculteur, List productions) {
        this.libelle = libelle;
        this.surface = surface;
        this.libelleCadastre = libelleCadastre;
        this.parVersion = parVersion;
        this.agriculteur = agriculteur;
        this.productions = productions;
    }

    /**
     * default constructor
     */
    public Parcelle() {
    }

    /**
     * minimal constructor
     */
    public Parcelle(String libelle, int surface, Agriculteur agriculteur, List productions) {
        this.libelle = libelle;
        this.surface = surface;
        this.agriculteur = agriculteur;
        this.productions = productions;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getSurface() {
        return this.surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getLibelleCadastre() {
        return this.libelleCadastre;
    }

    public void setLibelleCadastre(String libelleCadastre) {
        this.libelleCadastre = libelleCadastre;
    }

    public int getParVersion() {
        return this.parVersion;
    }

    public void setParVersion(int parVersion) {
        this.parVersion = parVersion;
    }

    public Agriculteur getAgriculteur() {
        return this.agriculteur;
    }

    public void setAgriculteur(Agriculteur agriculteur) {
        this.agriculteur = agriculteur;
    }

    public List getProductions() {
        return this.productions;
    }

    public void setProductions(List productions) {
        this.productions = productions;
    }

    @Override
    public String toString() {
        return getLibelle();
    }

    /**
     * @see fr.gouv.agriculture.orion.business.Business#getBusinessObjectId()
     */
    public Serializable getIdentifier() {

        return getId();
    }

    /**
     * @see fr.gouv.agriculture.orion.business.Entity#breakAssociations()
     */
    public void breakAssociations() {
        if (getAgriculteur() != null) {
            getAgriculteur().getParcelles().remove(this);
        }
    }

}
