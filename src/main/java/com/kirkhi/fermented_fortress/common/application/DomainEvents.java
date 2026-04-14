package com.kirkhi.fermented_fortress.common.application;

import java.util.ArrayList;
import java.util.List;

import com.kirkhi.fermented_fortress.common.domain.DomainEvent;

public final class DomainEvents {
    private static final List<DomainEventListener> listeners = new ArrayList<>();

    private DomainEvents() {
    }

    public static synchronized void register(DomainEventListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public static synchronized void clear() {
        listeners.clear();
    }

    public static void publish(DomainEvent event) {
        // Copy the list to avoid ConcurrentModification if listeners register during handling
        List<DomainEventListener> copy;
        synchronized (DomainEvents.class) {
            copy = new ArrayList<>(listeners);
        }
        for (DomainEventListener l : copy) {
            try {
                l.handle(event);
            } catch (Exception ex) {
                // swallow - user can add logging here if desired
            }
        }
    }
}
