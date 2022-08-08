package mc.craig.software.notnotyet.client.models;


import mc.craig.software.notnotyet.client.Animations;
import mc.craig.software.notnotyet.common.capability.ModCapability;
import mc.craig.software.notnotyet.util.GliderUtil;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class GliderModel<T extends LivingEntity> extends HierarchicalModel<T> {

    private final ModelPart CentreBrace;
    private final ModelPart RMain;
    private final ModelPart LMain;
    private final ModelPart root;

    public GliderModel(ModelPart root) {
        this.root = root;
        this.CentreBrace = root.getChild("CentreBrace");
        this.RMain = root.getChild("RMain");
        this.LMain = root.getChild("LMain");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition CentreBrace = partdefinition.addOrReplaceChild("CentreBrace", CubeListBuilder.create().texOffs(25, 45).addBox(-1.0F, -0.1F, -8.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));

        PartDefinition RMain = partdefinition.addOrReplaceChild("RMain", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 0.0F));

        PartDefinition RMain_r1 = RMain.addOrReplaceChild("RMain_r1", CubeListBuilder.create().texOffs(34, 0).addBox(-13.0F, 0.0F, -0.25F, 13.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.75F, 0.0F, 0.0F, -0.3927F));

        PartDefinition RStrut = RMain.addOrReplaceChild("RStrut", CubeListBuilder.create(), PartPose.offset(-12.0F, 5.0F, 0.0F));

        PartDefinition RStrut_r1 = RStrut.addOrReplaceChild("RStrut_r1", CubeListBuilder.create().texOffs(2, 27).addBox(-0.1166F, -0.5788F, -0.25F, 12.0F, 1.0F, 16.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.75F, 0.0F, 0.0F, 0.2182F));

        PartDefinition RArm = RMain.addOrReplaceChild("RArm", CubeListBuilder.create(), PartPose.offset(-12.0F, 5.0F, 0.0F));

        PartDefinition RPole_r1 = RArm.addOrReplaceChild("RPole_r1", CubeListBuilder.create().texOffs(43, 16).addBox(-13.5F, -0.5F, -0.25F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, -5.0F, -7.75F, 0.0F, 0.0F, -0.3927F));

        PartDefinition RMain_r2 = RArm.addOrReplaceChild("RMain_r2", CubeListBuilder.create().texOffs(5, 0).addBox(-1.9134F, 4.5922F, -0.25F, 1.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -7.75F, 0.0F, 0.0F, -0.3927F));

        PartDefinition RBrace = RArm.addOrReplaceChild("RBrace", CubeListBuilder.create(), PartPose.offset(2.0F, 2.4F, 0.0F));

        PartDefinition RBrace_r1 = RBrace.addOrReplaceChild("RBrace_r1", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-0.2687F, -0.5127F, -16.0F, 4.0F, 1.0F, 16.0F, new CubeDeformation(-0.02F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 8.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition LMain = partdefinition.addOrReplaceChild("LMain", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 0.0F));

        PartDefinition LMain_r1 = LMain.addOrReplaceChild("LMain_r1", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, 0.0F, -0.25F, 13.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.75F, 0.0F, 0.0F, 0.3927F));

        PartDefinition LStrut = LMain.addOrReplaceChild("LStrut", CubeListBuilder.create(), PartPose.offset(12.0F, 5.0F, -7.75F));

        PartDefinition LStrut_r1 = LStrut.addOrReplaceChild("LStrut_r1", CubeListBuilder.create().texOffs(9, 44).addBox(-11.8834F, -0.5788F, -0.25F, 12.0F, 1.0F, 16.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition LArm = LMain.addOrReplaceChild("LArm", CubeListBuilder.create(), PartPose.offset(12.0F, 5.0F, 0.0F));

        PartDefinition LPole_r1 = LArm.addOrReplaceChild("LPole_r1", CubeListBuilder.create().texOffs(43, 16).addBox(12.5F, -0.5F, -0.25F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, -5.0F, -7.75F, 0.0F, 0.0F, 0.3927F));

        PartDefinition LMain_r2 = LArm.addOrReplaceChild("LMain_r2", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -0.0272F, -0.25F, 1.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.75F, 0.0F, 0.0F, 0.3927F));

        PartDefinition LBrace = LArm.addOrReplaceChild("LBrace", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.4F, 0.0F));

        PartDefinition LBrace_r1 = LBrace.addOrReplaceChild("LBrace_r1", CubeListBuilder.create().texOffs(0, 46).addBox(-3.7313F, -0.5127F, -16.0F, 4.0F, 1.0F, 16.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.3927F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        ModCapability.get(entity).ifPresent(iCap -> {
            this.root().getAllParts().forEach(ModelPart::resetPose);
            if (GliderUtil.isGlidingWithActiveGlider(entity)) {
                this.animate(iCap.getAnimation(ModCapability.AnimationStates.GLIDER_OPENING), Animations.OPENING, ageInTicks);
            }
        });
    }

    @Override
    public ModelPart root() {
        return root;
    }
}