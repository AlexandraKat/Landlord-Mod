package landlordmod.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.powers.ShelteredPower;
import landlordmod.util.CardStats;

import java.util.List;

public class Rent extends BaseCard {
    public static final String ID = makeID(Rent.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            2

    );


    public Rent() {
        super(ID, info);
        setMagic(2,1);
        setCostUpgrade(1);
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return shelteredTooltip;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new ShelteredPower(m, p, this.magicNumber), this.magicNumber));
    }

}