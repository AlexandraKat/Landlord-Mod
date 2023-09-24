package landlordmod.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import landlordmod.Landlord;
import landlordmod.powers.ShelteredPower;
import landlordmod.util.CardStats;

import java.util.List;

public class Eviction extends BaseCard {
    public static final String ID = makeID(Eviction.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            0
    );

    public Eviction() {
        super(ID, info);
        setMagic(3,-1);
        this.upgRetain=true;
    }
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return shelteredTooltip;
    }
    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.getMonsters().monsters.stream().anyMatch(q -> q.hasPower(ShelteredPower.POWER_ID)) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m))
            return false;
        if (m != null) {
            AbstractPower power = m.getPower(ShelteredPower.POWER_ID);
            this.cantUseMessage = String.format(cardStrings.EXTENDED_DESCRIPTION[0], magicNumber);
            return power != null && power.amount > magicNumber;
        }
        return false;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RemoveSpecificPowerAction(m, p, ShelteredPower.POWER_ID));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Eviction();
    }
}