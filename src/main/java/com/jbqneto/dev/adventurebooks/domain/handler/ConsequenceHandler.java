package com.jbqneto.dev.adventurebooks.domain.handler;

import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.model.Option;
import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;

public interface ConsequenceHandler {
    ConsequenceType handleConsequence();
    void apply(PlayerProgress playerProgress, Option selectedOption);


}
