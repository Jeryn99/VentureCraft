package mc.craig.software.notnotyet.client;

import mc.craig.software.notnotyet.common.items.ParagliderItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class AnimationHandler {
    public static void animate(LivingEntity livingEntity, HumanoidModel<LivingEntity> bipedModel) {
        if (livingEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ParagliderItem && !livingEntity.isOnGround()) {

            if(livingEntity instanceof Player player){
                if(player.getAbilities().flying){
                   return;
                }
            }

            bipedModel.leftArm.xRot = (livingEntity.level.getDayTime() / 2) ;
            bipedModel.rightArm.xRot = (livingEntity.level.getDayTime() / 2);

            bipedModel.leftLeg.yRot = (float) Math.toRadians(-10);
            bipedModel.rightLeg.yRot = (float) Math.toRadians(10);

            bipedModel.leftLeg.zRot = (float) Math.toRadians(-10);
            bipedModel.rightLeg.zRot = (float) Math.toRadians(10);

        }
    }
}
