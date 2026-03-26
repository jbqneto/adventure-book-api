package com.jbqneto.dev.adventurebooks.domain.handler;

import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.model.Option;
import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;
import org.springframework.stereotype.Component;

@Component
public class GainHealthConsequenceHandler implements ConsequenceHandler {

    @Override
    public ConsequenceType handleConsequence() {
        return ConsequenceType.GAIN_HEALTH;
    }

    @Override
    public void apply(PlayerProgress playerProgress, Option selectedOption) {
        var health = playerProgress.getHealth() + selectedOption.getConsequence().getValue();

        playerProgress.setHealth(health);
    }
}
