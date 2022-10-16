package mc.craig.software.craftplus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.craftplus.common.ModBlocks;
import mc.craig.software.craftplus.common.block.LockedLootChestBlock;
import mc.craig.software.craftplus.common.block.VCChestTypes;
import mc.craig.software.craftplus.common.blockentity.LockedLootChestBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {

    private static final LockedLootChestBlockEntity goldBlockEntity = new LockedLootChestBlockEntity(BlockPos.ZERO, ModBlocks.GOLD_LOOT_CHEST.get().defaultBlockState(), VCChestTypes.GOLD);
    private static final LockedLootChestBlockEntity voidBlockEntity = new LockedLootChestBlockEntity(BlockPos.ZERO, ModBlocks.VOID_LOOT_CHEST.get().defaultBlockState(), VCChestTypes.VOID);
    private static final LockedLootChestBlockEntity ironBlockEntity = new LockedLootChestBlockEntity(BlockPos.ZERO, ModBlocks.IRON_LOOT_CHEST.get().defaultBlockState(), VCChestTypes.IRON);
    private static final LockedLootChestBlockEntity sapphireBlockEntity = new LockedLootChestBlockEntity(BlockPos.ZERO, ModBlocks.SAPPHIRE_LOOT_CHEST.get().defaultBlockState(), VCChestTypes.SAPPHIRE);

    @Inject(at = @At("HEAD"), cancellable = true, method = "renderByItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V")
    private void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, CallbackInfo ci) {
        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof LockedLootChestBlock lootChestBlock) {
            LockedLootChestBlockEntity blockEntity = null;
            switch (lootChestBlock.getChestType()) {
                case GOLD -> blockEntity = goldBlockEntity;
                case VOID -> blockEntity = voidBlockEntity;
                case SAPPHIRE -> blockEntity = sapphireBlockEntity;
                case IRON -> blockEntity = ironBlockEntity;
            }
            Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem((BlockEntity) blockEntity, poseStack, buffer, packedLight, packedOverlay);
            ci.cancel();
        }
    }

}
