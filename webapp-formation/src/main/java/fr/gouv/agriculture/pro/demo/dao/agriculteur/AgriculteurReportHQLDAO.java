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
import fr.gouv.agriculture.orion.report.datasource.ReportCollectionBeanDataSource;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO pour l'objet métier Agriculteur permettant de définir les propriétes à inclure dans l'export en défnissant
 * programmatiquement une requête HQL
 *
 * @author MINAGRI
 * @version $Id: $
 */
public class AgriculteurReportHQLDAO extends DefaultBusinessDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgriculteurReportHQLDAO.class);

    private static final String AGRI_HQL_REQ = "select NEW Agriculteur(a.nom, a.prenom, a.adresse1, a.actif, a.codePostal) from Agriculteur as a";
    private static final String AGRI_HQL_REQ_CRIT = " where a.id in :identifier";
    private static final String IDENTIFIER_PARAM_HQL_REQ = "identifier";
    /**
     * @return Returns the reportDataSource
     */
    public ReportDataSource getReportDataSource(String reportName,
            BusinessSearchContext context) throws PersistenceException,
            Exception {
        try {
            Collection selection = context.getSelection();
            
            //Chargement des données par une reuête HQL
            Collection<Agriculteur> agriculteursList = null;
            if (selection.isEmpty()) {
                agriculteursList = getPersistenceService().find(AGRI_HQL_REQ, true);
            } else {
                Map parameters = new HashMap();
                List<Long> identifierList = new ArrayList<Long>(); 
                Iterator<Agriculteur> agriculteurIt= (Iterator<Agriculteur>) selection.iterator();
                while (agriculteurIt.hasNext()) {
                    identifierList.add(agriculteurIt.next().getId());
                }
                parameters.put(IDENTIFIER_PARAM_HQL_REQ, identifierList);
                agriculteursList = getPersistenceService().find(AGRI_HQL_REQ+AGRI_HQL_REQ_CRIT,parameters, true);
            }
            //Constrution d'un ReportDataSource
            ReportCollectionBeanDataSource reportDataSource = new ReportCollectionBeanDataSource("agriculteur", agriculteursList);

            Map<Class<?>, List<String>> propertiesToOmit = new HashMap<Class<?>, List<String>>();
            propertiesToOmit.put(Agriculteur.class, Arrays.asList("nbParcelles", "agrVersion"));
            reportDataSource.setPropertiesToOmit(propertiesToOmit);
            return reportDataSource;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw ex;
        }
    }
}
