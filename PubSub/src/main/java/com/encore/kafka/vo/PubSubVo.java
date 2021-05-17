package com.encore.kafka.vo;

import java.util.Date;


import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PubSubVo {
	
	@JsonProperty(value = "GTHR_ID")
	private String gthr_id;
	@JsonProperty(value = "GTHR_DTTM")
	private Date gthr_dttm;
	@JsonProperty(value = "SRC_MDL_ID")
	private String src_mdl_id;
	@JsonProperty(value = "SRC_BSIF_ID")
	private String src_bsif_id;
	@JsonProperty(value = "SRC_SCMA")
	private String src_scma;
	@JsonProperty(value = "SRC_ENT_NM")
	private String src_ent_nm;
	@JsonProperty(value = "SRC_TBL_NM")
	private String src_tbl_nm;
	@JsonProperty(value = "REF_MDL_ID")
	private String ref_mdl_id;
	@JsonProperty(value = "REF_SCMA")
	private String ref_scma;
	@JsonProperty(value = "REF_ENT_NM")
	private String ref_ent_nm;
	@JsonProperty(value = "REF_TBL_NM")
	private String ref_tbl_nm;
	@JsonProperty(value = "PRSS_YN")
	private String prss_yn;
	@JsonProperty(value = "PRSS_DTTM")
	private Date prss_dttm;
	
	public String toString() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("GTHR_ID", this.getGthr_id());
        jsonObject.put("GTHR_DTTM", this.getGthr_dttm());
        jsonObject.put("SRC_MDL_ID", this.getSrc_mdl_id());
        jsonObject.put("SRC_BSIF_ID", this.getSrc_bsif_id());
        jsonObject.put("SRC_SCMA", this.getSrc_scma());
        jsonObject.put("SRC_ENT_NM", this.getSrc_ent_nm());
        jsonObject.put("SRC_TBL_NM", this.getSrc_tbl_nm());
        jsonObject.put("REF_MDL_ID", this.getRef_mdl_id());
        jsonObject.put("REF_APP_ID", this.getRef_scma());
        jsonObject.put("REF_APP_ID", this.getRef_ent_nm());
        jsonObject.put("REF_APP_NM", this.getRef_tbl_nm());
        jsonObject.put("PRSS_YN", this.getPrss_yn());
        jsonObject.put("PRSS_DTTM", this.getPrss_dttm());
        
        return jsonObject.toString();
       
	}


}
