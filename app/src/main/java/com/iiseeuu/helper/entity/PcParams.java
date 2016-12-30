package com.iiseeuu.helper.entity;

/**
 * Author: 30453
 * Date: 2016/12/26 16:06
 */
public class PcParams {

    private String mac_address;
    private String print_type;
    private String client_software_version;
    private String client_system;
    private String client_memory;
    private String client_cpu;
    private String client_install_path;

    public String getMacAddress() {
        return mac_address;
    }

    public String getPrintType() {
        return print_type;
    }

    public String getClientSoftwareVersion() {
        return client_software_version;
    }

    public String getClientSystem() {
        return client_system;
    }

    public String getClientMemory() {
        return client_memory;
    }

    public String getClientCpu() {
        return client_cpu;
    }

    public String getClientInstallPath() {
        return client_install_path;
    }
}
