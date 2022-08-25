package mc.craig.software.craftplus.client.models;

import mc.craig.software.craftplus.common.entities.Stalker;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class PlayerModelChanges extends PlayerModel {
    public PlayerModelChanges(ModelPart root, boolean smallArms) {
        super(root, smallArms);
    }


    @Override
    public void setupAnim(@Nullable LivingEntity weepingAngel, float v, float v1, float v2, float v3, float v4) {

        if (weepingAngel instanceof Stalker stalker) {
            Stalker.Pose pose = stalker.getStalkerPose();

            if (pose == Stalker.Pose.FURIOUS) {
                rightArm.xRot = (float) Math.toRadians(-115);
                rightArm.yRot = (float) Math.toRadians(0);
                rightArm.zRot = (float) Math.toRadians(0);

                leftArm.xRot = (float) Math.toRadians(-55);
                leftArm.yRot = (float) Math.toRadians(0);
                leftArm.zRot = (float) Math.toRadians(0);
                fixLayers(this);
    /*        head.xRot = (float) Math.toRadians(17.5);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(-10);*/
                return;
            }


            if (pose == Stalker.Pose.ANGRY) {
                rightArm.xRot = (float) Math.toRadians(-90);
                rightArm.yRot = (float) Math.toRadians(-20);
                rightArm.zRot = (float) Math.toRadians(30);

                leftArm.xRot = (float) Math.toRadians(-90);
                leftArm.yRot = (float) Math.toRadians(25);
                leftArm.zRot = (float) Math.toRadians(-17.5);
                fixLayers(this);
    /*        head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(-12.5);
            head.zRot = (float) Math.toRadians(0);*/
                return;
            }


            if (pose == Stalker.Pose.HIDING) {
                head.xRot = (float) Math.toRadians(20);
                head.yRot = (float) Math.toRadians(0);
                head.zRot = (float) Math.toRadians(0);

                rightArm.xRot = (float) Math.toRadians(-105);
                rightArm.yRot = (float) Math.toRadians(20);
                rightArm.zRot = (float) Math.toRadians(12.5);

                leftArm.xRot = (float) Math.toRadians(-105);
                leftArm.yRot = (float) Math.toRadians(-20);
                leftArm.zRot = (float) Math.toRadians(-12.5);
                fixLayers(this);
                return;
            }

            if (pose == Stalker.Pose.APPROACH) {
                rightArm.xRot = -1.04533F;
                rightArm.yRot = -0.55851F;
                rightArm.zRot = 0.0F;
                leftArm.xRot = -1.04533F;
                leftArm.yRot = 0.55851F;
                leftArm.zRot = 0.0F;
                fixLayers(this);
                return;
            }

            if (pose == Stalker.Pose.IDLE) {
      /*      head.xRot = (float) Math.toRadians(0);
            head.yRot = (float) Math.toRadians(0);
            head.zRot = (float) Math.toRadians(0);*/

                rightArm.xRot = (float) Math.toRadians(0);
                rightArm.yRot = (float) Math.toRadians(0);
                rightArm.zRot = (float) Math.toRadians(7.5);

                leftArm.xRot = (float) Math.toRadians(0);
                leftArm.yRot = (float) Math.toRadians(0);
                leftArm.zRot = (float) Math.toRadians(-7.5);
                fixLayers(this);
                return;
            }

            if (pose == Stalker.Pose.SHY) {
                rightArm.xRot = (float) Math.toRadians(-90);
                rightArm.yRot = (float) Math.toRadians(-1.5);
                rightArm.zRot = (float) Math.toRadians(-20);

                leftArm.xRot = (float) Math.toRadians(-120);
                leftArm.yRot = (float) Math.toRadians(-36);
                leftArm.zRot = (float) Math.toRadians(10);
                fixLayers(this);
/*
            head.xRot = (float) Math.toRadians(20);
            head.yRot = (float) Math.toRadians(-40);
            head.zRot = (float) Math.toRadians(-20);*/
            }
        }
    }

    private void fixLayers(HumanoidModel<?> bipedModel) {
        if (bipedModel instanceof PlayerModel<?> playerModel) {
            playerModel.jacket.copyFrom(bipedModel.body);
            playerModel.leftPants.copyFrom(bipedModel.leftLeg);
            playerModel.rightPants.copyFrom(bipedModel.rightLeg);
            playerModel.leftSleeve.copyFrom(bipedModel.leftArm);
            playerModel.rightSleeve.copyFrom(bipedModel.rightArm);
            playerModel.hat.copyFrom(bipedModel.head);
        }
    }

}
