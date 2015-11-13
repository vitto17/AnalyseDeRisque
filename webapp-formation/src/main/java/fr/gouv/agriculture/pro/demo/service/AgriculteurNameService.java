package fr.gouv.agriculture.pro.demo.service;

import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import java.util.List;

/**
 * @author MINAGRI
 * @version $Id: AgriculteurNameService.java 37611 2015-02-25 14:26:36Z olivier.dupre $
 */
public interface AgriculteurNameService {

    List<Agriculteur> find(String label);

    List<Agriculteur> find(String label, int max);
}
