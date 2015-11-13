/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.pro.demo.dao.agriculteur;

import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO pour l'objet métier Agriculteur permettant de définir les propriétes à omeetre dans pour un export XML
 *
 * @author MINAGRI
 * @version $Id: $
 */
public class AgriculteurReportDAO extends DefaultBusinessDAO {

    /**
     * @return Returns the reportDataSource
     */
    public ReportDataSource getReportDataSource(String reportName,
            BusinessSearchContext context) throws PersistenceException,
            Exception {
        ReportDataSource reportDataSource = super.getReportDataSource(reportName, context);
        Map<Class<?>, List<String>> propertiesToOmit = new HashMap<Class<?>, List<String>>();

        propertiesToOmit.put(Agriculteur.class, Arrays.asList("id", "adresse2", "etatAgriculteur", "typeAgriculteur", "nbParcelles", "surfaceTotale", "parcelles","agrVersion"));
        //propertiesToOmit.put(Parcelle.class, Arrays.asList("surface", "libelleCadastre", "productions"));
        reportDataSource.setPropertiesToOmit(propertiesToOmit);
        return reportDataSource;
    }
}
