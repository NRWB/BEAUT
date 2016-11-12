# BEAUT
Bittorrent Easy Auto Upload Tool

## About
BEAUT is defined to generically weave in and out of tracker niche's in order to supplement ease with uploading of files.

## Requirements
Internet Access
Java 1.7 installed on host operating system running BEAUT

## FAQ
| #             | Q               | A     |
|:------------- |:---------------:| -----:|
| 1             | How do I setup? | Read the Install/Setup |
| 2             |                 |   TBD |
| 3             |                 |   TBD |

## Install / Setup
(windows) BEAUT.bat
(linux) BEAUT.sh
(osx) -TBD-

TBD

## How it [will] work(s)
You specify a directory (say `dirA`) to monitor. When a new file appears in this monitored directory (again, `dirA`) a .torrent file is created/generated automatically and placed into a predefined directory (let's say `dirB`). When `dirB` notices .torrent file it will attempt to upload the file automatically. After attempting to upload the .torrent file (and it's respective torrent), the .torrent file is moved into a directory (how about `dirC`) where the .torrent will remain as archived (will not be used or touched again by BEAUT).

How does that look in the file system? Here's an example:
- Start out with `~/Downloads/bittorrent/` as being empty directory. Let's assume you put media into this folder in order to upload it.
- So now move some media into `~/Downloads/bittorrent/`. Let's say this is the current layout:
```
~/Downloads/bittorrent/
~/Downloads/bittorrent/BigBuckBunny_320x180.mp4
~/Downloads/bittorrent/ubuntu/16.04/ubuntu-16.04.1-server-amd64.iso
```
Note: files found open/freely at, http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4 and https://www.ubuntu.com/download/alternative-downloads

- Notice how one media is single file, `BigBuckBunny_320x180.mp4` and another media `ubuntu-16.04.1-server-amd64.iso` is two directories below from `~/Downloads/bittorrent/`.
- Open the `prefs.conf` file and specify the "dirA" value for the `mediaSrcDir` field. In this example, the line would look like this, `mediaSrcDir=~/Downloads/bittorrent/`.
- Now start up BEAUT with the shell script (or run in/through existing terminal to see output in console).
- BEAUT should recognize the two media's, generate a .torrent file for each, and search for an existing .nfo file (else one is created by default).
