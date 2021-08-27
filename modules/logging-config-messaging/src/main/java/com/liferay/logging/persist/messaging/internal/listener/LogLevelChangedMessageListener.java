package com.liferay.logging.persist.messaging.internal.listener;

import com.liferay.petra.log4j.Log4JUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import org.osgi.service.component.annotations.Component;

/**
 * class LogLevelChangedMessageListener: A LMB message listener to process received
 * log level changed messages.
 *
 * @author dnebinger
 */
@Component(
        immediate = true,
        service = MessageListener.class
)
public class LogLevelChangedMessageListener extends BaseMessageListener {
    @Override
    protected void doReceive(Message message) throws Exception {
        // extract the values we need from the message
        String loggerName = message.getString("loggerName");
        String priority = message.getString("priority");

        // now we can update log4j...
        Log4JUtil.setLevel(loggerName, priority, true);

        // done. We don't need to persist because the node handling the UI change
        // has already completed that, we just need to update the local log4j logging.
    }
}
