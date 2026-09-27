package org.marcus.internalrequest.event;

import org.adempiere.base.annotation.EventTopicDelegate;
import org.adempiere.base.annotation.ModelEventTopic;
import org.adempiere.base.event.annotations.ModelEventDelegate;
import org.adempiere.base.event.annotations.po.AfterChange;
import org.adempiere.base.event.annotations.po.AfterNew;
import org.compiere.util.CLogger;
import org.marcus.internalrequest.model.MXXInternalRequest;
import org.marcus.internalrequest.model.X_XX_InternalRequest;
import org.osgi.service.event.Event;

/**
 * Event Delegate para Solicitação Interna (Internal Request).
 * Implementa a arquitetura moderna de Event Annotations (NF9 / iDempiere 9 a 13).
 * Intercepta eventos de ciclo de vida do modelo com tipagem estática e desacoplamento de boot.
 * 
 * @author Marcus
 */
@EventTopicDelegate
@ModelEventTopic(modelClass = MXXInternalRequest.class)
public class InternalRequestEventDelegate extends ModelEventDelegate<MXXInternalRequest> {

	private static final CLogger log = CLogger.getCLogger(InternalRequestEventDelegate.class);

	public InternalRequestEventDelegate(MXXInternalRequest po, Event event) {
		super(po, event);
	}

	@AfterNew
	@AfterChange
	public void onAfterSave() {
		MXXInternalRequest req = getModel();
		if (req == null) {
			return;
		}

		// Alerta especial no console quando a Prioridade for Urgente
		if (X_XX_InternalRequest.PRIORITY_Urgente.equals(req.getPriority())) {
			String alertMsg = String.format(
				"[URGENT REQUEST ALERT] Solicitação Urgente! Nº: %s | Solicitante: %d",
				req.getDocumentNo(),
				req.getRequestedBy_ID()
			);

			System.out.println("================================================================================");
			System.out.println("🚨 " + alertMsg);
			System.out.println("   Tópico OSGi: " + getEvent().getTopic());
			System.out.println("   Descrição: " + req.getDescription());
			System.out.println("================================================================================");

			log.warning(alertMsg);
		}
	}
}
