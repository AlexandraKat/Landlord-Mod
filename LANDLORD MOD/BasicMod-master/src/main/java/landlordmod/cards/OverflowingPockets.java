package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.powers.OverflowingPocketsPower;
import landlordmod.util.CardStats;

public class OverflowingPockets extends BaseCard {
    public static final String ID = makeID(OverflowingPockets.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public OverflowingPockets() {
        super(ID, info);
        setCustomVar("Invest", 4,-1);
        setMagic(4,-1);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (p.gold < this.magicNumber) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OverflowingPocketsPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new OverflowingPockets();
    }
}