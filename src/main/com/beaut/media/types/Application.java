package com.beaut.media.types;

/**
 * A default / simplified subset of the IANA list of Media-Application's.
 * Note: Some also included that are not listed by IANA for sake
 * of software design.
 * 
 * https://en.wikipedia.org/wiki/List_of_file_formats
 * 
 * @author Nick
 */
public enum Application implements MediaType {
	
	BALSAMIQ("BALSAMIQ"),
	BAT("BAT"),
	BATCH("BATCH"),
	BZ2("BZ2"),
	CALENDAR("CALENDAR"),
	CMS("CMS"),
	CURL("CURL"),
	DASH("DASH"),
	DNS("DNS"),
	DOC("DOC"),
	DOCX("DOCX"),
	DPKG("DPKG"),
	EPUB("EPUB"),
	EXE("EXE"),
	GPG("GPG"),
	GZIP("GZIP"),
	ISO("ISO"),
	JS("JS"),
	JSON("JSON"),
	LNK("LNK"),
	PDF("PDF"),
	PGP("PGP"),
	PPT("PPT"),
	PPTX("PPTX"),
	RAR("RAR"),
	SH("SH"),
	SQL("SQL"),
	TGZ("TGZ"),
	ZIP("ZIP");

	private String typeName;

	private Application(String name) {
		this.typeName = name;
	}

	@Override
	public String getTypeName() {
		return this.typeName;
	}

}
