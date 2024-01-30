import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Instrument {
    private String name;
    private double price;
    private boolean available;

    public Instrument(String instrumentName, double instrumentPrice) {
        this.name = instrumentName;
        this.price = instrumentPrice;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markAsSold() {
        available = false;
    }
}

class Musician {
    private String name;
    private List<Instrument> instruments;

    public Musician(String musicianName) {
        this.name = musicianName;
        this.instruments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void associateInstrument(Instrument instrument) {
        instruments.add(instrument);
        System.out.println("Musician " + name + " associated with instrument: " + instrument.getName());
    }
}

class Inventory {
    private List<Instrument> instruments;
    private List<Musician> musicians;

    public Inventory() {
        this.instruments = new ArrayList<>();
        this.musicians = new ArrayList<>();
    }

    public void addInstrument(String name, double price) {
        instruments.add(new Instrument(name, price));
        System.out.println("Instrument added to inventory: " + name);
    }

    public void addMusician(String name) {
        musicians.add(new Musician(name));
        System.out.println("Musician added to store: " + name);
    }

    public void sellInstrument(Musician musician, Instrument instrument) {
        if (instrument != null && instrument.isAvailable()) {
            musician.associateInstrument(instrument);
            instrument.markAsSold();
            System.out.println("Instrument sold to musician " + musician.getName() + ": " + instrument.getName());
        } else {
            System.out.println("Instrument not available for sale or not found.");
        }
    }

    public Instrument findAvailableInstrument(String instrumentName) {
        Optional<Instrument> optionalInstrument = instruments.stream()
                .filter(instrument -> instrument.getName().equals(instrumentName) && instrument.isAvailable())
                .findFirst();

        return optionalInstrument.orElse(null);
    }

    public Musician findMusician(String musicianName) {
        Optional<Musician> optionalMusician = musicians.stream()
                .filter(musician -> musician.getName().equals(musicianName))
                .findFirst();

        if (optionalMusician.isPresent()) {
            return optionalMusician.get();
        } else {
            Musician newMusician = new Musician(musicianName);
            musicians.add(newMusician);
            return newMusician;
        }
    }
}

public class Prac83_MusicStoreInventory {
    public static void main(String[] args) {
        Inventory musicStore = new Inventory();

        musicStore.addInstrument("Guitar", 500.0);
        musicStore.addInstrument("Piano", 1500.0);
        musicStore.addInstrument("Drums", 800.0);

        musicStore.addMusician("John");
        musicStore.addMusician("Alice");

        Musician john = musicStore.findMusician("John");
        Musician alice = musicStore.findMusician("Alice");

        musicStore.sellInstrument(john, musicStore.findAvailableInstrument("Guitar"));
        musicStore.sellInstrument(alice, musicStore.findAvailableInstrument("Piano"));
    }
}
