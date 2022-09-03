package mc.craig.software.craftplus.mixin;

import com.mojang.authlib.GameProfile;
import mc.craig.software.craftplus.common.capability.PlayerInventoryWrapper;
import mc.craig.software.craftplus.common.menu.ExtendedInventoryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Player.class)
public class PlayerMixin {

    @Mutable
    @Shadow
    @Final
    private Inventory inventory;

    @Mutable
    @Shadow
    @Final
    public InventoryMenu inventoryMenu;

    @Shadow public AbstractContainerMenu containerMenu;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void constructor(Level pLevel, BlockPos pPos, float pYRot, GameProfile pGameProfile, @Nullable ProfilePublicKey pProfilePublicKey, CallbackInfo ci) {
        this.inventory = new PlayerInventoryWrapper(this.inventory);
        ExtendedInventoryMenu.PREVENT_SLOTS = true;
        this.inventoryMenu = new ExtendedInventoryMenu(this.inventory, !pLevel.isClientSide, (Player) (Object) this);
        this.containerMenu = this.inventoryMenu;
    }

}
