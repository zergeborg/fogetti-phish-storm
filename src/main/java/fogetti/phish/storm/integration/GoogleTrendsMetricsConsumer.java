package fogetti.phish.storm.integration;

import java.util.Collection;
import java.util.Map;

import org.apache.storm.metric.api.IMetricsConsumer;
import org.apache.storm.task.IErrorReporter;
import org.apache.storm.task.TopologyContext;

public class GoogleTrendsMetricsConsumer implements IMetricsConsumer {

    @Override
    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, Object registrationArgument, TopologyContext context, IErrorReporter errorReporter) { }

    @Override
    public void handleDataPoints(TaskInfo taskInfo, Collection<DataPoint> dataPoints) {
        if ("google-trends-success".equals(taskInfo.srcComponentId)) {
            for (DataPoint p : dataPoints) {
                // FIXME: send 'p.value' to Sentry
            }
        }
    }

    @Override
    public void cleanup() { }

}
