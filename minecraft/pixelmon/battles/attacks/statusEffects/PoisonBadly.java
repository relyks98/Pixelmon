package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class PoisonBadly extends StatusEffectBase {

	double poisonAmount = 1 / 16;

	public PoisonBadly() {
		super(StatusEffectType.PoisonBadly, true, false, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			if (target.type.contains(EnumType.Poison)) {
				return;
			}
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Poison || e.type == StatusEffectType.PoisonBadly) {
					return;
				}
			poisonAmount = 1 / 16;
			target.status.add(this);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has been badly poisoned!");
		}
	}

	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is hurt by poison!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) * poisonAmount));
		poisonAmount += 1 / 16;
	}

	@Override
	public boolean clearsOnBattleEnd() {
		return false;
	}
}
