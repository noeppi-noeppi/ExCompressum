package net.blay09.mods.excompressum.item;

import net.blay09.mods.excompressum.ExCompressum;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class WoodChippingItem extends Item {

    public static final String name = "wood_chippings";
    public static final ResourceLocation registryName = new ResourceLocation(ExCompressum.MOD_ID, name);

    public WoodChippingItem(Properties properties) {
        super(properties);
    }
}
