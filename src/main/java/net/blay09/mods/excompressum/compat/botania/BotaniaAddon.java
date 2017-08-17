package net.blay09.mods.excompressum.compat.botania;

import net.blay09.mods.excompressum.ExCompressum;
import net.blay09.mods.excompressum.compat.Compat;
import net.blay09.mods.excompressum.compat.IAddon;
import net.blay09.mods.excompressum.config.ModConfig;
import net.blay09.mods.excompressum.item.ItemManaHammer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

import java.util.Iterator;

public class BotaniaAddon implements IAddon {

    public static final String SUBTILE_ORECHID_EVOLVED = ExCompressum.MOD_ID + ".orechidEvolved";
    private static final String LEXICON_ORECHID_EVOLVED = "botania.entry." + ExCompressum.MOD_ID + ".orechidEvolved";
    private static final String LEXICON_ORECHID_EVOLVED_PAGE = "botania.page." + ExCompressum.MOD_ID + ".orechidEvolved";

    public static LexiconEntry lexiconOrechidEvolved;

    public static Item manaHammer;

    public BotaniaAddon() {
        BotaniaAPI.registerSubTile(SUBTILE_ORECHID_EVOLVED, SubTileOrechidEvolved.class);
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        registry.register(manaHammer = new ItemManaHammer().setRegistryName("mana_hammer"));
    }

    @Override
    @SideOnly(Side.CLIENT) // account for unnecessary SideOnly in BotaniaAPIClient
    public void registerModels() {
        BotaniaAPIClient.registerSubtileModel(SubTileOrechidEvolved.class, new ModelResourceLocation(new ResourceLocation(Compat.BOTANIA, "orechid"), "normal"));
    }

    @Override
    public void postInit() {
        if(ModConfig.compat.enableEvolvedOrechid) {
            BotaniaAPI.registerSubTileSignature(SubTileOrechidEvolved.class, new SubTileOrechidEvolvedSignature());
            ItemStack orechidEvolved = ItemBlockSpecialFlower.ofType(SUBTILE_ORECHID_EVOLVED);
            ExCompressum.creativeTab.addAdditionalItem(orechidEvolved);
            RecipePetals recipeOrechidEvolved = BotaniaAPI.registerPetalRecipe(orechidEvolved, "petalGray", "petalGray", "petalYellow", "petalYellow", "petalGreen", "petalGreen", "petalRed", "petalRed");
            lexiconOrechidEvolved = new LexiconEntry(LEXICON_ORECHID_EVOLVED, BotaniaAPI.categoryFunctionalFlowers) {
                @Override
                public String getWebLink() {
                    return "http://blay09.net/mods/excompressum/";
                }

                @Override
                public String getTagline() {
                    return "botania.tagline.excompressum.orechidEvolved";
                }
            };
            lexiconOrechidEvolved.setLexiconPages(new PageText(LEXICON_ORECHID_EVOLVED_PAGE + "0"), new PagePetalRecipe<>(LEXICON_ORECHID_EVOLVED_PAGE + "1", recipeOrechidEvolved));
            lexiconOrechidEvolved.setPriority();
            BotaniaAPI.addEntry(lexiconOrechidEvolved, lexiconOrechidEvolved.category);
            BotaniaAPI.addSubTileToCreativeMenu(SUBTILE_ORECHID_EVOLVED);
        }

        if(ModConfig.compat.disableVanillaOrechid) {
            Iterator<LexiconEntry> it = BotaniaAPI.getAllEntries().iterator();
            while(it.hasNext()) {
                if(it.next().getUnlocalizedName().equals("botania.entry.orechid")) {
                    it.remove();
                    break;
                }
            }
            it = BotaniaAPI.categoryFunctionalFlowers.entries.iterator();
            while(it.hasNext()) {
                if(it.next().getUnlocalizedName().equals("botania.entry.orechid")) {
                    it.remove();
                    break;
                }
            }
            Iterator<RecipePetals> it2 = BotaniaAPI.petalRecipes.iterator();
            ItemStack itemStackOrechid = ItemBlockSpecialFlower.ofType("orechid");
            while(it2.hasNext()) {
                ItemStack output = it2.next().getOutput();
                if(ItemStack.areItemStacksEqual(itemStackOrechid, output)) {
                    it2.remove();
                    break;
                }
            }
        }
    }

}
