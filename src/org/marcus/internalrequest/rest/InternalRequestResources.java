package org.marcus.internalrequest.rest;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

import com.trekglobal.idempiere.rest.api.ResourceExtension;

/**
 * Componente OSGi Declarative Services responsável por estender o container
 * REST do iDempiere.
 * Implementa a interface ResourceExtension conforme especificação oficial
 * 
 */
@Component(service = ResourceExtension.class, immediate = true)
public class InternalRequestResources implements ResourceExtension {

	public InternalRequestResources() {
	}

	@Override
	public Set<Class<?>> getResourceClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(InternalRequestResource.class);
		return classes;
	}

}
