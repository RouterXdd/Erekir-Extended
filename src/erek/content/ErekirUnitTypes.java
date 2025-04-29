package erek.content;

import arc.audio.Sound;
import arc.graphics.Color;
import arc.math.geom.Rect;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.*;
import mindustry.type.weapons.RepairBeamWeapon;

import static arc.struct.Seq.with;
import static mindustry.Vars.tilesize;

public class ErekirUnitTypes {
    public static UnitType
    //alt tanks
    run, input, output, softlock,
    //over-flyers
    way, path, road, bridge, highway,
    //support units
    heal, regeneration, repair, rebuild, reconstruct,
    //gliders
    slide, fall, pit, depth, abyss,
    //core units
    realise, encounter, cooperative,
    //sector
    reroll, pellet, prevent, rupturer, phoenix
    ;

    public static void load() {
        run = new TankUnitType("t2-rubid"){{
            hitSize = 18f;
            treadPullOffset = 3;
            speed = 0.7f;
            rotateSpeed = 3.5f;
            health = 1860;
            armor = 6f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(24 - 96f / 2, 13 - 96f / 2, 17, 72)};
            researchCostMultiplier = 0.4f;
            constructor = TankUnit::create;

            weapons.add(new Weapon("erek-rubid-weapon"){{
                layerOffset = 0.0001f;
                reload = 40f;
                shootY = 5f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 2.2f;
                mirror = false;
                x = 0f;
                y = 3.5f;
                inaccuracy = 3f;
                shoot = new ShootSpread(15, 1f);
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 30f;
                velocityRnd = 0.7f;

                bullet = new BasicBulletType(5f, 13){{
                    smokeEffect = Fx.shootSmallSmoke;
                    shootEffect = Fx.shootSmallColor;
                    pierceCap = 2;
                    pierceBuilding = true;
                    width = 3f;
                    height = 12f;
                    lifetime = 15f;
                    hitSize = 4f;
                    hitColor = backColor = trailColor = Color.valueOf("e1c6c1");
                    frontColor = Color.white;
                    trailWidth = 1f;
                    trailLength = 8;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        heal = new ErekirUnitType("r1-heal"){{
            defaultCommand = UnitCommand.repairCommand;

            range = 60f;
            faceTarget = flying = true;
            lowAltitude = false;
            drag = 0.08f;
            speed = 7f;
            rotateSpeed = 8f;
            accel = 0.09f;
            itemCapacity = 70;
            health = 730f;
            hitSize = 11f;
            engineColor = Pal.heal;
            engineSize = 0;

            fogRadius = 4f;
            constructor = UnitEntity::create;

            setEnginesMirror(
                    new UnitEngine(12 / 4f, -22 / 4f, 2.5f, -45f)
            );

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 0f;
                y = 4f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                repairSpeed = 1.2f;
                fractionRepairSpeed = 0.08f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = false;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.heal;
                healColor = Pal.heal;

                bullet = new BulletType(){{
                    maxRange = 60f;
                }};
            }});
        }};
        slide = new ErekirUnitType("g1-slide"){{
            speed = 2.1f;
            drag = 0.12f;
            flying = true;
            health = 390;
            armor = 2.75f;
            engineSize = 0f;
            hitSize = 11;
            constructor = UnitEntity::create;
                parts.add(new HoverPart(){{
                    x = 0f;
                    y = 0;
                    mirror = false;
                    radius = 8f;
                    phase = 50f;
                    stroke = 2f;
                    sides = 4;
                    layerOffset = -0.001f;
                    color = Color.valueOf("37e995");
                }});

            weapons.add(new Weapon(){{
                y = 3f;
                x = 0f;
                reload = 24f;
                ejectEffect = Fx.none;
                mirror = false;
                bullet = new ShrapnelBulletType(){{
                    length = 24;
                    damage = 30f;
                    lifetime = 20;
                    width = 7f;
                    toColor = Color.valueOf("37e995");
                }};
                shootSound = Sounds.shootSnap;
            }});
        }};
        fall = new ErekirUnitType("g2-fall"){{
            speed = 1.85f;
            drag = 0.14f;
            flying = true;
            health = 890;
            engineSize = 0f;
            hitSize = 15;
            armor = 5.85f;
            constructor = UnitEntity::create;
            parts.add(new HoverPart(){{
                x = 0f;
                y = 0;
                mirror = false;
                radius = 15f;
                phase = 45f;
                stroke = 2.5f;
                sides = 4;
                layerOffset = -0.001f;
                color = Color.valueOf("37e995");
            }});

            weapons.add(new Weapon(){{
                y = 0f;
                x = 0f;
                reload = 32f;
                shootCone = 360;
                baseRotation = 180;
                ejectEffect = Fx.none;
                mirror = false;
                bullet = new BasicBulletType(){{
                    damage = 50f;
                    lifetime = 55;
                    speed = 5;
                    height = 13f;
                    width = 5.5f;
                    frontColor = Color.valueOf("37e995");
                    backColor = trailColor = Color.valueOf("0a9489");
                    trailWidth = 2;
                    trailLength = 9;
                    homingDelay = 2;
                    homingPower = 0.25f;
                    pierceCap = 2;
                    pierceBuilding = true;
                }};
                shootSound = Sounds.blaster;
            }});
        }};
        realise = new ErekirUnitType("realise"){{
            coreUnitDock = true;
            controller = u -> new BuilderAI(true, 600);
            isEnemy = false;
            envDisabled = 0;

            constructor = PayloadUnit::create;
            range = 70f;
            faceTarget = true;
            targetPriority = -2;
            lowAltitude = false;
            mineWalls = true;
            mineFloor = false;
            mineHardnessScaling = false;
            flying = true;
            mineSpeed = 7f;
            mineTier = 3;
            buildSpeed = 1.2f;
            drag = 0.08f;
            speed = 5.8f;
            rotateSpeed = 7f;
            accel = 0.09f;
            itemCapacity = 60;
            health = 340f;
            armor = 1f;
            hitSize = 9f;
            engineSize = 2.2f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;
            engineOffset = 4f;
            engineColor = Color.valueOf("cb3874");

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.15f;
                reload = 20f;
                x = 0f;
                y = 6.5f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                repairSpeed = 3.25f;
                fractionRepairSpeed = 0.06f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = false;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Color.valueOf("cb3874");
                healColor = Color.valueOf("cb3874");

                bullet = new BulletType(){{
                    maxRange = 70f;
                }};
            }});
        }};
        pellet = new ErekirUnitType("pellet"){{
            speed = 1.85f;
            drag = 0.1f;
            flying = true;
            health = 90;
            armor = 50f;
            engineSize = 0f;
            hitSize = 11;
            constructor = UnitEntity::create;
            circleTarget = true;
            drawCell = false;
            parts.add(new HoverPart(){{
                x = 0f;
                y = 0;
                mirror = false;
                radius = 14f;
                phase = 35f;
                stroke = 2.5f;
                sides = 6;
                layerOffset = -0.001f;
                color = Color.valueOf("d8465c");
            }});

            weapons.add(new Weapon(){{
                y = 0f;
                x = 0f;
                reload = 42f;
                ejectEffect = Fx.none;
                mirror = false;
                bullet = new LaserBulletType(30){{
                    colors = new Color[]{Color.valueOf("d8465c").cpy().a(0.4f), Color.valueOf("d8465c"), Color.white};

                    hitEffect = Fx.hitLancer;
                    hitSize = 5;
                    lifetime = 10f;
                    drawSize = 400f;
                    length = 50f;
                    ammoMultiplier = 1f;
                    pierceCap = 1;
                }};
                shootSound = Sounds.laser;
            }});
        }};
        prevent = new ErekirUnitType("prevent"){{
            speed = 2.5f;
            drag = 0.1f;
            accel = 0.3f;
            flying = true;
            drawCell = false;
            health = 8750;
            armor = 4;
            engineSize = 0f;
            hitSize = 24;
            constructor = UnitEntity::create;
            setEnginesMirror(
                    new UnitEngine(15.25f, -2.25f, 4f, -45),
                    new UnitEngine(12.5f, -9.75f, 4f, -45)
            );

            weapons.add(new Weapon(){{
                y = 0f;
                x = 0f;
                reload = 110f;
                ejectEffect = Fx.none;
                mirror = rotate = false;
                shoot = new ShootSpread(3, 10);
                bullet = new MissileBulletType(5.5f, 240){{
                    smokeEffect = Fx.shootBigSmoke;
                    shootEffect = Fx.shootBigColor;
                    drag = 0.02f;
                    width = 6f;
                    height = 10f;
                    lifetime = 75f;
                    hitSize = 4f;
                    hitColor = backColor = trailColor = Color.valueOf("c1dc53");
                    frontColor = Color.white;
                    homingPower = 0.3f;
                    homingDelay = 14;
                    trailRotation = true;
                    trailChance = 0.55f;
                    trailEffect = Fx.disperseTrail;
                    trailWidth = 1.3f;
                    trailLength = 8;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
                shootSound = Sounds.missile;
            }});
        }};
        rupturer = new TankUnitType("rupturer"){{
            hovering = true;
            shadowElevation = 0.15f;
            drag = 0.1f;
            hitSize = 26f;
            treadPullOffset = 3;
            speed = 0.45f;
            rotateSpeed = 2.2f;
            health = 12000;
            armor = 18f;
            fogRadius = 100f * 3f / 8f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(24 - 96f / 2, 13 - 96f / 2, 17, 72)};
            researchCostMultiplier = 0.4f;
            constructor = ElevationMoveUnit::create;

            for(float f : new float[]{-13f, 13f}){
                parts.add(new HoverPart(){{
                    x = 10f;
                    y = f;
                    mirror = true;
                    radius = 14f;
                    phase = 70f;
                    stroke = 3.5f;
                    sides = 6;
                    layerOffset = -0.001f;
                    color = Color.valueOf("8f3a9c");
                }});
            }

            weapons.add(new Weapon("erek-rupturer-weapon"){{
                layerOffset = 1f;
                reload = 240f;
                shootY = 19.5f;
                recoil = 3f;
                rotate = true;
                rotateSpeed = 1.5f;
                mirror = false;
                x = 0f;
                y = -5f;
                inaccuracy = 0f;
                shootSound = Sounds.largeCannon;
                heatColor = Color.valueOf("f9350f");
                cooldownTime = 150f;
                parts.addAll(
                        new RegionPart("-barrel"){{
                            mirror = false;
                            under = true;
                            recoilIndex = 2;
                            cooldownTime = 50;
                            heatProgress = PartProgress.recoil;
                            progress = PartProgress.recoil;
                            moveY = -3f;
                        }});

                bullet = new ArtilleryBulletType(3f, 200, "shell"){{
                    hitEffect = Fx.blastExplosion;
                    knockback = 0.8f;
                    lifetime = 200f;
                    width = height = 20f;
                    splashDamageRadius = 60f;
                    splashDamage = 300f;
                    hitColor = backColor = trailColor = Color.valueOf("8f3a9c");
                    frontColor = Color.white;
                    trailWidth = 5.5f;
                    trailLength = 35;
                    trailEffect = Fx.none;

                    intervalBullet = new ExplosionBulletType(80, 40){{
                        killShooter = false;
                    }};

                    bulletInterval = 25f;
                    intervalRandomSpread = 20f;
                    intervalBullets = 1;
                    intervalAngle = 180f;
                    intervalSpread = 300f;
                }};
            }});
        }};
    }
}
