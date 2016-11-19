package com.beaut.cli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.beaut.mktor.MkTor;

public class CmdParser {

	private String[] args;

	private Map<String, String> values;

	public CmdParser(String[] argv) {
		this.args = argv;
		this.values = new HashMap<String, String>();
		parse();
	}

	private void parse() {
		// create the command line parser
		CommandLineParser parser = new DefaultParser();

		// create the Options
		Options options = new Options();

		Option optAnnounce = Option.builder("a")
				.longOpt("announce")
				.required(false)
				.hasArg(true)
				.type(String.class)
				.desc("the url to annouce for torrent")
				.build();
		options.addOption(optAnnounce);

		Option optAnnouncePrivate = Option.builder("p")
				.longOpt("private")
				.required(false)
				.hasArg(true)
				.type(Boolean.class)
				.desc("determine if the torrent to be marked as private")
				.build();
		options.addOption(optAnnouncePrivate);

		Option optTargetFile = Option.builder("t")
				.longOpt("target")
				.required(false)
				.hasArg(true)
				.desc("the target file or folder to use as source for creating the .torrent file from")
				.build();
		options.addOption(optTargetFile);

		Option optDestination = Option.builder("d")
				.longOpt("destination")
				.required(false)
				.hasArg(true)
				.desc("the destination folder for moving media/.torrent files to")
				.build();
		options.addOption(optDestination);

		Option optMonitor = Option.builder("m")
				.longOpt("monitor")
				.required(false)
				.hasArg(true)
				.desc("the folder to monitor/watch for media and create torrent files during runtime")
				.build();
		options.addOption(optMonitor);

		Option optMove = Option.builder("M")
				.longOpt("move")
				.required(false)
				.hasArg(true)
				.desc("the destination folder to move monitored/watched media to along with the created .torrent file (respectively)")
				.build();
		options.addOption(optMove);

		Option optKeepMedia = Option.builder("k")
				.longOpt("keep-media")
				.required(false)
				.hasArg(true)
				.desc("keep media in montior/watched folder (doesn't move to a destination folder) - enabled by default")
				.build();
		options.addOption(optKeepMedia);

		Option optKeepTorrent = Option.builder("K")
				.longOpt("keep-torrent")
				.required(false)
				.hasArg(true)
				.desc("keep the .torrent file in the monitored/watched folder (doesn't move to a destination folder - enabled by default")
				.build();
		options.addOption(optKeepTorrent);

		Option optSize = Option.builder("s")
				.longOpt("size")
				.required(false)
				.hasArg(true)
				.type(String.class)
				.desc("specify a constant block size (in KiB/MiB) to use - by default block size is variablistic depending on input media size (other most common sizes used are: 32/64/128/256/512 KiB or 1/2/4/8/16 MiB)")
				.build();
		options.addOption(optSize);

		Option optUsername = Option.builder("U")
				.longOpt("username")
				.required(false)
				.hasArg(true)
				.type(String.class)
				.desc("specific username if required for use in a private tracker")
				.build();
		options.addOption(optUsername);

		Option optPassword = Option.builder("P")
				.longOpt("password")
				.required(false)
				.hasArg(true)
				.type(String.class)
				.desc("specific password if required for use in a private tracker")
				.build();
		options.addOption(optPassword);

		Option optFormat = Option.builder("f")
				.longOpt("format")
				.required(false)
				.hasArg(true)
				.type(String.class)
				.desc("format processed media/torrents with a given format (renames both files, doesn't actually move anything/files)")
				.build();
		options.addOption(optFormat);

		Option optUpload = Option.builder("u")
				.longOpt("upload")
				.required(false)
				.hasArg(true)
				.desc("upload processed media/torrents to a given tracker (will likely require a username/password of some sort in order to post to the website)")
				.build();
		options.addOption(optUpload);

		Option optInfoAuthor = Option.builder("A")
				.longOpt("author")
				.required(false)
				.hasArg(true)
				.type(String.class)
				.desc("specify an author to this created torrent file of media")
				.build();
		options.addOption(optInfoAuthor);

		Option optInfoComment = Option.builder("C")
				.longOpt("comment")
				.required(false)
				.hasArg(true)
				.type(String.class)
				.desc("specify a comment to this created torrent file of media (perhaps a short and descriptive .nfo file)")
				.build();
		options.addOption(optInfoComment);

		Option optHelp = Option.builder("h")
				.longOpt("help")
				.required(false)
				.hasArg(false)
				.desc("display all of the command line input options")
				.build();
		options.addOption(optHelp);

		Option optDepth = Option.builder("D")
				.longOpt("depth-level")
				.required(false)
				.hasArg(true)
				.type(Integer.class)
				.desc("the depth level to search in regards to sub folders (e.g. one sub folder from a specified start folder is one level, etc.), if any, when recursively searching for media content to be used in a torrent")
				.build();
		options.addOption(optDepth);
		
		Option optThreads = Option.builder("T")
				.longOpt("threads-hashing")
				.required(false)
				.hasArg(true)
				.type(Integer.class)
				.desc("set the number of threads to use when hashing, default is detected by java runtime with available processors")
				.build();
		options.addOption(optThreads);

		//args = new String[] { "-a", "10", "-p", "false", "-t", "~/bittorrent/" };

		// should spit out, "ERROR: no valid options given"
		// args = new String[] { "h" };

		// should spit out, "ERROR: no options given (zero read)"
		// args = new String[] { "-h" };

		// should spit out, "ERROR: no options given (zero read)"
		// args = new String[] { };

		// should spit out, "ERROR: Unexpected exception:Unrecognized option:
		// --NotAValidOptionTest"
		// args = new String[] { "--NotAValidOptionTest" };

		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			// int len = line.getArgList().size();
			int len = line.getOptions().length;
			// System.out.println(" - arg list size given = " + len);
			// System.out.println();

			// check to see if at least has one valid option to use...
			if (len > 0) {
				boolean hasValidOpt = false;
				Iterator<Option> lnIter = line.iterator();
				while (lnIter.hasNext()) {
					Option opt = lnIter.next();
					// System.out.println("opt: " + opt.toString());
					// System.out.println(options.hasOption(opt.getOpt()));
					if (options.hasOption(opt.getOpt())) {
						hasValidOpt = true;
						// System.out.println("has at least one valid option");
						break;
					}
				}
				if (!hasValidOpt) {
					printUsage("no valid options given", options);
					return; // System.exit(-1);
				}
			} else if (len == 0) {
				printUsage("no options given (zero read)", options);
				return; // System.exit(-1);
			}

			if (line.hasOption('h') || line.hasOption("help")) {
				printUsage("Displaying the -h / --help command output: ", options);
				return; // System.exit(-1);
			} else {
				// System.out.println("Starting up " + CLASS_NAME);
			}

			Collection<Option> opts = options.getOptions();
			List<String> optsShortVals = new ArrayList<String>();
			for (Option opt : opts)
				optsShortVals.add(opt.getOpt());
			//System.out.println(optsShortVals);
			
			//Iterator<Option> iter = line.iterator();
			//while (iter.hasNext()) {System.out.println(iter.next().getValue());}

			for (String s : optsShortVals)
				if (line.hasOption(s)) {
					this.values.put(s, line.getOptionValue(s));
					//System.out.println(" : " + line.getOptionValue(s));
				}

		} catch (ParseException exp) {
			printUsage("Unexpected exception:" + exp.getMessage(), options);
			return; // System.exit(-1);
		}
	}
	
	private static void printUsage(String msg, Options opts) {
		System.err.println("ERROR: " + msg + System.lineSeparator());
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(MkTor.CLASS_NAME, opts, true);
	}

	public Map<String, String> getProperties() {
		return this.values;
	}

}
