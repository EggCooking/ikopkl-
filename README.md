# Panorama Randomizer (Minecraft 26.1.2 / Fabric)

A tiny client-only Fabric mod. Its only feature is to choose a random PNG panorama overlay whenever the title screen is opened.

## What it scans

In the active resource packs, it searches this folder:

`assets/minecraft/textures/gui/title/background/`

It includes every `.png` filename in that folder whose name contains `panorama_overlay` (case-insensitive), including the normal `panorama_overlay.png`.

Examples it will match:

- `panorama_overlay.png`
- `panorama_overlay_1.png`
- `my_panorama_overlay_alt.png`
- `panorama_overlay-special.png`

It chooses one once when the title screen opens. It does not animate or change the selection every frame. It does not edit the resource pack or its files.

## Build requirements

- Minecraft Java 26.1.2
- Fabric Loader 0.19.3 or newer
- Java/JDK 25
- Gradle 9.4.1

Minecraft 26.1+ uses the unobfuscated Fabric Loom plugin (`net.fabricmc.fabric-loom`) and Java 25.

## Build

Open a terminal in this project folder and run:

Windows:
```bat
gradle build
```

macOS/Linux:
```bash
gradle build
```

The mod jar should be created in:

`build/libs/panorama-randomizer-1.0.0.jar`

Put that jar in your Minecraft `mods` folder. Keep your resource pack enabled.

## Notes

- The mod scans the active resource manager, so it can read resource packs that are ZIP files or folders.
- It selects among resource identifiers visible in the active resource-pack stack. If two enabled packs contain the exact same filename/path, Minecraft's normal pack priority means only the winning version of that identifier is visible.
- If no matching overlay files are found, Minecraft uses its normal overlay.
- This project is a source project; it has not been compiled or tested in a live Minecraft client in this environment.
