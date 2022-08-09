package mc.craig.software.craftplus.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.craftplus.MinecraftPlus;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnimationUtil {

    public static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    public static ModelPart getAnyDescendantWithName(HumanoidModel<?> model, String partName) {
        return switch (partName) {
            case "RightLeg" -> model.rightLeg;
            case "LeftLeg" -> model.leftLeg;
            case "LeftArm" -> model.leftArm;
            case "RightArm" -> model.rightArm;
            case "Body" -> model.body;
            case "Head" -> model.head;
            default -> null;
        };

    }

    public static void animate(HumanoidModel<?> humanoidModel, AnimationDefinition animationDefinition, long p_232322_, float p_232323_, Vector3f p_232324_) {
        float elapsedSeconds = getElapsedSeconds(animationDefinition, p_232322_);

        for (Map.Entry<String, List<AnimationChannel>> entry : animationDefinition.boneAnimations().entrySet()) {
            ModelPart optional = getAnyDescendantWithName(humanoidModel, entry.getKey());

            List<AnimationChannel> list = entry.getValue();

            if (optional != null) {
                list.forEach((p_232311_) -> {
                    Keyframe[] akeyframe = p_232311_.keyframes();
                    int i = Math.max(0, Mth.binarySearch(0, akeyframe.length, (p_232315_) -> {
                        return elapsedSeconds<= akeyframe[p_232315_].timestamp();
                    }) - 1);
                    int j = Math.min(akeyframe.length - 1, i + 1);
                    Keyframe keyframe = akeyframe[i];
                    Keyframe keyframe1 = akeyframe[j];
                    float f1 = elapsedSeconds - keyframe.timestamp();
                    float f2 = Mth.clamp(f1 / (keyframe1.timestamp() - keyframe.timestamp()), 0.0F, 1.0F);
                    keyframe1.interpolation().apply(p_232324_, f2, akeyframe, i, j, p_232323_);
                    p_232311_.target().apply(optional, p_232324_);
                });
            } else {
                MinecraftPlus.LOGGER.debug("Could not find:" + entry.getKey());
            }
        }
    }

    public static Optional<ModelPart> getAnyDescendantWithName(HierarchicalModel<?> hierarchicalModel, String p_233394_) {
        return hierarchicalModel.root().getAllParts().filter((p_233400_) -> {
            return p_233400_.hasChild(p_233394_);
        }).findFirst().map((p_233397_) -> {
            return p_233397_.getChild(p_233394_);
        });
    }

    private static float getElapsedSeconds(AnimationDefinition animationDefinition, long p_232318_) {
        float f = (float) p_232318_ / 1000.0F;
        return animationDefinition.looping() ? f % animationDefinition.lengthInSeconds() : f;
    }

    public static void animate(HumanoidModel<?> model, AnimationState animationState, AnimationDefinition animationDefinition, float p_233388_, float p_233389_) {
        animationState.updateTime(p_233388_, p_233389_);
        animationState.ifStarted((p_233392_) -> {
            animate(model, animationDefinition, animationState.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE);
        });
    }

    public static void setupRotations(AbstractClientPlayer abstractClientPlayer, PoseStack poseStack, float p_117804_, float p_117805_, float p_117806_, CallbackInfo callbackInfo) {

    }
}
