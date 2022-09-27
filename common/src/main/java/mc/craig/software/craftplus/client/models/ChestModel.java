package mc.craig.software.craftplus.client.models;

import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ChestModel extends HierarchicalModel implements AnimatableBEModel<LockedLootChestBlockEntity> {

    private final ModelPart bone;
    private final ModelPart bb_main;
    private final ModelPart root;

    public ChestModel(ModelPart root) {
        this.root = root;
        this.bone = root.getChild("bone");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition getModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, 14.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -7.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(2, 6).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 14.5F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, -7.0F, 7.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, -7.5F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, -10.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }

    @Override
    public void animate(LockedLootChestBlockEntity blockentity) {

    }
}
