package dontneg.bananabaking.rei;

import dontneg.bananabaking.BananaBaking;
import dontneg.bananabaking.block.BananaBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BakingCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(BananaBaking.MODID, "textures/gui/baking_oven_rei.png");
    public static final CategoryIdentifier<BakingDisplay> BAKING =
            CategoryIdentifier.of(BananaBaking.MODID, "baking");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return BAKING;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Baking Oven");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BananaBlocks.BAKING_OVEN.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX(), bounds.getCenterY());
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x-88, startPoint.y-40, 176, 83)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x - 68, startPoint.y - 24))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 50, startPoint.y - 24))
                .entries(display.getInputEntries().get(1)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 32, startPoint.y - 24))
                .entries(display.getInputEntries().get(2)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 68, startPoint.y - 6))
                .entries(display.getInputEntries().get(3)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 50, startPoint.y - 6))
                .entries(display.getInputEntries().get(4)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 32, startPoint.y - 6))
                .entries(display.getInputEntries().get(5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 68, startPoint.y + 12))
                .entries(display.getInputEntries().get(6)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 50, startPoint.y + 12))
                .entries(display.getInputEntries().get(7)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 32, startPoint.y + 12))
                .entries(display.getInputEntries().get(8)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 38, startPoint.y - 5))
                .markOutput().disableBackground().entries(display.getOutputEntries().get(0)));
        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }

}