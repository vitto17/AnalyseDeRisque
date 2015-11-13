package fr.gouv.agriculture.pro.demo.service;

import fr.gouv.agriculture.o2.kernel.Configurable;
import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.factory.PersistenceServiceFactory;
import fr.gouv.agriculture.orion.persistence.PersistenceService;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author MINAGRI
 * @version $Id: AgriculteurNameServiceImpl.java 37611 2015-02-25 14:26:36Z olivier.dupre $
 */
public class AgriculteurNameServiceImpl implements AgriculteurNameService, Configurable {

    private final static int DEFAULT_MAX = 15;

    @Inject
    private PersistenceServiceFactory persistenceServiceFactory;
    private final List<Agriculteur> agriculteursList = new ArrayList<Agriculteur>();

    @Override
    public List<Agriculteur> find(final String name, int max) {
        if (name == null) {
            return (List<Agriculteur>) Collections.unmodifiableCollection(agriculteursList);
        }

        List<Agriculteur> result = new ArrayList<Agriculteur>();

        for (Agriculteur agriculteur : agriculteursList) {
            final String nom = agriculteur.getNom();
            if ((nom != null) && (nom.toUpperCase().contains(name.toUpperCase()))) {
                result.add(agriculteur);
                if (result.size() == max) {
                    return result;
                }
            }
        }

        return result;
    }

    @Override
    public List<Agriculteur> find(String label) {
        return find(label, DEFAULT_MAX);
    }

    @Override
    public void configure() throws Exception {
        PersistenceService persistenceService = persistenceServiceFactory.createPersistenceService();

        try {
            persistenceService.setMaxResults(5000);
            String hqlQuery = "from Agriculteur order by nom asc";
            Collection<Agriculteur> agriculteurs = persistenceService.find(hqlQuery, false);
            agriculteursList.addAll(agriculteurs);
        } finally {
            persistenceService.stop();
        }
    }
}
