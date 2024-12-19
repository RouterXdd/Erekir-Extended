package erek.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.type.StatusEffect;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class ErekirStatusEffects {
    public static StatusEffect overload;

    public static void load(){
        overload = new StatusEffect("overload"){{
            color = Color.valueOf("ee687e");
            speedMultiplier = 0.75f;
            reloadMultiplier = 0.6f;
            effect = new MultiEffect(
                    new Effect(40f, e -> {
                        color(color);

                        randLenVectors(e.id, 1, 1f + e.fin() * 2f, (x, y) -> {
                            Fill.square(e.x + x, e.y + y, e.fslope(), 45f);
                        });
                    })
            );
            effectChance = 0.1f;
            show = false;
        }};
    }
}
