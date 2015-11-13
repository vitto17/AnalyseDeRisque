package fr.gouv.agriculture.pro.demo.controller;

import fr.gouv.agriculture.orion.controller.form.FormController;

/**
 * Controleur du formulaire d'agriculteur permettant de savoir si on est en modification ou en lecture.
 *
 * @author MINAGRI
 * @version $Id: AgriculteurFC.java 37834 2015-03-05 09:14:51Z olivier.dupre $
 */
public class AgriculteurFC extends FormController {

    private boolean readOnly = true;

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

}
