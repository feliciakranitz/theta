package hu.bme.mit.theta.cloud.workQueue;

import com.rabbitmq.client.*;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.contollers.NotFoundException;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Work queue implementation that integrates with RabbitMQ.
 */
public class RabbitWorkQueue implements WorkQueue, Managed {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitWorkQueue.class);

    private static final String QUEUE_NAME = "AnalysisWorkQueue";

    private Connection connection;
    private Channel channel;

    private String consumerTag;

    private final String username;
    private final String password;
    private final String host;
    private final int port;

    public RabbitWorkQueue(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    @Override
    public void pushWork(String jobId) throws Exception {
        Channel c = acquireChannel();
        c.queueDeclare(QUEUE_NAME, true, false, false, null);
        c.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, jobId.getBytes());
    }

    @Override
    public void startConsumingWork(int workPrefetchCount, final WorkListener workListener) throws Exception {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String jobId = new String(delivery.getBody(), StandardCharsets.UTF_8);

            try {
                workListener.onNewWork(jobId, delivery.getEnvelope().getDeliveryTag());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        };

        Channel c = acquireChannel();

        c.queueDeclare(QUEUE_NAME, true, false, false, null);
        c.basicQos(workPrefetchCount);
        consumerTag = c.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }

    @Override
    public void stopConsumingWork() throws Exception {
        if(consumerTag != null){
            acquireChannel().basicCancel(consumerTag);
        }
    }

    @Override
    public synchronized void ackWork(long deliveryTag) throws Exception {
        acquireChannel().basicAck(deliveryTag, false);
    }

    @Override
    public void start() {
        try {
            connect();
        } catch (Exception e) {
            LOGGER.error("Unexpected error while connecting to RabbitMQ ", e);
        }
    }

    @Override
    public void stop() {
        disconnect();
    }

    private Connection connect() throws Exception {
        if (connection == null || !connect().isOpen()) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(username);
            factory.setPassword(password);
            factory.setHost(host);
            factory.setPort(port);

            connection = factory.newConnection();
        }

        return connection;
    }

    private Channel acquireChannel() throws Exception {
        if (channel == null || !channel.isOpen()) {
            channel = connect().createChannel();
        }

        return channel;
    }

    private void disconnect() {
        if (connection != null && connection.isOpen()) {
            try {
                connection.close();
            } catch (IOException e) {
                LOGGER.error("Unexpected error while disconnecting from RabbitMQ ", e);
                connection = null;
            }
        }
    }

    public interface WorkListener {
        void onNewWork(String jobId, long deliveryTag) throws NotFoundException;
    }
}
