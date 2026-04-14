package com.kirkhi.fermented_fortress.fermentation.domain.events;

import com.kirkhi.fermented_fortress.common.domain.DomainEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.model.batch.BatchLog;
import com.kirkhi.fermented_fortress.fermentation.domain.model.batch.FermentationBatch;

public class BatchLogAddedEvent implements DomainEvent {
    private final FermentationBatch batch;
    private final BatchLog log;

    public BatchLogAddedEvent(FermentationBatch batch, BatchLog log) {
        this.batch = batch;
        this.log = log;
    }

    public FermentationBatch getBatch() {
        return batch;
    }

    public BatchLog getLog() {
        return log;
    }
}
