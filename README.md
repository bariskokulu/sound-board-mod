# sound-board-mod
 
 Sound names on client and server **HAVE TO BE THE SAME**.
 Take a look at the example if something below doesn't make sense.
 If something wrong is written below, then follow the example.
 My discord is `Barış - Irenchin#2002` if more help is needed.
 
## On Client:
1. Put your sounds in `config\soundboard\assets\soundboard\sounds`. (ik long file path but mc loads resources this way)
2. Create a `sounds.json` inside `config\soundboard\assets\soundboard`.
   - For every sound, put a line of: `"soundname": {"category": "master", "sounds":[{"name": "filenamewithoutextension", "stream": true}]}`
     - Replace the `soundname` with whatever name you want.
     - Replace the `filenamewithoutextension` with the sound file's name.
     - The `"stream": true` can be either true or false. `true` if this sound should be streamed from its file. It is recommended that this is set to "true" for sounds that have a duration longer than a few seconds to avoid lag. Setting this to false allows many more instances of the sound to be ran at the same time while setting it to true only allows 4 instances (of that type) to be ran at the same time. (From [Minecraft Wiki](https://minecraft.fandom.com/wiki/Sounds.json))
     - Sounds should be seperated with a `,` in between. Look at the example.
   - See [Minecraft Wiki](https://minecraft.fandom.com/wiki/Sounds.json) for more info on how to use the `sounds.json`.
3. Launch the game.
4. Press K to open keybinds gui and set your keys. (Gui key can be changed from options.)
   - When you change a keybind it will automatically save to `config\soundboard\keys.json`.
5. Enjoy.

## On Server:
1. If `config\soundboard\keys.json` doesn't exist, create it.
2. Open the file.
   - The text inside should start with an `[` and end with an `]`.
   - For every sound, put a line of: `{"key":0,"name":"soundname","volume":1.0,"pitch":1.0}`.
     - The key is NOT important for the server. It is just stored in this file so we don't use two seperate files for saving data.
     - Replace the `soundname` with whatever name you want.
     - The volume will affect every player. If you set it too high the sound will be heard from very long distances.
     - The pitch will affect every player too.
     - Sounds should be seperated with a `,` in between. Look at the example.
