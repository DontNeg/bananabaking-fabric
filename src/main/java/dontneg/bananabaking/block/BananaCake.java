package dontneg.bananabaking.block;

import net.minecraft.block.CakeBlock;

public class BananaCake extends CakeBlock {

    public BananaCake(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(BITES,0));
    }

}
