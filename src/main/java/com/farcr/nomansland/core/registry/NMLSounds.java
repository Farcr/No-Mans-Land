package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NMLSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, NoMansLand.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> SPIKES_EXTEND = registerSound("block.spike_trap.spikes_extend");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIKES_RETRACT = registerSound("block.spike_trap.spikes_retract");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(NoMansLand.MODID, name)));
    }
}
