package config;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.osgi.annotation.bundle.Capability;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(immediate = true)
@Capability(
	attribute = "objectClass:List<String>=javax.management.MBeanServer",
	effective = "active",
	namespace = "osgi.service"
)
public class JMXHelper {

	private ServiceRegistration<MBeanServer> mbsReg;

	@Activate
	void activate(BundleContext bundleContext) {
		mbsReg = bundleContext.registerService(MBeanServer.class, ManagementFactory.getPlatformMBeanServer(), null);
	}

	@Deactivate
	void deactivate() {
		mbsReg.unregister();
	}

}
