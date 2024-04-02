package dontneg.bananabaking.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class BananaFood {
    public static final FoodComponent BANANA = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f)
            .build();
    public static final FoodComponent BANANA_BREAD = new FoodComponent.Builder().hunger(10).saturationModifier(0.7f)
            .build();
    public static final FoodComponent BANANA_PIE = new FoodComponent.Builder().hunger(12).saturationModifier(0.2f)
            .build();
    public static final FoodComponent BANANA_PIE_SLICE = new FoodComponent.Builder().hunger(3).saturationModifier(0.2f)
            .build();
    public static final FoodComponent BANANA_CHOCOLATE = new FoodComponent.Builder().hunger(7).saturationModifier(0.4f)
            .build();
    public static final FoodComponent BANANA_FRIED = new FoodComponent.Builder().hunger(7).saturationModifier(0.4f)
            .build();
    public static final FoodComponent BANANA_DONUT = new FoodComponent.Builder().hunger(14).saturationModifier(0.2f)
            .build();
    public static final FoodComponent BANANA_COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80,1), 0.25f)
            .build();
    public static final FoodComponent BANANA_PUDDING = new FoodComponent.Builder().hunger(8).saturationModifier(0.f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600,1), 0.25f)
            .build();
    public static final FoodComponent BANANA_SMOOTHIE = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300,1), 0.25f)
            .build();
    public static final FoodComponent BANANA_OILED = new FoodComponent.Builder().hunger(6).saturationModifier(0.35f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 1200,0), 0.25f)
            .build();



}
