package ec.ftt.model;

public class Draw {

	private int id;
	public int artistId;
	public String title;
	public String img;
	public String desc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = Integer.parseInt(artistId);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	
	@Override
	public String toString() {
		return "Draw [id=" + id + ", artistId=" + artistId + ", title=" + title + ", img=" + img + ", desc=" + desc + "]";
	}
}
