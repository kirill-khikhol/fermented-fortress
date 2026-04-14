package com.kirkhi.fermented_fortress.fermentation.domain.events;

import com.kirkhi.fermented_fortress.common.domain.DomainEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.model.batch.FermentationBatch;

public class FermentationCompletedEvent implements DomainEvent {
    private final FermentationBatch batch;

    public FermentationCompletedEvent(FermentationBatch batch) {
        this.batch = batch;
    }

    public FermentationBatch getBatch() {
        return batch;
    }
}
