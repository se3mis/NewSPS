/**
 * MobileServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _179._6._235._119.cebwebservice;

public class MobileServiceLocator extends org.apache.axis.client.Service implements _179._6._235._119.cebwebservice.MobileService {

    public MobileServiceLocator() {
    }


    public MobileServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MobileServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MobileServiceSoap
    private java.lang.String MobileServiceSoap_address = "http://cebwebservice.ceb/CEBWebService/MobileService.asmx";

    public java.lang.String getMobileServiceSoapAddress() {
        return MobileServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MobileServiceSoapWSDDServiceName = "MobileServiceSoap";

    public java.lang.String getMobileServiceSoapWSDDServiceName() {
        return MobileServiceSoapWSDDServiceName;
    }

    public void setMobileServiceSoapWSDDServiceName(java.lang.String name) {
        MobileServiceSoapWSDDServiceName = name;
    }

    public _179._6._235._119.cebwebservice.MobileServiceSoap getMobileServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MobileServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMobileServiceSoap(endpoint);
    }

    public _179._6._235._119.cebwebservice.MobileServiceSoap getMobileServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            _179._6._235._119.cebwebservice.MobileServiceSoapStub _stub = new _179._6._235._119.cebwebservice.MobileServiceSoapStub(portAddress, this);
            _stub.setPortName(getMobileServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMobileServiceSoapEndpointAddress(java.lang.String address) {
        MobileServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (_179._6._235._119.cebwebservice.MobileServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                _179._6._235._119.cebwebservice.MobileServiceSoapStub _stub = new _179._6._235._119.cebwebservice.MobileServiceSoapStub(new java.net.URL(MobileServiceSoap_address), this);
                _stub.setPortName(getMobileServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MobileServiceSoap".equals(inputPortName)) {
            return getMobileServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "MobileService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "MobileServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MobileServiceSoap".equals(portName)) {
            setMobileServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
