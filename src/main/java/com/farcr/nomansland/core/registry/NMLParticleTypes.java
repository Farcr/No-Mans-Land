package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class NMLParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NoMansLand.MODID);
    public static final RegistryObject<ParticleType> PALE_CHERRY_LEAVES = registerParticle("pale_cherry_leaves",
            ()-> new SimpleParticleType(false) {});
    public static final RegistryObject<ParticleType> CAVE_DUST = registerParticle("cave_dust",
            ()-> new SimpleParticleType(false) {});

    public static final RegistryObject<ParticleType> RESIN_DROPLET = registerParticle("resin_droplet",
            ()-> new SimpleParticleType(false) {});

    public static final RegistryObject<ParticleType> RESIN_DROPLET_FLAT = registerParticle("resin_droplet_flat",
            ()-> new SimpleParticleType(false) {});
    public static final RegistryObject<ParticleType> MALEVOLENT_FLAME = registerParticle("malevolent_flame",
            ()-> new SimpleParticleType(false) {});
    public static final RegistryObject<ParticleType> MALEVOLENT_EMBERS = registerParticle("malevolent_embers",
            ()-> new SimpleParticleType(false) {});

    private static <T extends ParticleType> RegistryObject<T> registerParticle(String name, Supplier<T> particle ) {
        return PARTICLE_TYPES.register(name, particle);
    }

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
