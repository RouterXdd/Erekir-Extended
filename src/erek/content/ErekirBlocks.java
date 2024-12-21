package erek.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.struct.Seq;
import erek.classes.blocks.defence.*;
import erek.classes.blocks.effects.Bomb;
import erek.classes.blocks.liquids.HeatPump;
import erek.classes.blocks.storage.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.world.blocks.campaign.LaunchPad;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.payloads.Constructor;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.meta.*;
import erek.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static erek.content.ErekirAttributes.*;
import static erek.content.ErekirUnitTypes.*;
import static mindustry.Vars.state;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static erek.content.ErekirItems.*;
import static mindustry.type.ItemStack.*;

public class ErekirBlocks {
    public static Block
            //environment
            metalVent, pipe, rubidiumWallOre, bperillWallOre, staticF,
            //crafting
            toxideKiln, replacer, neoplasmFurnace, bperillExtractor, hydrogenHeater, ozoneHeater, smallRedirector,
            //production
            plasmaDrill, largePlasmaDrill, siliconPiper,
            //defence
            strikeProjector, tungstenBarrier, rubidiumWall, rubidiumWallLarge, bperillWall, bperillWallLarge,
            //power
            rubidiumNode, carbideNode, cryliteReactor,
            //storage
            coreStation, coreSite, coreLaboratory,
            //effect
            rubRadar, berylliumProjector, rubidiumLaunchPad, bomb, bombConstructor,
            //liquid
            heatPump,
            //turrets
            ecription, blaster, crossbow, peweless, abyssHunter, conclusion, timeLauncher, endTime, bonfire
    ;
    public static void load(){
        //environment
        Blocks.regolithWall.attributes.set(ErekirAttributes.slag, 0.1f);
        Blocks.yellowStoneWall.attributes.set(ErekirAttributes.slag, 0.3f);
        metalVent = new SteamVent("metal-vent"){{
            parent = Blocks.metalFloor;
            variants = 0;
            blendGroup = Blocks.metalFloor;
            attributes.set(Attribute.steam, 1.25f);
        }};
        pipe = new StaticWall("pipe-wall"){{
            variants = 1;
            attributes.set(ErekirAttributes.slag, 1f);
        }};
        rubidiumWallOre = new OreBlock("rubidium-wall-ore"){{
            wallOre = true;
            variants = 2;
            itemDrop = ErekirItems.rubidium;
        }};
        bperillWallOre = new OreBlock("bperill-wall-ore"){{
            wallOre = true;
            variants = 2;
            itemDrop = ErekirItems.bperill;
        }};
        staticF = new Floor("static");
        toxideKiln = new HeatCrafter("carbide-klin"){{
            requirements(Category.crafting, with(Items.silicon, 100, Items.graphite, 80, Items.tungsten, 80, Items.oxide, 80));

            size = 2;

            itemCapacity = 12;
            heatRequirement = 4f;
            craftTime = 60f * 5f;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.9f;

            outputItem = new ItemStack(toxide, 1);

            craftEffect = new RadialEffect(new ParticleEffect(){{
                particles = 1;
                lifetime = 30;
                length = 9;
                colorTo = Color.valueOf("ee687e");
            }}, 5, 90f, 3.5f);

            drawer = new DrawMulti(new DrawDefault(), new DrawHeatInput(),
                    new DrawGlowRegion(){{
                        suffix = "-glow";
                        color = Color.valueOf("cb4b7f");
                    }});
            squareSprite = false;
            consumeItem(tungsten, 4);
            consumeLiquid(water, 8f / 60f);
            consumePower(2f);
        }};
        replacer = new GenericCrafter("replacer"){{
            requirements(Category.crafting, with(graphite, 175, silicon, 235, oxide, 35, Items.tungsten, 135));
            size = 3;

            researchCostMultiplier = 0.75f;
            craftTime = 220f;
            rotate = true;
            invertFlip = true;
            group = BlockGroup.liquids;
            itemCapacity = 0;

            liquidCapacity = 20f;

            consumeItems(with(beryllium, 2, tungsten, 2));
            consumePower(3.75f);

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(radon, 2f),
                    new DrawRegion(),
                    new DrawLiquidOutputs()
            );

            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.08f;

            regionRotated1 = 3;
            outputLiquids = LiquidStack.with(radon, 0.08f, tungllium, 0.035f);
            liquidOutputDirections = new int[]{1, 4};
        }};
        neoplasmFurnace  = new HeatCrafter("neoplasm-furnance"){{
            requirements(Category.crafting, with(graphite, 290, silicon, 240, tungsten, 185, oxide, 120));
            craftEffect = Fx.none;
            heatRequirement = 3;
            outputLiquid = new LiquidStack(neoplasm, 0.3f);
            craftTime = 185f;
            size = 3;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 12;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawArcSmelt(){{
                flameColor = Color.valueOf("F98F4AFF");
                midColor = Color.valueOf("E05438FF");
                flameRad = 1;
                circleSpace = 0.4f;
                circleStroke = 0.7f;
                alpha = 4;
                particleRad = 6;
                particles = 10;
                particleLen = 1.3f;
                particleStroke = 0.5f;
                particleLife = 6;
            }}, new DrawDefault());
            fogRadius = 3;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;

            consumeItems(with(graphite, 3));
            consumeLiquid(Liquids.slag, 0.25f);
            consumePower(4f);
        }};
        bperillExtractor = new HeatCrafter("bperill-extractor"){{
            requirements(Category.crafting, with(beryllium, 420, tungsten, 280, silicon, 210, oxide, 120, toxide, 95));

            size = 3;

            heatRequirement = 5f;
            craftTime = 220f;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.9f;

            outputItem = new ItemStack(bperill, 2);

            drawer = new DrawMulti( new DrawRegion("-bottom"),
                    new DrawSpikes(){{
                        amount = 8;
                        radius = 2;
                        length = 4;
                        rotateSpeed = 3.4f;
                        color = Color.valueOf("0a9489");
                    }},
                    new DrawDefault()
            );
            consumeItem(sand, 3);
            consumeLiquids(LiquidStack.with(ozone, 12f / 60f, tungllium, 0.07f));
            consumePower(5f);
        }};
        hydrogenHeater = new HeatProducer("hydrogen-heater"){{
            requirements(Category.crafting, with(oxide, 20, graphite, 35, beryllium, 50));

            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            size = 2;
            heatOutput = 1.5f;
            ambientSound = Sounds.hum;
            consumeLiquid(hydrogen, 1f / 60f);
        }};
        ozoneHeater = new HeatProducer("ozone-heater"){{
            requirements(Category.crafting, with(oxide, 25, tungsten, 40, toxide, 20));

            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            size = 2;
            heatOutput = 3f;
            ambientSound = Sounds.hum;
            consumeLiquid(ozone, 0.5f / 60f);
        }};
        smallRedirector = new HeatConductor("small-redirector"){{
            requirements(Category.crafting, with(graphite, 14, toxide, 6));

            researchCostMultiplier = 3f;

            group = BlockGroup.heat;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
        }};
        //production
        plasmaDrill = new BeamDrill("plasma-drill"){{
            requirements(Category.production, with(beryllium, 65, silicon, 30, graphite, 45));
            consumePower(20 / 60f);

            drillTime = 112f;
            tier = 3;
            size = 2;
            range = 6;
            fogRadius = 3;


            consumeLiquid(hydrogen, 0.5f / 60f).boost();
        }};
        largePlasmaDrill = new BeamDrill("large-plasma-drill"){{
            requirements(Category.production, with(graphite, 75,silicon, 120, carbide, 40, beryllium, 155, tungsten, 170));
            consumePower(1f);
            drillTime = 80f;

            tier = 5;
            size = 3;
            range = 6;
            fogRadius = 4;
            laserWidth = 0.9f;
            itemCapacity = 20;

            consumeLiquid(hydrogen, 0.75f / 60f);
            consumeLiquid(cyanogen, 3f / 60f).boost();
        }};
        siliconPiper = new WallCrafter("silicon-piper"){{
            requirements(Category.production, with(graphite, 65, silicon, 45, beryllium, 20));
            consumePower(20 / 60f);

            drillTime = 90f;
            size = 2;
            attribute = ErekirAttributes.slag;
            output = silicon;
            fogRadius = 2;
            researchCost = with(beryllium, 650, graphite, 350);
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.04f;
        }};
        //defence
        strikeProjector = new PowerTurret("strike-projector"){{
            requirements(Category.effect, with(rubidium,160, silicon, 280, tungsten, 160, oxide, 75));
            size = 3;
            range = 360f;
            health = 550;
            reload = 70f;
            rotateSpeed = 3.5f;
            shootSound = Sounds.laser;
            shake = 1.5f;
            shootY = 0f;
            consumePower(1.6f);
            consumeLiquid(hydrogen, 2f/60f);
            shootEffect = new MultiEffect(
                    new WaveEffect(){{
                        lifetime = 30;
                        sizeFrom = 0;
                        sizeTo = 9;
                        strokeFrom = strokeTo = 2;
                        colorFrom = colorTo = Color.valueOf("ba9191");
                    }},
                    new ParticleEffect(){{
                        lifetime = 20;
                        length = 0;
                        particles = 1;
                        sizeFrom = 5;
                        colorTo = Color.valueOf("91686d");
                    }},
                    new ParticleEffect(){{
                        lifetime = 30;
                        length = 16;
                        particles = 8;
                        sizeFrom = 3;
                        colorTo = Color.valueOf("ba9191");
            }});
            shootType = new EmpBulletType(){{
                buildingDamageMultiplier = 0f;
                splashDamage = 100f;
                splashDamageRadius = 40f;
                damage = 0;
                radius = 0;
                speed = 20f;
                lifetime = 18f;
                sprite = "circle-bullet";
                width = 8;
                height = 8;
                shrinkY = 0;
                frontColor = Color.white;
                backColor = ErekirPal.rubidium;
                lightColor = ErekirPal.rubidium;
                hitColor = ErekirPal.rubidium;
                hitEffect = new MultiEffect(
                        new WaveEffect(){{
                            sizeFrom = sizeTo = 15;
                            strokeFrom = 2;
                            colorFrom = colorTo = Color.valueOf("ba9191");
                        }},
                        new ParticleEffect(){{
                        lifetime = 40;
                        length = 12;
                        particles = 6;
                        sizeFrom = 4;
                        colorTo = Color.valueOf("ba9191");
                }});
            }};
            squareSprite = false;
        }};
        tungstenBarrier = new DirectShieldProjector("tungsten-barrier"){{
            requirements(Category.effect, with(graphite, 125, tungsten, 165, beryllium, 210, oxide, 90));
            size = 3;
            width = 45;
            length = 55;
            shieldHealth = 1600;
            cooldownNormal = 1f;
            cooldownBrokenBase = 0.6f;
            consumePower(3.5f);
        }};
        rubidiumWall = new Wall("rubidium-wall"){{
            requirements(Category.defense, with(tungsten, 3, rubidium, 4));
            health = 200 * 4;
            armor = 14f;
            buildCostMultiplier = 8f;
            chanceDeflect = 12f;
        }};

        rubidiumWallLarge = new Wall("rubidium-wall-large"){{
            requirements(Category.defense, ItemStack.mult(rubidiumWall.requirements, 4));
            health = 180 * 4 * 4;
            armor = 14f;
            buildCostMultiplier = 5f;
            chanceDeflect = 12f;
            size = 2;
        }};
        bperillWall = new ErekirWall("bperill-wall"){{
            requirements(Category.defense, with(surgeAlloy, 6, bperill, 6));
            health = 260 * 4;
            armor = 20;
            lightningChance = 0.2f;
            lightningDamage = 25;
            lightningAmount = 3;
            lightningColor = Color.valueOf("37e995");
        }};
        bperillWallLarge = new ErekirWall("bperill-wall-large"){{
            requirements(Category.defense, ItemStack.mult(bperillWall.requirements, 4));
            health = 260 * 4 * 4;
            size = 2;
            armor = 20;
            lightningChance = 0.2f;
            lightningDamage = 25;
            lightningAmount = 3;
            lightningColor = Color.valueOf("37e995");
        }};
        rubidiumNode = new BeamNode("rubidium-beam-node"){{
            requirements(Category.power, with(beryllium, 6, silicon, 2, rubidium, 6));
            consumesPower = outputsPower = placeableLiquid = true;
            health = 160;
            range = 6;
            fogRadius = 1;

            consumePowerBuffered(8000f);
        }};
        carbideNode = new BeamNode("beam-nodeup"){{
            requirements(Category.power, with(Items.beryllium, 10, oxide, 4, carbide, 6));
            consumesPower = outputsPower = true;
            health = 200;
            range = 16;
            fogRadius = 1;
            consumePowerBuffered(5000f);
        }};
        cryliteReactor = new ConsumeGenerator("crylite-reactor"){{
            requirements(Category.power, with(silicon, 145, beryllium, 120, graphite, 90));
            health = 160;
            powerProduction = 80 / 60f;
            researchCostMultiplier = 0.45f;
            consumeLiquids(LiquidStack.with(water, 5f / 60f));
            consumeItem(graphite, 1);
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
            generateEffect = Fx.smokePuff;

            liquidCapacity = 10;
            itemDuration = 100;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;
        }};
        coreStation = new UpgradeCore("core-station"){{
            requirements(Category.effect, with(graphite, 1400, silicon, 1200, beryllium, 1100, tungsten, 780));

            isFirstTier = true;
            squareSprite = false;
            unitType = realise;
            health = 7000;
            itemCapacity = 2500;
            size = 4;
            thrusterLength = 34/4f;
            armor = 7f;
            alwaysUnlocked = true;
            incinerateNonBuildable = true;
            requiresCoreZone = false;

            unitCapModifier = 15;
        }};
        //effect
        rubRadar = new Radar("rubradar"){{
            requirements(Category.effect, BuildVisibility.fogOnly,with(rubidium, 25, silicon, 90, graphite, 70, tungsten, 40));
            outlineColor = Pal.darkOutline;
            fogRadius = 56;
            consumePower(2.5f);
        }};
        berylliumProjector = new OverdriveProjector("beryllium-reactor"){{
            requirements(Category.effect, with(silicon, 320, graphite, 265));
            size = 3;
            health = 1080;
            range = 75;
            speedBoost = 1.3f;
            hasBoost = false;
            fogRadius = 3;
            consumePower(5f);
            consumeItem(beryllium, 5);
            squareSprite = false;
        }};
        rubidiumLaunchPad = new LaunchPad("rubidium-launch-pad"){{
            requirements(Category.effect, BuildVisibility.campaignOnly,with(beryllium, 300, graphite, 245, silicon, 290, rubidium, 160));
            size = 3;
            itemCapacity = 100;
            launchTime = 300f;
            consumePower(4.5f);
        }};
        bomb = new Bomb("bomb"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(tungsten, 20, silicon, 30));
        }};
        bombConstructor = new BombConstructor("bomb-constructor"){{
            requirements(Category.units, BuildVisibility.sandboxOnly, with(silicon, 190, beryllium, 170, toxide, 80, rubidium, 110));
            regionSuffix = "-dark";
            hasPower = true;
            buildSpeed = 0.5f;
            consumePower(2.5f);
            size = 3;
            configurable = false;
        }
        };
        heatPump = new HeatPump("heat-pump"){{
            requirements(Category.liquid,with(beryllium, 90, silicon, 70, tungsten, 45, toxide, 50));
            size = 3;
            health = 760;
            pumpAmount = 0.22233f;
            liquidCapacity = 220;
            consumePower(3f);
            heatRequirement = 4;
            maxEfficiency = 2.5f;
            squareSprite = false;
        }};
        ecription = new ItemTurret("ecription"){{
            requirements(Category.turret, with(beryllium, 130, silicon, 185, graphite, 260));

            ammo(
                    beryllium, new BasicBulletType(5f, 20){{
                        width = 8f;
                        hitSize = 7f;
                        height = 12f;
                        ammoMultiplier = 1;
                        hitColor = backColor = trailColor = Pal.berylShot;
                        frontColor = Color.white;
                        trailWidth = 1.6f;
                        trailLength = 7;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                        pierceArmor = true;
                    }},
                    silicon, new BasicBulletType(5f, 35){{
                        width = 8f;
                        height = 12f;
                        hitSize = 7f;
                        ammoMultiplier = 1;
                        reloadMultiplier = 1f;
                        hitColor = backColor = trailColor = Pal.darkerGray;
                        frontColor = Color.white;
                        trailWidth = 1.6f;
                        trailLength = 7;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                        pierceArmor = true;
                    }}
            );

            squareSprite = false;
            coolantMultiplier = 5f;
            shake = 1f;
            drawer = new DrawTurret("reinforced-");
            outlineColor = Pal.darkOutline;
            size = 3;
            envEnabled |= Env.space;
            reload = 10f;
            recoil = 2f;
            range = 200;
            shootCone = 5f;
            scaledHealth = 130;
            researchCostMultiplier = 0.05f;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
        }};
        blaster = new ItemTurret("blaster"){{
            requirements(Category.turret, with(beryllium, 320, silicon, 275, graphite, 290));

            ammo(
                    graphite, new BasicBulletType(6f, 100){{
                        width = 10f;
                        height = 13f;
                        lifetime = 280f / 6;
                        hitColor = backColor = trailColor = Pal.coalBlack;
                        frontColor = Color.white;
                        trailWidth = 2f;
                        trailLength = 10;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        status = StatusEffects.unmoving;
                        statusDuration = 80;
                        buildingDamageMultiplier = 0.3f;
                    }},
                    tungsten, new BasicBulletType(6f, 180){{
                        width = 10f;
                        height = 13f;
                        lifetime = 280f / 6;
                        hitColor = backColor = trailColor = Pal.tungstenShot;
                        frontColor = Color.white;
                        trailWidth = 2f;
                        trailLength = 10;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        status = StatusEffects.unmoving;
                        statusDuration = 100;
                        buildingDamageMultiplier = 0.3f;
                    }}
            );

            coolantMultiplier = 5f;
            shake = 3f;
            drawer = new DrawTurret("reinforced-"){{
                    parts.addAll(
                            new RegionPart("-barrel"){{
                                progress = PartProgress.recoil.curve(Interp.pow2In);
                                moveY = -3f;
                                mirror = false;
                    }});
            }};
            outlineColor = Pal.darkOutline;
            size = 3;
            envEnabled |= Env.space;
            reload = 170f;
            recoil = 2f;
            range = 280;
            scaledHealth = 144;
            researchCostMultiplier = 0.05f;
            squareSprite = false;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
        }};
        crossbow = new ItemTurret("crossbow"){{
            requirements(Category.turret, with(graphite, 95, silicon, 170, rubidium, 110));

            ammo(
                    rubidium, new BasicBulletType(5f, 60){{
                        width = 7f;
                        height = 15f;
                        lifetime = 48;
                        knockback = 32;
                        pierceCap = 4;
                        hitColor = backColor = trailColor = Color.valueOf("976d73");
                        frontColor = Color.valueOf("e1c6c1");
                        sprite = "erek-bolt";
                        trailWidth = 1.5f;
                        trailLength = 7;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                    }},
                    toxide, new BasicBulletType(5f, 75){{
                        width = 7f;
                        height = 15f;
                        lifetime = 48;
                        knockback = 40;
                        pierceCap = 5;
                        hitColor = backColor = trailColor = Color.valueOf("cb3874");
                        frontColor = Color.valueOf("ee687e");
                        sprite = "erek-bolt";
                        trailWidth = 1.5f;
                        trailLength = 7;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        status = ErekirStatusEffects.overload;
                        statusDuration = 140;
                        buildingDamageMultiplier = 0.3f;
                    }}
            );

            coolantMultiplier = 5f;
            drawer = new DrawTurret("reinforced-"){{
                parts.addAll(
                        new RegionPart("-barrel"){{
                            progress = PartProgress.recoil.curve(Interp.pow2In);
                            moveY = -3f;
                            mirror = false;
                            under = true;
                        }},
                        new RegionPart("-wing"){{
                            progress = PartProgress.recoil;
                            moveX = -2f;
                            mirror = under = true;
                        }}
                );
            }};
            outlineColor = Pal.darkOutline;
            size = 3;
            envEnabled |= Env.space;
            reload = 55f;
            recoil = 2f;
            range = 190;
            scaledHealth = 150;
            researchCostMultiplier = 0.05f;
            squareSprite = false;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
        }};
        peweless = new ContinuousLiquidTurret("peweless"){{
            requirements(Category.turret, with(tungsten, 190, silicon, 295, oxide, 65));

            drawer = new DrawTurret("reinforced-"){{

                Color heatc = Color.valueOf("356de1");
                heatColor = heatc;

                parts.addAll(
                        new RegionPart("-up"){{
                            progress = PartProgress.warmup;
                            mirror = true;
                            x = 0;
                            y = 0.8f;
                            moveX = 0.8f;
                            moveY = -0.8f;
                            under = true;
                            heatColor = heatc;
                        }});
            }};
            outlineColor = Pal.darkOutline;

            liquidConsumed = 12f / 60f;
            targetUnderBlocks = false;
            consumePower(190 / 60f);

            float r = range = 150f;

            loopSound = Sounds.torch;
            shootSound = Sounds.none;
            loopSoundVolume = 1f;

            ammo(
                    water, new ContinuousFlameBulletType(){{
                        damage = 20f;
                        rangeChange = 0f;
                        length = r + rangeChange;
                        knockback = 1f;
                        pierceCap = 4;
                        buildingDamageMultiplier = 0.3f;

                        colors = new Color[]{Color.valueOf("0071e4").a(0.5f), Color.valueOf("1287ff").a(0.7f), Color.valueOf("3c9cff"), Color.white};
                        flareColor = Color.valueOf("3c9cff");

                        lightColor = hitColor = flareColor;
                    }},
                    radon, new ContinuousFlameBulletType(){{
                        damage = 24f;
                        rangeChange = 50f;
                        length = r + rangeChange;
                        knockback = 1.5f;
                        pierceCap = 6;
                        buildingDamageMultiplier = 0.3f;

                        colors = new Color[]{Color.valueOf("29a600").a(0.5f), Color.valueOf("43c818").a(0.7f), Color.valueOf("56dd2b"), Color.white};
                        flareColor = Color.valueOf("56dd2b");

                        lightColor = hitColor = flareColor;
                    }},
                    neoplasm, new ContinuousFlameBulletType(){{
                        damage = 30f;
                        rangeChange = 90f;
                        length = r + rangeChange;
                        knockback = 2.8f;
                        pierceCap = 8;
                        buildingDamageMultiplier = 0.3f;

                        colors = new Color[]{Color.valueOf("9e172c").a(0.5f), Color.valueOf("e05438").a(0.7f), Color.valueOf("f98f4a"), Color.white};
                        flareColor = Color.valueOf("f98f4a");

                        lightColor = hitColor = flareColor;
                    }}
            );

            scaledHealth = 230;
            size = 2;
            squareSprite = false;
        }};
        abyssHunter = new ContinuousTurret("abysshunter"){{
            requirements(Category.turret, with(Items.silicon, 250, Items.graphite, 200, Items.oxide, 50, Items.carbide, 90));
            range = 180f;
            shootType = new ContinuousFlameBulletType(){{
                damage = 30f;
                length = 180;
                buildingDamageMultiplier = 0.3f;

                colors = new Color[]{Color.valueOf("0071e4").a(0.5f), Color.valueOf("1287ff").a(0.7f), Color.valueOf("3c9cff"), Color.white};
                flareColor = Color.valueOf("3c9cff");

                lightColor = hitColor = flareColor;
            }};

            drawer = new DrawTurret("reinforced-"){{

                parts.add(new ShapePart(){{
                              progress = PartProgress.warmup.delay(0.2f);
                              color = Color.valueOf("4874cf");
                              circle = false;
                              hollow = true;
                              stroke = 0f;
                              strokeTo = 2f;
                              radius = 3f;
                              rotation = 90;
                              sides = 3;
                              layer = Layer.effect;
                              y = -9;
                              rotateSpeed = 0;
                          }},
                        new HaloPart(){{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Color.valueOf("4874cf");
                            layer = Layer.effect;
                            y = -9;

                            haloRotateSpeed = 5f;
                            shapes = 3;
                            triLength = 0f;
                            triLengthTo = 6f;
                            haloRadius = 4.5f;
                            tri = true;
                            radius = 5f;
                        }});
            }};

            shootSound = Sounds.none;
            loopSoundVolume = 1f;
            loopSound = Sounds.laserbeam;

            shootWarmupSpeed = 0.08f;
            shootCone = 360f;

            aimChangeSpeed = 0.9f;
            rotateSpeed = 0.9f;

            outlineColor = Pal.darkOutline;
            size = 3;
            envEnabled |= Env.space;
            scaledHealth = 150;


            consumePower(8);
            squareSprite = false;
        }};
        conclusion = new ItemTurret("conclusion"){{
            requirements(Category.turret, with(toxide, 140, oxide, 170, silicon, 290, tungsten, 210));

            ammo(tungsten, new BasicBulletType(7, 120){{
                width = 12;
                height = 16;
                shootEffect = Fx.shootBig2;
                smokeEffect = Fx.shootSmokeDisperse;
                frontColor = Color.white;
                backColor = trailColor = hitColor = Pal.orangeSpark;
                ammoMultiplier = 3f;
                sprite = "missile-large";

                lifetime = 49f;
                trailInterval = 3;
                trailEffect = Fx.hitSquaresColor;
                homingDelay = 8;
                homingPower = 0.4f;
                homingRange = 50;
                buildingDamageMultiplier = 0.3f;

                hitEffect = despawnEffect = Fx.blastExplosion;
            }});

            reload = 190f;
            shootY = 9;
            rotateSpeed = 6.5f;
            shootCone = 55f;
            consumeAmmoOnce = true;
            shootSound = Sounds.shootBig;

            drawer = new DrawTurret("reinforced-"){{
                parts.add(new RegionPart("-blade"){{
                              mirror = true;
                              under = true;
                              moveX = -1.75f;
                              moveY = -0.75f;
                          }});
            }};

            shoot = new ShootSpread(){{
                spread = 5.6f;
                shots = 5;
            }};
            inaccuracy = 20f;

            shootWarmupSpeed = 0.1f;

            outlineColor = Pal.darkOutline;

            scaledHealth = 240;
            range = 340f;
            size = 4;

            coolant = consume(new ConsumeLiquid(Liquids.water, 20f / 60f));
            coolantMultiplier = 1.8f;
            squareSprite = false;
        }};
        bonfire = new ContinuousLiquidTurret("bonfire"){{
            requirements(Category.turret, with(beryllium, 1820, graphite, 1420, silicon, 940, tungsten, 760, oxide, 530, carbide, 370, bperill, 326, toxide, 275));

            drawer = new DrawTurret("reinforced-"){{

                Color heatc = Color.valueOf("f98f4a");
                heatColor = heatc;

                parts.addAll(
                        new RegionPart("-barrel"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            x = 0;
                            y = 7.5f;
                            moveX = 0f;
                            moveY = -3f;
                            under = true;
                            heatColor = heatc;
                        }},
                        new RegionPart("-barrel"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            x = 11;
                            y = 5.5f;
                            moveX = 0f;
                            moveY = -3f;
                            under = true;
                            heatColor = heatc;
                        }},
                        new RegionPart("-barrel"){{
                            progress = PartProgress.warmup;
                            mirror = false;
                            x = -11;
                            y = 5.5f;
                            moveX = 0f;
                            moveY = -3f;
                            under = true;
                            heatColor = heatc;
                        }},
                        new RegionPart("-spine"){{
                            outline = false;
                            progress = PartProgress.warmup.delay(0.2f);
                            heatProgress = PartProgress.warmup.add(p -> (Mathf.absin(3f, 0.2f) - 0.2f) * p.warmup);
                            mirror = true;
                            under = true;
                            layerOffset = -0.3f;
                            turretHeatLayer = Layer.turret - 0.2f;
                            moveY = -5f;
                            moveX = 5;

                            color = Color.valueOf("f2ae8f");
                            heatColor = heatc;
                        }},
                        new ShapePart(){{
                            progress = PartProgress.warmup.delay(0.2f);
                            color = Color.valueOf("f2ae8f");
                            circle = false;
                            hollow = true;
                            sides = 5;
                            stroke = 0f;
                            strokeTo = 4f;
                            radius = 8f;
                            rotation = 90;
                            layer = Layer.effect;
                            y = -14;
                            rotateSpeed = 2;
                        }},
                        new HaloPart(){{
                            progress = PartProgress.warmup;
                            color = Color.valueOf("f2ae8f");
                            layer = Layer.effect;
                            y = -14;

                            haloRotateSpeed = 5;
                            shapes = 4;
                            triLength = 0f;
                            triLengthTo = 10f;
                            haloRadius = 5f;
                            hollow = false;
                            tri = true;
                            radius = 4f;
                        }},
                        new HaloPart(){{
                            progress = PartProgress.warmup;
                            color = Color.valueOf("f2ae8f");
                            layer = Layer.effect;
                            y = -14;
                            haloRotation = 90;
                            shapes = 2;
                            triLength = 0f;
                            triLengthTo = 18f;
                            haloRadius = 12f;
                            hollow = false;
                            tri = true;
                            radius = 8f;
                        }});
            }};
            shoot = new ShootMulti(new ShootAlternate(){{
                spread = 0;
                shots = barrels = 1;
            }}, new ShootBarrel(){{
                barrels = new float[]{0, -11, 0};
            }}, new ShootBarrel(){{
                barrels = new float[]{11, -14, 0};
            }}, new ShootBarrel(){{
                barrels = new float[]{-11, -14, 0};
            }});
            outlineColor = Pal.darkOutline;

            liquidConsumed = 20f / 60f;
            targetUnderBlocks = false;

            float r = range = 240f;

            loopSound = Sounds.torch;
            shootSound = Sounds.none;
            loopSoundVolume = 1f;

            ammo(
                    radon, new ContinuousFlameBulletType(){{
                        damage = 170f;
                        length = r;
                        knockback = 2f;
                        pierceCap = 5;
                        buildingDamageMultiplier = 0.5f;

                        colors = new Color[]{Color.valueOf("29a600").a(0.5f), Color.valueOf("43c818").a(0.7f), Color.valueOf("56dd2b"), Color.white};
                        flareColor = Color.valueOf("56dd2b");

                        lightColor = hitColor = flareColor;
                    }},
                    neoplasm, new ContinuousFlameBulletType(){{
                        damage = 300f;
                        length = r;
                        knockback = 4.5f;
                        pierceCap = 10;
                        buildingDamageMultiplier = 0.5f;

                        colors = new Color[]{Color.valueOf("9e172c").a(0.5f), Color.valueOf("e05438").a(0.7f), Color.valueOf("f98f4a"), Color.white};
                        flareColor = Color.valueOf("f98f4a");

                        lightColor = hitColor = flareColor;
                    }}
            );

            health = 12600;
            armor = 6;
            size = 5;
            squareSprite = false;
        }};
    }
}
