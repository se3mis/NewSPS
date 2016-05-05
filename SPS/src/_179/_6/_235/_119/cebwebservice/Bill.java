/**
 * Bill.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _179._6._235._119.cebwebservice;

public class Bill  implements java.io.Serializable {
    private java.lang.String error;

    private java.lang.String kwhChg;

    private java.lang.String totFxd;

    private java.lang.String fulChg;

    private java.lang.String txtChg;

    private java.lang.String rbtChg;

    private java.lang.String total;

    private _179._6._235._119.cebwebservice.Blocks[] rangeCharges;

    public Bill() {
    }

    public Bill(
           java.lang.String error,
           java.lang.String kwhChg,
           java.lang.String totFxd,
           java.lang.String fulChg,
           java.lang.String txtChg,
           java.lang.String rbtChg,
           java.lang.String total,
           _179._6._235._119.cebwebservice.Blocks[] rangeCharges) {
           this.error = error;
           this.kwhChg = kwhChg;
           this.totFxd = totFxd;
           this.fulChg = fulChg;
           this.txtChg = txtChg;
           this.rbtChg = rbtChg;
           this.total = total;
           this.rangeCharges = rangeCharges;
    }


    /**
     * Gets the error value for this Bill.
     * 
     * @return error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this Bill.
     * 
     * @param error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }


    /**
     * Gets the kwhChg value for this Bill.
     * 
     * @return kwhChg
     */
    public java.lang.String getKwhChg() {
        return kwhChg;
    }


    /**
     * Sets the kwhChg value for this Bill.
     * 
     * @param kwhChg
     */
    public void setKwhChg(java.lang.String kwhChg) {
        this.kwhChg = kwhChg;
    }


    /**
     * Gets the totFxd value for this Bill.
     * 
     * @return totFxd
     */
    public java.lang.String getTotFxd() {
        return totFxd;
    }


    /**
     * Sets the totFxd value for this Bill.
     * 
     * @param totFxd
     */
    public void setTotFxd(java.lang.String totFxd) {
        this.totFxd = totFxd;
    }


    /**
     * Gets the fulChg value for this Bill.
     * 
     * @return fulChg
     */
    public java.lang.String getFulChg() {
        return fulChg;
    }


    /**
     * Sets the fulChg value for this Bill.
     * 
     * @param fulChg
     */
    public void setFulChg(java.lang.String fulChg) {
        this.fulChg = fulChg;
    }


    /**
     * Gets the txtChg value for this Bill.
     * 
     * @return txtChg
     */
    public java.lang.String getTxtChg() {
        return txtChg;
    }


    /**
     * Sets the txtChg value for this Bill.
     * 
     * @param txtChg
     */
    public void setTxtChg(java.lang.String txtChg) {
        this.txtChg = txtChg;
    }


    /**
     * Gets the rbtChg value for this Bill.
     * 
     * @return rbtChg
     */
    public java.lang.String getRbtChg() {
        return rbtChg;
    }


    /**
     * Sets the rbtChg value for this Bill.
     * 
     * @param rbtChg
     */
    public void setRbtChg(java.lang.String rbtChg) {
        this.rbtChg = rbtChg;
    }


    /**
     * Gets the total value for this Bill.
     * 
     * @return total
     */
    public java.lang.String getTotal() {
        return total;
    }


    /**
     * Sets the total value for this Bill.
     * 
     * @param total
     */
    public void setTotal(java.lang.String total) {
        this.total = total;
    }


    /**
     * Gets the rangeCharges value for this Bill.
     * 
     * @return rangeCharges
     */
    public _179._6._235._119.cebwebservice.Blocks[] getRangeCharges() {
        return rangeCharges;
    }


    /**
     * Sets the rangeCharges value for this Bill.
     * 
     * @param rangeCharges
     */
    public void setRangeCharges(_179._6._235._119.cebwebservice.Blocks[] rangeCharges) {
        this.rangeCharges = rangeCharges;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Bill)) return false;
        Bill other = (Bill) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.kwhChg==null && other.getKwhChg()==null) || 
             (this.kwhChg!=null &&
              this.kwhChg.equals(other.getKwhChg()))) &&
            ((this.totFxd==null && other.getTotFxd()==null) || 
             (this.totFxd!=null &&
              this.totFxd.equals(other.getTotFxd()))) &&
            ((this.fulChg==null && other.getFulChg()==null) || 
             (this.fulChg!=null &&
              this.fulChg.equals(other.getFulChg()))) &&
            ((this.txtChg==null && other.getTxtChg()==null) || 
             (this.txtChg!=null &&
              this.txtChg.equals(other.getTxtChg()))) &&
            ((this.rbtChg==null && other.getRbtChg()==null) || 
             (this.rbtChg!=null &&
              this.rbtChg.equals(other.getRbtChg()))) &&
            ((this.total==null && other.getTotal()==null) || 
             (this.total!=null &&
              this.total.equals(other.getTotal()))) &&
            ((this.rangeCharges==null && other.getRangeCharges()==null) || 
             (this.rangeCharges!=null &&
              java.util.Arrays.equals(this.rangeCharges, other.getRangeCharges())));
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
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getKwhChg() != null) {
            _hashCode += getKwhChg().hashCode();
        }
        if (getTotFxd() != null) {
            _hashCode += getTotFxd().hashCode();
        }
        if (getFulChg() != null) {
            _hashCode += getFulChg().hashCode();
        }
        if (getTxtChg() != null) {
            _hashCode += getTxtChg().hashCode();
        }
        if (getRbtChg() != null) {
            _hashCode += getRbtChg().hashCode();
        }
        if (getTotal() != null) {
            _hashCode += getTotal().hashCode();
        }
        if (getRangeCharges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRangeCharges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRangeCharges(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Bill.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Bill"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kwhChg");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "KwhChg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totFxd");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "TotFxd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fulChg");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "FulChg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txtChg");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "TxtChg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rbtChg");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "RbtChg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rangeCharges");
        elemField.setXmlName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "RangeCharges"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Blocks"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("https://119.235.6.179/cebwebservice/", "Blocks"));
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
