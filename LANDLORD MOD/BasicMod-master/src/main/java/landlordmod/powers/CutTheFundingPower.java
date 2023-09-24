package landlordmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static landlordmod.LandLordThingyMod.makeID;

public class CutTheFundingPower extends BasePower{
    public static final String POWER_ID = makeID("CutTheFundingPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public CutTheFundingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

}
