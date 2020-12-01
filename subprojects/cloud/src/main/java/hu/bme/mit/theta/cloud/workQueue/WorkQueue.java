package hu.bme.mit.theta.cloud.workQueue;

/**
 * Abstract conversion work queue client interface.
 */
public interface WorkQueue {

    /**
     * Push new conversion work onto the work queue by ID.
     *
     * @param jobId The ID (UUID format) to be submitted to the Work Queue.
     * @throws Exception
     */
    void pushWork(String jobId) throws Exception;

    /**
     * Start listening to work items from the queue.
     *
     * @param workPrefetchCount The number of work items to prefetch from the queue. Items in prefetch stage are pulled from the queue, but not yet ACK'd so the queue should consider them as 'being processed'.
     * @param workListener Listener instance the queue client should use to trigger notifications of new work items.
     * @throws Exception
     */
    void startConsumingWork(int workPrefetchCount, final RabbitWorkQueue.WorkListener workListener) throws Exception;

    /**
     * Stop listening to work items from the work queue and de-register the last listener registered.
     * @throws Exception
     */
    void stopConsumingWork() throws Exception;

    /**
     * Send and acknowledging message to the work queue, meaning that a work item has finished processing.
     *
     * @param deliveryTag The identifier used by the work queue implementation for identifying messages.
     * @throws Exception
     */
    void ackWork(long deliveryTag) throws Exception;

}
