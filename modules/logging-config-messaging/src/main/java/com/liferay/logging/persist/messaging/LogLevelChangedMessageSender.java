package com.liferay.logging.persist.messaging;

/**
 * class LogLevelChangedMessageSender: Utility interface to facilitate sending log level changed messages
 * to the cluster.
 *
 * @author dnebinger
 */
public interface LogLevelChangedMessageSender {

    /**
     * sendLogLevelChangedMessage: Sends the message to the cluster that the log level changed.
     * @param loggerName Name of the logger that changed.
     * @param priority The new priority to use for the logger.
     */
    void sendLogLevelChangedMessage(final String loggerName, final String priority);
}
