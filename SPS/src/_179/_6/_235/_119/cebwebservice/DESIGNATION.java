/**
 * DESIGNATION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _179._6._235._119.cebwebservice;

public class DESIGNATION  implements java.io.Serializable {
    private int COUNT;

    private java.lang.String NAME;

    private int TOTAL_SALARY;

    public DESIGNATION() {
    }

    public DESIGNATION(
           int COUNT,
           java.lang.String NAME,
           int TOTAL_SALARY) {
           this.COUNT = COUNT;
           this.NAME = NAME;
           this.TOTAL_SALARY = TOTAL_SALARY;
    }


    /**
     * Gets the COUNT value for this DESIGNATION.
     * 
     * @return COUNT
     */
    public int getCOUNT() {
        return COUNT;
    }


    /**
     * Sets the COUNT value for this DESIGNATION.
     * 
     * @param COUNT
     */
    public void setCOUNT(int COUNT) {
        this.COUNT = COUNT;
    }


    /**
     * Gets the NAME value for this DESIGNATION.
     * 
     * @return NAME
     */
    public java.lang.String getNAME() {
        return NAME;
    }


    /**
     * Sets the NAME value for this DESIGNATION.
     * 
     * @param NAME
     */
    public void setNAME(java.lang.String NAME) {
        this.NAME = NAME;
    }


    /**
     * Gets the TOTAL_SALARY value for this DESIGNATION.
     * 
     * @return TOTAL_SALARY
     */
    public int getTOTAL_SALARY() {
        return TOTAL_SALARY;
    }


    /**
     * Sets the TOTAL_SALARY value for this DESIGNATION.
     * 
     * @param TOTAL_SALARY
     */
    public void setTOTAL_SALARY(int TOTAL_SALARY) {
        this.TOTAL_SALARY = TOTAL_SALARY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DESIGNATION)) return false;
        DESIGNATION other = (DESIGNATION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.COUNT == other.getCOUNT() &&
            ((this.NAME==null && other.getNAME()==null) || 
             (this.NAME!=null &&
              this.NAME.equals(other.getNAME()))) &&
            this.TOTAL_SALARY == other.getTOTAL_SALARY();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getCOUNT();
        if (getNAME() != null) {
            _hashCode += getNAME().hashCode();
        }
        _hashCode += getTOTAL_SALARY();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DESIGNATION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "DESIGNATION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "COUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_SALARY");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "TOTAL_SALARY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
