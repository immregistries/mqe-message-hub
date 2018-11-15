//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.10 at 03:58:07 PM EDT 
//


package org.immregistries.mqe.hub.report.vaccineReport.generated;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.immregistries.mqe.hub.report.vaccineReport.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AgeMin_QNAME = new QName("", "ageMin");
    private final static QName _Cvx_QNAME = new QName("", "cvx");
    private final static QName _AgeMax_QNAME = new QName("", "ageMax");
    private final static QName _DisplayPriority_QNAME = new QName("", "displayPriority");
    private final static QName _Label_QNAME = new QName("", "label");
    private final static QName _Status_QNAME = new QName("", "status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.immregistries.mqe.hub.report.vaccineReport.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AgeCategoryStatus }
     * 
     */
    public AgeCategoryStatus createAgeCategoryStatus() {
        return new AgeCategoryStatus();
    }

    /**
     * Create an instance of {@link AgeCategories }
     * 
     */
    public AgeCategories createAgeCategories() {
        return new AgeCategories();
    }

    /**
     * Create an instance of {@link AgeCategory }
     * 
     */
    public AgeCategory createAgeCategory() {
        return new AgeCategory();
    }

    /**
     * Create an instance of {@link VaccinesAdministeredExpectation }
     * 
     */
    public VaccinesAdministeredExpectation createVaccinesAdministeredExpectation() {
        return new VaccinesAdministeredExpectation();
    }

    /**
     * Create an instance of {@link VaccineReportGroup }
     * 
     */
    public VaccineReportGroup createVaccineReportGroup() {
        return new VaccineReportGroup();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ageMin")
    public JAXBElement<BigInteger> createAgeMin(BigInteger value) {
        return new JAXBElement<BigInteger>(_AgeMin_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cvx")
    public JAXBElement<BigInteger> createCvx(BigInteger value) {
        return new JAXBElement<BigInteger>(_Cvx_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ageMax")
    public JAXBElement<BigInteger> createAgeMax(BigInteger value) {
        return new JAXBElement<BigInteger>(_AgeMax_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "displayPriority")
    public JAXBElement<BigInteger> createDisplayPriority(BigInteger value) {
        return new JAXBElement<BigInteger>(_DisplayPriority_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "label")
    public JAXBElement<String> createLabel(String value) {
        return new JAXBElement<String>(_Label_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "status")
    public JAXBElement<String> createStatus(String value) {
        return new JAXBElement<String>(_Status_QNAME, String.class, null, value);
    }

}