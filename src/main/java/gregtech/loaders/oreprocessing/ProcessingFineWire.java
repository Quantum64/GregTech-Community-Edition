package gregtech.loaders.oreprocessing;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.IOreRegistrationHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.SimpleItemStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.material.type.DustMaterial.MatFlags.NO_SMASHING;
import static gregtech.api.unification.material.type.DustMaterial.MatFlags.NO_WORKING;
import static gregtech.api.unification.material.type.Material.MatFlags.NO_UNIFICATION;

public class ProcessingFineWire implements IOreRegistrationHandler {

	private ProcessingFineWire() {}

	public static void register() {
		OrePrefix.wireFine.addProcessingHandler(new ProcessingFineWire());
	}

	public void registerOre(UnificationEntry entry, String modName, SimpleItemStack simpleStack) {
        ItemStack stack = simpleStack.asItemStack();

		if (!entry.material.hasFlag(NO_SMASHING)) {

			RecipeMap.WIREMILL_RECIPES.recipeBuilder()
					.inputs(OreDictUnifier.get(OrePrefix.ingot, entry.material))
					.outputs(GTUtility.copy(OreDictUnifier.get(OrePrefix.wireGt01, entry.material, 2), stack))
					.duration(100)
					.EUt(4)
					.buildAndRegister();

			RecipeMap.WIREMILL_RECIPES.recipeBuilder()
					.inputs(OreDictUnifier.get(OrePrefix.stick, entry.material))
					.outputs(GTUtility.copy(OreDictUnifier.get(OrePrefix.wireGt01, entry.material, 1), stack))
					.duration(50)
					.EUt(4)
					.buildAndRegister();
		}

		if (!entry.material.hasFlag(NO_WORKING | NO_UNIFICATION)) {
			ModHandler.addShapelessRecipe("finewire_" + entry.material, stack, 'x', OreDictUnifier.get(OrePrefix.ingot, entry.material));
		}

	}

}