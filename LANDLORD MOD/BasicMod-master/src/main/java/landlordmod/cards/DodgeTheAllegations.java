package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.actions.LoseGoldAction;
import landlordmod.util.CardStats;

public class DodgeTheAllegations extends BaseCard {
    public static final String ID = makeID(DodgeTheAllegations.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    public DodgeTheAllegations() {
        super(ID, info);
        setCustomVar("Invest",4,-1);
        setBlock(8,4);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (!super.canUse(p, m))
            return false;
        if (m != null) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return p.gold >= this.customVar("Invest") && super.canUse(p,m);
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseGoldAction(this.customVar("Invest")));
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DodgeTheAllegations();
    }
}