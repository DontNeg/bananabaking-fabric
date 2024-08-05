package dontneg.bananabaking.codec;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record BakingData(BlockPos pos) {
    public static final PacketCodec<RegistryByteBuf, BakingData> PACKET_CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, BakingData::pos, BakingData::new
    );
}
