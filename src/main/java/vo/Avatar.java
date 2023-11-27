package vo; 

public class Avatar {
	private String id;
	private String alt;
	private String imageUrl;
	
	public Avatar() {
		super();
	}

	public Avatar(String id, String alt, String imageUrl) {
		super();
		this.id = id;
		this.alt = alt;
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
	

}
