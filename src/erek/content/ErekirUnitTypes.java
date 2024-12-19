package erek.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.PayloadUnit;
import mindustry.gen.TankUnit;
import mindustry.gen.UnitEntity;
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
    //worms
    way, path, road, bridge, highway,
    //support units
    heal, regeneration, repair, rebuild, reconstruct,
    //gliders
    slide, fall, pit, depth, abyss,
    //core units
    realise, encounter, cooperative,
    //sectoring
    reroll, phoenix
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
    }
}
