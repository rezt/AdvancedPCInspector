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
    private TextArea otherTextArea;
    @FXML
    private Tab otherTabName;

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
    List<String> usbStringList = new ArrayList<>();
    List<UsbDevice> usbDevicesList;
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
        usbDevicesList = hardware.getUsbDevices(false);
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
        setOtherInfo();
    }

    //--------------------------------------------------------------
    // All functions retriving informations about system and devices:
    //--------------------------------------------------------------

    // General info:
    public void setGeneralInfo() {
        generalTabName.setText(Constants.general.tabName[language]);
        osStringList.clear();
        osStringList.add(Constants.general.family[language] + operatingSystem.getFamily());
        osStringList.add(Constants.general.manufacturer[language] + operatingSystem.getManufacturer());
        osStringList.add(Constants.general.version[language] + operatingSystem.getVersionInfo().getVersion());
        osStringList.add(Constants.general.threadCount[language] + operatingSystem.getThreadCount());
        osStringList.add(Constants.general.sessions[language]);
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
        motherboardTabName.setText(Constants.motherboard.tabName[language]);
        motherboardTextArea.setText("Hello World!");
    }

    // CPU related info:
    public void setCPUInfo() {
        cpuTabName.setText(Constants.cpu.tabName[language]);
        float maxFreq = (float) cpu.getMaxFreq();
        maxFreq = maxFreq / 1000000000;
        cpuTextArea.setText(Constants.cpu.freq[language] + Float.toString(maxFreq) + " GHZ");
    }

    // GPU related info:
    public void setGPUInfo() {
        gpuTabName.setText(Constants.gpu.tabName[language]);
        gpuStringList.clear();
        for (GraphicsCard gpu : gpuList) {

            float Vram = (float) gpu.getVRam();
            Vram = Vram / 1000000000;

            String result = gpu.getVersionInfo();
            result = result.replaceAll(".+=", "");

            gpuStringList.add(Constants.gpu.name[language] + gpu.getName());
            gpuStringList.add(Constants.gpu.id[language] + gpu.getDeviceId());
            gpuStringList.add(Constants.gpu.vendor[language] + gpu.getVendor());
            gpuStringList.add(Constants.gpu.version[language] + result);
            gpuStringList.add(Constants.gpu.vram[language] + String.format("%.02f", Vram) + " GB");
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
            ramStringList.add(Constants.ram.manufacturer[language] + physicalMemory.getManufacturer());
            ramStringList.add(Constants.ram.memoryType[language] + physicalMemory.getMemoryType());
            ramStringList.add(Constants.ram.bank[language] + physicalMemory.getBankLabel());
            ramStringList.add(Constants.ram.capacity[language] + FormatUtil.formatBytes(physicalMemory.getCapacity()));
            ramStringList.add(Constants.ram.freq[language] + FormatUtil.formatHertz(physicalMemory.getClockSpeed()));
            ramStringList.add("--------------------------------------------");
        }
        for (Object h : ramStringList) {
            ramTextArea.appendText(h + "\n");
        }
    }

    

    //Hard drive related info:
    public void setHardDriveInfo() {
        harddriveTabName.setText(Constants.hardDrive.tabName[language]);
        fileStoreStringList.clear();
        for (OSFileStore fileStore : osFileStoreList) {
            fileStoreStringList.add(Constants.hardDrive.description[language] + fileStore.getDescription());
            fileStoreStringList.add(Constants.hardDrive.label[language] + fileStore.getLabel());
            fileStoreStringList.add(Constants.hardDrive.mount[language] + fileStore.getMount());
            fileStoreStringList.add(Constants.hardDrive.name[language] + fileStore.getName());
            fileStoreStringList.add(Constants.hardDrive.type[language] + fileStore.getType());
            fileStoreStringList.add(Constants.hardDrive.free[language] + FormatUtil.formatBytes(fileStore.getFreeSpace()));
            fileStoreStringList.add(Constants.hardDrive.total[language] + FormatUtil.formatBytes(fileStore.getTotalSpace()));
            fileStoreStringList.add("--------------------------------------------");
        }
        for (Object o : fileStoreStringList) {
            hardDriveTextArea.appendText(o + "\n");
        }
    }

    // Network related info:
    public void setNetworkInfo() {
        networkTabName.setText(Constants.network.tabName[language]);
        networkTextArea.setText("Inne Informacje o SIECI");
    }

    // Help tab.
    public void setOtherInfo() {
        otherTabName.setText(Constants.other.tabName[language]);
        usbStringList.clear();
        System.out.println(usbDevicesList.size());
        for (UsbDevice usbDevice : usbDevicesList) {
            usbStringList.add(Constants.other.name[language] + usbDevice.getName());
            usbStringList.add(Constants.other.productId[language] + usbDevice.getProductId());
            usbStringList.add(Constants.other.serialNumber[language] + usbDevice.getSerialNumber());
            usbStringList.add(Constants.other.udid[language] + usbDevice.getUniqueDeviceId());
            usbStringList.add(Constants.other.vendor[language] + usbDevice.getVendor());
            usbStringList.add(Constants.other.vendorId[language] + usbDevice.getVendorId());
            usbStringList.add("--------------------------------------------");
        }
        for (Object o : usbStringList) {
            otherTextArea.appendText(o + "\n");
        }
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
        otherTextArea.clear();
    }


}
