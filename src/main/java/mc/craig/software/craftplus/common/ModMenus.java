package mc.craig.software.craftplus.common;

import com.mojang.blaze3d.platform.ScreenManager;
import mc.craig.software.craftplus.client.screen.ExtendedInventoryScreen;
import mc.craig.software.craftplus.common.menu.ExtendedInventoryMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ScreenUtils;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mc.craig.software.craftplus.MinecraftPlus.MODID;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    public static final RegistryObject<MenuType<ExtendedInventoryMenu>> EXTENDED_INVENTORY = MENU_TYPES.register("extended_inventory",
            () -> new MenuType<>(ExtendedInventoryMenu::new));

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens() {
        MenuScreens.register(EXTENDED_INVENTORY.get(), new MenuScreens.ScreenConstructor<ExtendedInventoryMenu, ExtendedInventoryScreen>() {
            @Override
            public ExtendedInventoryScreen create(ExtendedInventoryMenu pMenu, Inventory pInventory, Component pTitle) {
                return new ExtendedInventoryScreen(pMenu, pInventory);
            }
        });
    }

}
