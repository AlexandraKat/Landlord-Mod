package landlordmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static landlordmod.LandLordThingyMod.makeID;

public class RentingPower extends BasePower{
    public static final String POWER_ID = makeID("RentingPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public RentingPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.amount>1){
            addToBot(new ReducePowerAction(this.owner, AbstractDungeon.player, this, 1));
        }
        if (this.amount == 1){
            addToBot(new RemoveSpecificPowerAction(this.owner, AbstractDungeon.player, RentingPower.POWER_ID));
            addToBot(new ApplyPowerAction(this.owner, AbstractDungeon.player, new ShelteredPower(this.owner, AbstractDungeon.player, 1)));
        }
    }
}
