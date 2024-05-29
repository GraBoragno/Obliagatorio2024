public class Cancion {
    String url;
    String titulo;
    String artist;
    int daily_rank;
    int daily_movement;
    int weekly_movement;
    String country;
    String snapshot_date;
    int popularity;
    boolean is_explicit;
    int duration_ms;
    String album_name;
    String album_release_date;
    float danceability;
    float energy;
    int key;
    float loudness;
    int mode;
    float speechiness;
    float acusticness;
    float instrumentalness;
    float liveness;
    float valence;
    float tempo;
    int time_signature;

    public Cancion() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String artista) {
        this.titulo = artista;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDaily_rank() {
        return daily_rank;
    } //int

    public void setDaily_rank(int daily_rank) {
        this.daily_rank = daily_rank;
    } //int

    public int getDaily_movement() {
        return daily_movement;
    }

    public void setDaily_movement(int daily_movement) {
        this.daily_movement = daily_movement;
    }

    public int getWeekly_movement() {
        return weekly_movement;
    }

    public void setWeekly_movement(int weekly_movement) {
        this.weekly_movement = weekly_movement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSnapshot_date() {
        return snapshot_date;
    }

    public void setSnapshot_date(String snapshot_date) {
        this.snapshot_date = snapshot_date;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isIs_explicit() {
        return is_explicit;
    }

    public void setIs_explicit(boolean is_explicit) {
        this.is_explicit = is_explicit;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_release_date() {
        return album_release_date;
    }

    public void setAlbum_release_date(String album_release_date) {
        this.album_release_date = album_release_date;
    }

    public float getDanceability() {
        return danceability;
    }

    public void setDanceability(float danceability) {
        this.danceability = danceability;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public float getLoudness() {
        return loudness;
    }

    public void setLoudness(float loudness) {
        this.loudness = loudness;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(float speechiness) {
        this.speechiness = speechiness;
    }

    public float getAcusticmess() {
        return acusticness;
    }

    public void setAcusticmess(float acusticmess) {
        this.acusticness = acusticmess;
    }

    public float getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(float instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public float getLiveness() {
        return liveness;
    }

    public void setLiveness(float liveness) {
        this.liveness = liveness;
    }

    public float getValence() {
        return valence;
    }

    public void setValence(float valence) {
        this.valence = valence;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public int getTime_signature() {
        return time_signature;
    }

    public void setTime_signature(int time_signature) {
        this.time_signature = time_signature;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "url='" + url + '\'' +
                ", titulo='" + titulo + '\'' +
                ", artist='" + artist + '\'' +
                ", daily_rank=" + daily_rank +
                ", daily_movement=" + daily_movement +
                ", weekly_movement=" + weekly_movement +
                ", country='" + country + '\'' +
                ", snapshot_date='" + snapshot_date + '\'' +
                ", popularity=" + popularity +
                ", is_explicit=" + is_explicit +
                ", duration_ms=" + duration_ms +
                ", album_name='" + album_name + '\'' +
                ", album_release_date='" + album_release_date + '\'' +
                ", danceability=" + danceability +
                ", energy=" + energy +
                ", key=" + key +
                ", loudness=" + loudness +
                ", mode=" + mode +
                ", speechiness=" + speechiness +
                ", acusticness=" + acusticness +
                ", instrumentalness=" + instrumentalness +
                ", liveness=" + liveness +
                ", valence=" + valence +
                ", tempo=" + tempo +
                ", time_signature=" + time_signature +
                '}';
    }
}
