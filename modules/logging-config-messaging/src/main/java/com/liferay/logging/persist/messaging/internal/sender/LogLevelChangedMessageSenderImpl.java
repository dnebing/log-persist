package com.liferay.logging.persist.messaging.internal.sender;

import com.liferay.logging.persist.messaging.LogLevelChangedMessageSender;
import com.liferay.logging.persist.messaging.LoggingMessagingConstants;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.net.InetAddress;

/**
 * class LogLevelChangedMessageSenderImpl: Sends the message that the logging level has changed.
 *
 * @author dnebinger
 */
@Component(
        immediate = true,
        service = LogLevelChangedMessageSender.class
)
public class LogLevelChangedMessageSenderImpl implements LogLevelChangedMessageSender {
    /**
     * sendLogLevelChangedMessage: Sends the message to the cluster that the log level changed.
     *
     * @param loggerName Name of the logger that changed.
     * @param priority   The new priority to use for the logger.
     */
    @Override
    public void sendLogLevelChangedMessage(String loggerName, String priority) {
        // we need a message to populate
        Message message = new Message();

        // populate with the fields we expect
        message.put("loggerName", loggerName);
        message.put("priority", priority);

        // add some other fields that might be useful
        message.put("timestamp", System.currentTimeMillis());

        try {
            InetAddress address = InetAddressUtil.getLocalInetAddress();
            message.put("origin", address.getHostName());
        } catch (Exception e) {
            // we do not need this, so we'll just discard the exception
        }

        // ready to send the message
        messageBus.sendMessage(LoggingMessagingConstants.DESTINATION, message);
    }

    @Reference(unbind = "-")
    private MessageBus messageBus;

    @Reference(unbind = "-")
    private Portal portal;
}
