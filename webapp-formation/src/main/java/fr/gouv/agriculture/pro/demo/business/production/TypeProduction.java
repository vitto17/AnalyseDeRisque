package fr.gouv.agriculture.pro.demo.business.production;

import java.io.Serializable;

import fr.gouv.agriculture.orion.business.BaseEntity;

/**
 * @author Hibernate CodeGenerator
 */
public class TypeProduction extends BaseEntity implements Serializable {

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
    private int tprodVersion;

    /**
     * full constructor
     */
    public TypeProduction(String libelle, String rfa, int tprodVersion) {
        this.libelle = libelle;
        this.rfa = rfa;
        this.tprodVersion = tprodVersion;
    }

    /**
     * default constructor
     */
    public TypeProduction() {
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

    public int getTprodVersion() {
        return this.tprodVersion;
    }

    public void setTprodVersion(int tprodVersion) {
        this.tprodVersion = tprodVersion;
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

    public void breakAssociations() {
    }

}
