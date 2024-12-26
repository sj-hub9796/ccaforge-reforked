package net.sjhub.ccaforge.mixin.accessor;

import net.minecraft.server.network.ServerPlayerConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(targets = "net/minecraft/server/level/ChunkMap$TrackedEntity")
public interface EntityMapAccessor {

    @Accessor("seenBy")
    Set<ServerPlayerConnection> getPlayersTracking();
}
