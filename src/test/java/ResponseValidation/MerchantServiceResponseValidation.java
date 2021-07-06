package ResponseValidation;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testing.logs.log4j;

import io.restassured.response.Response;

public class MerchantServiceResponseValidation {
	
	
	public static void ListingDataValidation(Response listingResponse, List<Response> merchantServiceResponse){
		System.out.println("*************Fetching Merchant Service response documents*******************");
		int msresponseSize= merchantServiceResponse.size();
		HashSet<String> msallmids= new  HashSet<String>();
		HashSet<String> msallmnames= new  HashSet<String>();
		HashSet<String> msallpermalink= new  HashSet<String>();
		HashMap<String,String> msprimaryCat= new  HashMap<String,String>();
		HashMap<String,String> mspaytmChannelId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmShopId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmKybId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmWarehouseId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmMerchantId= new  HashMap<String,String>();
		HashMap<String,String> mslatall= new  HashMap<String,String>();
		HashMap<String,String> mslngall= new  HashMap<String,String>();
		HashMap<String,String> mscityTown= new  HashMap<String,String>();

        for(int i=0;i<msresponseSize;i++) {
    		JSONObject object0= new JSONObject(merchantServiceResponse.get(i).asString());
    		JSONObject object1= object0.getJSONObject("rs");
    		object1.get("merchantId");
    		object1.get("name");
    		object1.get("permalink");
    		
    		JSONObject externalMapping= object1.getJSONObject("externalMapping");
    		externalMapping.get("paytmChannelId");
    		externalMapping.get("paytmShopId");
    		externalMapping.get("paytmKybId");
    		externalMapping.get("paytmWarehouseId");
    		externalMapping.get("paytmMerchantId");
    		
    		JSONObject msaddress= object1.getJSONObject("address");
    		msaddress.get("latitude");
    		msaddress.get("longitude");
    		msaddress.get("cityTown");
    		
    		JSONArray catInfoArray= object1.getJSONArray("cats");
    		for(int j=0;j<catInfoArray.length();j++)
    		{
    		JSONObject arrayobject1= catInfoArray.getJSONObject(j);
    		Boolean value=   (Boolean) arrayobject1.get("isPrimary");
    		if (value) {
    			msprimaryCat.put(object1.get("merchantId").toString(), arrayobject1.get("key").toString());
                break;
    		}
    		}

    		msallmids.add(object1.get("merchantId").toString());
    		msallmnames.add(object1.get("name").toString());
    		msallpermalink.add(object1.get("permalink").toString());
    		mspaytmChannelId.put(object1.get("merchantId").toString(), externalMapping.get("paytmChannelId").toString());
    		mspaytmShopId.put(object1.get("merchantId").toString(), externalMapping.get("paytmShopId").toString());
    		mspaytmKybId.put(object1.get("merchantId").toString(), externalMapping.get("paytmKybId").toString());
    		mspaytmWarehouseId.put(object1.get("merchantId").toString(), externalMapping.get("paytmWarehouseId").toString());
    		mspaytmMerchantId.put(object1.get("merchantId").toString(), externalMapping.get("paytmMerchantId").toString());
    		mslatall.put(object1.get("merchantId").toString(), msaddress.get("latitude").toString());
    		mslngall.put(object1.get("merchantId").toString(), msaddress.get("longitude").toString());
    		mscityTown.put(object1.get("merchantId").toString(), msaddress.get("cityTown").toString());	     
        }
        
        
        System.out.println("All mids from Merchant Service");
        for(String mid:msallmids ) {
        	System.out.println(mid);
        }
        System.out.println("All Merchant Names from Merchant Service");

        for(String name: msallmnames ) {
        	System.out.println(name);
        }
        System.out.println("All Merchant permalink from Merchant Service");
        for(String permalink:msallpermalink ) {
        	System.out.println(permalink);
        }
        System.out.println("Merchant's Primary catory from Merchant Service");

        for (String mcat : msprimaryCat.keySet()) {
		         System.out.println("Mid is:  " + mcat + ", Primary Catogry is: " + msprimaryCat.get(mcat));
        }
        System.out.println("Channel Ids from Merchant Service");

        for (String mChannelId : mspaytmChannelId.keySet()) {
	         System.out.println("Mid is: " + mChannelId + ", Channel Id: " + mspaytmChannelId.get(mChannelId));
        }
        System.out.println("Paytm Shop Ids from Merchant Service");

        for (String mshopids : mspaytmShopId.keySet()) {
	         System.out.println("Mid is: " + mshopids + ", Paytm Shop Id: " + mspaytmShopId.get(mshopids));
        }
        System.out.println("Paytm KYB Ids from Merchant Service");

        for (String mkybids : mspaytmKybId.keySet()) {
	         System.out.println("Mid is: " + mkybids + ", Paytm Kybid: " + mspaytmKybId.get(mkybids));
        }
        System.out.println("Paytm Warehouse Ids from Merchant Service");
    
        for (String mwarehouse : mspaytmWarehouseId.keySet()) {
	         System.out.println("Mid is: " + mwarehouse + ", Warehouse id:  " + mspaytmWarehouseId.get(mwarehouse));
        }
        System.out.println("Paytm Merchant Ids from Merchant Service");

        for (String paytmMerchantId : mspaytmMerchantId.keySet()) {
	         System.out.println("Mid is: " + paytmMerchantId + ", Paytm Merchant Id: " + mspaytmMerchantId.get(paytmMerchantId));
        }
        System.out.println("Paytm Merchant Latitude from Merchant Service");

        for (String lat : mslatall.keySet()) {
	         System.out.println("Mid is: " + lat + ", Lat value: " + mslatall.get(lat));
        }
        System.out.println("Paytm Merchant Longitude from Merchant Service");

        for (String lng : mslngall.keySet()) {
	         System.out.println("Mid is: " + lng + ", Lgn value= " + mslngall.get(lng));
        }
        System.out.println(" Merchant City from Merchant Service");

        for (String city : mscityTown.keySet()) {
	         System.out.println("Mid is:" + city + ", Merchant City is: " + mscityTown.get(city));
        }
        
		System.out.println("*************Fetching Merchant Listing response documents*******************");

		JSONObject listingobject1= new JSONObject(listingResponse.asString());
		JSONObject mappedMerchantsobject= listingobject1.getJSONObject("mappedMerchants");
		JSONArray listingmerchantarray= mappedMerchantsobject.getJSONArray("merchants");
		int arraySize= listingmerchantarray.length();
		
		HashSet<String> mlmids= new  HashSet<String>();
		HashSet<String> mlmnames= new  HashSet<String>();
		HashSet<String> mlpermalink= new  HashSet<String>();
		HashMap<String,String> mlpaytmChannelId= new  HashMap<String,String>();
		HashMap<String,String> mlpaytmShopId= new  HashMap<String,String>();
		HashMap<String,String> mlpaytmKybId= new  HashMap<String,String>();
		HashMap<String,String> mlpaytmWarehouseId= new  HashMap<String,String>();
		HashMap<String,String> mlpaytmMerchantId= new  HashMap<String,String>();
		HashMap<String,String> mlllat= new  HashMap<String,String>();
		HashMap<String,String> mlllng= new  HashMap<String,String>();
		HashMap<String,String> mlprimaryCat= new  HashMap<String,String>();
		HashMap<String,String> mlcityTown= new  HashMap<String,String>();

				for(int i=0;i<arraySize;i++ ) {
					JSONObject jsonobject1 = listingmerchantarray.getJSONObject(i) ;
					jsonobject1.get("merchantId");
					jsonobject1.get("merchantName");
					jsonobject1.get("merchantPermalink");
					jsonobject1.get("gaPrimaryCat");
					jsonobject1.get("merchantCity");
					JSONArray locationsArray= jsonobject1.getJSONArray("locations");
				    for(int k=0; k<locationsArray.length();k++) {
						JSONObject loc= locationsArray.getJSONObject(k);
						loc.get("lat");
						loc.get("lng");
						mlllat.put(jsonobject1.get("merchantId").toString(),loc.get("lat").toString());
						mlllng.put(jsonobject1.get("merchantId").toString(),loc.get("lng").toString());
					
					}
					
					JSONObject externalPlatformDetails= jsonobject1.getJSONObject("externalPlatformDetails");
					externalPlatformDetails.get("paytmChannelId");
					externalPlatformDetails.get("paytmShopId");
					externalPlatformDetails.get("paytmKybId");
					externalPlatformDetails.get("paytmWarehouseId");
					externalPlatformDetails.get("paytmMerchantId");

					mlmids.add(jsonobject1.get("merchantId").toString());
					mlmnames.add(jsonobject1.get("merchantName").toString());
					mlpermalink.add(jsonobject1.get("merchantPermalink").toString());
					
					mlpaytmChannelId.put(jsonobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmChannelId").toString());
					mlpaytmShopId.put(jsonobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmShopId").toString());
					mlpaytmKybId.put(jsonobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmKybId").toString());
					mlpaytmWarehouseId.put(jsonobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmWarehouseId").toString());
					mlpaytmMerchantId.put(jsonobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmMerchantId").toString());
					mlprimaryCat.put(jsonobject1.get("merchantId").toString(), jsonobject1.get("gaPrimaryCat").toString());
					mlcityTown.put(jsonobject1.get("merchantId").toString(), jsonobject1.get("merchantCity").toString());		
	}
				
				boolean midstatus= msallmids.equals(mlmids);
				System.out.println("Mid Status: " + midstatus);  
				boolean mnamestatus= msallmnames.equals(mlmnames);
				System.out.println("Merchant Name Status: " + mnamestatus);
				boolean mlatstatus= mslatall.equals(mlllat);
				System.out.println("Latitude Status: " + mlatstatus);
				boolean mlngstatus= mslngall.equals(mlllng);
				System.out.println("Longitude Status: " + mlngstatus);	
				boolean permalinkstatus= msallpermalink.equals(mlpermalink);
				System.out.println("Permalink Status: " + permalinkstatus);
				boolean channelstatus= mspaytmChannelId.equals(mlpaytmChannelId);
				System.out.println("ChannelId Status: " + channelstatus);
				boolean shopidstatus= mspaytmShopId.equals(mlpaytmShopId);
				System.out.println("Shopid Status: " + shopidstatus);
				boolean kybidstatus= mspaytmKybId.equals(mlpaytmKybId);
				System.out.println("Kybid Status: " + kybidstatus);
				boolean warehousestatus= mspaytmWarehouseId.equals(mlpaytmWarehouseId);
				System.out.println("Warehouseid Status: " + warehousestatus);
				boolean paytmmidstatus= mspaytmMerchantId.equals(mlpaytmMerchantId);
				System.out.println("Paytm mid Status: " + paytmmidstatus);
				boolean catstatus= msprimaryCat.equals(mlprimaryCat);
				System.out.println("Category Status: " + catstatus);
				boolean mcitystatus= mscityTown.equals(mlcityTown);
				System.out.println("Merchant City Status: " + mcitystatus);
				
				assertTrue(msallmids.equals(mlmids));
			    assertTrue(msallmnames.equals(mlmnames));
			    assertTrue(mslatall.equals(mlllat));
			    assertTrue(mslngall.equals(mlllng));
			    assertTrue(msallpermalink.equals(mlpermalink));
			    assertTrue(mspaytmChannelId.equals(mlpaytmChannelId));
			    assertTrue(mspaytmShopId.equals(mlpaytmShopId));
			    assertTrue(mspaytmKybId.equals(mlpaytmKybId));
			    assertTrue(mspaytmWarehouseId.equals(mlpaytmWarehouseId));
			    assertTrue(mspaytmMerchantId.equals(mlpaytmMerchantId));
			    assertTrue(msprimaryCat.equals(mlprimaryCat));
			    assertTrue(mscityTown.equals(mlcityTown));
				
				}
	
	
	public static void MerchantDetailDataValidation(Response listingResponse, Response merchantServiceResponse){
		System.out.println("*************Merchant Service response documents*******************");
		HashSet<String> msallmids= new  HashSet<String>();
		HashSet<String> msallmnames= new  HashSet<String>();
		HashSet<String> msallpermalink= new  HashSet<String>();

		
		HashMap<String,String> msprimaryCat= new  HashMap<String,String>();
		HashMap<String,String> mspaytmChannelId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmShopId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmKybId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmWarehouseId= new  HashMap<String,String>();
		HashMap<String,String> mspaytmMerchantId= new  HashMap<String,String>();
		HashMap<String,String> mslatall= new  HashMap<String,String>();
		HashMap<String,String> mslngall= new  HashMap<String,String>();
		HashMap<String,String> mscityTown= new  HashMap<String,String>();


    		JSONObject object0= new JSONObject(merchantServiceResponse.asString());
    		JSONObject object1= object0.getJSONObject("rs");
    		object1.get("merchantId");
    		object1.get("name");
    		object1.get("permalink");
    		
    		JSONObject externalMapping= object1.getJSONObject("externalMapping");
    		externalMapping.get("paytmChannelId");
    		externalMapping.get("paytmShopId");
    		externalMapping.get("paytmKybId");
    		externalMapping.get("paytmWarehouseId");
    		externalMapping.get("paytmMerchantId");
    		
    		JSONObject msaddress= object1.getJSONObject("address");
    		msaddress.get("latitude");
    		msaddress.get("longitude");
    		msaddress.get("cityTown");
    			
    		
    		JSONArray catInfoArray= object1.getJSONArray("cats");
    		for(int j=0;j<catInfoArray.length();j++)
    		{
    		JSONObject arrayobject1= catInfoArray.getJSONObject(j);
    		Boolean value=   (Boolean) arrayobject1.get("isPrimary");
    		if (value) {
    			msprimaryCat.put(object1.get("merchantId").toString(), arrayobject1.get("key").toString());
                break;
    		}
    		
    		}
    
    		msallmids.add(object1.get("merchantId").toString());
    		msallmnames.add(object1.get("name").toString());
    		msallpermalink.add(object1.get("permalink").toString());
    		mspaytmChannelId.put(object1.get("merchantId").toString(), externalMapping.get("paytmChannelId").toString());
    		mspaytmShopId.put(object1.get("merchantId").toString(), externalMapping.get("paytmShopId").toString());
    		mspaytmKybId.put(object1.get("merchantId").toString(), externalMapping.get("paytmKybId").toString());
    		mspaytmWarehouseId.put(object1.get("merchantId").toString(), externalMapping.get("paytmWarehouseId").toString());
    		mspaytmMerchantId.put(object1.get("merchantId").toString(), externalMapping.get("paytmMerchantId").toString());
    		mslatall.put(object1.get("merchantId").toString(), msaddress.get("latitude").toString());
    		mslngall.put(object1.get("merchantId").toString(), msaddress.get("longitude").toString());
    		mscityTown.put(object1.get("merchantId").toString(), msaddress.get("cityTown").toString());

        	     
        
        System.out.println("All mids from Merchant Service");
        for(String mid:msallmids ) {
        	System.out.println(mid);
        }
        System.out.println("All Merchant Names from Merchant Service");

        for(String name: msallmnames ) {
        	System.out.println(name);
        }
        System.out.println("All Merchant permalink from Merchant Service");
        for(String permalink:msallpermalink ) {
        	System.out.println(permalink);
        }
        System.out.println("Merchant's Primary catory from Merchant Service");

        for (String mcat : msprimaryCat.keySet()) {
		         System.out.println("Mid is:  " + mcat + ", Primary Catogry is: " + msprimaryCat.get(mcat));
        }
        System.out.println("Channel Ids from Merchant Service");

        for (String mChannelId : mspaytmChannelId.keySet()) {
	         System.out.println("Mid is: " + mChannelId + ", Channel Id: " + mspaytmChannelId.get(mChannelId));
        }
        System.out.println("Paytm Shop Ids from Merchant Service");

        for (String mshopids : mspaytmShopId.keySet()) {
	         System.out.println("Mid is: " + mshopids + ", Paytm Shop Id: " + mspaytmShopId.get(mshopids));
        }
        System.out.println("Paytm KYB Ids from Merchant Service");

        for (String mkybids : mspaytmKybId.keySet()) {
	         System.out.println("Mid is: " + mkybids + ", Paytm Kybid: " + mspaytmKybId.get(mkybids));
        }
        System.out.println("Paytm Warehouse Ids from Merchant Service");
    
        for (String mwarehouse : mspaytmWarehouseId.keySet()) {
	         System.out.println("Mid is: " + mwarehouse + ", Warehouse id:  " + mspaytmWarehouseId.get(mwarehouse));
        }
        System.out.println("Paytm Merchant Ids from Merchant Service");

        for (String paytmMerchantId : mspaytmMerchantId.keySet()) {
	         System.out.println("Mid is: " + paytmMerchantId + ", Paytm Merchant Id: " + mspaytmMerchantId.get(paytmMerchantId));
        }
        System.out.println("Paytm Merchant Latitude from Merchant Service");

        for (String lat : mslatall.keySet()) {
	         System.out.println("Mid is: " + lat + ", Lat value: " + mslatall.get(lat));
        }
        System.out.println("Paytm Merchant Longitude from Merchant Service");

        for (String lng : mslngall.keySet()) {
	         System.out.println("Mid is: " + lng + ", Lgn value= " + mslngall.get(lng));
        }
        System.out.println(" Merchant City from Merchant Service");

        for (String city : mscityTown.keySet()) {
	         System.out.println("Mid is:" + city + ", Merchant City is: " + mscityTown.get(city));
        }
	         
	 		System.out.println("*************Merchant Deatil response documents*******************");

			JSONObject mdobject1= new JSONObject(listingResponse.asString());
			HashSet<String> mdmids= new  HashSet<String>();
			HashSet<String> mdmnames= new  HashSet<String>();
			HashSet<String> mdpermalink= new  HashSet<String>();
			HashMap<String,String> mdpaytmChannelId= new  HashMap<String,String>();
			HashMap<String,String> mdpaytmShopId= new  HashMap<String,String>();
			HashMap<String,String> mdpaytmKybId= new  HashMap<String,String>();
			HashMap<String,String> mdpaytmWarehouseId= new  HashMap<String,String>();
			HashMap<String,String> mdpaytmMerchantId= new  HashMap<String,String>();
			HashMap<String,String> mdlat= new  HashMap<String,String>();
			HashMap<String,String> mdlng= new  HashMap<String,String>();
			HashMap<String,String> mdprimaryCat= new  HashMap<String,String>();
			HashMap<String,String> mdcityTown= new  HashMap<String,String>();
			
			mdobject1.get("merchantId");
			mdobject1.get("name");
			mdobject1.get("permalink");
			
			JSONObject externalPlatformDetails= mdobject1.getJSONObject("externalPlatformDetails");
			externalPlatformDetails.get("paytmChannelId");
			externalPlatformDetails.get("paytmShopId");
			externalPlatformDetails.get("paytmKybId");
			externalPlatformDetails.get("paytmWarehouseId");
			externalPlatformDetails.get("paytmMerchantId");
			
			JSONObject redemptionAddress= mdobject1.getJSONObject("redemptionAddress");
			JSONObject coordinates= redemptionAddress.getJSONObject("coordinates");

			coordinates.get("latitude");
			coordinates.get("longitude");
			JSONObject mcity= redemptionAddress.getJSONObject("city");
			mcity.get("name");
			
			JSONObject catInfo= mdobject1.getJSONObject("catInfo");
			catInfo.get("primaryId");
			
			mdmids.add(mdobject1.get("merchantId").toString());
			mdmnames.add(mdobject1.get("name").toString());
			mdpermalink.add(mdobject1.get("permalink").toString());
			
			mdpaytmChannelId.put(mdobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmChannelId").toString());
			mdpaytmShopId.put(mdobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmShopId").toString());
			mdpaytmKybId.put(mdobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmKybId").toString());
			mdpaytmWarehouseId.put(mdobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmWarehouseId").toString());
			mdpaytmMerchantId.put(mdobject1.get("merchantId").toString(), externalPlatformDetails.get("paytmMerchantId").toString());

			mdlat.put(mdobject1.get("merchantId").toString(), coordinates.get("latitude").toString());
			mdlng.put(mdobject1.get("merchantId").toString(), coordinates.get("longitude").toString());
			
			mdprimaryCat.put(mdobject1.get("merchantId").toString(), catInfo.get("primaryId").toString());
			mdcityTown.put(mdobject1.get("merchantId").toString(), mcity.get("name").toString());
	        
	        boolean midstatus= msallmids.equals(mdmids);
			System.out.println("Mid Status: " + midstatus);  
			boolean mnamestatus= msallmnames.equals(mdmnames);
			System.out.println("Merchant Name Status: " + mnamestatus);
			boolean mlatstatus= mslatall.equals(mdlat);
			System.out.println("Latitude Status: " + mlatstatus);
			boolean mlngstatus= mslngall.equals(mdlng);
			System.out.println("Longitude Status: " + mlngstatus);	
			boolean permalinkstatus= msallpermalink.equals(mdpermalink);
			System.out.println("Permalink Status: " + permalinkstatus);
			boolean channelstatus= mspaytmChannelId.equals(mdpaytmChannelId);
			System.out.println("ChannelId Status: " + channelstatus);
			boolean shopidstatus= mspaytmShopId.equals(mdpaytmShopId);
			System.out.println("Shopid Status: " + shopidstatus);
			boolean kybidstatus= mspaytmKybId.equals(mdpaytmKybId);
			System.out.println("Kybid Status: " + kybidstatus);
			boolean warehousestatus= mspaytmWarehouseId.equals(mdpaytmWarehouseId);
			System.out.println("Warehouseid Status: " + warehousestatus);
			boolean paytmmidstatus= mspaytmMerchantId.equals(mdpaytmMerchantId);
			System.out.println("Paytm mid Status: " + paytmmidstatus);
			boolean catstatus= msprimaryCat.equals(mdprimaryCat);
			System.out.println("Category Status: " + catstatus);
			boolean mcitystatus= mscityTown.equals(mdcityTown);
			System.out.println("Merchant City Status: " + mcitystatus);
			
			assertTrue(msallmids.equals(mdmids));
		    assertTrue(msallmnames.equals(mdmnames));
		    assertTrue(mslatall.equals(mdlat));
		    assertTrue(mslngall.equals(mdlng));
		    assertTrue(msallpermalink.equals(mdpermalink));
		    assertTrue(mspaytmChannelId.equals(mdpaytmChannelId));
		    assertTrue(mspaytmShopId.equals(mdpaytmShopId));
		    assertTrue(mspaytmKybId.equals(mdpaytmKybId));
		    assertTrue(mspaytmWarehouseId.equals(mdpaytmWarehouseId));
		    assertTrue(mspaytmMerchantId.equals(mdpaytmMerchantId));
		    assertTrue(msprimaryCat.equals(mdprimaryCat));
		    assertTrue(mscityTown.equals(mdcityTown));
			

		
	}

	
	

	


}
