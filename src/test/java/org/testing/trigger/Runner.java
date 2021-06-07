package org.testing.trigger;

import java.io.IOException;

import org.testing.TestScripts.TC1_MerchantListingAllRecords;
import org.testing.TestScripts.TC3_openMerchantDetail;
import org.testing.TestScripts.TC2_LocationListingAllRecords;
import org.testing.TestScripts.TC4_openMerchantDetailViaChaining;
import org.testing.utilities.JsonParsingUsingOrgJSON;

public class Runner {

	public static void main(String[] args) throws IOException {
		
		// open merchant listing via RHRN
		//TC1_MerchantListingAllRecords mlr= new TC1_MerchantListingAllRecords();
		//mlr.merchantlisting();
		
		// open merchant listing via HOTSPOT
		//TC2_LocationListingAllRecords lmlr= new TC2_LocationListingAllRecords();
		//lmlr.loctionlisting();
		
		// open merchant detail page MDP
		//TC3_openMerchantDetail oml= new TC3_openMerchantDetail();
		//oml.merchantDetail();
		
		// open open merchant detail page MDP via chaning using TC1_MerchantListingAllRecords
		//TC4_openMerchantDetailViaChaining pmlvc= new TC4_openMerchantDetailViaChaining();
		//pmlvc.merchantDetail();

		
	}

}
