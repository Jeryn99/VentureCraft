package mc.craig.software.craftplus.common.items;

import mc.craig.software.craftplus.common.block.VCChestTypes;
import net.minecraft.world.item.Item;

public class KeyItem extends Item {

    private final VCChestTypes chestType;


    public KeyItem(Properties properties, VCChestTypes chestType) {
        super(properties);
        this.chestType = chestType;
    }


    public VCChestTypes getChestType() {
        return chestType;
    }
}
