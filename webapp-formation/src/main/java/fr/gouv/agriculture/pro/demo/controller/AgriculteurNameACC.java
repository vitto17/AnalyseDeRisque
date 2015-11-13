package fr.gouv.agriculture.pro.demo.controller;

import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.controller.BusinessController;
import fr.gouv.agriculture.orion.controller.complete.AbstractCompleteController;
import fr.gouv.agriculture.orion.model.CompletionItem;
import fr.gouv.agriculture.orion.model.FormModel;
import fr.gouv.agriculture.orion.query.CriteriaMap;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import fr.gouv.agriculture.pro.demo.service.AgriculteurNameService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MINAGRI
 * @version $Id: AgriculteurNameACC.java 37615 2015-02-25 16:25:42Z olivier.dupre $
 */
public class AgriculteurNameACC extends AbstractCompleteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgriculteurNameACC.class);

    @Inject
    private AgriculteurNameService agriculteurNameService;

    @Override
    protected void populateModel() throws Exception {
        final String queryString = getQuery().toString();
        final Collection<Agriculteur> matches = agriculteurNameService.find(queryString, 15);
        for (Agriculteur agriculteur : matches) {
            CompletionItem completionItem = new CompletionItem(agriculteur, agriculteur.getPrenom(), agriculteur.getNom());
            getCompletionData().add(completionItem);
        }
    }

    @Override
    public void endCompletion(Object value) {
        LOGGER.trace("Ending completion of the name...");

        // Différents objets accessibles ici...
        Agriculteur agriculteur = (Agriculteur) value;
        BusinessController businessController = (BusinessController) getContainer();
        FormModel formModel = (FormModel) businessController.getModel();
        CriteriaMap criteriaMap = (CriteriaMap) formModel.getObject();
    }
}
