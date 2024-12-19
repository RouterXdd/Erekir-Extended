package erek.classes.blocks.defence;

import arc.math.Mathf;
import mindustry.entities.Lightning;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.world.blocks.defense.Wall;

public class ErekirWall extends Wall {
    public int lightningAmount = 1;
    public ErekirWall(String name) {
        super(name);
    }
    public class ErekirWallBuild extends Building {
        public float hit;
        @Override
        public boolean collision(Bullet bullet){
            super.collision(bullet);
            hit = 1f;

            //create lightning if necessary
            if(lightningChance > 0f){
                if(Mathf.chance(lightningChance)){
                    for(int i = 0; i < lightningAmount; i++) Lightning.create(team, lightningColor, lightningDamage, x, y, bullet.rotation() + 180f, lightningLength);
                    lightningSound.at(tile, Mathf.random(0.9f, 1.1f));
                }
            }

            //deflect bullets if necessary
            if(chanceDeflect > 0f){
                //slow bullets are not deflected
                if(bullet.vel.len() <= 0.1f || !bullet.type.reflectable) return true;

                //bullet reflection chance depends on bullet damage
                if(!Mathf.chance(chanceDeflect / bullet.damage())) return true;

                //make sound
                deflectSound.at(tile, Mathf.random(0.9f, 1.1f));

                //translate bullet back to where it was upon collision
                bullet.trns(-bullet.vel.x, -bullet.vel.y);

                float penX = Math.abs(x - bullet.x), penY = Math.abs(y - bullet.y);

                if(penX > penY){
                    bullet.vel.x *= -1;
                }else{
                    bullet.vel.y *= -1;
                }

                bullet.owner = this;
                bullet.team = team;
                bullet.time += 1f;

                //disable bullet collision by returning false
                return false;
            }

            return true;
        }
    }
}
