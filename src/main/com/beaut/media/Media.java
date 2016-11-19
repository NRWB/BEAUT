package com.beaut.media;

import java.util.Arrays;

import com.beaut.media.types.Application;
import com.beaut.media.types.Audio;
import com.beaut.media.types.Image;
import com.beaut.media.types.MediaType;
import com.beaut.media.types.Text;
import com.beaut.media.types.Video;

/**
 * Default accepted media as listed by the IANA. For more information see, https://www.iana.org/assignments/media-types/media-types.xhtml
 * 
 * Only 5 are used in this enumeration:
 * 1. Application
 * 2. Audio
 * 3. Image
 * 4. Text
 * 5. Video
 * 
 * @author Nick
 */
public enum Media {

	APPLICATION("APPLICATION", Application.values()),
	AUDIO("AUDIO", Audio.values()),
	IMAGE("IMAGE", Image.values()),
	TEXT("TEXT", Text.values()),
	VIDEO("VIDEO", Video.values());

	private String type;
	private MediaType[] subTypes;

	private Media(String name, MediaType[] mt) {
		this.type = name;
		this.subTypes = mt;
	}

	public String getType() {
		return this.type;
	}

	public MediaType[] getSubTypes() {
		return this.subTypes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Media m : Media.values()) {
			sb.append(m.getType());
			sb.append(": ");
			sb.append(Arrays.toString(m.getSubTypes()));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
