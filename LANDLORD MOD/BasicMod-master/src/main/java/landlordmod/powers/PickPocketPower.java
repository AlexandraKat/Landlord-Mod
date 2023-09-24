package landlordmod.powers;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static landlordmod.LandLordThingyMod.makeID;

public class PickPocketPower extends BasePower{
    public static final String POWER_ID = makeID("PickPocketPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public PickPocketPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            this.addToTop(new GainGoldAction(this.owner.getPower(PickPocketPower.POWER_ID).amount));
        }

    }

}
