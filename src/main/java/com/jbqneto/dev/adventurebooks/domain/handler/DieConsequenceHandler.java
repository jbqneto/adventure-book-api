package com.jbqneto.dev.adventurebooks.domain.handler;

import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;
import com.jbqneto.dev.adventurebooks.domain.model.Option;
import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;
import org.springframework.stereotype.Component;

@Component
public class DieConsequenceHandler implements ConsequenceHandler {

    @Override
    public ConsequenceType handleConsequence() {
        return ConsequenceType.DIE;
    }

    @Override
    public void apply(PlayerProgress playerProgress, Option selectedOption) {
        playerProgress.setHealth(0);
    }
}
