package fr.gouv.agriculture.pro.demo.dao.parcelle;

import fr.gouv.agriculture.orion.context.BusinessContext;
import fr.gouv.agriculture.orion.dao.defaults.DefaultBusinessDAO;
import fr.gouv.agriculture.orion.persistence.PersistenceException;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle;
import java.util.ArrayList;

/**
 * Surcharge du DAO permettant de créer des parcelles et les associer à l'agriculteur présent dans la stack.
 *
 * @author MINAGRI
 * @version $Id: ParcelleDAO.java 37721 2015-03-02 16:08:05Z olivier.dupre $
 */
public class ParcelleDAO extends DefaultBusinessDAO {

    @Override
    public Object create(BusinessContext businessContext, Class businessClass) throws PersistenceException {
        Parcelle parcelle = new Parcelle();
        parcelle.setLibelle("");
        parcelle.setLibelleCadastre("");
        parcelle.setSurface(0);

        // On récupère l'agriculteur courant dans la stack
        Agriculteur agriculteur = (Agriculteur) findBusinessInStack(Agriculteur.class, businessContext.getStack());

        // Et s'il existe on l'associe à la parcelle
        if (agriculteur != null) {
            if (agriculteur.getParcelles() == null) {
                agriculteur.setParcelles(new ArrayList());
            }

            parcelle.setAgriculteur(agriculteur);
            agriculteur.getParcelles().add(parcelle);
        }

        return parcelle;
    }
}
