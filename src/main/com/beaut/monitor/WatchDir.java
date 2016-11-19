package com.beaut.monitor;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

/**
 * Example to watch a directory (or tree) for changes to files.
 * https://docs.oracle.com/javase/tutorial/essential/io/examples/WatchDir.java
 */
public class WatchDir {

	private final WatchService watcher;
	private final Map<WatchKey, Path> keys;
	private boolean trace = false;

	// Creates a WatchService and registers the given directory
	public WatchDir(Path dir) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();
		register(dir);
		// enable trace after initial registration
		this.trace = true;
	}

	@SuppressWarnings("unchecked")
	public static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}

	// Register the given directory with the WatchService
	private void register(Path dir) throws IOException {
		WatchKey key = dir.register(this.watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		if (this.trace) {
			Path prev = this.keys.get(key);
			if (prev == null)
				System.out.format("register: %s\n", dir);
			else if ((prev != null) && (!dir.equals(prev)))
				System.out.format("update: %s -> %s\n", prev, dir);
		}
		this.keys.put(key, dir);
	}

	// Process all events for keys queued to the watcher
	public void processEvents() {
		for (;;) {
			// wait for key to be signalled
			WatchKey key;
			try {
				key = this.watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			Path dir = this.keys.get(key);
			if (dir == null) {
				System.err.println("WatchKey not recognized!!");
				continue;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();

				// TBD - provide example of how OVERFLOW event is handled
				if (kind == OVERFLOW)
					continue;

				// Context for directory entry event is the file name of entry
				WatchEvent<Path> ev = cast(event);
				Path name = ev.context();
				Path child = dir.resolve(name);

				if (event.kind().name().equals("ENTRY_CREATE")) {
					/*
					try {
						new CreateTorrent(child).create();
					} catch (IOException e) {
						e.printStackTrace();
					}
					*/
				}

				// print out event
				System.out.format("%s: %s\n", event.kind().name(), child);
			}

			// reset key and remove from set if directory no longer accessible
			boolean valid = key.reset();
			if (!valid) {
				this.keys.remove(key);
				if (this.keys.isEmpty()) // all directories are inaccessible
					break;
			}
		}
	}
}
