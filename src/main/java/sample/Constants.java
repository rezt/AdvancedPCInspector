package sample;

public class Constants {

    static class general {
        static String[] tabName = {"General","Ogólne"};
        static String[] family = {"Operating system family: ", "Rodzina systemu operacyjnego: "};
        static String[] manufacturer = {"Operating system manufacturer: ", "Producent systemu operacyjnego: "};
        static String[] version = {"Operating system version: ", "Wersja systemu operacyjnego: "};
        static String[] threadCount = {"Number of threads currently running: ", "Liczba aktualnie aktywnych wątków: "};
        static String[] sessions = {"Active users: ", "Aktywni użytkownicy: "};
    }

    static class motherboard {
        static String[] tabName = {"Motherboard","Płyta główna"};
        static String[] manufacturer = {"Motherboard manufacturer: ","Producent płyty głównej: "};
        static String[] model = {"Motherboard model: ","Model płyty głównej: "};
        static String[] serial = {"Motherboard serial number: ","Numer seryjny płyty głównej: "};
        static String[] version = {"Motherboard version: ","Wersja płyty głównej: "};
    }

    static class cpu {
        static String[] tabName = {"CPU","Procesor"};
        static String[] freq = {"CPU maximum frequency: ","Maksymalne taktowanie CPU: "};
        static String[] cpuVendor = {"Vendor: ", "Producent: "};
        static String[] cpuName = {"Processor Name: ", "Nazwa Procesora: "};
        static String[] cpuID = {"Processor ID: ", "ID Procesora: "};
        static String[] cpuArchitekture = {"Microarchitecture: ", "Architektura: "};
        static String[] cpuPhysThreads = {"Number of physical CPUs: ", "Liczba rdzeni fizycznych: "};
        static String[] cpuLogThreads = {"Number of logical CPUs: ", "Liczba rdzeni logicznych: "};
        
    }

    static class gpu {
        static String[] tabName = {"GPU","Karta graficzna"};
        static String[] name = {"Name: ","Nazwa: "};
        static String[] id = {"Device ID: ","ID Urządzenia: "};
        static String[] vendor = {"Vendor: ","Sprzedawca: "};
        static String[] version = {"Driver Version: ","Wersja Sterownika: "};
        static String[] vram = {"Video RAM: ","Video RAM: "};
    }
    
    static class ram {
        static String[] manufacturer = {"Manufacturer: ","Producent: "};
        static String[] memoryType = {"Memory type: ","Rodzaj Pamięci: "};
        static String[] bank = {"Bank/slot label: ","Bank Pamięci: "};
        static String[] capacity = {"Capacity: ","Przepustowość: "};
        static String[] freq = {"RAM Clock Speed: ","Taktowanie zegara pamięci RAM: "};
    }

    static class hardDrive {
        static String[] tabName = {"Hard drive","Dysk twardy"};
        static String[] description = {"Description: ","Opis: "};
        static String[] label = {"Label: ","Etykieta: "};
        static String[] mount = {"Mount: ","Partycja: "};
        static String[] name = {"Name: ","Nazwa: "};
        static String[] type = {"Type: ","System Plików: "};
        static String[] free = {"Free Space: ","Wolna przestrzeń: "};
        static String[] total = {"Total Space: ","Całkowita przestrzeń: "};
    }
    
    static class network {
        static String[] tabName = {"Network","Sieć"};
        static String[] name = {"Interface name: ", "Nazwa interfejsu: "};
        static String[] displayName = {"Description: ", "Opis: "};
        static String[] mac = {"MAC address: ", "Adres MAC: "};
        static String[] ipv4 = {"IPV4 address: ", "Adres IPV4: "};
        static String[] ipv6 = {"IPV6 address: ", "Adres IPV6: "};
    }
    
    static class other {
        static String[] tabName = {"Other","Inne"};
        static String[] name = {"Name: ","Nazwa: "};
        static String[] productId = {"Product ID: ","ID produktu: "};
        static String[] serialNumber = {"Serial number: ","Numer seryjny: "};
        static String[] udid = {"Unique device id: ","Unikalne id urządzenia: "};
        static String[] vendor = {"Vendor: ","Producent: "};
        static String[] vendorId = {"Vendor id: ",": "};
    }
    
}
