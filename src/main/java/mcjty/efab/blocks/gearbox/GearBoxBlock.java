package mcjty.efab.blocks.gearbox;

import mcjty.efab.blocks.GenericEFabMultiBlockPart;
import mcjty.efab.config.GeneralConfiguration;
import mcjty.lib.container.EmptyContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class GearBoxBlock extends GenericEFabMultiBlockPart<GearBoxTE, EmptyContainer> {

    public GearBoxBlock() {
        super(Material.IRON, GearBoxTE.class, EmptyContainer::new, "gearbox", false);
    }

    @Override
    public RotationType getRotationType() {
        return RotationType.NONE;
    }

    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        tooltip.add(TextFormatting.WHITE + "This block adds " + TextFormatting.GREEN + "gearbox"
                + TextFormatting.WHITE + " style crafting to the fabricator");
        if (GeneralConfiguration.maxSpeedupBonus > 1) {
            tooltip.add(TextFormatting.GOLD + "You can use up to " + GeneralConfiguration.maxSpeedupBonus + " gearboxes");
            tooltip.add(TextFormatting.GOLD + "to speed up gearbox related recipes");
        }
    }

}
