package com.beaut.media.types;

/**
 * A default / simplified subset of the IANA list of Media-Video's.
 * Note: Some also included that are not listed by IANA for sake
 * of software design.
 * 
 * https://en.wikipedia.org/wiki/Video_file_format
 * 
 * @author Nick
 */
public enum Video implements MediaType {

	AVI("AVI"),
	F4V("F4V"),
	FLV("FLV"),
	H264("H264"),
	H265("H265"),
	M4P("M4P"),
	M4V("M4V"),
	MKV("MKV"),
	MOV("MOV"),
	MP4("MP4"),
	MPEG("MPEG"),
	MPG("MPG"),
	MPV("MPV"),
	OGG("OGG"),
	QT("QT"),
	RAW("RAW"),
	SWF("SWF"),
	VOB("VOB"),
	WEBM("WEBM"),
	WMV("WMV"),
	YUV("YUV");

	private String typeName;

	private Video(String name) {
		this.typeName = name;
	}

	@Override
	public String getTypeName() {
		return this.typeName;
	}

}
