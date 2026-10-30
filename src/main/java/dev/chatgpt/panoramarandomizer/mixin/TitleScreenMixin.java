package dev.chatgpt.panoramarandomizer.mixin;

import dev.chatgpt.panoramarandomizer.PanoramaSelection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {
    private static final Logger PANORAMA_RANDOMIZER_LOGGER =
            LoggerFactory.getLogger("Panorama Randomizer");

    @Inject(method = "registerTextures", at = @At("HEAD"))
    private static void panoramaRandomizer$chooseOverlay(
            TextureManager textureManager,
            CallbackInfo ci
    ) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

        List<Identifier> overlays = resourceManager
                .listResources("textures/gui/title/background", id -> {
                    String path = id.getPath().toLowerCase(Locale.ROOT);
                    return id.getNamespace().equals("minecraft")
                            && path.contains("panorama_overlay")
                            && path.endsWith(".png");
                })
                .keySet()
                .stream()
                .sorted(Comparator.comparing(Identifier::toString))
                .toList();

        if (overlays.isEmpty()) {
            PanoramaSelection.setSelected(null);
            PANORAMA_RANDOMIZER_LOGGER.warn(
                    "No PNG files containing 'panorama_overlay' were found in the active resource packs."
            );
            return;
        }

        Identifier chosen = overlays.get(ThreadLocalRandom.current().nextInt(overlays.size()));
        PanoramaSelection.setSelected(chosen);

        PANORAMA_RANDOMIZER_LOGGER.info("Selected panorama overlay: {}", chosen);
    }
}
