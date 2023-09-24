package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import landlordmod.Landlord;
import landlordmod.powers.DependentPower;
import landlordmod.util.CardStats;

public class Dependency extends BaseCard {
    public static final String ID = makeID(Dependency.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    public Dependency() {
        super(ID, info);
        setCustomVar("Invest",3,-1);
        setMagic(12,-4);
        setExhaust(true);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (m != null) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return p.gold >= customVar("Invest");
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new GainBlockAction(mo, this.magicNumber));
            addToBot(new ApplyPowerAction(mo, p, new BarricadePower(mo)));
            addToBot(new ApplyPowerAction(mo, p, new DependentPower(mo, p, -1)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Dependency();
    }
}