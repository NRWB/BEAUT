package com.beaut.media.types;

/**
 * A default / simplified subset of the IANA list of Media-Image's.
 * Note: Some also included that are not listed by IANA for sake
 * of software design.
 * 
 * https://en.wikipedia.org/wiki/Image_file_formats
 * 
 * @author Nick
 */
public enum Image implements MediaType {

	BMP("BMP"),
	EMF("EMF"),
	GIF("GIF"),
	ICON("ICON"),
	IEF("IEF"),
	JP2("JP2"),
	JPEG("JPEG"),
	JPG("JPG"),
	JPM("JPM"),
	JPX("JPX"),
	PNG("PNG"),
	RASTER("RASTER"),
	SVF("SVF"),
	SVG("SVG"),
	TIFF("TIFF"),
	WMF("WMF");

	private String typeName;

	private Image(String name) {
		this.typeName = name;
	}

	@Override
	public String getTypeName() {
		return this.typeName;
	}

}









