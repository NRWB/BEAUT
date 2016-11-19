# BEAUT
(B)ittorrent (E)asy (A)uto (U)pload (T)ool

## About
BEAUT is defined to generically weave in and out of tracker niche's in order to supplement ease with uploading of files. With the simple and powerful design, BEAUT makes `.torrent` files easily and can upload said content autonomously to supported web trackers (given a supported template exists).

## What is BitTorrent?
Read the [introduction](http://www.bittorrent.org/introduction.html). Also, the [proposals/specification(s)](http://www.bittorrent.org/beps/bep_0000.html). Followed by more helpful knowledge at some [wiki specification(s)](https://wiki.theory.org/BitTorrentSpecification).

## How BEAUT works
See the `workflow` directory for visual representation. To summarize, BEAUT monitors a given directory, e.g. `target` and as BEAUT recognizes content, BEAUT proceeds to make `.torrent` files for said respective content. The results (one content and one `.torrent` file) are moved to a given destination, e.g. `output`, directory. There's also a

## Features
There are many features available for use with BEAUT

## Requirements
- Internet Access
- Java 1.7+
- Apache Commons CLI
- JSoup
- Selenium (?)

## Install / Setup
See the `INSTALL` file for more information about how to setup and/or install [BEAUT](https://github.com/NRWB/BEAUT).

## FAQ
- Q: How do I edit/view a `.torrent` file to see what information is in there?
- A: A `.torrent` file usually contains general metadata. Try the [bencode-editor](https://sites.google.com/site/ultimasites/bencode-editor), transmission-show, etc.

- Q: What is in the info hash of a '.torrent' file?
- A: See [this](http://stackoverflow.com/questions/28348678/what-exactly-is-the-info-hash-in-a-torrent-file) stackoverflow post.

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

## References
- the mldht project - https://github.com/the8472/mldht
- azureus - https://sourceforge.net/projects/azureus/

### softwares
- http://www.benf.org/other/cfr/
- http://jd.benow.ca/
- https://ssebuild.cased.de/nightly/soot/doc/soot_options.htm#section_1
