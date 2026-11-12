# Changelog / Update Logs

All notable updates, improvements, bug fixes, and global APK releases for **EggDecide** are documented here.

---

## [v1.2.0] - 2026-11-12

### Added — Visual Polish & Multi-Theme Experience
- **Light Mode & AMOLED Black Themes**: Added a 3-mode **Theme Mode** switcher (`Midnight`, `AMOLED`, and `Light`) in **Settings**, dynamically adapting card surfaces, text contrast tokens, shadows, and scrims alongside all 9 egg skin accent palettes.
- **Frosted Glass & Stage Glow**: Added translucent `backdrop-filter` glassmorphism across cards, chips, modals, and toasts, plus a reactive radial aura behind the egg hatcher and scratch card.
- **Dynamic Animated Gradient Backgrounds**: Added dual slow-drifting ambient gradient orbs synchronized with the active theme.
- **Bouncy Micro-Animations on Tap**: Added tactile spring-curve scale/rebound feedback to chips, cards, action buttons, and navigation tabs.
- **Floating `+Coins` & Particle Effects**: Coin gains now launch animated `+Coins` floating text popups and celebratory particle bursts from the trigger button.

### Added — Gameplay, Mystery Boxes & Sanctuary Depth
- **Mystery Egg Blind Boxes (Shop)**: Featured a **65 Coin Mystery Egg Box** with a shake animation that randomly rewards unowned Egg Skins, Pet Accessories, Golden Coin Jackpots (`80–140 Coins`), or Royal XP Feasts (`+60 Pet XP + 25 Coins`).
- **Streak Coin Multiplier Boosts**: Added a live **Coin Multiplier Pill** (`1.0x` → `1.25x` → `1.5x` → `1.75x` → `2.0x`) driven by **Urge Shield Wins + Daily Quest Streak Days**, automatically multiplying completed activity rewards.
- **Pet Accessories & Sanctuary Habitat Upgrades**:
  - Added 5 unlockable and equippable **Pet Accessories** (*Lucky Leaf*, *Royal Crown*, *Star Halo*, *Wizard Hat*, *Cyber Visor*) rendered live atop companion SVGs both on hatch and in Sanctuary.
  - Added 3 **Sanctuary Habitat Tiers** (*Cozy Nest* `+25 XP` → *Crystal Alcove* `+35 XP` → *Celestial Shrine* `+50 XP` per treat).

### Added — Save Backup & Global Release Pipeline
- **Offline Export & Import Save Backup**: Added JSON backup controls inside **Settings** supporting one-tap clipboard copy, `.json` file export/load, and validated import with a preview of Coins, Creatures, and Badges before restoring.
- **Global APK GitHub Releases & Tags**: Upgraded `.github/workflows/build-apk.yml` to trigger on both `main` pushes and `v*` Git tags, generating `EggDecide-global.apk` (universal ABI support), SHA-256 checksums, automated changelog release notes, and a rolling `global` tag release.
- **Version Bump**: Updated Android `versionCode` to `3` and `versionName` to `1.2.0`.

---

## [v1.1.0] - 2026-11-11

### Added — Progression, Pet Sanctuary & Audio
- **Interactive Pet Sanctuary**: Feed treats (`15 Coins` for `+25+ XP`), tap pets to play (`+2 XP`), evolve across Hatchling → Fledgling → Mythic Aura tiers, and set custom pet nicknames.
- **Daily Quests & Streak Chest**: Added 3 daily objectives with quick **Go** action shortcuts and a **Daily Streak Chest** (`+60 Coins`) on full completion.
- **50 Achievement Badges**: Expanded the achievement catalog to **50 unlockable badges** across 9 progression tracks with `All`, `Unlocked`, and `Locked` filter chips.
- **Built-in Offline Ambient Focus Soundscapes**: Synthesized offline **Rain**, **Space**, and **Fire** Web Audio loops with a volume slider during the Focus Timer.

### Fixed
- Fixed Daily Quests prematurely completing upon egg hatch, scratch reveal, or timer ticking without task completion—quest progress now validates real task completion.

---

## [v1.0.0] - 2026-11-10

### Added — Initial Release
- **3-Tap Organic Egg Hatcher**: Animated fracture lines, peeking companion reveal, burst animation, haptic feedback, and Web Audio crack SFX.
- **GPU-Accelerated Scratch Cards**: Pointer-event foil scratching with `contain: strict`, `will-change: transform`, and anti-shortcut coverage tracking.
- **9 Collectible Egg Skins & Dynamic Themes**: Cosmic, Ruby Dragon, Ender Dragon, Golden Sovereign, Emerald Wyrm, Frost Griffin, Solar Lion, Shadow Kitsune, and Cyber Mecha.
- **Mood Suggestions & Urge Shield**: Instant `Quick`, `Chill`, and `Urge Shield` suggestion cards plus optional toggle in Settings.
- **Custom Activity Lists & History**: Full CRUD list manager with batch multi-line import, custom coin values, and recent decisions log.
- **Floating Curved Navigation & Dual App Icons**: Liquid Glass, Floating M3, and Transparent navbar modes plus switchable Cosmic/Ender Android launcher icons.
