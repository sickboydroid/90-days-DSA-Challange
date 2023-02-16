# Audacity

## TIPS

- Keep first few seconds empty
  - This will be used to remove bg noise
- Record some sample and test it before recording

## MISC

- You can configure output/input device, record in mono and stereo in the action bar
- At top you can monitor your microphone
- Adjust volume at top
- Waves can be cut-off if they are too loud
- Saving and exporting are two different things
- The track is a graph: Loudness V/s Time
  - Louder the sound, taller the graph
- `Project Rate`: Equailvent to frame rate in video.

---

- `view` > `toolbar` > `reset toolbar` to reset everything
- Deleting Tracks: Click cross at the top
- `F1`: Select selection tool
- `File`>`Open`: To Bring external files
- Click twice track to select it

---

- `space`: Start playing at start point (black line)
  - If you hit again, it will pause
  - Hitting space again will start playing from start point (not where you paused)
  - If you want to pause and resume from same position (where playhead is) use `P`

---

- `shift+space`: Loop play. Can be used with selection
  - Use finger icon to extend selection
  - Use shift+click to expand selection
- `highlighting`: Use the track top part to highlight. It will be autoplayed

---

- `shift+home`: Beggining of track
- `shift+end`: End of track

---

- `zoom tool`: Left click to zoom in waves
  - Zoom enough to see samples (eq. to frames of video)
  - Selecting this time will zoom in on selected portion
- `ctrl+1`: zoom in
- `ctrl+3`: zoom out
- `ctrl+F`: Fits track to screen

---

- Controls to the left of every track:
  - `mute`: ...
  - `solo`: Mute everything else except this track
  - You can lower the levels or loudness via a slidebar (-___________+)

---

- `delete`: Delete the selected part or track
- `Time Shft`: This tool lets you in moving track
- `Generate`: You can generate silence at playhead etc. here

---

- `split`: `Edit`>`Clip boundries`>`Split`
  - Shortcut: Ctrl+I

---

- `envelope`: Used to adjust volume levels within track
  - Select a point from where you want to adjust
  - Drag the light gray area up and down to adjust
- Sometimes enevelope tool is not enough the you can use `amplify`

---

- `ctrl-c`: copy selected track
- `ctrl-v`: paste at playhead

---

## Effects

- `amplify`: Increases volume
  - Select the portion you want to amplify
  - `Effect`>`amplify`
  - You need to adjust the slider
  - You can use the audo monitor at top to determine what to enter

---

- `auto duck`: Automatically lower background vol while you are talking
  - Your voice track should be last
  - Select the portion of the music track that you want to auto duck
  - Select the auto duck feature

---

- `fade out`: select the portion and apply

---

- `noise reduction`: Remove the sound of fans etc
  - Select the portion which contains only noise (starting feww secs)
  - `Effects`>`Noise Reduction`>`Get Profile` Now the Audacity knows about noises
  - Go back and select the portion where you want to remove noise
  - Again go to noise reduction and click of to remove noise

---

- `compressor`: even out the sound waves

---

## Export

- `mp3`: Compressed and small file size
- `wave`: Uncompressed and large file size

## Sounding Nice

- Remove background noise using `noise reduction`
- Select track
  - Goto filter curve effect > manage > factory preset > Bass boost
- Again goto factory preset > Treble boost
- Again goto factory preset > Low rolloff for speech
- Then apply `compressor` effect
- Then effect > limiter > type > hard limit
