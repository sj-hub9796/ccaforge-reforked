package net.sjhub.ccaforge.util;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.chunk.ChunkSource;
import net.sjhub.ccaforge.mixin.accessor.ChunkMapAccessor;
import net.sjhub.ccaforge.mixin.accessor.EntityMapAccessor;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author DustW
 */
public class Players {
    public static Collection<ServerPlayer> tracking(Entity entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        ChunkSource manager = entity.level().getChunkSource();

        if (manager instanceof ServerChunkCache) {
            ChunkMap storage = ((ServerChunkCache) manager).chunkMap;
            EntityMapAccessor tracker = ((ChunkMapAccessor) storage).getEntityMap().get(entity.getId());

            if (tracker != null) {
                return Collections.unmodifiableCollection(tracker.getPlayersTracking()
                        .stream().map(ServerPlayerConnection::getPlayer).collect(Collectors.toSet()));
            }

            return Collections.emptySet();
        }

        throw new IllegalArgumentException("Only supported on server worlds!");
    }
}
