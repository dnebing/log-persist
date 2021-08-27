package com.liferay.logging.persist.messaging.internal.destination;

import com.liferay.logging.persist.messaging.LoggingMessagingConstants;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.MapUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * class LogLevelChangedMessageDestinationRegistrator: This class registers the
 * logging messaging destination. It must exist in order to send or receive messages.
 *
 * @author dnebinger
 */
@Component(
        immediate = true
)
public class LogLevelChangedMessageDestinationRegistrator {
    /**
     * activate: Creates the destination and registers the service.
     * @param bundleContext Injected for us by the framework.
     */
    @Activate
    private void activate(BundleContext bundleContext) {
        Destination destination = _destinationFactory.createDestination(
                DestinationConfiguration.createSerialDestinationConfiguration(
                        LoggingMessagingConstants.DESTINATION));

        _serviceRegistration = bundleContext.registerService(
                Destination.class, destination,
                MapUtil.singletonDictionary(
                        "destination.name", destination.getName()));
    }

    /**
     * deactivate: Cleans up the messaging destination since we're going away.
     */
    @Deactivate
    private void deactivate() {
        if (_serviceRegistration != null) {
            _serviceRegistration.unregister();
        }
    }

    @Reference
    private DestinationFactory _destinationFactory;

    private ServiceRegistration<Destination> _serviceRegistration;
}
