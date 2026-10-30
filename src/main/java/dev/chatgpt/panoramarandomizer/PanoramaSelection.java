package dev.chatgpt.panoramarandomizer;

import net.minecraft.resources.Identifier;

public final class PanoramaSelection {
    public static final Identifier DEFAULT_OVERLAY =
            Identifier.parse("minecraft:textures/gui/title/background/panorama_overlay.png");

    private static volatile Identifier selected;

    private PanoramaSelection() {
    }

    public static Identifier getSelected() {
        return selected;
    }

    public static void setSelected(Identifier identifier) {
        selected = identifier;
    }
}
