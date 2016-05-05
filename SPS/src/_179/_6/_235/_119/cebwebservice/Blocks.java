/**
 * Blocks.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _179._6._235._119.cebwebservice;

public class Blocks  implements java.io.Serializable {
    private int limit;

    private java.math.BigDecimal rate;

    private int units;

    private java.lang.String strCharge;

    private java.lang.String strLimit;

    private java.math.BigDecimal stdChg;

    private java.math.BigDecimal discnt;

    public Blocks() {
    }

    public Blocks(
           int limit,
           java.math.BigDecimal rate,
           int units,
           java.lang.String strCharge,
           java.lang.String strLimit,
           java.math.BigDecimal stdChg,
           java.math.BigDecimal discnt) {
           this.limit = limit;
           this.rate = rate;
           this.units = units;
           this.strCharge = strCharge;
           this.strLimit = strLimit;
           this.stdChg = stdChg;
           this.discnt = discnt;
    }


    /**
     * Gets the limit value for this Blocks.
     * 
     * @return limit
     */
    public int getLimit() {
        return limit;
    }


    /**
     * Sets the limit value for this Blocks.
     * 
     * @param limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }


    /**
     * Gets the rate value for this Blocks.
     * 
     * @return rate
     */
    public java.math.BigDecimal getRate() {
        return rate;
    }


    /**
     * Sets the rate value for this Blocks.
     * 
     * @param rate
     */
    public void setRate(java.math.BigDecimal rate) {
        this.rate = rate;
    }


    /**
     * Gets the units value for this Blocks.
     * 
     * @return units
     */
    public int getUnits() {
        return units;
    }


    /**
     * Sets the units value for this Blocks.
     * 
     * @param units
     */
    public void setUnits(int units) {
        this.units = units;
    }


    /**
     * Gets the strCharge value for this Blocks.
     * 
     * @return strCharge
     */
    public java.lang.String getStrCharge() {
        return strCharge;
    }


    /**
     * Sets the strCharge value for this Blocks.
     * 
     * @param strCharge
     */
    public void setStrCharge(java.lang.String strCharge) {
        this.strCharge = strCharge;
    }


    /**
     * Gets the strLimit value for this Blocks.
     * 
     * @return strLimit
     */
    public java.lang.String getStrLimit() {
        return strLimit;
    }


    /**
     * Sets the strLimit value for this Blocks.
     * 
     * @param strLimit
     */
    public void setStrLimit(java.lang.String strLimit) {
        this.strLimit = strLimit;
    }


    /**
     * Gets the stdChg value for this Blocks.
     * 
     * @return stdChg
     */
    public java.math.BigDecimal getStdChg() {
        return stdChg;
    }


    /**
     * Sets the stdChg value for this Blocks.
     * 
     * @param stdChg
     */
    public void setStdChg(java.math.BigDecimal stdChg) {
        this.stdChg = stdChg;
    }


    /**
     * Gets the discnt value for this Blocks.
     * 
     * @return discnt
     */
    public java.math.BigDecimal getDiscnt() {
        return discnt;
    }


    /**
     * Sets the discnt value for this Blocks.
     * 
     * @param discnt
     */
    public void setDiscnt(java.math.BigDecimal discnt) {
        this.discnt = discnt;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Blocks)) return false;
        Blocks other = (Blocks) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.limit == other.getLimit() &&
            ((this.rate==null && other.getRate()==null) || 
             (this.rate!=null &&
              this.rate.equals(other.getRate()))) &&
            this.units == other.getUnits() &&
            ((this.strCharge==null && other.getStrCharge()==null) || 
             (this.strCharge!=null &&
              this.strCharge.equals(other.getStrCharge()))) &&
            ((this.strLimit==null && other.getStrLimit()==null) || 
             (this.strLimit!=null &&
              this.strLimit.equals(other.getStrLimit()))) &&
            ((this.stdChg==null && other.getStdChg()==null) || 
             (this.stdChg!=null &&
              this.stdChg.equals(other.getStdChg()))) &&
            ((this.discnt==null && other.getDiscnt()==null) || 
             (this.discnt!=null &&
              this.discnt.equals(other.getDiscnt())));
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
        _hashCode += getLimit();
        if (getRate() != null) {
            _hashCode += getRate().hashCode();
        }
        _hashCode += getUnits();
        if (getStrCharge() != null) {
            _hashCode += getStrCharge().hashCode();
        }
        if (getStrLimit() != null) {
            _hashCode += getStrLimit().hashCode();
        }
        if (getStdChg() != null) {
            _hashCode += getStdChg().hashCode();
        }
        if (getDiscnt() != null) {
            _hashCode += getDiscnt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Blocks.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Blocks"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limit");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Limit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rate");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("units");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Units"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strCharge");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "StrCharge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "StrLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stdChg");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "StdChg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discnt");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Discnt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
