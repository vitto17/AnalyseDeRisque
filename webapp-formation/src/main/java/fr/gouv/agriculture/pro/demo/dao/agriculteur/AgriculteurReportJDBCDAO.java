/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gouv.agriculture.pro.demo.dao.agriculteur;

import fr.gouv.agriculture.orion.context.BusinessSearchContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.orion.report.PropertyDescriptor;
import fr.gouv.agriculture.orion.report.ReportDataSource;
import fr.gouv.agriculture.orion.report.datasource.DefaultPropertyDescriptor;
import fr.gouv.agriculture.orion.report.datasource.ReportResultSetDataSource;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * DAO pour l'objet métier Agriculteur permettant de définir les propriétes à inclure dans l'export en défnissant
 * programmatiquement une requête JDBC
 *
 * @author MINAGRI
 * @version $Id: $
 */
public class AgriculteurReportJDBCDAO extends DefaultBusinessDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgriculteurReportJDBCDAO.class);

    private static final String AGRI_JDBC_REQ = "select agr_nom_lb as nom, agr_prenom_lb as prenom, agr_adr1_lb as adresse1, agr_actif_on as actif, agr_codpos_lb as codepostal from AGRICULTEUR";
    private static final String AGRI_JDBC_REQ_CRIT = " where agr_cdn in (?)";
    
    /**
     * @return Returns the reportDataSource
     */
    public ReportDataSource getReportDataSource(String reportName,
            BusinessSearchContext context) throws PersistenceException,
            Exception {
        try {
            Collection selection = context.getSelection();
            Statement statement = null;
            //Chargement des données par une reuête JDBC
            if (selection.isEmpty()) {
                statement = getPersistenceService().getConnection().createStatement();
                statement.execute(AGRI_JDBC_REQ);
            } else {
                //TODO A revoir : cet algo ne fonctionne pas
                // en tous les cas il necessite la version de c3p0 (groupId com.mchange) 0.9.5 à la place de c3p0 0.9.1 (groupId c3p0)
//                List<String> identifierList = new ArrayList<String>(); 
                List<Long> identifierList = new ArrayList<Long>(); 
                Iterator<Agriculteur> agriculteurIt= (Iterator<Agriculteur>) selection.iterator();
                while (agriculteurIt.hasNext()) {
//                    identifierList.add(agriculteurIt.next().getId().toString());
                    identifierList.add(agriculteurIt.next().getId());
                }
                PreparedStatement ps = getPersistenceService().getConnection().prepareStatement(AGRI_JDBC_REQ+AGRI_JDBC_REQ_CRIT);
                statement = ps;
//                String identifierListStr = StringUtils.collectionToCommaDelimitedString(identifierList);
//                identifierListStr = StringUtils.trimAllWhitespace(identifierListStr);
//                ps.setString(1,identifierListStr);
                //TODO l'appel à setArray lève une exception JDBC incompatible data type in conversion
                java.sql.Array array = getPersistenceService().getConnection().createArrayOf( "BIGINT", identifierList.toArray());
//                ps.setArray(  1,  getPersistenceService().getConnection().createArrayOf( java.sql.Types.BIGINT, identifierList.toArray() )  );
                ps.setArray(  1,  array   );
                //TODO 
                ps.execute();
                //fin TODO A revoir 
            }
            
            ResultSet rs = statement.getResultSet();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            //Constrution d'un ReportDataSource
            ReportResultSetDataSource reportDataSource = new ReportResultSetDataSource("ResultSet", rs, rsMetaData);
            
            //Définition des nom de propriétés à utiliser dans le fichier d'export
            List<PropertyDescriptor>  propertiesDescriptorList = new ArrayList<PropertyDescriptor>();
            String source = Agriculteur.class.getSimpleName();
            propertiesDescriptorList.add(new DefaultPropertyDescriptor(source, "nom"));
            propertiesDescriptorList.add(new DefaultPropertyDescriptor(source, "prenom"));
            propertiesDescriptorList.add(new DefaultPropertyDescriptor(source, "adresse1"));
            propertiesDescriptorList.add(new DefaultPropertyDescriptor(source, "codePostal"));
            propertiesDescriptorList.add(new DefaultPropertyDescriptor(source, "actif"));
            reportDataSource.setProperties(propertiesDescriptorList);
            
            return reportDataSource;
        } catch (Exception ex) {
            ex.printStackTrace();;
            LOGGER.error(ex.getMessage(), ex);
            throw ex;
        }
    }
}
