package mc.craig.software.craftplus.handlers;

import mc.craig.software.craftplus.client.screen.ExtendedInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.ScreenEvents;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ClientEvents implements ScreenEvents.Opening {

    public static int lightLevel = 0;

    public static void init() {
        var instance = new ClientEvents();
        ScreenEvents.OPENING.register(instance);
    }

    @Override
    public EventResult screenOpening(@Nullable Screen currentScreen, AtomicReference<Screen> newScreen) {
        if (newScreen.get() instanceof InventoryScreen) {
            newScreen.set(new ExtendedInventoryScreen(Objects.requireNonNull(Minecraft.getInstance().player)));
        }
        return EventResult.pass();
    }
}
