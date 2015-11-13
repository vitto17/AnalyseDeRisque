package fr.gouv.agriculture.pro.demo.action;

import fr.gouv.agriculture.o2.kernel.Inject;
import fr.gouv.agriculture.orion.action.navigation.LinkAction;
import fr.gouv.agriculture.pro.demo.controller.AgriculteurFC;

/**
 * Action permettant de déclencher la consultation d'un agriculteur.
 *
 * @author MINAGRI
 * @version $Id: ViewAgriculteurAction.java 37834 2015-03-05 09:14:51Z olivier.dupre $
 */
public class ViewAgriculteurAction extends LinkAction {

    @Inject(value = "agriculteurFC")
    private AgriculteurFC agriculteurFC;

    @Override
    public Object execute() throws Exception {
        agriculteurFC.setReadOnly(true);

        return super.execute();
    }
}
