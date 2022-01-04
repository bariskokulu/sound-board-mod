package bariss26.soundboard;

public class SoundToPlay {
	
	public int key;
	public String name;
	public float volume = 1f;
	public float pitch = 1f;
	
	public SoundToPlay(int key, String name) {
		this.key = key;
		this.name = name;
	}
	
}
