package fr.gouv.agriculture.pro.demo.business.agriculteur.rule;

import fr.gouv.agriculture.orion.i18n.Messages;
import fr.gouv.agriculture.orion.business.rule.AbstractBusinessRule;
import fr.gouv.agriculture.orion.message.MessageSeverity;
import fr.gouv.agriculture.orion.message.RuleMessage;
import fr.gouv.agriculture.orion.message.RuleReport;
import fr.gouv.agriculture.pro.demo.business.agriculteur.Agriculteur;

/**
 * @author MINAGRI
 * @version $Id: AddressRule.java 38440 2015-04-01 08:28:27Z olivier.dupre $
 */
public class AddressRule extends AbstractBusinessRule {

    public AddressRule() {
        setName(Messages.getMessage("agriculteur.rule.name"));
    }

    @Override
    public RuleReport validate(Object object) {
        RuleReport report = new RuleReport();

        Agriculteur agriculteur = (Agriculteur) object;

        if (agriculteur.getAdresse1() == null || agriculteur.getAdresse1().trim().equals("")) {
            report.addMessage(new RuleMessage(Messages.getMessage("agriculteur.rule.address1"), "adresse1", MessageSeverity.WARN));
        }

        if (agriculteur.getAdresse2() == null || agriculteur.getAdresse2().trim().equals("")) {
            report.addMessage(new RuleMessage(Messages.getMessage("agriculteur.rule.address2"), "adresse2", MessageSeverity.INFO));
        }

        if (agriculteur.getCodePostal() == null || agriculteur.getCodePostal().trim().equals("")) {
            report.addMessage(new RuleMessage(Messages.getMessage("agriculteur.rule.zipCode"), "codePostal", MessageSeverity.ERROR));
        }

        return report;
    }
}
