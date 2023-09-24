package landlordmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.actions.PerDiscard;
import landlordmod.util.CardStats;

public class DumpTheTrash extends BaseCard {
    public static final String ID = makeID(DumpTheTrash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    public DumpTheTrash() {
        super(ID, info);
        setCustomVar("Invest",3,-1);
        setMagic(3, 3);
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
        addToBot(new PerDiscard(4, this.magicNumber, true, this));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DumpTheTrash();
    }
}