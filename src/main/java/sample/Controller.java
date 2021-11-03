package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSSession;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.OSVersionInfo;
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
    CentralProcessor cpu;
    HardwareAbstractionLayer hardware;
    GlobalMemory globalMemory;
    OperatingSystem operatingSystem;
    SystemInfo systemInfo;
    FileSystem fileSystem;
    List<String> fileStoreStringList = new ArrayList<>();
    List<String> gpuStringList = new ArrayList<>();
    List<String> ramStringList = new ArrayList<>();
    List<String> osStringList = new ArrayList<>();
    List<OSFileStore> osFileStoreList;
    List<GraphicsCard> gpuList;
    List<PhysicalMemory> physicalMemoryList;

    // Initialize all required "hooks".
    public void initialize() {
        language = 0;
        systemInfo = new SystemInfo();
        operatingSystem = systemInfo.getOperatingSystem();
        fileSystem = operatingSystem.getFileSystem();
        osFileStoreList = ((FileSystem) fileSystem).getFileStores();
        hardware = systemInfo.getHardware();
        gpuList = hardware.getGraphicsCards();
        globalMemory = hardware.getMemory();
        physicalMemoryList = globalMemory.getPhysicalMemory();
        cpu = hardware.getProcessor();
        setInfo();
        }


    // Get all the required information and send it to gui.
    public void setInfo() {
        System.out.println(language);
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

    // General info:
    public void setGeneralInfo() {
        osStringList.clear();
        osStringList.add(Constants.general.FamilyString[language] + operatingSystem.getFamily());
        osStringList.add(Constants.general.ManufacturerString[language] + operatingSystem.getManufacturer());
        osStringList.add(Constants.general.VersionString[language] + operatingSystem.getVersionInfo().getVersion());
        osStringList.add(Constants.general.ThreadCountString[language] + operatingSystem.getThreadCount());
        osStringList.add(Constants.general.sessionsString[language]);
        List<OSSession> sessions = operatingSystem.getSessions();
        List<String> users = new ArrayList<>();
        for (OSSession i : sessions) {
            users.add(i.getUserName());
        }
        for (Object s : users) {
            osStringList.add("- " + s + "\n");
        }
        for (Object o : osStringList) {
            generalTextArea.appendText(o + "\n");
        }
    }

    // Motherboard related info:
    public void setMotherboardInfo() {
        motherboardTextArea.setText("Hello World!");
    }

    // CPU related info:
    public void setCPUInfo() {
        cpuTabName.setText(Constants.cpu.cpuTabNameString[language]);
        float maxFreq = (float) cpu.getMaxFreq();
        maxFreq = maxFreq / 1000000000;
        cpuTextArea.setText(Constants.cpu.cpuFreqString[language] + Float.toString(maxFreq) + " GHZ");
    }

    // GPU related info:
    public void setGPUInfo() {
        gpuStringList.clear();
        for (GraphicsCard gpu : gpuList) {

            float Vram = (float) gpu.getVRam();
            Vram = Vram / 1000000000;

            String result = gpu.getVersionInfo();
            result = result.replaceAll(".+=", "");

            gpuStringList.add(Constants.gpu.gpuNameString[language] + gpu.getName());
            gpuStringList.add(Constants.gpu.gpuIDString[language] + gpu.getDeviceId());
            gpuStringList.add(Constants.gpu.gpuVendorString[language] + gpu.getVendor());
            gpuStringList.add(Constants.gpu.gpuVersionString[language] + result);
            gpuStringList.add(Constants.gpu.gpuVramString[language] + String.format("%.02f", Vram) + " GB");
            gpuStringList.add("--------------------------------------------");

        }
        for (Object g : gpuStringList) {
            gpuTextArea.appendText(g + "\n");
        }
    }

    // RAM related info:
    public void setRamInfo() {
        ramStringList.clear();
        for (PhysicalMemory physicalMemory : physicalMemoryList) {
            ramStringList.add(Constants.ram.ramManufacturerString[language] + physicalMemory.getManufacturer());
            ramStringList.add(Constants.ram.ramMemoryTypeString[language] + physicalMemory.getMemoryType());
            ramStringList.add(Constants.ram.ramBankString[language] + physicalMemory.getBankLabel());
            ramStringList.add(Constants.ram.ramCapacityString[language] + FormatUtil.formatBytes(physicalMemory.getCapacity()));
            ramStringList.add(Constants.ram.ramFreqString[language] + FormatUtil.formatHertz(physicalMemory.getClockSpeed()));
            ramStringList.add("--------------------------------------------");
        }
        for (Object h : ramStringList) {
            ramTextArea.appendText(h + "\n");
        }
    }

    

    //Hard drive related info:
    public void setHardDriveInfo() {
        fileStoreStringList.clear();
        for (OSFileStore fileStore : osFileStoreList) {
            fileStoreStringList.add(Constants.hardDrive.hardDriveDescriptionString[language] + fileStore.getDescription());
            fileStoreStringList.add(Constants.hardDrive.hardDriveLabelString[language] + fileStore.getLabel());
            fileStoreStringList.add(Constants.hardDrive.hardDriveMount[language] + fileStore.getMount());
            fileStoreStringList.add(Constants.hardDrive.hardDriveName[language] + fileStore.getName());
            fileStoreStringList.add(Constants.hardDrive.hardDriveType[language] + fileStore.getType());
            fileStoreStringList.add(Constants.hardDrive.hardDriveFree[language] + FormatUtil.formatBytes(fileStore.getFreeSpace()));
            fileStoreStringList.add(Constants.hardDrive.hardDriveTotal[language] + FormatUtil.formatBytes(fileStore.getTotalSpace()));
            fileStoreStringList.add("--------------------------------------------");
        }
        for (Object o : fileStoreStringList) {
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
        clearAllAreas();
        setInfo();
    }

    public void setToPolish() {
        language = 1;
        clearAllAreas();
        setInfo();
    }

    public void clearAllAreas() {
        generalTextArea.clear();
        motherboardTextArea.clear();
        cpuTextArea.clear();
        ramTextArea.clear();
        gpuTextArea.clear();
        hardDriveTextArea.clear();
        networkTextArea.clear();
        helpTextArea.clear();
    }


}
