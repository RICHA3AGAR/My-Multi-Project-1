package com.myexamples;

import java.util.Date;

public class PatientInfoType {
//	private org.apache.axis.types.NormalizedString patientFirstName;
//
//    private org.apache.axis.types.NormalizedString patientLastName;

    private java.lang.String groupNumber;

//    private org.apache.axis.types.NormalizedString sectionNumber;
//
//    private org.apache.axis.types.NormalizedString relationShipCd;

    private int memberPartyId;

//    private com.hcsc.www.health.ClientAgreement.v4_0.GenderEnum gender;
//
//    private org.apache.axis.types.NormalizedString patientPackageCd;
//
//    private org.apache.axis.types.NormalizedString memberMedicareEligibility;

    private java.util.Date patientDob;

    private java.lang.String boeingPlanCode;

	public java.lang.String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(java.lang.String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getMemberPartyId() {
		return memberPartyId;
	}

	public void setMemberPartyId(int memberPartyId) {
		this.memberPartyId = memberPartyId;
	}

	public java.util.Date getPatientDob() {
		return patientDob;
	}

	public void setPatientDob(java.util.Date patientDob) {
		this.patientDob = patientDob;
	}

	public java.lang.String getBoeingPlanCode() {
		return boeingPlanCode;
	}

	public void setBoeingPlanCode(java.lang.String boeingPlanCode) {
		this.boeingPlanCode = boeingPlanCode;
	}

	public PatientInfoType() {
    }
	
	public PatientInfoType(String groupNumber, int memberPartyId, Date patientDob, String boeingPlanCode) {
		super();
		this.groupNumber = groupNumber;
		this.memberPartyId = memberPartyId;
		this.patientDob = patientDob;
		this.boeingPlanCode = boeingPlanCode;
	}

    
    
}
