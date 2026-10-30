package dev.chatgpt.panoramarandomizer.mixin;

import dev.chatgpt.panoramarandomizer.PanoramaSelection;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(MultiPackResourceManager.class)
public abstract class MultiPackResourceManagerMixin {
    @Inject(method = "getResource", at = @At("HEAD"), cancellable = true)
    private void panoramaRandomizer$redirectOverlay(
            Identifier requested,
            CallbackInfoReturnable<Optional<Resource>> cir
    ) {
        Identifier chosen = PanoramaSelection.getSelected();

        if (requested.equals(PanoramaSelection.DEFAULT_OVERLAY)
                && chosen != null
                && !chosen.equals(PanoramaSelection.DEFAULT_OVERLAY)) {
            MultiPackResourceManager manager = (MultiPackResourceManager) (Object) this;
            cir.setReturnValue(manager.getResource(chosen));
        }
    }
}
