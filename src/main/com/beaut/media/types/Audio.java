package com.beaut.media.types;

/**
 * A default / simplified subset of the IANA list of Media-Audio's.
 * Note: Some also included that are not listed by IANA for sake
 * of software design.
 * 
 * https://en.wikipedia.org/wiki/Audio_file_format
 * 
 * @author Nick
 */
public enum Audio implements MediaType {

	AA("AA"),
	AAC("AAC"),
	AC3("AC3"),
	AIFF("AIFF"),
	AMR("AMR"),
	ASC("ASC"),
	AU("AU"),
	BASIC("BASIC"),
	DLS("DLS"),
	FLAC("FLAC"),
	M4A("M4A"),
	MP3("MP3"),
	MP4("MP4"),
	MP4A("MP4A"),
	MPA("MPA"),
	OPUS("OPUS"),
	RA("RA"),
	RAW("RAW"),
	RM("RM"),
	VBK("VBK"),
	VORBIS("VORBIS"),
	WAV("WAV"),
	WMA("WMA");

	private String typeName;

	private Audio(String name) {
		this.typeName = name;
	}

	@Override
	public String getTypeName() {
		return this.typeName;
	}

}
