package net.blay09.mods.excompressum.registry;

import net.blay09.mods.excompressum.ExCompressum;
import net.blay09.mods.excompressum.compat.jei.HeavySieveRecipe;
import net.blay09.mods.excompressum.registry.chickenstick.ChickenStickRegistry;
import net.blay09.mods.excompressum.registry.compressedhammer.CompressedHammerRegistry;
import net.blay09.mods.excompressum.registry.compressor.CompressedRecipeRegistry;
import net.blay09.mods.excompressum.registry.hammer.HammerRegistry;
import net.blay09.mods.excompressum.registry.heavysieve.HeavySieveRegistry;
import net.blay09.mods.excompressum.registry.woodencrucible.WoodenCrucibleRegistry;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExCompressum.MOD_ID)
public class ExRegistries {

    private static CompressedRecipeRegistry compressedRecipeRegistry;
    private static ChickenStickRegistry chickenStickRegistry;
    private static HammerRegistry hammerRegistry;
    private static CompressedHammerRegistry compressedHammerRegistry;
    private static WoodenCrucibleRegistry woodenCrucibleRegistry;
    private static HeavySieveRegistry heavySieveRegistry;

    @SubscribeEvent
    public static void addReloadListeners(AddReloadListenerEvent event) {
        event.addListener(compressedRecipeRegistry = new CompressedRecipeRegistry(event.getDataPackRegistries()));
        event.addListener(chickenStickRegistry = new ChickenStickRegistry());
        event.addListener(hammerRegistry = new HammerRegistry());
        event.addListener(compressedHammerRegistry = new CompressedHammerRegistry());
        event.addListener(woodenCrucibleRegistry = new WoodenCrucibleRegistry());
        event.addListener(heavySieveRegistry = new HeavySieveRegistry());
    }

    public static CompressedRecipeRegistry getCompressedRecipeRegistry() {
        return compressedRecipeRegistry;
    }

    public static ChickenStickRegistry getChickenStickRegistry() {
        return chickenStickRegistry;
    }

    public static HammerRegistry getHammerRegistry() {
        return hammerRegistry;
    }

    public static CompressedHammerRegistry getCompressedHammerRegistry() {
        return compressedHammerRegistry;
    }

    public static WoodenCrucibleRegistry getWoodenCrucibleRegistry() {
        return woodenCrucibleRegistry;
    }

    public static HeavySieveRegistry getHeavySieveRegistry() {
        return heavySieveRegistry;
    }
}
