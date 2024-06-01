package com.farcr.nomansland.core.registry;

import com.farcr.nomansland.core.NoMansLand;
import com.farcr.nomansland.core.content.block.StandingSignBlock;
import com.farcr.nomansland.core.content.block.WallHangingSignBlock;
import com.farcr.nomansland.core.content.block.WallSignBlock;
import com.farcr.nomansland.core.content.block.*;
import com.farcr.nomansland.core.content.world.tree.HugeMushrooms;
import com.farcr.nomansland.core.registry.integration.FDIntegration;
import com.google.common.collect.Sets;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class NMLBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NoMansLand.MODID);

    public static LinkedHashSet<DeferredHolder<Item, BlockItem>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    //Plants and Other Natural Decorations
    public static final DeferredBlock<Block> GRASS_SPROUTS = registerBlock("grass_sprouts",
            () -> new GrassSproutsBlock(Block.Properties.ofFullCopy(Blocks.FERN).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final DeferredBlock<Block> OAT_GRASS = registerBlock("oat_grass",
            () -> new SimpleFoliageBlock(Block.Properties.ofFullCopy(Blocks.FERN).offsetType(BlockBehaviour.OffsetType.XYZ)));

    public static final DeferredBlock<Block> DRIED_GRASS = registerBlock("dried_grass",
            () -> new DesertPlantBlock(Block.Properties.ofFullCopy(Blocks.DEAD_BUSH).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final DeferredBlock<Block> FIDDLEHEAD = registerBlock("fiddlehead",
            () -> new SimpleFoliageBlock(Block.Properties.ofFullCopy(Blocks.FERN).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final DeferredBlock<Block> CATTAIL = registerBlock("cattail",
            () -> new WaterDoublePlantBlock(Block.Properties.ofFullCopy(Blocks.TALL_GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final DeferredBlock<Block> PEBBLES = registerBlock("pebbles",
            () -> new PebbleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noCollission().instabreak().sound(SoundType.STONE).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> YELLOW_BIRCH_LEAVES = registerBlock("yellow_birch_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating(((s, g, p) -> false))));
    // TODO: fix sapling
//    public static final DeferredBlock<Block> YELLOW_BIRCH_SAPLING = registerBlock("yellow_birch_sapling",
//            () -> new SaplingBlock((new YellowBirchTreeGrower()), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SAPLING)));
//    public static final DeferredBlock<Block> POTTED_YELLOW_BIRCH_SAPLING = BLOCKS.register("potted_yellow_birch_sapling",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.YELLOW_BIRCH_SAPLING,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_BIRCH_SAPLING).noOcclusion()));
    public static final DeferredBlock<Block> AUTUMNAL_OAK_LEAVES = registerBlock("autumnal_oak_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating(((s, g, p) -> false))));
    // TODO: fix sapling
//    public static final DeferredBlock<Block> AUTUMNAL_OAK_SAPLING = registerBlock("autumnal_oak_sapling",
//            () -> new SaplingBlock((new AutumnalOakTreeGrower()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
//    public static final DeferredBlock<Block> POTTED_AUTUMNAL_OAK_SAPLING = BLOCKS.register("potted_autumnal_oak_sapling",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.AUTUMNAL_OAK_SAPLING,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));

    public static final DeferredBlock<Block> PALE_CHERRY_LEAVES = registerBlock("pale_cherry_leaves",
            () -> new PaleCherryLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating(((s, g, p) -> false))));

    // TODO: fix sapling
//    public static final DeferredBlock<Block> PALE_CHERRY_SAPLING = registerBlock("pale_cherry_sapling",
//            () -> new SaplingBlock((new PaleCherryTreeGrower()), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)));
//    public static final DeferredBlock<Block> POTTED_PALE_CHERRY_SAPLING = BLOCKS.register("potted_pale_cherry_sapling",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.PALE_CHERRY_SAPLING,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_CHERRY_SAPLING).noOcclusion()));

//    public static final DeferredBlock<Block> SHRUB = registerBlock("shrub",
//            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)));

    public static final DeferredBlock<Block> ACONITE = registerBlock("aconite",
            () -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 20,BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> POTTED_ACONITE = BLOCKS.register("potted_aconite",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.ACONITE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> BLUE_LUPINE = registerBlock("blue_lupine",
            () -> new FlowerBlock(MobEffects.ABSORPTION, 5,BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> POTTED_BLUE_LUPINE = BLOCKS.register("potted_blue_lupine",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.BLUE_LUPINE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> RED_LUPINE = registerBlock("red_lupine",
            () -> new FlowerBlock(MobEffects.ABSORPTION, 5,BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> POTTED_RED_LUPINE = BLOCKS.register("potted_red_lupine",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.RED_LUPINE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> YELLOW_LUPINE = registerBlock("yellow_lupine",
            () -> new FlowerBlock(MobEffects.ABSORPTION, 5,BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> POTTED_YELLOW_LUPINE = BLOCKS.register("potted_yellow_lupine",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.YELLOW_LUPINE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> PINK_LUPINE = registerBlock("pink_lupine",
            () -> new FlowerBlock(MobEffects.ABSORPTION, 5,BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> POTTED_PINK_LUPINE = BLOCKS.register("potted_pink_lupine",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.PINK_LUPINE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> AUTUMN_CROCUS = registerBlock("autumn_crocus",
            () -> new FlowerBlock(MobEffects.BLINDNESS, 10,BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> POTTED_AUTUMN_CROCUS = BLOCKS.register("potted_autumn_crocus",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.AUTUMN_CROCUS,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> WILD_MINT = registerBlock("wild_mint",
            () -> new FlowerBlock(MobEffects.SATURATION, 1,BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> POTTED_WILD_MINT = BLOCKS.register("potted_wild_mint",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.WILD_MINT,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> RAFFLESIA = registerBlock("rafflesia",
            () -> new FlatFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> BARREL_CACTUS = registerBlock("barrel_cactus",
            () -> new DesertPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.BIG_DRIPLEAF).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> POTTED_BARREL_CACTUS = BLOCKS.register("potted_barrel_cactus",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.BARREL_CACTUS,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> SUCCULENT = registerBlock("succulent",
            () -> new DesertPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.FLOWERING_AZALEA).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> POTTED_SUCCULENT = BLOCKS.register("potted_succulent",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.SUCCULENT,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final DeferredBlock<Block> CUT_VINE = BLOCKS.register("cut_vine",
            () -> new VineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> CUT_SUGAR_CANE = BLOCKS.register("cut_sugar_cane",
            () -> new CutSugarCaneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> CLOVER_PATCH = registerBlock("clover_patch",
            () -> new FlowerbedBlock(Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> WHITE_FLOWERBED = registerBlock("white_flowerbed",
            () -> new FlowerbedBlock(Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> YELLOW_FLOWERBED = registerBlock("yellow_flowerbed",
            () -> new FlowerbedBlock(Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> RED_FLOWERBED = registerBlock("red_flowerbed",
            () -> new FlowerbedBlock(Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> BLUE_FLOWERBED = registerBlock("blue_flowerbed",
            () -> new FlowerbedBlock(Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> VIOLET_FLOWERBED = registerBlock("violet_flowerbed",
            () -> new FlowerbedBlock(Block.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    //Underground
    public static final DeferredBlock<Block> QUARTZITE = registerBlock("quartzite",
            () -> new AmethystBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(1.3F).sound(SoundType.NETHER_GOLD_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BUDDING_QUARTZITE = registerBlock("budding_quartzite",
            () -> new BuddingQuartziteBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).randomTicks().strength(1.3F).sound(SoundType.NETHER_GOLD_ORE).requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> QUARTZITE_CLUSTER = registerBlock("quartzite_cluster",
            () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).forceSolidOn().noOcclusion().randomTicks().sound(SoundType.NETHER_GOLD_ORE).strength(1.3F).lightLevel((p_152632_) -> {
                return 5;
            }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> SMALL_QUARTZITE_BUD = registerBlock("small_quartzite_bud",
            () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(NMLBlocks.QUARTZITE_CLUSTER.get()).sound(SoundType.NETHER_GOLD_ORE).forceSolidOn().lightLevel((p_187409_) -> {
                return 1;
            }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> MEDIUM_QUARTZITE_BUD = registerBlock("medium_quartzite_bud",
            () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(NMLBlocks.QUARTZITE_CLUSTER.get()).sound(SoundType.NETHER_GOLD_ORE).forceSolidOn().lightLevel((p_152617_) -> {
                return 2;
            }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> LARGE_QUARTZITE_BUD = registerBlock("large_quartzite_bud",
            () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(NMLBlocks.QUARTZITE_CLUSTER.get()).sound(SoundType.NETHER_GOLD_ORE).forceSolidOn().lightLevel((p_152629_) -> {
                return 4;
            }).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> PETRIFIED_LOG = registerBlock("petrified_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final DeferredBlock<Block> PETRIFIED_WOOD = registerBlock("petrified_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));

    //Paths
    public static final DeferredBlock<Block> DIRT_PATH = registerBlock("dirt_path",
            () -> new PathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), Blocks.DIRT, false));
    public static final DeferredBlock<Block> MYCELIUM_PATH = registerBlock("mycelium_path",
            () -> new PathBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.6F).sound(SoundType.GRASS), Blocks.DIRT, false));
    public static final DeferredBlock<Block> PODZOL_PATH = registerBlock("podzol_path",
            () -> new PathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL), Blocks.PODZOL, false));
    public static final DeferredBlock<Block> SNOWY_GRASS_PATH = registerBlock("snowy_grass_path",
            () -> new PathBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.6F).sound(SoundType.GRASS), Blocks.DIRT, false));
    public static final DeferredBlock<Block> SNOW_PATH = registerBlock("snow_path",
            () -> new PathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK), Blocks.SNOW_BLOCK, false));
    public static final DeferredBlock<Block> GRAVEL_PATH = registerBlock("gravel_path",
            () -> new PathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL), Blocks.GRAVEL, true));
    public static final DeferredBlock<Block> SAND_PATH = registerBlock("sand_path",
            () -> new PathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND), Blocks.SAND, true));
    public static final DeferredBlock<Block> RED_SAND_PATH = registerBlock("red_sand_path",
            () -> new PathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SAND), Blocks.RED_SAND, true));

    //Decorations
    public static final DeferredBlock<Block> SCONCE_TORCH = BLOCKS.register("sconce_torch",
            () -> new SconceTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> SCONCE_WALL_TORCH = BLOCKS.register("sconce_wall_torch",
            () -> new SconceWallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> SCONCE_SOUL_TORCH = BLOCKS.register("sconce_soul_torch",
            () -> new SconceTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_TORCH).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> SCONCE_SOUL_WALL_TORCH = BLOCKS.register("sconce_soul_wall_torch",
            () -> new SconceWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_WALL_TORCH).sound(SoundType.LANTERN)));

    public static final DeferredBlock<Block> EXTINGUISHED_TORCH = BLOCKS.register("extinguished_torch",
            () -> new ExtinguishedTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), Blocks.TORCH));
    public static final DeferredBlock<Block> EXTINGUISHED_WALL_TORCH = BLOCKS.register("extinguished_wall_torch",
            () -> new ExtinguishedWallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), Blocks.WALL_TORCH));
    public static final DeferredBlock<Block> EXTINGUISHED_SOUL_TORCH = BLOCKS.register("extinguished_soul_torch",
            () -> new ExtinguishedTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), Blocks.SOUL_TORCH));
    public static final DeferredBlock<Block> EXTINGUISHED_SOUL_WALL_TORCH = BLOCKS.register("extinguished_soul_wall_torch",
            () -> new ExtinguishedWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), Blocks.SOUL_WALL_TORCH));
    public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_TORCH = BLOCKS.register("extinguished_sconce_torch",
            () -> new ExtinguishedSconceTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY), NMLBlocks.SCONCE_TORCH.get(), ParticleTypes.FLAME));
    public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_WALL_TORCH = BLOCKS.register("extinguished_sconce_wall_torch",
            () -> new ExtinguishedSconceWallTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY), NMLBlocks.SCONCE_WALL_TORCH.get(), ParticleTypes.FLAME));
    public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_SOUL_TORCH = BLOCKS.register("extinguished_sconce_soul_torch",
            () -> new ExtinguishedSconceTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY), NMLBlocks.SCONCE_SOUL_TORCH.get(), ParticleTypes.SOUL_FIRE_FLAME));
    public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_SOUL_WALL_TORCH = BLOCKS.register("extinguished_sconce_soul_wall_torch",
            () -> new ExtinguishedSconceWallTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY), NMLBlocks.SCONCE_SOUL_WALL_TORCH.get(), ParticleTypes.SOUL_FIRE_FLAME));
    public static final DeferredBlock<Block> WOODEN_SCAFFOLDING = BLOCKS.register("wooden_scaffolding",
            () -> new WoodenScaffoldingBlock(Block.Properties.ofFullCopy(Blocks.SCAFFOLDING).sound(SoundType.CHERRY_WOOD)));
    //Dungeon
    public static final DeferredBlock<Block> REMAINS = registerBlock("remains",
            () -> new RemainsBlock(Blocks.COARSE_DIRT, BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.SUSPICIOUS_SAND).pushReaction(PushReaction.DESTROY), SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED));
    public static final DeferredBlock<Block> MONSTER_ANCHOR = registerBlock("monster_anchor",
            () -> new MonsterAnchorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPAWNER).strength(7, 7).noOcclusion()));

    //Tiles
    public static final DeferredBlock<Block> MUNDANE_TILES = registerBlock("mundane_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> EARTHEN_TILES = registerBlock("earthen_tiles",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    //Stone
    public static final DeferredBlock<Block> FADED_STONE_BRICKS = registerBlock("faded_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> POLISHED_STONE = registerBlock("polished_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> POLISHED_STONE_STAIRS = registerBlock("polished_stone_stairs",
            () -> new StairBlock(() -> POLISHED_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(NMLBlocks.POLISHED_STONE.get())));
    public static final DeferredBlock<Block> POLISHED_STONE_SLAB = registerBlock("polished_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.POLISHED_STONE.get())));

    public static final DeferredBlock<Block> COBBLESTONE_BRICKS = registerBlock("cobblestone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final DeferredBlock<Block> COBBLESTONE_BRICK_STAIRS = registerBlock("cobblestone_brick_stairs",
            () -> new StairBlock(() -> COBBLESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(NMLBlocks.COBBLESTONE_BRICKS.get())));
    public static final DeferredBlock<Block> COBBLESTONE_BRICK_SLAB = registerBlock("cobblestone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.COBBLESTONE_BRICKS.get())));
    public static final DeferredBlock<Block> COBBLESTONE_BRICK_WALL = registerBlock("cobblestone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.COBBLESTONE_BRICKS.get())));

    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_BRICKS = registerBlock("mossy_cobblestone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));
    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_BRICK_STAIRS = registerBlock("mossy_cobblestone_brick_stairs",
            () -> new StairBlock(() -> MOSSY_COBBLESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(NMLBlocks.MOSSY_COBBLESTONE_BRICKS.get())));
    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_BRICK_SLAB = registerBlock("mossy_cobblestone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.MOSSY_COBBLESTONE_BRICKS.get())));
    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_BRICK_WALL = registerBlock("mossy_cobblestone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.MOSSY_COBBLESTONE_BRICKS.get())));


    //Trimmed Planks and Bookshelves
    public static final DeferredBlock<Block> TRIMMED_OAK_PLANKS = registerBlock("trimmed_oak_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> SPRUCE_BOOKSHELF = registerBlock("spruce_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_SPRUCE_PLANKS = registerBlock("trimmed_spruce_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
    public static final DeferredBlock<Block> BIRCH_BOOKSHELF = registerBlock("birch_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_BIRCH_PLANKS = registerBlock("trimmed_birch_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)));
    public static final DeferredBlock<Block> JUNGLE_BOOKSHELF = registerBlock("jungle_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_JUNGLE_PLANKS = registerBlock("trimmed_jungle_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)));
    public static final DeferredBlock<Block> DARK_OAK_BOOKSHELF = registerBlock("dark_oak_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_DARK_OAK_PLANKS = registerBlock("trimmed_dark_oak_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)));
    public static final DeferredBlock<Block> ACACIA_BOOKSHELF = registerBlock("acacia_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_ACACIA_PLANKS = registerBlock("trimmed_acacia_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
    public static final DeferredBlock<Block> MANGROVE_BOOKSHELF = registerBlock("mangrove_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_MANGROVE_PLANKS = registerBlock("trimmed_mangrove_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)));
    public static final DeferredBlock<Block> CHERRY_BOOKSHELF = registerBlock("cherry_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_CHERRY_PLANKS = registerBlock("trimmed_cherry_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)));
    public static final DeferredBlock<Block> WARPED_BOOKSHELF = registerBlock("warped_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_WARPED_PLANKS = registerBlock("trimmed_warped_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)));
    public static final DeferredBlock<Block> CRIMSON_BOOKSHELF = registerBlock("crimson_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_CRIMSON_PLANKS = registerBlock("trimmed_crimson_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)));
    public static final DeferredBlock<Block> BAMBOO_BOOKSHELF = registerBlock("bamboo_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_BAMBOO_PLANKS = registerBlock("trimmed_bamboo_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)));

    //Pine
    public static final DeferredBlock<Block> PINE_PLANKS = registerBlock("pine_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> PINE_STAIRS = registerBlock("pine_stairs",
            () -> new StairBlock(() -> PINE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(NMLBlocks.PINE_PLANKS.get())));
    public static final DeferredBlock<Block> PINE_SLAB = registerBlock("pine_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.PINE_PLANKS.get())));
    public static final DeferredBlock<Block> PINE_LOG = registerBlock("pine_log",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> PINE_WOOD = registerBlock("pine_wood",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_PINE_LOG = registerBlock("stripped_pine_log",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_PINE_WOOD = registerBlock("stripped_pine_wood",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<Block> PINE_FENCE = registerBlock("pine_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            () -> new FenceGateBlock(NMLWoodTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<Block> PINE_LEAVES = registerBlock("pine_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating(((s, g, p) -> false))));
    // TODO: fix sapling
//    public static final DeferredBlock<Block> PINE_SAPLING = registerBlock("pine_sapling",
//            () -> new SaplingBlock((new PineTreeGrower()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
//    public static final DeferredBlock<Block> POTTED_PINE_SAPLING = BLOCKS.register("potted_pine_sapling",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.PINE_SAPLING,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));
    public static final DeferredBlock<Block> PINE_BUTTON = registerBlock("pine_button",
            () -> new ButtonBlock(NMLBlockSetTypes.PINE, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            () -> new PressurePlateBlock(NMLBlockSetTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> PINE_DOOR = registerBlock("pine_door",
            () -> new DoorBlock(NMLBlockSetTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            () -> new TrapDoorBlock(NMLBlockSetTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<Block> PINE_SIGN = BLOCKS.register("pine_sign",
            () -> new StandingSignBlock(NMLWoodTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> PINE_WALL_SIGN = BLOCKS.register("pine_wall_sign",
            () -> new WallSignBlock(NMLWoodTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> PINE_HANGING_SIGN = BLOCKS.register("pine_hanging_sign",
            () -> new HangingSignBlock(NMLWoodTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> PINE_HANGING_WALL_SIGN = BLOCKS.register("pine_wall_hanging_sign",
            () -> new WallHangingSignBlock(NMLWoodTypes.PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    public static final DeferredBlock<Block> PINE_BOOKSHELF = registerBlock("pine_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_PINE_PLANKS = registerBlock("trimmed_pine_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.PINE_PLANKS.get())));
    public static final DeferredBlock<Block> PINE_CABINET = registerIntegrationBlock("pine_cabinet",
            !ModList.get().isLoaded("farmersdelight") ?
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)) : FDIntegration.cabinetBlock(), "farmersdelight", true);

    //Maple
    public static final DeferredBlock<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_STAIRS = registerBlock("maple_stairs",
            () -> new StairBlock(() -> MAPLE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(NMLBlocks.MAPLE_PLANKS.get())));
    public static final DeferredBlock<Block> MAPLE_SLAB = registerBlock("maple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.MAPLE_PLANKS.get())));
    public static final DeferredBlock<Block> MAPLE_LOG = registerBlock("maple_log",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> MAPLE_WOOD = registerBlock("maple_wood",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<Block> MAPLE_FENCE = registerBlock("maple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<Block> MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            () -> new FenceGateBlock(NMLWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating(((s, g, p) -> false))));
    // TODO: fix sapling
//    public static final DeferredBlock<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
//            () -> new SaplingBlock((new MapleTreeGrower()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
//    public static final DeferredBlock<Block> POTTED_MAPLE_SAPLING = BLOCKS.register("potted_maple_sapling",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.MAPLE_SAPLING,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));
    public static final DeferredBlock<Block> RED_MAPLE_LEAVES = registerBlock("red_maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating(((s, g, p) -> false))));
    // TODO: fix sapling
//    public static final DeferredBlock<Block> RED_MAPLE_SAPLING = registerBlock("red_maple_sapling",
//            () -> new SaplingBlock((new RedMapleTreeGrower()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
//    public static final DeferredBlock<Block> POTTED_RED_MAPLE_SAPLING = BLOCKS.register("potted_red_maple_sapling",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.RED_MAPLE_SAPLING,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));
    public static final DeferredBlock<Block> MAPLE_BUTTON = registerBlock("maple_button",
            () -> new ButtonBlock(NMLBlockSetTypes.MAPLE, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<Block> MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            () -> new PressurePlateBlock(NMLBlockSetTypes.MAPLE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> MAPLE_DOOR = registerBlock("maple_door",
            () -> new DoorBlock(NMLBlockSetTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<Block> MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            () -> new TrapDoorBlock(NMLBlockSetTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<Block> MAPLE_SIGN = BLOCKS.register("maple_sign",
            () -> new StandingSignBlock(NMLWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> MAPLE_WALL_SIGN = BLOCKS.register("maple_wall_sign",
            () -> new WallSignBlock(NMLWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> MAPLE_HANGING_SIGN = BLOCKS.register("maple_hanging_sign",
            () -> new HangingSignBlock(NMLWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> MAPLE_HANGING_WALL_SIGN = BLOCKS.register("maple_wall_hanging_sign",
            () -> new WallHangingSignBlock(NMLWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    public static final DeferredBlock<Block> MAPLE_BOOKSHELF = registerBlock("maple_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_MAPLE_PLANKS = registerBlock("trimmed_maple_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.MAPLE_PLANKS.get())));
    public static final DeferredBlock<Block> MAPLE_CABINET = registerIntegrationBlock("maple_cabinet",
            ModList.get().isLoaded("farmersdelight") == false ?
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)) : FDIntegration.cabinetBlock(), "farmersdelight", true);

    //Walnut
    public static final DeferredBlock<Block> WALNUT_PLANKS = registerBlock("walnut_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> WALNUT_STAIRS = registerBlock("walnut_stairs",
            () -> new StairBlock(() -> WALNUT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(NMLBlocks.WALNUT_PLANKS.get())));
    public static final DeferredBlock<Block> WALNUT_SLAB = registerBlock("walnut_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.WALNUT_PLANKS.get())));
    public static final DeferredBlock<Block> WALNUT_LOG = registerBlock("walnut_log",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> WALNUT_WOOD = registerBlock("walnut_wood",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_WALNUT_LOG = registerBlock("stripped_walnut_log",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_WALNUT_WOOD = registerBlock("stripped_walnut_wood",
            () -> new LogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<Block> WALNUT_FENCE = registerBlock("walnut_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<Block> WALNUT_FENCE_GATE = registerBlock("walnut_fence_gate",
            () -> new FenceGateBlock(NMLWoodTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<Block> WALNUT_LEAVES = registerBlock("walnut_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating(((s, g, p) -> false))));
    // TODO: fix sapling
//    public static final DeferredBlock<Block> WALNUT_SAPLING = registerBlock("walnut_sapling",
//            () -> new SaplingBlock((new WalnutTreeGrower()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
//    public static final DeferredBlock<Block> POTTED_WALNUT_SAPLING = BLOCKS.register("potted_walnut_sapling",
//            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.WALNUT_SAPLING,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));
    public static final DeferredBlock<Block> WALNUT_BUTTON = registerBlock("walnut_button",
            () -> new ButtonBlock(NMLBlockSetTypes.WALNUT, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<Block> WALNUT_PRESSURE_PLATE = registerBlock("walnut_pressure_plate",
            () -> new PressurePlateBlock(NMLBlockSetTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<Block> WALNUT_DOOR = registerBlock("walnut_door",
            () -> new DoorBlock(NMLBlockSetTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<Block> WALNUT_TRAPDOOR = registerBlock("walnut_trapdoor",
            () -> new TrapDoorBlock(NMLBlockSetTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredHolder<Block, StandingSignBlock> WALNUT_SIGN = BLOCKS.register("walnut_sign",
            () -> new StandingSignBlock(NMLWoodTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredHolder<Block, WallSignBlock> WALNUT_WALL_SIGN = BLOCKS.register("walnut_wall_sign",
            () -> new WallSignBlock(NMLWoodTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredHolder<Block, HangingSignBlock> WALNUT_HANGING_SIGN = BLOCKS.register("walnut_hanging_sign",
            () -> new HangingSignBlock(NMLWoodTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredHolder<Block, WallHangingSignBlock> WALNUT_HANGING_WALL_SIGN = BLOCKS.register("walnut_wall_hanging_sign",
            () -> new WallHangingSignBlock(NMLWoodTypes.WALNUT, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    public static final DeferredBlock<Block> WALNUT_BOOKSHELF = registerBlock("walnut_bookshelf",
            () -> new BookshelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));
    public static final DeferredBlock<Block> TRIMMED_WALNUT_PLANKS = registerBlock("trimmed_walnut_planks",
            () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.WALNUT_PLANKS.get())));
    public static final DeferredBlock<Block> WALNUT_CABINET = registerIntegrationBlock("walnut_cabinet",
            !ModList.get().isLoaded("farmersdelight") ?
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)) : FDIntegration.cabinetBlock(), "farmersdelight", true);

    //Tapping
    public static final DeferredBlock<Block> TAP = registerBlock("tap",
            () -> new TapBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noOcclusion().strength(2.0F).randomTicks().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> RESIN_CAULDRON = BLOCKS.register("resin_cauldron",
            () -> new ResinCauldronBlock(null, null, BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));
    public static final DeferredBlock<Block> HONEY_CAULDRON = BLOCKS.register("honey_cauldron",
            () -> new HoneyCauldronBlock(null, null, BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));

    public static final DeferredBlock<Block> SPIKE_TRAP = registerBlock("spike_trap",
            () -> new SpikeTrapBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F, 6.0F).noOcclusion()));


    //Storage
    public static final DeferredBlock<Block> COD_BARREL = registerBlock("cod_barrel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));
    public static final DeferredBlock<Block> SALMON_BARREL = registerBlock("salmon_barrel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.COD_BARREL.get())));
    public static final DeferredBlock<Block> PUFFERFISH_BARREL = registerBlock("pufferfish_barrel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.COD_BARREL.get())));
    public static final DeferredBlock<Block> TROPICAL_FISH_BARREL = registerBlock("tropical_fish_barrel",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.COD_BARREL.get())));

    //Mushrooms
    public static final DeferredBlock<Block> FIELD_MUSHROOM = registerBlock("field_mushroom",
            () -> new SurfaceMushroomBlock(HugeMushrooms.HUGE_FIELD_MUSHROOM, (BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM).mapColor(MapColor.TERRACOTTA_WHITE))));
    public static final DeferredBlock<Block> FIELD_MUSHROOM_COLONY = registerIntegrationBlock("field_mushroom_colony",
            !ModList.get().isLoaded("farmersdelight") ? () -> new Block(BlockBehaviour.Properties.ofFullCopy(NMLBlocks.FIELD_MUSHROOM.get()))
                    : FDIntegration.mushroomColony(), "farmersdelight", false);

    public static final DeferredBlock<Block> FIELD_MUSHROOM_BLOCK = registerBlock("field_mushroom_block",
            () -> new HugeMushroomBlock((BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK))));
    public static final DeferredHolder<Block, FlowerPotBlock> POTTED_FIELD_MUSHROOM = BLOCKS.register("potted_field_mushroom",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NMLBlocks.FIELD_MUSHROOM,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_RED_MUSHROOM).noOcclusion()));


    private static <T extends Block> DeferredBlock<Block> registerBlock(String name, Supplier<? extends Block> block) {
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        CREATIVE_TAB_ITEMS.add(registerBlockItem(name, toReturn));
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<Block> registerIntegrationBlock(String name, Supplier<? extends Block> block, String modId, Boolean blockItem) {
        DeferredBlock<Block> blocks = BLOCKS.register(name, block);
        if (ModList.get().isLoaded(modId) && blockItem) {
            CREATIVE_TAB_ITEMS.add(registerIntegrationBlockItem(name, blocks));
        }
        return blocks;
    }

    private static <T extends Block> DeferredHolder<Item, BlockItem> registerBlockItem(String name, Supplier<? extends Block> block) {
        return NMLItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredHolder<Item, BlockItem> registerIntegrationBlockItem(String name, Supplier<? extends Block> block) {
        return NMLItems.ITEMS.register(name, !ModList.get().isLoaded("farmersdelight") ? () -> new MissingIntegrationBlock(block.get(), new Item.Properties()) : () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
