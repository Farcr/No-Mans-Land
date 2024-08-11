package com.farcr.nomansland.core.config;

import com.farcr.nomansland.core.events.listeners.AnchorListener;
import com.ibm.icu.impl.Trie2;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class NMLConfig {

    public static final String CATEGORY_OVERRIDES = "overrides";
    public static final String CATEGORY_TAP = "tap";
    public static final String CATEGORY_ANCHOR = "monster_anchor";
    public static final String CATEGORY_SPIKE = "spike";
    public static final String CATEGORY_MISC = "miscellaneous";
    public static final String CATEGORY_FOG_MODIFIERS = "fog_modifiers";
    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec.BooleanValue MYCELIUM_SPREADS;
    public static ModConfigSpec.BooleanValue GRASS_SPREADS;
    public static ModConfigSpec.BooleanValue MALEVOLENT_SPAWNER;
    public static ModConfigSpec.DoubleValue FILLING_SPEED_MULTIPLIER;
    public static ModConfigSpec.IntValue TICKS_TO_FILL_CAULDRON;
    public static ModConfigSpec.IntValue TICKS_BETWEEN_RESURRECTIONS;
    public static ModConfigSpec.ConfigValue<List<? extends String>> MONSTER_BLACKLIST;
    public static ModConfigSpec.DoubleValue POKING_DAMAGE;
    public static ModConfigSpec.DoubleValue FALLING_DAMAGE;
    public static ModConfigSpec.DoubleValue IMPALING_DAMAGE;
    public static ModConfigSpec.DoubleValue SKEWERING_DAMAGE;
    public static ModConfigSpec.IntValue WOODEN_SCAFFOLDING_DISTANCE;
    public static ModConfigSpec.DoubleValue BURIED_SPAWNING_CHANCE;
    public static ModConfigSpec CLIENT_CONFIG;
    public static ModConfigSpec.BooleanValue FOG_MODIFIERS;

    static {

        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_OVERRIDES);
        GRASS_SPREADS = COMMON_BUILDER.comment("If grass or mycelium spread to nearby dirt.").define("grassSpreads", true);
        MYCELIUM_SPREADS = COMMON_BUILDER.define("myceliumSpreads", true);
        MALEVOLENT_SPAWNER = COMMON_BUILDER.comment("If monster spawners should produce malevolent fire instead of regular fire (like monster anchors).")
                .define("malevolentSpawner", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_TAP);
        FILLING_SPEED_MULTIPLIER = COMMON_BUILDER
                .comment("Multiplier on how fast taps should fill up cauldrons with resin. Set it to 0.0 to disable this.")
                .defineInRange("fillingSpeedMultiplier", 1.0, 0, 10);
        TICKS_TO_FILL_CAULDRON = COMMON_BUILDER
                .comment("The time it takes to fill a cauldron with honey using a tap. 20 ticks make one second.")
                .defineInRange("ticksToFillCauldron", 80, 1, 400);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_ANCHOR);
        TICKS_BETWEEN_RESURRECTIONS = COMMON_BUILDER
                .comment("The time between each resurrection from a monster anchor.")
                .defineInRange("ticksBetweenResurrections", 80, 78, 400);
        MONSTER_BLACKLIST = COMMON_BUILDER
                .comment("A list of monsters that the monster anchor will not resurrect")
                .defineList("monsterBlacklist", Arrays.asList("examplemod:example_entity"), () -> "", entry -> entry.toString().contains(":"));
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_SPIKE);
        COMMON_BUILDER.comment("Damage is dealt in hit points. 2 hit points make 1 heart.");
        POKING_DAMAGE = COMMON_BUILDER
                .comment("The continuous damage dealt to an entity as it stands on an active spike.")
                .defineInRange("pokingDamage", 1.5, 0, Double.MAX_VALUE);
        FALLING_DAMAGE = COMMON_BUILDER
                .comment("The added damage dealt to an entity when it falls on a spike from any height.")
                .defineInRange("fallingDamage", 2.0, 0, Double.MAX_VALUE);
        IMPALING_DAMAGE = COMMON_BUILDER
                .comment("The damage dealt to an entity when an adjacent spike is activated.")
                .defineInRange("impalingDamage", 12.0, 0, Double.MAX_VALUE);
        SKEWERING_DAMAGE = COMMON_BUILDER
                .comment("The damage dealt to an entity when an active spike is pushed into it.")
                .defineInRange("skeweringDamage", 12.0, 0, Double.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_MISC);
        WOODEN_SCAFFOLDING_DISTANCE = COMMON_BUILDER
                .comment("The max distance a wooden scaffolding can be from another scaffolding.")
                .defineInRange("woodenScaffoldingDistance", 6, 2, 20);
        BURIED_SPAWNING_CHANCE = COMMON_BUILDER
                .comment("The chance a buried is spawned upon brushing a remains block.")
                .defineInRange("buriedSpawningChance", 0.05, 0, 1);
        COMMON_BUILDER.pop();


        COMMON_CONFIG = COMMON_BUILDER.build();

        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

        CLIENT_BUILDER.push(CATEGORY_FOG_MODIFIERS);
        FOG_MODIFIERS = CLIENT_BUILDER
                .comment("If the custom fog modifiers are enabled")
                .define("fogModifiers", true);
        CLIENT_BUILDER.pop();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

}
