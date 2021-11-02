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

    // FXML declarations required for JavaFX:

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

    // Containers for results and other needed variables:
    
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

    // Initialize all required "hooks".
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


    // Get all the required information and send it to gui.
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

    //--------------------------------------------------------------
    // All functions retriving informations about system and devices:
    //--------------------------------------------------------------

    // CPU related info:
    public void setCPUInfo() {
        float maxFreq = (float) cpu.getMaxFreq();
        maxFreq = maxFreq / 1000000000;
        System.out.println(language);
        cpuTextArea.setText(Constants.cpuFreqString[language] + Float.toString(maxFreq) + " GHZ");
    }

    // GPU related info:
    public void setGPUInfo() {
        for (GraphicsCard karta : cards) {

            float Vram = (float) karta.getVRam();
            Vram = Vram / 1000000000;

            String result = karta.getVersionInfo();
            result = result.replaceAll(".+=", "");

            kartaGraficzna.add(Constants.gpuNameString[language] + karta.getName());
            kartaGraficzna.add(Constants.gpuIDString[language] + karta.getDeviceId());
            kartaGraficzna.add(Constants.gpuVendorString[language] + karta.getVendor());
            kartaGraficzna.add(Constants.gpuVersionString[language] + result);
            kartaGraficzna.add(Constants.gpuVramString[language] + String.format("%.02f", Vram) + " GB");
            kartaGraficzna.add("--------------------------------------------");

        }
        for (Object g : kartaGraficzna) {
            gpuTextArea.appendText(g + "\n");
        }
    }

    // General info:
    public void setGeneralInfo() {
        generalTextArea.setText("Generalnie Informacje");
    }

    // RAM related info:
    public void setRamInfo() {
        for (PhysicalMemory physicalMemory : physicalMemories) {
            RAM.add(Constants.ramManufacturerString[language] + physicalMemory.getManufacturer());
            RAM.add(Constants.ramMemoryTypeString[language] + physicalMemory.getMemoryType());
            RAM.add(Constants.ramBankString[language] + physicalMemory.getBankLabel());
            RAM.add(Constants.ramCapacityString[language] + FormatUtil.formatBytes(physicalMemory.getCapacity()));
            RAM.add(Constants.ramFreqString[language] + FormatUtil.formatHertz(physicalMemory.getClockSpeed()));
            RAM.add("--------------------------------------------");
        }
        for (Object h : RAM) {
            ramTextArea.appendText(h + "\n");
        }
    }

    // Motherboard related info:
    public void setMotherboardInfo() {
        motherboardTextArea.setText("Hello World!");
    }

    //Hard drive related info:
    public void setHardDriveInfo() {
        for (OSFileStore fileStore : osFileStores) {
            Dyski.add(Constants.hardDriveDescriptionString[language] + fileStore.getDescription());
            Dyski.add(Constants.hardDriveLabelString[language] + fileStore.getLabel());
            Dyski.add(Constants.hardDriveMount[language] + fileStore.getMount());
            Dyski.add(Constants.hardDriveName[language] + fileStore.getName());
            Dyski.add(Constants.hardDriveType[language] + fileStore.getType());
            Dyski.add(Constants.hardDriveFree[language] + FormatUtil.formatBytes(fileStore.getFreeSpace()));
            Dyski.add(Constants.hardDriveTotal[language] + FormatUtil.formatBytes(fileStore.getTotalSpace()));
            Dyski.add("--------------------------------------------");
        }
        for (Object o : Dyski) {
            hardDriveTextArea.appendText(o + "\n");
        }
    }

    // Network related info:
    public void setNetworkInfo() {
        networkTextArea.setText("Inne Informacje o SIECI");
    }

    // Help tab.
    public void setHelpInfo() {
        helpTextArea.setText("Pomoc");
    }

    //---------------------
    // Language selectors:
    //---------------------

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
