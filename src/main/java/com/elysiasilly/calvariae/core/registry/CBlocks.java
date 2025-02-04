package com.elysiasilly.calvariae.core.registry;

import com.elysiasilly.calvariae.common.block.base.TallGrassBlock;
import com.elysiasilly.calvariae.common.block.base.*;
import com.elysiasilly.calvariae.common.block.custom.WhiteFlowerBlock;
import com.elysiasilly.calvariae.common.block.custom.detoriatedworkbench.DeterioratedCraftingTableBlock;
import com.elysiasilly.calvariae.common.block.custom.lanternkelp.LanternKelpBlock;
import com.elysiasilly.calvariae.common.block.custom.lanternkelp.LanternKelpPlantBlock;
import com.elysiasilly.calvariae.common.block.custom.oxylotus.AirBudBlock;
import com.elysiasilly.calvariae.common.block.custom.sanctuary.GreenMossBlock;
import com.elysiasilly.calvariae.common.block.custom.sanctuary.SanctuaryGuardianBlock;
import com.elysiasilly.calvariae.common.block.custom.sanctuary.TallSanctuaryGrassBlock;
import com.elysiasilly.calvariae.common.block.custom.sanctuary.WailingStoneBlock;
import com.elysiasilly.calvariae.common.block.custom.shaderblock.ShaderBlock;
import com.elysiasilly.calvariae.common.block.custom.tyrian.TyrianWartBlock;
import com.elysiasilly.calvariae.common.block.neo.base.*;
import com.elysiasilly.calvariae.common.block.neo.special.*;
import com.elysiasilly.calvariae.core.Calvariae;
import com.elysiasilly.calvariae.core.registry.keys.ModFeatureKeys;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Calvariae.MODID);
    public static final DeferredRegister.Items BLOCKITEMS = DeferredRegister.createItems(Calvariae.MODID);

    /// ADELINE

    //public static class adeline {

        public static final BlockBehaviour.Properties NORMAL = BlockBehaviour.Properties.of();

        public static final DeferredBlock<Block> ADEL_BERRY_BUNCH = blockItemS("adel_berry_bunch", () -> new Block(NORMAL));

        public static final DeferredBlock<Block> ADELINE_BLOCK = blockItemS("adeline_block", () -> new Block(NORMAL));

        public static final DeferredBlock<Block> ENGRAVED_ADELINE = blockItemS("engraved_adeline", () -> new Block(NORMAL));

        public static final DeferredBlock<Block> EMBEDDED_ADELINE = blockItemS("embedded_adeline", () -> new RotatedPillarBlock(NORMAL));

        public static final DeferredBlock<Block> RIMMED_ADELINE = blockItemS("rimmed_adeline", () -> new SandwichBlock(NORMAL));
    //}

    /// SCULK

    //public static class sculk {

        public static final DeferredBlock<Block> SCULK_FUZZ =
            blockItemS("sculk_fuzz", () -> new SculkFolliageBlock(properties(Blocks.SCULK)));

        public static final DeferredBlock<Block> MOB_HUSK =
                blockItemS("mob_husk", () -> new MobHuskBlock(properties(Blocks.SCULK)));
    //}

    /// DEEPSLATE

    //public static class deepslate {

        //public static final BlockBehaviour.Properties NORMAL = property(Blocks.POLISHED_DEEPSLATE);

        public static final DeferredBlock<Block> DEEPSLATE_PILLAR =
            blockItemS("deepslate_pillar", () -> new FancyPillarBlock(NORMAL));
    //}

    /// SWEETROOT

    //public static class sweetroot {

        //public static final BlockBehaviour.Properties NORMAL = BlockBehaviour.Properties.of();
        public static final BlockBehaviour.Properties CARAMELISED = BlockBehaviour.Properties.of();
        public static final BlockBehaviour.Properties PLANKS = BlockBehaviour.Properties.of();

        /// LOG

        public static final DeferredBlock<Block> STRIPPED_SWEETROOT
                = blockItemS("stripped_sweetroot", () -> new SweetrootBlock(NORMAL));

        public static final DeferredBlock<Block> CARAMELISED_SWEETROOT
                = blockItemS("caramelised_sweetroot", () -> new SweetrootBlock(CARAMELISED));

        public static final DeferredBlock<Block> SWEETROOT
                = blockItemS("sweetroot", () -> new SweetrootBlock(NORMAL));

        /// BUNDLE

        public static final DeferredBlock<Block> STRIPPED_SWEETROOT_BUNDLE
                = blockItemS("stripped_sweetroot_bundle", () -> new LogBlock(null, true, NORMAL));

        public static final DeferredBlock<Block> CARAMELISED_SWEETWOOD_BUNDLE
                = blockItemS("caramelised_sweetroot_bundle", () -> new RotatedPillarBlock(CARAMELISED));

        public static final DeferredBlock<Block> SWEETROOT_BUNDLE
                = blockItemS("sweetroot_bundle", () -> new SweetrootBundleBlock(STRIPPED_SWEETROOT_BUNDLE.get(), CARAMELISED_SWEETWOOD_BUNDLE.get()));

        /// WOOD

        public static final DeferredBlock<Block> STRIPPED_SWEETWOOD
                = blockItemS("stripped_sweetwood", () -> new LogBlock(null, true, NORMAL));

        public static final DeferredBlock<Block> CARAMELISED_SWEETWOOD
                = blockItemS("caramelised_sweetwood", () -> new RotatedPillarBlock(CARAMELISED));

        public static final DeferredBlock<Block> SWEETWOOD_WOOD
                = blockItemS("sweetwood", () -> new SweetrootBundleBlock(STRIPPED_SWEETWOOD.get(), CARAMELISED_SWEETWOOD.get()));

        /// PLANKS

        public static final DeferredBlock<Block> SWEETROOT_PLANKS
                = blockItemS("sweetroot_planks", () -> new FlammableBlock(PLANKS));

        public static final DeferredBlock<Block> SWEETROOT_STAIRS
                = blockItemS("sweetroot_stairs", () -> new FlammableStairsBlock(PLANKS, SWEETROOT_PLANKS.get()));

        public static final DeferredBlock<Block> SWEETROOT_SLAB
                = blockItemS("sweetroot_slab", () -> new FlammableSlabBlock(PLANKS));

        public static final DeferredBlock<Block> SWEETROOT_DOOR
                = blockItemS("sweetroot_door", () -> new DoorBlock(CBlockSets.SWEETWOOD, PLANKS));

        public static final DeferredBlock<Block> SWEETROOT_TRAPDOOR
                = blockItemS("sweetroot_trapdoor", () -> new TrapDoorBlock(CBlockSets.SWEETWOOD, PLANKS));

        public static final DeferredBlock<Block> SWEETROOT_FENCE
                = blockItemS("sweetroot_fence", () -> new FlammableFenceBlock(PLANKS));

        public static final DeferredBlock<Block> SWEETROOT_FENCE_GATE
                = blockItemS("sweetroot_fence_gate", () -> new FlammableFenceGateBlock(PLANKS, CWoodSets.SWEETWOOD));

        public static final DeferredBlock<Block> SWEETROOT_PRESSURE_PLATE
                = blockItemS("sweetroot_pressure_plate", () -> new PressurePlateBlock(CBlockSets.SWEETWOOD, PLANKS));

        public static final DeferredBlock<Block> SWEETROOT_BUTTON
                = blockItemS("sweetroot_button", () -> new ButtonBlock(CBlockSets.SWEETWOOD, 30, PLANKS));

        /// ROOT

        //public static final DeferredBlock<Block> TALL_SWEETROOT
        //        = blockItem("tall_sweetroot", TallSweetrootBlock::new);

        //public static final DeferredBlock<Block> SHORT_SWEETROOT
        //        = blockItem("short_sweetroot", () -> new ShortSweetrootBlock(TALL_SWEETROOT.get()));
    //}

    /// AZALEA

    //public static class azalea {

    //}

    // VELVET MOSS

    public static final Supplier<? extends Block> VELVET_MOSS_BLOCK
            = registerBlockItem("velvet_moss_block", () -> new BaseMossBlock(getProperties(Blocks.MOSS_BLOCK), ModFeatureKeys.VELVET_MOSS_PATCH_BONEMEAL));

    public static final Supplier<? extends Block> VELVET_MOSS_CARPET
            = registerBlockItem("velvet_moss_carpet", () -> new CarpetBlock(getProperties(Blocks.MOSS_CARPET)));

    public static final Supplier<? extends Block> TALL_VELVET_MOSSPROUT
            = registerBlockItem("tall_velvet_mosssprout", TallGrassBlock::new);

    public static final Supplier<? extends Block> SHORT_VELVET_MOSSSPROUT
            = registerBlockItem("short_velvet_mosssprout", () -> new ShortGrassBlock(TALL_VELVET_MOSSPROUT.get()));

    // SANCTUARY MOSS

    public static final Supplier<? extends Block> SANCTUARY_MOSS_BLOCK
            = registerBlockItem("sanctuary_moss_block", () -> new BaseMossBlock(getProperties(Blocks.MOSS_BLOCK), ModFeatureKeys.SANCTUARY_MOSS_PATCH_BONEMEAL));

    public static final Supplier<? extends Block> SANCTUARY_MOSS_CARPET
            = registerBlockItem("sanctuary_moss_carpet", () -> new CarpetBlock(getProperties(Blocks.MOSS_CARPET)));

    public static final Supplier<? extends Block> TALL_SANCTUARY_MOSSPROUT
            = registerBlockItem("tall_sanctuary_mosssprout", TallSanctuaryGrassBlock::new);

    public static final Supplier<? extends Block> SHORT_SANCTUARY_MOSSSPROUT
            = registerBlockItem("short_sanctuary_mosssprout", () -> new ShortGrassBlock(TALL_SANCTUARY_MOSSPROUT.get()));

    public static final Supplier<? extends Block> LARGE_SANCTUARY_BUSH
            = registerBlockItem("large_sanctuary_bush", TallGrassBlock::new);

    public static final Supplier<? extends Block> SMALL_SANCTUARU_BUSH
            = registerBlockItem("small_sanctuary_bush", () -> new ShortGrassBlock(LARGE_SANCTUARY_BUSH.get()));

    /*
    public static final Supplier<? extends Block> SANCTUARY_VINES_HEAD
            = registerBlockItem("sanctuary_vines_head", FloweringVinesBlock::new);

    public static final Supplier<Block> SANCTUARY_VINES_BODY
            = BLOCKS.register("sanctuary_vines_body", FloweringVinesPlantBlock::new);

     */

    public static final Supplier<? extends Block> GREEN_MOSS
            = registerBlockItem("green_moss", () -> new GreenMossBlock(getProperties(Blocks.PINK_PETALS)));

    public static final Supplier<? extends Block> GREEN_MOSS_SHELF
            = registerBlockItem("green_moss_shelf", BaseDirectionalBlock::new);

    public static final Supplier<? extends Block> TYRIAN_STEM
            = registerBlockItem("tyrian_stem", () -> new RotatedPillarBlock(getProperties(Blocks.CRIMSON_STEM)));

    public static final Supplier<? extends Block> TYRIAN_WART_BLOCK
            = registerBlockItem("tyrian_wart_block", () -> new Block(getProperties(Blocks.NETHER_WART_BLOCK)));

    public static final Supplier<? extends Block> TYRIAN_WART
            = registerBlockItem("tyrian_wart", TyrianWartBlock::new);

    public static final Supplier<? extends Block> WHITE_FLOWER
            = registerBlockItem("white_flower", () -> new WhiteFlowerBlock(getProperties(Blocks.ROSE_BUSH)));



    // AZALEA WOOD


    public static final Supplier<? extends Block> STRIPPED_AZALEA_LOG
            = registerBlockItem("stripped_azalea_log", () -> new LogBlock(null, true, properties(Blocks.OAK_LOG)));

    public static final Supplier<? extends Block> STRIPPED_AZALEA_WOOD
            = registerBlockItem("stripped_azalea_wood", () -> new LogBlock(null, true, properties(Blocks.OAK_LOG)));

    public static final Supplier<? extends Block> AZALEA_LOG
            = registerBlockItem("azalea_log", () -> new LogBlock(STRIPPED_AZALEA_LOG.get(), true, properties(Blocks.OAK_LOG)));

    public static final Supplier<? extends Block> AZALEA_WOOD
            = registerBlockItem("azalea_wood", () -> new LogBlock(STRIPPED_AZALEA_WOOD.get(), true, properties(Blocks.OAK_LOG)));

    public static final Supplier<? extends Block> AZALEA_PLANKS
            = registerBlockItem("azalea_planks", () -> new FlammableBlock(properties(Blocks.ACACIA_PLANKS)));

    public static final Supplier<? extends Block> AZALEA_STAIRS
            = registerBlockItem("azalea_stairs", () -> new FlammableStairsBlock(properties(Blocks.ACACIA_STAIRS), AZALEA_PLANKS.get()));

    public static final Supplier<? extends Block> AZALEA_SLAB
            = registerBlockItem("azalea_slab", () -> new FlammableSlabBlock(properties(Blocks.ACACIA_SLAB)));

    public static final Supplier<? extends Block> AZALEA_DOOR
            = registerBlockItem("azalea_door", () -> new DoorBlock(CBlockSets.AZALEA, getProperties(Blocks.OAK_DOOR)));

    public static final Supplier<? extends Block> AZALEA_TRAPDOOR
            = registerBlockItem("azalea_trapdoor", () -> new TrapDoorBlock(CBlockSets.AZALEA, getProperties(Blocks.OAK_TRAPDOOR)));

    public static final Supplier<? extends Block> AZALEA_FENCE
            = registerBlockItem("azalea_fence", () -> new FlammableFenceBlock(properties(Blocks.ACACIA_FENCE)));

    public static final Supplier<? extends Block> AZALEA_FENCE_GATE
            = registerBlockItem("azalea_fence_gate", () -> new FlammableFenceGateBlock(properties(Blocks.OAK_FENCE_GATE), CWoodSets.AZALEA));

    public static final Supplier<? extends Block> AZALEA_PRESSURE_PLATE
            = registerBlockItem("azalea_pressure_plate", () -> new PressurePlateBlock(CBlockSets.AZALEA, getProperties(Blocks.OAK_PRESSURE_PLATE)));

    public static final Supplier<? extends Block> AZALEA_BUTTON
            = registerBlockItem("azalea_button", () -> new ButtonBlock(CBlockSets.AZALEA, 30, getProperties(Blocks.OAK_BUTTON)));

    // SWEETWOOD



    // DEEP DARK

    public static final Supplier<? extends Block> DEEPSLATE_LATTICE
            = registerBlockItem("deepslate_lattice", () -> new Block(getProperties(Blocks.POLISHED_DEEPSLATE)));

    public static final Supplier<? extends Block> STONE_CHEST
            = registerBlockItem("stone_chest", StoneChestBlock::new);

    /*
    public static final Supplier<? extends Block> BRUV
            = registerBlockItem("bruv", () -> new BurnTransformBlock(getProperties(Blocks.POLISHED_DEEPSLATE)));

     */

    // LANTERN KELP

    public static final Supplier<? extends Block> LANTERN_KELP_BULB
            = registerBlockItem("lantern_bulb", LanternBulbBlock::new);

    public static final Supplier<? extends Block> ROTTING_LANTERN_KELP_BULB
            = registerBlockItem("rotting_lantern_bulb", RottingLanternBulbBlock::new);

    public static final Supplier<? extends Block> LANTERN_KELP_HEAD
            = registerBlockItem("lantern_kelp_head", LanternKelpBlock::new);

    public static final Supplier<? extends Block> LANTERN_KELP_BODY
            = BLOCKS.register("lantern_kelp_body", LanternKelpPlantBlock::new);

    public static final Supplier<? extends Block> LANTERN_PASTE
            = BLOCKS.register("lantern_paste", LanternPasteBlock::new);

    // PLANTS

    public static final Supplier<? extends Block> FLOWERING_MOSSPROUT
            = registerBlockItem("flowering_mosssprout", () -> new TallFlowerBlock(getProperties(Blocks.ROSE_BUSH)));

    public static final Supplier<? extends Block> TALL_MOSSSPROUT
            = registerBlockItem("tall_mosssprout", TallGrassBlock::new);

    public static final Supplier<? extends Block> SHORT_MOSSPROUT
            = registerBlockItem("short_mosssprout", () -> new ShortGrassBlock(TALL_MOSSSPROUT.get()));


    public static final Supplier<? extends Block> AZALEA_PETALS
            = registerBlockItem("azalea_petals", () -> new BaseBonemealableMultiFaceBlock(getProperties(Blocks.PINK_PETALS)));

    public static final Supplier<? extends Block> MOSS_CARPET
            = registerBlockItem("moss_carpet", () -> new BaseLayerBlock(getProperties(Blocks.MOSS_BLOCK)));

    // FOSSIL

    public static final Supplier<? extends Block> NATURAL_FOSSILITE
            = registerBlockItem("natural_fossilite", () -> new RotatedPillarBlock(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> FOSSILITE
            = registerBlockItem("fossilite", () -> new RotatedPillarBlock(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> POLISHED_FOSSILITE
            = registerBlockItem("polished_fossil_block", () -> new Block(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> POLISHED_FOSSILITE_STAIRS
            = registerBlockItem("polished_fossil_block_stairs", () -> new StairBlock(POLISHED_FOSSILITE.get().defaultBlockState(), getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> POLISHED_FOSSILITE_SLAB
            = registerBlockItem("polished_fossil_block_slab", () -> new SlabBlock(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> POLISHED_FOSSILITE_WALL
            = registerBlockItem("polished_fossil_block_wall", () -> new WallBlock(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> FOSSILITE_BRICKS
            = registerBlockItem("fossil_bricks", () -> new Block(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> CRACKED_FOSSILITE_BRICKS
            = registerBlockItem("cracked_fossil_bricks", () -> new Block(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> FOSSILITE_BRICK_STAIRS
            = registerBlockItem("fossil_brick_stairs", () -> new StairBlock(FOSSILITE_BRICKS.get().defaultBlockState(), getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> FOSSILITE_BRICK_SLAB
            = registerBlockItem("fossil_brick_slab", () -> new SlabBlock(getProperties(Blocks.BONE_BLOCK)));

    public static final Supplier<? extends Block> FOSSILITE_BRICK_WALL
            = registerBlockItem("fossil_brick_wall", () -> new WallBlock(getProperties(Blocks.BONE_BLOCK)));

    // STONE
    public static final Supplier<? extends Block> STONE_WALL
            = registerBlockItem("stone_wall", () -> new WallBlock(getProperties(Blocks.STONE)));

    public static final Supplier<? extends Block> MOSSY_STONE
            = registerBlockItem("mossy_stone", () -> new Block(getProperties(Blocks.STONE)));

    public static final Supplier<? extends Block> MOSSY_STONE_STAIRS
            = registerBlockItem("mossy_stone_stairs", () -> new StairBlock(MOSSY_STONE.get().defaultBlockState(), getProperties(MOSSY_STONE.get())));

    public static final Supplier<? extends Block> MOSSY_STONE_SLAB
            = registerBlockItem("mossy_stone_slab", () -> new SlabBlock(getProperties(MOSSY_STONE.get())));

    public static final Supplier<? extends Block> MOSSY_STONE_WALL
            = registerBlockItem("mossy_stone_wall", () -> new WallBlock(getProperties(MOSSY_STONE.get())));

    public static final Supplier<? extends Block> CRACKED_STONE_BRICK_STAIRS
            = registerBlockItem("cracked_stone_brick_stairs", () -> new StairBlock(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), getProperties(Blocks.CRACKED_STONE_BRICKS)));

    public static final Supplier<? extends Block> CRACKED_STONE_BRICK_SLAB
            = registerBlockItem("cracked_stone_brick_slab", () -> new SlabBlock(getProperties(Blocks.CRACKED_STONE_BRICKS)));

    public static final Supplier<? extends Block> CRACKED_STONE_BRICK_WALL
            = registerBlockItem("cracked_stone_brick_wall", () -> new WallBlock(getProperties(Blocks.CRACKED_STONE_BRICKS)));

    public static final Supplier<? extends Block> POLISHED_STONE
            = registerBlockItem("polished_stone", () -> new Block(getProperties(Blocks.STONE_BRICKS)));

    public static final Supplier<? extends Block> POLISHED_STONE_STAIRS
            = registerBlockItem("polished_stone_stairs", () -> new StairBlock(POLISHED_STONE.get().defaultBlockState(), getProperties(POLISHED_STONE.get())));

    public static final Supplier<? extends Block> POLISHED_STONE_SLAB
            = registerBlockItem("polished_stone_slab", () -> new SlabBlock(getProperties(POLISHED_STONE.get())));

    public static final Supplier<? extends Block> POLISHED_STONE_WALL
            = registerBlockItem("polished_stone_wall", () -> new WallBlock(getProperties(POLISHED_STONE.get())));

    public static final Supplier<? extends Block> MOSSY_POLISHED_STONE
            = registerBlockItem("mossy_polished_stone", () -> new Block(getProperties(Blocks.MOSSY_STONE_BRICKS)));

    public static final Supplier<? extends Block> MOSSY_POLISHED_STONE_STAIRS
            = registerBlockItem("mossy_polished_stone_stairs", () -> new StairBlock(MOSSY_POLISHED_STONE.get().defaultBlockState(), getProperties(MOSSY_POLISHED_STONE.get())));

    public static final Supplier<? extends Block> MOSSY_POLISHED_STONE_SLAB
            = registerBlockItem("mossy_polished_stone_slab", () -> new SlabBlock(getProperties(MOSSY_POLISHED_STONE.get())));

    public static final Supplier<? extends Block> MOSSY_POLISHED_STONE_WALL
            = registerBlockItem("mossy_polished_stone_wall", () -> new WallBlock(getProperties(MOSSY_POLISHED_STONE.get())));

    public static final Supplier<? extends Block> CRACKED_POLISHED_STONE
            = registerBlockItem("cracked_polished_stone", () -> new Block(getProperties(Blocks.CRACKED_STONE_BRICKS)));

    public static final Supplier<? extends Block> CRACKED_POLISHED_STONE_STAIRS
            = registerBlockItem("cracked_polished_stone_stairs", () -> new StairBlock(CRACKED_POLISHED_STONE.get().defaultBlockState(), getProperties(CRACKED_POLISHED_STONE.get())));

    public static final Supplier<? extends Block> CRACKED_POLISHED_STONE_SLAB
            = registerBlockItem("cracked_polished_stone_slab", () -> new SlabBlock(getProperties(CRACKED_POLISHED_STONE.get())));

    public static final Supplier<? extends Block> CRACKED_POLISHED_STONE_WALL
            = registerBlockItem("cracked_polished_stone_wall", () -> new WallBlock(getProperties(CRACKED_POLISHED_STONE.get())));

    public static final Supplier<? extends Block> STONE_TILES
            = registerBlockItem("stone_tiles", () -> new Block(getProperties(Blocks.STONE_BRICKS)));

    public static final Supplier<? extends Block> STONE_TILE_STAIRS
            = registerBlockItem("stone_tile_stairs", () -> new StairBlock(STONE_TILES.get().defaultBlockState(), getProperties(STONE_TILES.get())));

    public static final Supplier<? extends Block> STONE_TILE_SLAB
            = registerBlockItem("stone_tile_slab", () -> new SlabBlock(getProperties(STONE_TILES.get())));

    public static final Supplier<? extends Block> STONE_TILE_WALL
            = registerBlockItem("stone_tile_wall", () -> new WallBlock(getProperties(STONE_TILES.get())));

    public static final Supplier<? extends Block> MOSSY_STONE_TILES
            = registerBlockItem("mossy_stone_tiles", () -> new Block(getProperties(Blocks.MOSSY_STONE_BRICKS)));

    public static final Supplier<? extends Block> MOSSY_STONE_TILE_STAIRS
            = registerBlockItem("mossy_stone_tile_stairs", () -> new StairBlock(MOSSY_STONE_TILES.get().defaultBlockState(), getProperties(MOSSY_STONE_TILES.get())));

    public static final Supplier<? extends Block> MOSSY_STONE_TILE_SLAB
            = registerBlockItem("mossy_stone_tile_slab", () -> new SlabBlock(getProperties(MOSSY_STONE_TILES.get())));

    public static final Supplier<? extends Block> MOSSY_STONE_TILE_WALL
            = registerBlockItem("mossy_stone_tile_wall", () -> new WallBlock(getProperties(MOSSY_STONE_TILES.get())));

    public static final Supplier<? extends Block> CRACKED_STONE_TILES
            = registerBlockItem("cracked_stone_tiles", () -> new Block(getProperties(Blocks.CRACKED_STONE_BRICKS)));

    public static final Supplier<? extends Block> CRACKED_STONE_TILE_STAIRS
            = registerBlockItem("cracked_stone_tile_stairs", () -> new StairBlock(CRACKED_STONE_TILES.get().defaultBlockState(), getProperties(CRACKED_STONE_TILES.get())));

    public static final Supplier<? extends Block> CRACKED_STONE_TILE_SLAB
            = registerBlockItem("cracked_stone_tile_slab", () -> new SlabBlock(getProperties(CRACKED_STONE_TILES.get())));

    public static final Supplier<? extends Block> CRACKED_STONE_TILE_WALL
            = registerBlockItem("cracked_stone_tile_wall", () -> new WallBlock(getProperties(CRACKED_STONE_TILES.get())));

    public static final Supplier<? extends Block> SMOOTH_STONE_STAIRS
            = registerBlockItem("smooth_stone_stairs", () -> new StairBlock(Blocks.SMOOTH_STONE.defaultBlockState(), getProperties(Blocks.SMOOTH_STONE)));

    public static final Supplier<? extends Block> SMOOTH_STONE_WALL
            = registerBlockItem("smooth_stone_wall", () -> new WallBlock(getProperties(Blocks.SMOOTH_STONE)));

    public static final Supplier<? extends Block> STONE_LATTICE
            = registerBlockItem("stone_lattice", () -> new Block(getProperties(Blocks.STONE_BRICKS)));

    public static final Supplier<? extends Block> STONE_LATTICE_STAIRS
            = registerBlockItem("stone_lattice_stairs", () -> new StairBlock(STONE_LATTICE.get().defaultBlockState(), getProperties(STONE_LATTICE.get())));

    public static final Supplier<? extends Block> STONE_LATTICE_SLAB
            = registerBlockItem("stone_lattice_slab", () -> new SlabBlock(getProperties(STONE_LATTICE.get())));

    public static final Supplier<? extends Block> STONE_LATTICE_WALL
            = registerBlockItem("stone_lattice_wall", () -> new WallBlock(getProperties(STONE_LATTICE.get())));

    public static final Supplier<? extends Block> CHISELED_STONE
            = registerBlockItem("chiseled_stone", () -> new Block(getProperties(Blocks.CHISELED_STONE_BRICKS)));

    public static final Supplier<? extends Block> STONE_PILLAR
            = registerBlockItem("stone_pillar", () -> new RotatedPillarBlock(getProperties(Blocks.STONE_BRICKS)));

    //

    public static final Supplier<? extends Block> AIR_BUD = registerBlockItem("air_bud", AirBudBlock::new);
    public static final Supplier<? extends Block> WAILING_STONE = registerBlockItem("wailing_stone", WailingStoneBlock::new);
    public static final Supplier<? extends Block> SANCTUARY_GUARDIAN = registerBlockItem("sanctuary_guardian", SanctuaryGuardianBlock::new);
    public static final Supplier<? extends Block> DETERIORATED_CRAFTING_TABLE = registerBlockItem("deteriorated_crafting_table", DeterioratedCraftingTableBlock::new);


    public static final Supplier<? extends Block> CAVE_SOIL = registerBlockItem(
            "cave_soil", () -> new BaseCanSustainPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));

    public static final Supplier<? extends Block> BRAIDED_ROOTS = registerBlockItem(
            "braided_roots", () -> new FlammableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));

    public static final Supplier<? extends Block> VARNISHED_BRAIDED_ROOTS = registerBlockItem(
            "varnished_braided_roots", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));

    public static final Supplier<? extends Block> CAVE_ROOTS = registerBlockItem(
            "cave_roots", () -> new HangingPlantBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final Supplier<? extends Block> INFESTED_BRAIDED_ROOTS = registerBlockItem(
            "infested_braided_roots", () -> new InfestedRootBlock(properties(Blocks.ACACIA_PLANKS)));

    public static final Supplier<? extends Block> SHADER_BLOCK
            = registerBlockItem("shader_block", () -> new ShaderBlock(getProperties(Blocks.GLASS)));

    /// HELPERS

    // registers block and item if class extends block
    private static Supplier<? extends Block> registerBlockItem(String resourceLocation, Supplier<? extends Block> blockType) {
        var tempBlock = BLOCKS.register(resourceLocation, blockType);
        BLOCKITEMS.registerSimpleBlockItem(tempBlock);
        return tempBlock;
    }

    // returns block properties
    private static BlockBehaviour.Properties getProperties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block);
    }

    ///

    /// register block and item
    private static <T extends Block> DeferredBlock<T> blockItemS(String id, Supplier<T> block) {
        var temp = BLOCKS.register(id, block);
        BLOCKITEMS.registerSimpleBlockItem(temp);
        return temp;
    }

    /// overload for item properties
    private static <T extends Block> DeferredBlock<T> blockItem(String id, Supplier<T> block, Item.Properties properties) {
        var temp = BLOCKS.register(id, block);
        BLOCKITEMS.registerSimpleBlockItem(temp, properties);
        return temp;
    }

    /// register block
    private static <T extends Block> DeferredBlock<T> block(String id,  Supplier<T> block) {
        return BLOCKS.register(id, block);
    }

    /// returns block properties
    private static BlockBehaviour.Properties properties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block);
    }

    private static BlockBehaviour.Properties properties(DeferredBlock<Block> block) {
        return properties(block.get());
    }

    private static BlockState state(DeferredBlock<Block> block) {
        return block.get().defaultBlockState();
    }
}
