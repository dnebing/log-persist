package com.liferay.logging.persist.messaging.internal.listener;

import com.liferay.logging.persist.messaging.LoggingMessagingConstants;
import com.liferay.portal.kernel.cluster.Priority;
import com.liferay.portal.kernel.cluster.messaging.ClusterBridgeMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * class ClusterBridgeMessageListenerRegistrator: This class is responsible for registering the
 * cluster bridge message listener to forward all of our logging level changed messages to the
 * cluster for broadcast.
 *
 * @author dnebinger
 */
@Component(
        immediate = true
)
public class ClusterBridgeMessageListenerRegistrator {
    /**
     * activate: Instantiates the cluster bridge message listener and then adds it to the destination.
     */
    @Activate
    protected void activate() {
        // create the new instance of the bridge
        ClusterBridgeMessageListener bridgeMessageListener = new ClusterBridgeMessageListener();

        // use a lower level since these messages are not that critical
        bridgeMessageListener.setPriority(Priority.LEVEL2);

        // register the listener with the destination
        _destination.register(bridgeMessageListener);

        // hold the reference for deactivation.
        _clusterBridgeMessageListener = bridgeMessageListener;
    }

    /**
     * deactivate: Unregisters the cluster bridge listener.
     */
    @Deactivate
    protected void deactivate() {
        if (_clusterBridgeMessageListener != null) {
            _destination.unregister(_clusterBridgeMessageListener);

            _clusterBridgeMessageListener = null;
        }
    }

    @Reference(target = "(destination.name=" + LoggingMessagingConstants.DESTINATION + ")")
    private Destination _destination;

    private MessageListener _clusterBridgeMessageListener = null;
}
