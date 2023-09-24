package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.actions.LoseGoldAction;
import landlordmod.util.CardStats;

public class CallForMaintenance extends BaseCard {
    public static final String ID = makeID(CallForMaintenance.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public CallForMaintenance() {
        super(ID, info);
        setMagic(3,3);
        setCustomVar("Invest",3,-1);
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
        addToBot(new LoseGoldAction(customVar("Invest")));
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CallForMaintenance();
    }
}