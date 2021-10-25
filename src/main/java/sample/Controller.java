package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class Controller {

    @FXML TextArea CPU_OUTPUT;

    CentralProcessor cpu;

    //Create all links to devices 
    public void init() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        CentralProcessor cpu = hal.getProcessor();
    }

    //All functions retriving informations about system and devices:
    public void getCPUInfo() {
        CPU_OUTPUT.setText(Long.toString(cpu.getMaxFreq()));
    }






}
