package com.jbqneto.dev.adventurebooks.domain.handler;

import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.model.Consequence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConsequenceRegistry {

    private final Map<ConsequenceType, ConsequenceHandler> handlersMap = new HashMap<>();

    public ConsequenceRegistry(List<ConsequenceHandler> handlers) {
        handlers.forEach(handler -> handlersMap.put(handler.handleConsequence(), handler));
    }

    public ConsequenceHandler get(Consequence consequence) {
        ConsequenceHandler handler = handlersMap.get(consequence.getType());

        if (handler == null) {
            throw new IllegalStateException("No handler found for consequence type: " + consequence.getType());
        }

        return handler;
    }
}
