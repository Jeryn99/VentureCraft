package mc.craig.software.craftplus.common.items.armor;

import mc.craig.software.craftplus.common.items.ArmorAction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.awt.*;
import java.util.Objects;
import java.util.UUID;

public class SpeedArmor implements ArmorAction {

    private static final SpeedArmor INSTANCE = new SpeedArmor();

    public static final UUID SPRINT_UUID = UUID.fromString("7dec4b0e-a904-46a9-bc03-b35697cdafdc");


    @Override
    public void tick(LivingEntity living) {
        Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).removeModifier(SPRINT_UUID);
        if (living.isSprinting()) {
            Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).addTransientModifier(new AttributeModifier(SPRINT_UUID, "Sprint modifier", 2, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }

    @Override
    public void onAdded(LivingEntity entity) {

    }

    @Override
    public void onRemoved(LivingEntity entity) {

    }

    @Override
    public ArmorAction getInstance() {
        return INSTANCE;
    }

    @Override
    public int getColor() {
        return Color.RED.getRGB();
    }
}
