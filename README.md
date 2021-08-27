# log-persist Project

This is a series of modules for Liferay 7 CE / Liferay DXP to persist and restore logging config changes
across restarts.

Note this is pretty simple.  First there's a Service Builder layer to manage the persistence of the log
configurtion details.

Next there's a PortletFilter component to intercept action requests for the Server Admin portlet.  The filter
intercepts the action and, if either an add for a category or update for a category, the values will be persisted.

When the component is activating, it will retrieve the current list of stored filter definitions and will reapply them.

Because of the way that updates are submitted, when you save a single update change each row on the page will be persisted.

Also the log levels will only be updated when the component starts.

~~So in a cluster configuration, changes to logs in one node will be persisted but other nodes will not process
the changes until they are restarted.~~

~~Normally this will not be a problem as you don't often change logging levels all that frequently.~~

~~But it is noted here so you can keep it in mind.~~

That used to be the case, but not any more.

In this branch, you have access to the new cluster-wide messaging fix.

In here, when the filter intercepts a log level change, it will persist it, but it will also
send a message on the LMB about the change. We bridge that to ClusterLink for broadcast, and
each node will get the message and update the log4j config to match.

Now this repo is enterprise-ready since log level changes are shared across the cluster!
