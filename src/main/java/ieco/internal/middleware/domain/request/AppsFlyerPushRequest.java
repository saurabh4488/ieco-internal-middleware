package ieco.internal.middleware.domain.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AppsFlyerPushRequest {

    @JsonProperty("idfv")
    public String idfv;
    @JsonProperty("device_category")
    public String device_category;
    @JsonProperty("af_sub1")
    public String af_sub1;
    @JsonProperty("customer_user_id")
    public String customer_user_id;
    @JsonProperty("is_lat")
    public String is_lat;
    @JsonProperty("contributor_2_af_prt")
    public String contributor_2_af_prt;
    @JsonProperty("bundle_id")
    public String bundle_id;
    @JsonProperty("gp_broadcast_referrer")
    public String gp_broadcast_referrer;
    @JsonProperty("contributor_2_touch_time")
    public String contributor_2_touch_time;
    @JsonProperty("contributor_3_touch_type")
    public String contributor_3_touch_type;
    @JsonProperty("event_source")
    public String event_source;
    @JsonProperty("af_cost_value")
    public String af_cost_value;
    @JsonProperty("contributor_1_match_type")
    public String contributor_1_match_type;
    @JsonProperty("app_version")
    public String app_version;
    @JsonProperty("contributor_3_af_prt")
    public String contributor_3_af_prt;
    @JsonProperty("custom_data")
    public String custom_data;
    @JsonProperty("contributor_2_touch_type")
    public String contributor_2_touch_type;
    @JsonProperty("gp_install_begin")
    public String gp_install_begin;
    @JsonProperty("city")
    public String city;
    @JsonProperty("amazon_aid")
    public String amazon_aid;
    @JsonProperty("gp_referrer")
    public String gp_referrer;
    @JsonProperty("af_cost_model")
    public String af_cost_model;
    @JsonProperty("af_c_id")
    public String af_c_id;
    @JsonProperty("attributed_touch_time_selected_timezone")
    public String attributed_touch_time_selected_timezone;
    @JsonProperty("selected_currency")
    public String selected_currency;
    @JsonProperty("app_name")
    public String app_name;
    @JsonProperty("install_time_selected_timezone")
    public String install_time_selected_timezone;
    @JsonProperty("postal_code")
    public String postal_code;
    @JsonProperty("wifi")
    public boolean wifi;
    @JsonProperty("install_time")
    public String install_time;
    @JsonProperty("operator")
    public String operator;
    @JsonProperty("attributed_touch_type")
    public String attributed_touch_type;
    @JsonProperty("af_attribution_lookback")
    public String af_attribution_lookback;
    @JsonProperty("keyword_match_type")
    public String keyword_match_type;
    @JsonProperty("af_adset_id")
    public String af_adset_id;
    @JsonProperty("device_download_time_selected_timezone")
    public String device_download_time_selected_timezone;
    @JsonProperty("contributor_2_media_source")
    public String contributor_2_media_source;
    @JsonProperty("contributor_2_match_type")
    public String contributor_2_match_type;
    @JsonProperty("api_version")
    public String api_version;
    @JsonProperty("attributed_touch_time")
    public String attributed_touch_time;
    @JsonProperty("revenue_in_selected_currency")
    public String revenue_in_selected_currency;
    @JsonProperty("is_retargeting")
    public boolean is_retargeting;
    @JsonProperty("country_code")
    public String country_code;
    @JsonProperty("gp_click_time")
    public String gp_click_time;
    @JsonProperty("contributor_1_af_prt")
    public String contributor_1_af_prt;
    @JsonProperty("match_type")
    public String match_type;
    @JsonProperty("appsflyer_id")
    public String appsflyer_id;
    @JsonProperty("dma")
    public String dma;
    @JsonProperty("http_referrer")
    public String http_referrer;
    @JsonProperty("af_sub5")
    public String af_sub5;
    @JsonProperty("af_prt")
    public String af_prt;
    @JsonProperty("event_revenue_currency")
    public String event_revenue_currency;
    @JsonProperty("store_reinstall")
    public String store_reinstall;
    @JsonProperty("install_app_store")
    public String install_app_store;
    @JsonProperty("media_source")
    public String media_source;
    @JsonProperty("deeplink_url")
    public String deeplink_url;
    @JsonProperty("campaign")
    public String campaign;
    @JsonProperty("af_keywords")
    public String af_keywords;
    @JsonProperty("region")
    public String region;
    @JsonProperty("cost_in_selected_currency")
    public String cost_in_selected_currency;
    @JsonProperty("event_value")
    public String event_value;
    @JsonProperty("ip")
    public String ip;
    @JsonProperty("oaid")
    public String oaid;
    @JsonProperty("event_time")
    public String event_time;
    @JsonProperty("is_receipt_validated")
    public String is_receipt_validated;
    @JsonProperty("contributor_1_campaign")
    public String contributor_1_campaign;
    @JsonProperty("af_sub4")
    public String af_sub4;
    @JsonProperty("imei")
    public String imei;
    @JsonProperty("contributor_3_campaign")
    public String contributor_3_campaign;
    @JsonProperty("event_revenue_usd")
    public String event_revenue_usd;
    @JsonProperty("af_sub2")
    public String af_sub2;
    @JsonProperty("original_url")
    public String original_url;
    @JsonProperty("contributor_2_campaign")
    public String contributor_2_campaign;
    @JsonProperty("android_id")
    public String android_id;
    @JsonProperty("contributor_3_media_source")
    public String contributor_3_media_source;
    @JsonProperty("af_adset")
    public String af_adset;
    @JsonProperty("af_ad")
    public String af_ad;
    @JsonProperty("state")
    public String state;
    @JsonProperty("network_account_id")
    public String network_account_id;
    @JsonProperty("device_type")
    public String device_type;
    @JsonProperty("idfa")
    public String idfa;
    @JsonProperty("retargeting_conversion_type")
    public String retargeting_conversion_type;
    @JsonProperty("af_channel")
    public String af_channel;
    @JsonProperty("af_cost_currency")
    public String af_cost_currency;
    @JsonProperty("contributor_1_media_source")
    public String contributor_1_media_source;
    @JsonProperty("keyword_id")
    public String keyword_id;
    @JsonProperty("device_download_time")
    public String device_download_time;
    @JsonProperty("contributor_1_touch_type")
    public String contributor_1_touch_type;
    @JsonProperty("af_reengagement_window")
    public String af_reengagement_window;
    @JsonProperty("af_siteid")
    public String af_siteid;
    @JsonProperty("language")
    public String language;
    @JsonProperty("app_id")
    public String app_id;
    @JsonProperty("contributor_1_touch_time")
    public String contributor_1_touch_time;
    @JsonProperty("event_revenue")
    public String event_revenue;
    @JsonProperty("af_ad_type")
    public String af_ad_type;
    @JsonProperty("carrier")
    public String carrier;
    @JsonProperty("event_name")
    public String event_name;
    @JsonProperty("af_sub_siteid")
    public String af_sub_siteid;
    @JsonProperty("advertising_id")
    public String advertising_id;
    @JsonProperty("os_version")
    public String os_version;
    @JsonProperty("platform")
    public String platform;
    @JsonProperty("af_sub3")
    public String af_sub3;
    @JsonProperty("contributor_3_match_type")
    public String contributor_3_match_type;
    @JsonProperty("selected_timezone")
    public String selected_timezone;
    @JsonProperty("af_ad_id")
    public String af_ad_id;
    @JsonProperty("contributor_3_touch_time")
    public String contributor_3_touch_time;
    @JsonProperty("user_agent")
    public String user_agent;
    @JsonProperty("is_primary_attribution")
    public boolean is_primary_attribution;
    @JsonProperty("sdk_version")
    public String sdk_version;
    @JsonProperty("event_time_selected_timezone")
    public String event_time_selected_timezone;
}
