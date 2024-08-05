package dontneg.bananabaking.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BananaCake extends CakeBlock {

    public BananaCake(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(BITES,0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,40,2,false,false,true));
        return super.onUse(state, world, pos, player, hit);
    }
}
