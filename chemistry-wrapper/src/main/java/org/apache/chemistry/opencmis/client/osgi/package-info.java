/**
 * This file registers the bundle activator from the 
 * chemsitry client and causes a Provide-Capability 
 * to be added for the service registered by the 
 * Chemistry Wrapper.
 */
@org.osgi.annotation.bundle.Capability(
		namespace=SERVICE_NAMESPACE,
		attribute="objectClass:List<String>=org.apache.chemistry.opencmis.client.api.SessionFactory",
		uses=SessionFactory.class)
@org.osgi.annotation.bundle.Header(
		name="Bundle-Activator", 
		value="org.apache.chemistry.opencmis.client.osgi.Activator")
package org.apache.chemistry.opencmis.client.osgi;

import static org.osgi.namespace.service.ServiceNamespace.SERVICE_NAMESPACE;

import org.apache.chemistry.opencmis.client.api.SessionFactory;
