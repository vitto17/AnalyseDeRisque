package fr.gouv.agriculture.pro.demo.controller;

import fr.gouv.agriculture.orion.controller.menu.MenuController;
import fr.gouv.agriculture.orion.navigation.NavigationContext;
import fr.gouv.agriculture.orion.navigation.Transition;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import java.util.Collection;

/**
 * MenuController pour un agriculteur.
 *
 * @author olivierdupre
 * @version $Id: AgriculteurMC.java 37482 2015-02-19 16:33:57Z olivier.dupre $
 */
public class AgriculteurMC extends MenuController {

    /**
     * Renvoie le nom de l'agriculteur sélectionné lors de la précédente transition (sélection dans la liste
     * d'agriculteur pour arriver sur le formulaire de l'agriculteur).
     *
     * @return le nom de l'agriculteur sélectionné/positionné dans le context de navigation.
     */
    public String getAgriculteurName() {
        Agriculteur agriculteur = (Agriculteur) getLastCurrentObject();

        return agriculteur.getNom();
    }

    /**
     * Renvoie l'agriculteur sélectionné lors de la précédente transition (sélection dans la liste d'agriculteur pour
     * arriver sur le formulaire de l'agriculteur).
     *
     * @return l'agriculteur sélectionné/positionné dans le context de navigation.
     */
    protected Object getLastCurrentObject() {
        NavigationContext navigationContext = getNavigationContext();
        if (navigationContext != null) {
            Transition lastTransition = navigationContext.getTransitionStack().getLastTransition();
            if (lastTransition != null) {
                Collection selection = lastTransition.getSelection();
                if ((selection != null) && (!selection.isEmpty())) {
                    return selection.iterator().next();
                }
            }
        }

        throw new IllegalStateException("Cannot find last selected Agriculteur in the current state.");
    }
}
