/**
 * LEVEL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _179._6._235._119.cebwebservice;

public class LEVEL  implements java.io.Serializable {
    private int TOTAL_COUNT;

    private int TOTAL_SALARY;

    private java.lang.String HIE_NAME;

    private _179._6._235._119.cebwebservice.DESIGNATION[] DESIGNATIONS;

    private _179._6._235._119.cebwebservice.LEVEL[] SUB_LEVELS;

    private java.lang.String DSG;

    public LEVEL() {
    }

    public LEVEL(
           int TOTAL_COUNT,
           int TOTAL_SALARY,
           java.lang.String HIE_NAME,
           _179._6._235._119.cebwebservice.DESIGNATION[] DESIGNATIONS,
           _179._6._235._119.cebwebservice.LEVEL[] SUB_LEVELS,
           java.lang.String DSG) {
           this.TOTAL_COUNT = TOTAL_COUNT;
           this.TOTAL_SALARY = TOTAL_SALARY;
           this.HIE_NAME = HIE_NAME;
           this.DESIGNATIONS = DESIGNATIONS;
           this.SUB_LEVELS = SUB_LEVELS;
           this.DSG = DSG;
    }


    /**
     * Gets the TOTAL_COUNT value for this LEVEL.
     * 
     * @return TOTAL_COUNT
     */
    public int getTOTAL_COUNT() {
        return TOTAL_COUNT;
    }


    /**
     * Sets the TOTAL_COUNT value for this LEVEL.
     * 
     * @param TOTAL_COUNT
     */
    public void setTOTAL_COUNT(int TOTAL_COUNT) {
        this.TOTAL_COUNT = TOTAL_COUNT;
    }


    /**
     * Gets the TOTAL_SALARY value for this LEVEL.
     * 
     * @return TOTAL_SALARY
     */
    public int getTOTAL_SALARY() {
        return TOTAL_SALARY;
    }


    /**
     * Sets the TOTAL_SALARY value for this LEVEL.
     * 
     * @param TOTAL_SALARY
     */
    public void setTOTAL_SALARY(int TOTAL_SALARY) {
        this.TOTAL_SALARY = TOTAL_SALARY;
    }


    /**
     * Gets the HIE_NAME value for this LEVEL.
     * 
     * @return HIE_NAME
     */
    public java.lang.String getHIE_NAME() {
        return HIE_NAME;
    }


    /**
     * Sets the HIE_NAME value for this LEVEL.
     * 
     * @param HIE_NAME
     */
    public void setHIE_NAME(java.lang.String HIE_NAME) {
        this.HIE_NAME = HIE_NAME;
    }


    /**
     * Gets the DESIGNATIONS value for this LEVEL.
     * 
     * @return DESIGNATIONS
     */
    public _179._6._235._119.cebwebservice.DESIGNATION[] getDESIGNATIONS() {
        return DESIGNATIONS;
    }


    /**
     * Sets the DESIGNATIONS value for this LEVEL.
     * 
     * @param DESIGNATIONS
     */
    public void setDESIGNATIONS(_179._6._235._119.cebwebservice.DESIGNATION[] DESIGNATIONS) {
        this.DESIGNATIONS = DESIGNATIONS;
    }


    /**
     * Gets the SUB_LEVELS value for this LEVEL.
     * 
     * @return SUB_LEVELS
     */
    public _179._6._235._119.cebwebservice.LEVEL[] getSUB_LEVELS() {
        return SUB_LEVELS;
    }


    /**
     * Sets the SUB_LEVELS value for this LEVEL.
     * 
     * @param SUB_LEVELS
     */
    public void setSUB_LEVELS(_179._6._235._119.cebwebservice.LEVEL[] SUB_LEVELS) {
        this.SUB_LEVELS = SUB_LEVELS;
    }


    /**
     * Gets the DSG value for this LEVEL.
     * 
     * @return DSG
     */
    public java.lang.String getDSG() {
        return DSG;
    }


    /**
     * Sets the DSG value for this LEVEL.
     * 
     * @param DSG
     */
    public void setDSG(java.lang.String DSG) {
        this.DSG = DSG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LEVEL)) return false;
        LEVEL other = (LEVEL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.TOTAL_COUNT == other.getTOTAL_COUNT() &&
            this.TOTAL_SALARY == other.getTOTAL_SALARY() &&
            ((this.HIE_NAME==null && other.getHIE_NAME()==null) || 
             (this.HIE_NAME!=null &&
              this.HIE_NAME.equals(other.getHIE_NAME()))) &&
            ((this.DESIGNATIONS==null && other.getDESIGNATIONS()==null) || 
             (this.DESIGNATIONS!=null &&
              java.util.Arrays.equals(this.DESIGNATIONS, other.getDESIGNATIONS()))) &&
            ((this.SUB_LEVELS==null && other.getSUB_LEVELS()==null) || 
             (this.SUB_LEVELS!=null &&
              java.util.Arrays.equals(this.SUB_LEVELS, other.getSUB_LEVELS()))) &&
            ((this.DSG==null && other.getDSG()==null) || 
             (this.DSG!=null &&
              this.DSG.equals(other.getDSG())));
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
        _hashCode += getTOTAL_COUNT();
        _hashCode += getTOTAL_SALARY();
        if (getHIE_NAME() != null) {
            _hashCode += getHIE_NAME().hashCode();
        }
        if (getDESIGNATIONS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDESIGNATIONS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDESIGNATIONS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSUB_LEVELS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSUB_LEVELS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSUB_LEVELS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDSG() != null) {
            _hashCode += getDSG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LEVEL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "LEVEL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_COUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "TOTAL_COUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_SALARY");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "TOTAL_SALARY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HIE_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "HIE_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESIGNATIONS");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "DESIGNATIONS"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "DESIGNATION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "DESIGNATION"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUB_LEVELS");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "SUB_LEVELS"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "LEVEL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "LEVEL"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DSG");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "DSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
