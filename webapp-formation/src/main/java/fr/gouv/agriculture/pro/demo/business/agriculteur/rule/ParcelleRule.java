package fr.gouv.agriculture.pro.demo.business.agriculteur.rule;

import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.business.rule.AbstractBusinessRule;
import fr.gouv.agriculture.orion.message.MessageSeverity;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;
import fr.gouv.agriculture.pro.demo.business.parcelle.Parcelle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vérifie que deux parcelles n'ont pas le même libellé.
 *
 * @author MINAGRI
 * @version $Id: ParcelleRule.java 38440 2015-04-01 08:28:27Z olivier.dupre $
 */
public class ParcelleRule extends AbstractBusinessRule {

    public ParcelleRule() {
        setName(Messages.getMessage("agriculteur.rule.parcelles.name"));
    }

    @Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();
        Agriculteur agriculteur = (Agriculteur) object;

        final List<Parcelle> parcelles = agriculteur.getParcelles();

        final Map<String, Parcelle> mapLibellesToParcelle = new HashMap<String, Parcelle>();
        for (Parcelle parcelle : parcelles) {
            final String libelle = parcelle.getLibelle();
            if (mapLibellesToParcelle.containsKey(libelle)) {
                report.addMessage(new RuleMessage(
                        String.format(Messages.getMessage("agriculteur.rule.parcelles.duplicatedLibelle"), libelle), 
                        "parcellesList", MessageSeverity.ERROR));
            }

            mapLibellesToParcelle.put(libelle, parcelle);
        }

        return report;
    }
}
