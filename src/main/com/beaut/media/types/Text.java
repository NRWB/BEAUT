package com.beaut.media.types;

/**
 * A default / simplified subset of the IANA list of Media-Text's.
 * Note: Some also included that are not listed by IANA for sake
 * of software design.
 * 
 * https://en.wikipedia.org/wiki/Text_file
 * 
 * @author Nick
 */
public enum Text implements MediaType {

	CSS("CSS"),
	CSV("CSV"),
	HTML("HTML"),
	INI("INI"),
	MARKDOWN("MARKDOWN"),
	MD("MD"),
	NFO("NFO"),
	PLAIN("PLAIN"),
	RTF("RTF"),
	TSV("TSV"),
	TXT("TXT"),
	XML("XML");

	private String typeName;

	private Text(String name) {
		this.typeName = name;
	}

	@Override
	public String getTypeName() {
		return this.typeName;
	}

}
