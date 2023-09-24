package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.PressurePoints;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import landlordmod.Landlord;
import landlordmod.actions.LoseGoldAction;
import landlordmod.util.CardStats;

public class Graffiti extends BaseCard {
    public static final String ID = makeID(Graffiti.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    public Graffiti() {
        super(ID, info);
        setCustomVar("Invest",1);
        setMagic(3,2);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (!super.canUse(p, m))
            return false;
        if (m != null) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return p.gold >= customVar("Invest") && super.canUse(p,m);
        }
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseGoldAction(customVar("Invest")));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new MarkPower(mo, this.magicNumber)));
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo,1, false)));
        }
        addToBot(new TriggerMarksAction(new PressurePoints()));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Graffiti();
    }
}