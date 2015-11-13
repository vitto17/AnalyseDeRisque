package fr.gouv.agriculture.pro.demo.business.production;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import fr.gouv.agriculture.orion.business.BaseEntity;

/**
 * @author Hibernate CodeGenerator
 */
public class Production extends BaseEntity implements Serializable {

    /**
     * identifier field
     */
    private Long id;

    /**
     * nullable persistent field
     */
    private BigDecimal quantite;

    /**
     * nullable persistent field
     */
    private String unite;

    /**
     * nullable persistent field
     */
    private int prodVersion;

    /**
     * persistent field
     */
    private fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle parcelle;

    /**
     * nullable persistent field
     */
    private fr.gouv.agriculture.pro.demo.business.production.TypeProduction typeProduction;

    private Date dateRecolte;

    /**
     * full constructor
     */
    public Production(BigDecimal quantite, String unite, int prodVersion, fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle parcelle, fr.gouv.agriculture.pro.demo.business.production.TypeProduction typeProduction) {
        this.quantite = quantite;
        this.unite = unite;
        this.prodVersion = prodVersion;
        this.parcelle = parcelle;
        this.typeProduction = typeProduction;
    }

    /**
     * default constructor
     */
    public Production() {
    }

    /**
     * minimal constructor
     */
    public Production(fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle parcelle) {
        this.parcelle = parcelle;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantite() {
        return this.quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return this.unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getProdVersion() {
        return this.prodVersion;
    }

    public void setProdVersion(int prodVersion) {
        this.prodVersion = prodVersion;
    }

    public fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle getParcelle() {
        return this.parcelle;
    }

    public void setParcelle(fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle parcelle) {
        this.parcelle = parcelle;
    }

    public fr.gouv.agriculture.pro.demo.business.production.TypeProduction getTypeProduction() {
        return this.typeProduction;
    }

    public void setTypeProduction(fr.gouv.agriculture.pro.demo.business.production.TypeProduction typeProduction) {
        this.typeProduction = typeProduction;
    }

    @Override
    public String toString() {
        return getTypeProduction().getLibelle();
    }

    /**
     * @see fr.gouv.agriculture.orion.business.Business#getBusinessObjectId()
     */
    public Serializable getIdentifier() {

        return getId();
    }

    /**
     *
     * @inherit
     */
    public void breakAssociations() {
        getParcelle().getProductions().remove(this);
    }

    /**
     * @inherit
     */
//    public void buildRules() {       
//        super.buildRules();
//        addRule(new ProductionRule());
//    }
    public Date getDateRecolte() {
        return dateRecolte;
    }

    public void setDateRecolte(Date dateRecolte) {
        this.dateRecolte = dateRecolte;
    }

}
