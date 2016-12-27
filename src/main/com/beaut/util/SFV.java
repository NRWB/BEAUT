package com.beaut.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class SFV {
	
	private final Path path;
	//private final boolean verbose;
	
	public SFV(final Path p /*, final boolean b*/) {
		this.path = p;
		//this.verbose = b;
	}

	public void print() {
		if (Files.isDirectory(this.path))
			printDir();
		else
			printFile(this.path);
	}

	private void printDir() {
		List<Path> fileParts = new ArrayList<Path>();
		try {
			fileParts.addAll(Files.list(this.path).collect(Collectors.toCollection(() -> fileParts)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Path p : fileParts) {
			String s = p.getFileName().toString();
			int i = s.lastIndexOf('.');
			String ext = s.substring(i, s.length());
			if (ext.toLowerCase().charAt(1) != 'r') {
				System.out.println(" err - doesn't match: " + ext);
				continue;
			}
			printFile(p);
		}
	}

	private void printFile(final Path p) {
		//System.out.println(Long.toHexString(this.verbose ? getChecksumVerbose(p) : getChecksum(p)));
		StringBuilder sb = new StringBuilder();
		sb.append(Long.toHexString(getChecksum(p)));
		if (sb.length() == 7)
			sb.insert(0, "0");
		System.out.println(p.getFileName().toString() + " " + sb.toString());
	}

	@SuppressWarnings("unused")
	public static long getChecksum(final Path p) {
		try (FileInputStream fis = new FileInputStream(p.toFile());
				BufferedInputStream bis = new BufferedInputStream(fis);
				//final CheckedInputStream cis = new CheckedInputStream(bis, new CRC32())) {
				final CheckedInputStream cis = new CheckedInputStream(bis, new CRC32())) {

			final byte[] buffer = new byte[4096 * 2 * 2];
			int b = -1;
			while ((b = cis.read(buffer)) != -1) {
				// ..
			}
			return cis.getChecksum().getValue();

		} catch (IOException e) {
			System.err.println("getChecksum() error: " + p + e.getMessage());
			e.printStackTrace();
			return 0L;
		}
	}

	// doesn't make sense on the same output stream / thread...
	/*
	public static long getChecksumVerbose(final Path p) {
		try (FileInputStream fis = new FileInputStream(p.toFile());
				BufferedInputStream bis = new BufferedInputStream(fis);
				final CheckedInputStream cis = new CheckedInputStream(bis, new CRC32())) {

			final byte[] buffer = new byte[4096];
			int b = -1;
			final long ttl = Files.size(p);
			if (ttl == 0)
				throw new ArithmeticException("divide by zero");
			long curr = 0L;
			long percentage = 0;
			long tmp = 0;
			System.out.println(percentage);
			while ((b = cis.read(buffer)) != -1) {
				if (tmp > percentage) {
					percentage = tmp;
					System.out.println(percentage);
				}
				curr += b;
				tmp = (curr * 100) / ttl;
			}
			
			if (curr == ttl)
				System.out.println(100);

			return cis.getChecksum().getValue();

		} catch (IOException e) {
			System.err.println("getChecksum() error: " + p + e.getMessage());
			e.printStackTrace();
			return 0L;
		}
	}
	*/
}
