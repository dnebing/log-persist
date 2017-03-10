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

So in a cluster configuration, changes to logs in one node will be persisted but other nodes will not process
the changes until they are restarted.

Normally this will not be a problem as you don't often change logging levels all that frequently.

But it is noted here so you can keep it in mind.
