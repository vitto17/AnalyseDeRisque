package fr.gouv.agriculture.pro.demo.business.agriculteur;

import java.io.Serializable;

import fr.gouv.agriculture.orion.business.BaseEntity;

/**
 * @author Hibernate CodeGenerator
 * @version $Id : $
 */
public class EtatAgriculteur extends BaseEntity implements Serializable {

    /**
     * identifier field
     */
    private Long id;
    /**
     * nullable persistent field
     */
    private String libelle;
    /**
     * nullable persistent field
     */
    private String rfa;
    /**
     * nullable persistent field
     */
    private int etaVersion;

    /**
     * full constructor
     */
    public EtatAgriculteur(String libelle, String rfa, int etaVersion) {
        this.libelle = libelle;
        this.rfa = rfa;
        this.etaVersion = etaVersion;
    }

    /**
     * default constructor
     */
    public EtatAgriculteur() {
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

    public String getRfa() {
        return this.rfa;
    }

    public void setRfa(String rfa) {
        this.rfa = rfa;
    }

    public int getEtaVersion() {
        return this.etaVersion;
    }

    public void setEtaVersion(int etaVersion) {
        this.etaVersion = etaVersion;
    }

    @Override
    public String toString() {
        return getLibelle();
    }

    /**
     * @see fr.gouv.agriculture.orion.business.Business#getBusinessObjectId()
     */
    @Override
    public Serializable getIdentifier() {
        return getId();
    }

    @Override
    public void breakAssociations() {
    }
}
