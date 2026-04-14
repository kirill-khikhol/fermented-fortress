package com.kirkhi.fermented_fortress.common.application;

import com.kirkhi.fermented_fortress.common.domain.DomainEvent;

public interface DomainEventListener {
    void handle(DomainEvent event);
}
