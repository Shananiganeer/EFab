package mcjty.efab.recipes;

import mcjty.efab.EFab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.*;

public class EFabShapelessRecipe extends ShapedOreRecipe implements IEFabRecipe {

    @Nonnull private final Set<RecipeTier> requiredTiers = EnumSet.noneOf(RecipeTier.class);
    @Nonnull private final List<FluidStack> requiredFluids = new ArrayList<>();
    private int requiredRfPerTick = 0;
    private int requiredManaPerTick = 0;
    private int craftTime = 0;
    private String copyNBT;

    private List<String> inputs = new ArrayList<>();
    private Map<String,Object> inputMap = new HashMap<>();

    public EFabShapelessRecipe(ItemStack output, Object... recipe) {
        super(new ResourceLocation(EFab.MODID, "shapeless"), output, recipe);

        int idx = 0;
        while (idx < recipe.length) {
            Object o = recipe[idx];
            idx++;
            if (o instanceof String) {
                inputs.add((String) o);
            } else if (o instanceof Character) {
                Object item = recipe[idx];
                idx++;
                inputMap.put(String.valueOf(o), item);
            }
        }
    }

    @Override
    public List<List<ItemStack>> getInputLists() {
        List<List<ItemStack>> inputLists = new ArrayList<>();
        for (Object o : input) {
            if (o instanceof Ingredient) {
                ItemStack[] stacks = ((Ingredient) o).getMatchingStacks();
                inputLists.add(Arrays.asList(stacks));
            } else if (o instanceof ItemStack) {
                inputLists.add(Collections.singletonList((ItemStack) o));
            } else if (o instanceof List) {
                inputLists.add((List) o);
            } else {
                System.out.println("WHAT?");
            }
        }

        return inputLists;
    }

    @Override
    public void copyNBTFrom(String character) {
        copyNBT = character;
    }

    @Override
    public String getCopyNBTFrom() {
        return copyNBT;
    }

    @Override
    public List<String> getInputs() {
        return inputs;
    }

    @Override
    public Map<String, Object> getInputMap() {
        return inputMap;
    }

    @Override
    public EFabShapelessRecipe tier(RecipeTier tier) {
        requiredTiers.add(tier);
        return this;
    }

    @Override
    public EFabShapelessRecipe fluid(FluidStack stack) {
        requiredFluids.add(stack);
        return this;
    }

    @Override
    public EFabShapelessRecipe rfPerTick(int rf) {
        requiredRfPerTick = rf;
        return this;
    }

    @Override
    public IEFabRecipe manaPerTick(int mana) {
        requiredManaPerTick = mana;
        return this;
    }

    @Override
    public EFabShapelessRecipe time(int t) {
        craftTime = t;
        return this;
    }

    @Nonnull
    @Override
    public List<FluidStack> getRequiredFluids() {
        return requiredFluids;
    }

    @Override
    public int getRequiredRfPerTick() {
        return requiredRfPerTick;
    }

    @Override
    public int getRequiredManaPerTick() {
        return requiredManaPerTick;
    }

    @Override
    public int getCraftTime() {
        return craftTime;
    }

    @Nonnull
    @Override
    public Set<RecipeTier> getRequiredTiers() {
        return requiredTiers;
    }

    @Nonnull
    @Override
    public IRecipe cast() {
        return this;
    }
}
