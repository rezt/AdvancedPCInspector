package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class Controller {

    @FXML
    private TextArea generalTextArea;

    @FXML
    private TextArea motherboardTextArea;

    @FXML 
    private TextArea cpuTextArea;

    @FXML
    private TextArea gpuTextArea;

    @FXML
    private TextArea ramTextArea;

    @FXML
    private TextArea hardDriveTextArea;

    @FXML
    private TextArea networkTextArea;

    @FXML
    private TextArea helpTextArea;

    CentralProcessor cpu;

    //Create all links to devices 
    public void initialize() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        cpu = hal.getProcessor();
        cpuTextArea.setText("Test");
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
        float maxFreq = (float)cpu.getMaxFreq();
        maxFreq = maxFreq/1000000000;
        cpuTextArea.setText("CPU Clock Speed:  " + Float.toString(maxFreq) + " GHZ");
    }

    public void setGPUInfo() {
        gpuTextArea.setText("karta graficzna super mocna do krypto");
    }

    public void setGeneralInfo()
    {
        generalTextArea.setText("Generalnie Informacje");
    }

    public void setRamInfo()
    {
        ramTextArea.setText("Informacje o ramie");
    }

    public void setMotherboardInfo()
    {
        motherboardTextArea.setText("Hello World!");
    }

    public void setHardDriveInfo()
    {
        hardDriveTextArea.setText("Informacje o dysku twardym");
    }

    public void setNetworkInfo()
    {
        networkTextArea.setText("Inne Informacje o SIECI");
    }

    public void setHelpInfo()
    {
        helpTextArea.setText("Pomoc");
    }







}
