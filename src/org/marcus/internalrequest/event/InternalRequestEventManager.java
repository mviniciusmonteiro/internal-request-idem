package org.marcus.internalrequest.event;

import org.adempiere.base.AnnotationBasedEventManager;
import org.osgi.service.component.annotations.Component;

/**
 * Gerenciador de eventos baseado em anotações para o plugin Internal Request.
 * Escaneia o pacote org.marcus.internalrequest.event e registra os delegates anotados.
 * 
 * @author Marcus
 */
@Component(immediate = true, service = InternalRequestEventManager.class)
public class InternalRequestEventManager extends AnnotationBasedEventManager {

	public InternalRequestEventManager() {
	}

	@Override
	public String[] getPackages() {
		return new String[] {"org.marcus.internalrequest.event"};
	}
}
