package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.actions.LoseGoldAction;
import landlordmod.powers.CutTheFundingPower;
import landlordmod.util.CardStats;

public class CutTheFunding extends BaseCard {
    public static final String ID = makeID(CutTheFunding.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public CutTheFunding() {
        super(ID, info);
        setEthereal(true);
        setMagic(2,1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CutTheFundingPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CutTheFunding();
    }
}