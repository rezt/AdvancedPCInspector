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
    private TextArea ramTextArea;

    @FXML
    private TextArea hardDrivTextArea;

    @FXML
    private TextArea otherTextArea;

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
        getCPUInfo();
    }

    //All functions retriving informations about system and devices:
    public void getCPUInfo() {
        float maxFreq = (float)cpu.getMaxFreq();
        maxFreq = maxFreq/1000000000;
        cpuTextArea.setText(Float.toString(maxFreq) + " GHZ");
    }






}
