//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.19 at 12:35:48 PM MDT 
//


package org.immregistries.mqe.hub.report.vaccineReport.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vaccinesAdministeredExpectationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vaccinesAdministeredExpectationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ageCategories" type="{}ageCategoriesType"/>
 *         &lt;element name="vaccineReportGroup" type="{}vaccineReportGroupType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vaccinesAdministeredExpectationType", propOrder = {
    "ageCategories",
    "vaccineReportGroup"
})
public class VaccinesAdministeredExpectationType {

    @XmlElement(required = true)
    protected AgeCategoriesType ageCategories;
    protected List<VaccineReportGroupType> vaccineReportGroup;

    /**
     * Gets the value of the ageCategories property.
     * 
     * @return
     *     possible object is
     *     {@link AgeCategoriesType }
     *     
     */
    public AgeCategoriesType getAgeCategories() {
        return ageCategories;
    }

    /**
     * Sets the value of the ageCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgeCategoriesType }
     *     
     */
    public void setAgeCategories(AgeCategoriesType value) {
        this.ageCategories = value;
    }

    /**
     * Gets the value of the vaccineReportGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vaccineReportGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVaccineReportGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VaccineReportGroupType }
     * 
     * 
     */
    public List<VaccineReportGroupType> getVaccineReportGroup() {
        if (vaccineReportGroup == null) {
            vaccineReportGroup = new ArrayList<VaccineReportGroupType>();
        }
        return this.vaccineReportGroup;
    }

}
