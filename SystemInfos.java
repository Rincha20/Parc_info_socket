package systemInfo;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.lang.reflect.Field;

public class SystemInfos implements Serializable{
    String userName;
    String pcName;
    String ipAddress;
    String operatingSys;
    String OSVersion;
    int processorCores;
    String physicalAddress;
    long memorySize;
    long freeMemory;
    long diskTotal;
    long freeDisk;

    public SystemInfos() throws Exception{
        setUserName();
        setPcName();
        setIpAddress();
        setOperatingSys();
        setOSVersion();
        setProcessorCores();
        setPhysicalAddress();
        setMemorySize();
        setFreeMemory();
        setDiskTotal();
        setFreeDisk();
    }

    public Vector<Object> getSysInfoTable() throws Exception{
        Vector<Object> infos = new Vector<Object>();
        infos.add(getUserName());
        infos.add(getPcName());
        infos.add(getIpAddress());
        infos.add(getOperatingSys());
        infos.add(getOSVersion());
        infos.add(getProcessorCores()+"  cores");
        infos.add(getPhysicalAddress());
        infos.add(getMemorySize()+"  bytes");
        infos.add(getFreeMemory()+"  bytes");
        infos.add(getDiskTotal()+"  bytes");
        infos.add(getFreeDisk()+"  bytes");
        return infos;
    }
    
    public Vector<Object> getAttributs(){
        Field [] field = getClass().getDeclaredFields();
        Vector<Object> attr = new Vector<Object>();
        for (int i = 0; i < field.length; i++) {
            attr.add(field[i].getName());
        }
        return attr;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName() {
        this.userName = System.getProperty("user.name");
    }
    public String getPcName() {
        return pcName;
    }
    public void setPcName() throws Exception{
        this.pcName = InetAddress.getLocalHost().getHostName();;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress() throws Exception{
        this.ipAddress = InetAddress.getLocalHost().getHostAddress();
    }
    public String getOperatingSys() {
        return operatingSys;
    }
    public void setOperatingSys() {
        this.operatingSys = System.getProperty("os.name");
    }
    public String getOSVersion() {
        return OSVersion;
    }
    public void setOSVersion() {
        OSVersion = System.getProperty("os.version");
    }
    public int getProcessorCores() {
        return processorCores;
    }
    public void setProcessorCores() {
        this.processorCores = Runtime.getRuntime().availableProcessors();
    }
    public String getPhysicalAddress() {
        return physicalAddress;
    }
    public void setPhysicalAddress() throws SocketException{
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            byte[] hardwareAddress = ni.getHardwareAddress();
            if (hardwareAddress != null) {
                String[] hexadecimalFormat = new String[hardwareAddress.length];
                for (int i = 0; i < hardwareAddress.length; i++) {
                    //%02x means print at least 2 digits, prepend it with 0's if there's less. 
                    //Hexadecimal string value (if want result majuscule: X, else x) 
                    hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
                }
                this.physicalAddress =String.join(":", hexadecimalFormat);
            }
        }
    }
    public long getMemorySize() {
        return memorySize;
    }
    public void setMemorySize() {
        this.memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
        .getOperatingSystemMXBean()).getTotalPhysicalMemorySize();;
    }
    public long getFreeMemory() {
        return freeMemory;
    }
    public void setFreeMemory() {
        this.freeMemory = Runtime.getRuntime().freeMemory();
    }
    public long getDiskTotal() {
        return diskTotal;
    }
    public void setDiskTotal() {
        this.diskTotal = new File("C:/").getTotalSpace();
    }
    public long getFreeDisk() {
        return freeDisk;
    }
    public void setFreeDisk() {
        this.freeDisk = new File("C:/").getFreeSpace();
    }
}
