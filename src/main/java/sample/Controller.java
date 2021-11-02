package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextArea generalTextArea;
    @FXML
    private Tab generalTabName;

    @FXML
    private TextArea motherboardTextArea;
    @FXML
    private Tab motherboardTabName;

    @FXML
    private TextArea cpuTextArea;
    @FXML
    private Tab cpuTabName;

    @FXML
    private TextArea gpuTextArea;
    @FXML
    private Tab gpuTabName;

    @FXML
    private TextArea ramTextArea;
    @FXML
    private Tab ramTabName;

    @FXML
    private TextArea hardDriveTextArea;
    @FXML
    private Tab harddriveTabName;

    @FXML
    private TextArea networkTextArea;
    @FXML
    private Tab networkTabName;

    @FXML
    private TextArea helpTextArea;
    @FXML
    private Tab helpTabName;

    
    int language;

    List<String> Dyski = new ArrayList<>();
    CentralProcessor cpu;
    List<String> kartaGraficzna = new ArrayList<>();
    List<String> RAM = new ArrayList<>();
    List<OSFileStore> osFileStores;
    List<GraphicsCard> cards;
    HardwareAbstractionLayer hardware;
    GlobalMemory globalMemory;
    List<PhysicalMemory> physicalMemories;

    //Create all links to devices 
    public void initialize() {
        language = 0;
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        osFileStores = ((FileSystem) fileSystem).getFileStores();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        cards = hardware.getGraphicsCards();
        globalMemory = hardware.getMemory();
        physicalMemories = globalMemory.getPhysicalMemory();
        cpu = hardware.getProcessor();
        setInfo();
        }



    public void setInfo() {
        setCPUInfo();
        setGPUInfo();
        setMotherboardInfo();
        setGeneralInfo();
        setRamInfo();
        setHardDriveInfo();
        setNetworkInfo();
        setHelpInfo();
    }

    //All functions retriving informations about system and devices:
    public void setCPUInfo() {
        float maxFreq = (float) cpu.getMaxFreq();
        maxFreq = maxFreq / 1000000000;
        System.out.println(language);
        cpuTextArea.setText(Constants.cpuFreqString[language] + Float.toString(maxFreq) + " GHZ");
    }

    public void setGPUInfo() {
        for (GraphicsCard karta : cards) {
            kartaGraficzna.add("Name:" + karta.getName());
            kartaGraficzna.add("Device ID:" + karta.getDeviceId());
            kartaGraficzna.add("Vendor:" + karta.getVendor());
            kartaGraficzna.add("Version:" + karta.getVersionInfo());
            kartaGraficzna.add("Vram:" + karta.getVRam());
            kartaGraficzna.add("--------------------------------------------");
        }
        for (Object g : kartaGraficzna) {
            gpuTextArea.appendText(g + "\n");
        }
    }

    public void setGeneralInfo() {
        generalTextArea.setText("Generalnie Informacje");
    }

    public void setRamInfo() {
        for (PhysicalMemory physicalMemory : physicalMemories) {
            RAM.add("Manufacturer: " + physicalMemory.getManufacturer());
            RAM.add("Memory type: " + physicalMemory.getMemoryType());
            RAM.add("Bank/slot label: " + physicalMemory.getBankLabel());
            RAM.add("Capacity: " + FormatUtil.formatBytes(physicalMemory.getCapacity()));
            RAM.add("Clock speed: " + FormatUtil.formatHertz(physicalMemory.getClockSpeed()));
            RAM.add("--------------------------------------------");
        }
        for (Object h : RAM) {
            ramTextArea.appendText(h + "\n");
        }
    }

    public void setMotherboardInfo() {
        motherboardTextArea.setText("Hello World!");
    }

    public void setHardDriveInfo() {
        for (OSFileStore fileStore : osFileStores) {
            Dyski.add("Opis: " + fileStore.getDescription() + "\n");
            Dyski.add("Label: " + fileStore.getLabel() + "\n");
            Dyski.add("Partycja: " + fileStore.getMount() + "\n");
            Dyski.add("Nazwa: " + fileStore.getName() + "\n");
            Dyski.add("System Plików: " + fileStore.getType() + "\n");
            // Dyski.add("UUID: " + fileStore.getUUID() + "\n");
            // Dyski.add("Volume: " + fileStore.getVolume() + "\n");
            Dyski.add("Wolna przestrzeń: " + FormatUtil.formatBytes(fileStore.getFreeSpace()) + "\n");
            Dyski.add("Całkowita przestrzeń: " + FormatUtil.formatBytes(fileStore.getTotalSpace()) + "\n");
            //Dyski.add("Użytkowa przestrzeń: " + FormatUtil.formatBytes(fileStore.getUsableSpace()) + "\n");
            Dyski.add("--------------------------------------------");
        }
        for (Object o : Dyski) {
            hardDriveTextArea.appendText(o + "\n");
        }
    }

    public void setNetworkInfo() {
        networkTextArea.setText("Inne Informacje o SIECI");
    }

    public void setHelpInfo() {
        helpTextArea.setText("Pomoc");
    }

    public void setToEnglish() {
        language = 0;
        System.out.println(language);
        setInfo();
    }

    public void setToPolish() {
        language = 1;
        System.out.println(language);
        setInfo();
    }
}
